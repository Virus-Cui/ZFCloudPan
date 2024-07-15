package cn.mrcsh.zfcloudpanbackend.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.mrcsh.zfcloudpanbackend.annotation.AccessLog;
import cn.mrcsh.zfcloudpanbackend.entity.po.Role;
import cn.mrcsh.zfcloudpanbackend.entity.structure.PageStructure;
import cn.mrcsh.zfcloudpanbackend.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@CrossOrigin
@Slf4j
public class RoleController extends ABaseController{

    @Autowired
    private RoleService roleService;

    @GetMapping
    @AccessLog
    @SaCheckLogin
//    @SaCheckPermission("sys:role:select")
    public response allRoles(Integer page_size, Integer current_page){
        PageStructure<Role> roles = roleService.getRoles(current_page, page_size);
        return success(roles);
    }

    @AccessLog
    @PostMapping
    @SaCheckLogin
    @SaCheckPermission("sys:role:new")
    public response addRole(@RequestBody Role role){
        roleService.addRole(role);
        return success();
    }

    @AccessLog
    @DeleteMapping("/{id}")
    @SaCheckLogin
//    @SaCheckPermission("sys:role:remove")
    public response deleteRole(@PathVariable("id") Integer id){
        roleService.deleteRole(id);
        return success();
    }

    @AccessLog
    @PutMapping
    @SaCheckLogin
//    @SaCheckPermission("sys:role:update")
    public response updateRole(@RequestBody Role role){
        roleService.updateRole(role);
        return success();
    }
}
