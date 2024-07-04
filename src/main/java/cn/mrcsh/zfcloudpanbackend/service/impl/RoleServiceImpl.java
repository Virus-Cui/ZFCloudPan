package cn.mrcsh.zfcloudpanbackend.service.impl;

import cn.mrcsh.zfcloudpanbackend.config.APPConfig;
import cn.mrcsh.zfcloudpanbackend.entity.Role;
import cn.mrcsh.zfcloudpanbackend.entity.User;
import cn.mrcsh.zfcloudpanbackend.mapper.RoleMapper;
import cn.mrcsh.zfcloudpanbackend.service.RoleService;
import cn.mrcsh.zfcloudpanbackend.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private APPConfig appConfig;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserService userService;

    @Override
    public void addRole(Role role) {
        roleMapper.insert(role);
    }

    @Override
    @Transactional
    public void deleteRole(Role role) {
        List<User> users = userService.selectByRoleId(role.getId());
        if(!users.isEmpty()){
            for (User user : users) {
                user.setRole(appConfig.getDefaultRoleId());
                userService.updateUser(user);
            }
        }
        roleMapper.deleteById(role);
    }

    @Override
    public void updateRole(Role role) {
        //TODO 向受影响用户发送权限变更消息
        roleMapper.updateById(role);
    }

    @Override
    public List<Role> getRoles(String roleName) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_name", roleName);
        return roleMapper.selectList(queryWrapper);
    }
}
