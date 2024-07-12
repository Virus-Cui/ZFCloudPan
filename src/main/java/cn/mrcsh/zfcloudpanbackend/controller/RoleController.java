package cn.mrcsh.zfcloudpanbackend.controller;

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
    public response allRoles(Integer page_size, Integer current_page){
        PageStructure<Role> roles = roleService.getRoles(current_page, page_size);
        return success(roles);
    }

    @PostMapping
    public response addRole(@RequestBody Role role){
        roleService.addRole(role);
        return success();
    }

    @DeleteMapping
    public response deleteRole(Integer id){
        roleService.deleteRole(id);
        return success();
    }

    @PutMapping
    public response updateRole(@RequestBody Role role){
        roleService.updateRole(role);
        return success();
    }
}
