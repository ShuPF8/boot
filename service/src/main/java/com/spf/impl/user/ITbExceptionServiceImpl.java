package com.spf.impl.user;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.spf.mapper.user.TbExceptionMapper;
import com.spf.model.user.TbException;
import com.spf.service.user.ITbExceptionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ShuPF
 * @since 2018-04-17
 */
@Service
public class ITbExceptionServiceImpl extends ServiceImpl<TbExceptionMapper, TbException> implements ITbExceptionService {

}
