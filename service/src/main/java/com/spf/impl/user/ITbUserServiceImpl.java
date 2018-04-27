package com.spf.impl.user;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.spf.mapper.user.TbUserMapper;
import com.spf.model.user.TbUser;
import com.spf.service.user.ITbUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ShuPF
 * @since 2018-04-13
 */
@Slf4j
@Service
public class ITbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements ITbUserService {

    @Resource
    private TbUserMapper userMapper;

    @Override
    public TbUser getUserById(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public List<TbUser> getUserByName(String name) {
        return baseMapper.selectList(new EntityWrapper<TbUser>().eq("name",name));
    }

    @Override
    public List<TbUser> getUserAll() {
        return baseMapper.selectList(new EntityWrapper<>());
    }

    @Override
    public List<TbUser> getUserByPage(String pageNo, String pageSize) {
        PageHelper.startPage(Integer.parseInt(pageNo),Integer.parseInt(pageSize));
        return userMapper.getUserByPage();
    }
}
