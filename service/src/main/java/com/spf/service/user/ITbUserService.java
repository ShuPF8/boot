package com.spf.service.user;

import com.baomidou.mybatisplus.service.IService;
import com.spf.model.user.TbUser;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ShuPF
 * @since 2018-04-13
 */
public interface ITbUserService extends IService<TbUser> {

    TbUser getUserById(Long id);

    List<TbUser> getUserByName(String name);

    List<TbUser> getUserAll();

    List<TbUser>  getUserByPage(String pageNo, String pageSize);

}
