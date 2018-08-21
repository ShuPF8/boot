package com.spf.service.user;

import com.spf.model.user.SysUserLastOnline;

import java.util.List;

/**
 * @author ShuPF
 * @类说明：
 * @date 2018-08-15 12:44
 */
public interface IpToRegionService {

    List<SysUserLastOnline> findAll();

    int updateRegion(String regionId,String regionName,String city, String cityId, Long id);

}
