package com.spf.core.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.spf.model.user.ResultMessage;
import com.spf.utils.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuPF
 * @类说明：
 * @date 2018-04-13 11:00
 */
@Slf4j
@Component("headInterceptor")
public class HeadInterceptor implements HandlerInterceptor {

    private ThreadLocal<Long> requestTime = new ThreadLocal<Long>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        log.info("request IP --- "+IpUtil.getRealIp(request) +"  URI --- ["+request.getRequestURI() + "]");
        requestTime.set(System.currentTimeMillis());
        Map<String, Object> params = getRequstParamToMap(request);
        if (!params.isEmpty())
            log.info("request params --- " + JSONObject.toJSONString(params));

        Object debug = null;
        Object sign = params.get("sign");
        if (sign  == null) {
            if ((debug = params.get("debug")) == null || !String.valueOf(debug).equals("1")) {
                long statTime = requestTime.get().longValue();
                long endTime = System.currentTimeMillis();
                log.info("Total Time --- ["+ (endTime - statTime) + " ms]");

                ResultMessage message = new ResultMessage();
                message.setFail("访问受限");

                response.setHeader("Content-type", "text/html;charset=UTF-8");
                response.getWriter().println(JSONObject.toJSONString(message));
                return false;
            }
            return true;
        } else {
            log.info("此处写一些签名验证逻辑");
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        long statTime = requestTime.get().longValue();
        long endTime = System.currentTimeMillis();
        log.info("Total Time --- ["+ (endTime - statTime) + " ms]");
    }

    private Map<String, Object> getRequstParamToMap(HttpServletRequest request) {
        Map<String, String[]> param = request.getParameterMap();
        Map<String, Object> map = new HashMap<>();
        for (String key : param.keySet()) {
            map.put(key, param.get(key)[0]);
        }
        return map;
    }

}
