package com.spf.web;

import com.spf.utils.es.ElasticSearchUtil;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @author ShuPF
 * @类说明：
 * @date 2018-04-12 17:17
 */
@RestController
public class IndexController {

    @RequestMapping("index")
    public ModelAndView index(){
        return new ModelAndView("index/index");
    }

    @RequestMapping("esTest")
    public List<Map<String,Object>> esTest(){
        ElasticSearchUtil searchUtil = ElasticSearchUtil.getInstance().init();
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("title","斗破");
        List<Map<String,Object>> list = searchUtil.query("index1","blog",queryBuilder,1,10);

        return list;
    }

    @RequestMapping("test")
    public String test(){
        return "hello word";
    }

    @RequestMapping("teste")
    public String teste() throws Exception {
        if (2>1) {
            throw new Exception("全局异常测试");
        }
        return "hello word";
    }

}
