package com.spf.mapper;

import com.spf.model.user.SysUserLastOnline;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author ShuPF
 * @类说明：
 * @date 2018-08-15 12:08
 */
public interface IpToRegionMapper {

    String SQLFIELD = "id,user_id as userId, username, last_login_timestamp as lastLoginTimestamp,login_count as loginCount,host,user_agent as userAgent,region_id as regionId,region, city,city_id as cityId";

    @Select("select "+ SQLFIELD +" from sys_user_last_online where region_id is null and region is null;")
    List<SysUserLastOnline> findAll();

    @Update("update sys_user_last_online set region_id=#{regionId},region=#{regionName},city=#{city},city_id =#{cityId} where id =#{id}")
    int updateRegion(@Param("regionId") String regionId, @Param("regionName") String regionName, @Param("city")String city, @Param("cityId")String cityId, @Param("id") Long id);

}
