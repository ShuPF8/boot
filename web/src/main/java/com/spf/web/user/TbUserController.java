package com.spf.web.user;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.spf.model.user.ResultMessage;
import com.spf.model.user.TbUser;
import com.spf.service.user.ITbUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ShuPF
 * @since 2018-04-13
 */
@RestController
@RequestMapping("/tbUser")
public class TbUserController {

    @Resource
    private ITbUserService userService;

    @RequestMapping("info")
    public String info(){
        TbUser tbUser = userService.getUserById(1l);
        ResultMessage message = new ResultMessage();
        message.setSuccess();
        message.setData(tbUser);
        return JSONObject.toJSONString(message);
    }

    @RequestMapping("byname")
    public String byname(){
        List<TbUser> tbUser = userService.getUserByName("sx");
        ResultMessage message = new ResultMessage();
        message.setSuccess();
        message.setData(tbUser);
        return JSONObject.toJSONString(message);
    }

    @RequestMapping("getAll")
    public String getAll(){
        List<TbUser> tbUser = userService.getUserAll();
        ResultMessage message = new ResultMessage();
        message.setSuccess();
        message.setData(tbUser);
        return JSONObject.toJSONString(message);
    }

    @RequestMapping("page")
    public String getPage(){
        List<TbUser> tbUser =  userService.getUserByPage("2","2");
        PageInfo<TbUser> pageInfo = new PageInfo<>(tbUser);
        ResultMessage message = new ResultMessage();
        message.setSuccess();
        message.setData(pageInfo);
        return JSONObject.toJSONString(message);
    }

}
