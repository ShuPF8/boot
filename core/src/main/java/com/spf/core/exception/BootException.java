package com.spf.core.exception;

import com.alibaba.fastjson.JSONObject;
import com.spf.model.user.ResultMessage;
import com.spf.model.user.TbException;
import com.spf.service.user.ITbExceptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ShuPF
 * @类说明： 异常处理类
 * @date 2018-04-13 15:01
 */
@Slf4j
@Component("bootException")
public class BootException implements HandlerExceptionResolver {

    @Resource
    private ITbExceptionService exceptionService;

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
       log.error("exception" + e.toString());
        ResultMessage message = new ResultMessage();
       message.setFail();
       message.setException(e.toString());

        TbException exception = new TbException();
        exception.setException(e.toString());
        exception.setMsg(e.getMessage());

       try {
           httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
           httpServletResponse.getWriter().println(JSONObject.toJSONString(message));
           exceptionService.insert(exception);
       } catch (IOException e1) {
           e1.printStackTrace();
       }

        return null;
    }
}
