package com.spf.mapper.user;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.spf.model.user.TbUser;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author ShuPF
 * @since 2018-04-13
 */
//@Mapper
public interface TbUserMapper extends BaseMapper<TbUser> {

    Page<TbUser> getUserByPage();

    //@Select("select * from tb_user where name =#{name}")
    TbUser getByName(@Param("name") String name);

}