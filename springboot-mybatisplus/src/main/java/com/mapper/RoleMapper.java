package com.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pojo.Permission;
import com.pojo.Role;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

public interface RoleMapper extends BaseMapper<Role> {
    @Select("select * from permission where permissioncode in (select permissioncode from rolepermission where rolecode = #{roleCode})")
    Set<Permission> getRolePermissions(String roleCode);
}
