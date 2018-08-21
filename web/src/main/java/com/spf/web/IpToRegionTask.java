package com.spf.web;

import com.alibaba.fastjson.JSON;
import com.spf.model.user.SysUserLastOnline;
import com.spf.service.user.IpToRegionService;
import com.spf.utils.HttpUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author ShuPF
 * @类说明： 维护 192.168.1.11 表  sys_user_last_onlineong
 *  用过 IP 获取地区和区域
 * @date 2018-08-15 10:36
 */
@Component
public class IpToRegionTask {

    private Logger logger = LoggerFactory.getLogger(IpToRegionTask.class);

    private String url = "http://ip.taobao.com/service/getIpInfo.php";

    private long maxTime = 0l;
    private long minTime = 0l;

    @Autowired
    private IpToRegionService ipToRegionService;

    private List<SysUserLastOnline> userLastOnlines = null;

    // 初始化 userLastOnlines 查询 sys_user_last_online 所有没有区域数据的数据 {"code":1,"data":""}
    @PostConstruct
    private void init() {
        userLastOnlines = ipToRegionService.findAll();
        Execute execute = new Execute();
        Execute execute2 = new Execute();
        Execute execute3 = new Execute();
        Execute execute4 = new Execute();
        Execute execute5 = new Execute();
        execute.start();
        execute2.start();
        execute3.start();
        execute4.start();
        execute5.start();
    }

    private void findByIpToRegion(String ip, SysUserLastOnline u) {
        HttpGet httpRequst = new HttpGet(url+"?ip=" + ip +"&qq-pf-to=pcqq.c2c");
        httpRequst.addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
        HttpResponse res = null;
        String result = "";
        try {
            long startTime = System.currentTimeMillis();
            CloseableHttpClient client = HttpUtil.getClient();
            res = client.execute(httpRequst);
            HttpEntity entity = res.getEntity();
            result = EntityUtils.toString(entity);
            long endTime = System.currentTimeMillis();
            long c = endTime - startTime;

            if (minTime == 0 && maxTime == 0) {
                minTime = maxTime = c;
            }

            if (maxTime < c) {
                maxTime = c;
            }
            if (minTime > c)
                minTime = c;

            logger.info("请求最大时间 maxTime: {} ms, 最小时间 minTime ：{} ms", maxTime, minTime);

            if (result.contains("html")) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                }
                logger.error("FAIL---> id:{}, error:{}", u.getId(), result);
                return;
            }
        } catch (IOException e) {
            logger.error("获取区域地址失败，IP:" + ip, e);
            return;
        }
 
        Map<String, Object> data = (Map<String, Object>) JSON.parse(result);
        int code = (int) data.get("code");
        if(code == 0){ // 获取成功
            data = (Map<String, Object>) data.get("data");
            String regionName = data.get("region").toString();
            String regionId = data.get("region_id").toString();
            String city = data.get("city").toString();
            String cityId = data.get("city_id").toString();
            ipToRegionService.updateRegion(regionId,regionName,city,cityId, u.getId());
            userLastOnlines.remove(u);
            logger.info("SUCCESS-->id:{}, result:{}", u.getId(), result);
        }
    }

    class Execute extends Thread{
        public void run(){
            while (userLastOnlines.size() > 0) {
                logger.info("执行开始!!!!!!!!!!!!!!!!!!!!!!!");
                for (int i = 0;i < userLastOnlines.size(); i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    SysUserLastOnline u = userLastOnlines.get(i);
                    String ip = u.getHost();
                    findByIpToRegion(ip, u);
                }
            }
            logger.info("执行完毕!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }

}
