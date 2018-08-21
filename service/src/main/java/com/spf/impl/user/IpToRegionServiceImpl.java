package com.spf.impl.user;

import com.spf.mapper.IpToRegionMapper;
import com.spf.model.user.SysUserLastOnline;
import com.spf.service.user.IpToRegionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ShuPF
 * @类说明：
 * @date 2018-08-15 12:45
 */
@Service
public class IpToRegionServiceImpl implements IpToRegionService {

    @Resource
    private IpToRegionMapper ipToRegionMapper;

    @Override
    public List<SysUserLastOnline> findAll() {
        return ipToRegionMapper.findAll();
    }

    @Override
    public int updateRegion(String regionId, String regionName,String city, String cityId,  Long id) {
        return ipToRegionMapper.updateRegion(regionId, regionName, city, cityId, id);
    }
}
