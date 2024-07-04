package cn.mrcsh.zfcloudpanbackend.service;

import cn.mrcsh.zfcloudpanbackend.entity.Role;

import java.util.List;

public interface RoleService {
    void addRole(Role role);
    void deleteRole(Role role);
    void updateRole(Role role);
    List<Role> getRoles(String roleName);
}
