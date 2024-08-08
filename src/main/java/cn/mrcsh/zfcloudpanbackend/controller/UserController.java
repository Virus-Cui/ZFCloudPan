package cn.mrcsh.zfcloudpanbackend.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import cn.mrcsh.zfcloudpanbackend.annotation.AccessLog;
import cn.mrcsh.zfcloudpanbackend.entity.po.User;
import cn.mrcsh.zfcloudpanbackend.entity.structure.PageStructure;
import cn.mrcsh.zfcloudpanbackend.entity.vo.UserStorageVo;
import cn.mrcsh.zfcloudpanbackend.enums.WSType;
import cn.mrcsh.zfcloudpanbackend.manager.WSManager;
import cn.mrcsh.zfcloudpanbackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
@Slf4j
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping
    @AccessLog()
    @SaCheckLogin
    @SaCheckPermission("sys:user:select")
    public response getAllUsers(Integer page_size, Integer current_page, String username){
        if(username == null){
            username = "";
        }
        PageStructure<User> userPageStructure = userService.selectByPage(current_page, page_size, username);
        return success(userPageStructure);
    }

    @DeleteMapping("/{user_id}")
    @AccessLog
    @SaCheckLogin
    @SaCheckPermission("sys:user:remove")
    public response deleteUser(@PathVariable String user_id){
        if(user_id == null){
            return error("用户ID不能为空");
        }
        userService.deleteUser(user_id);
        return success();
    }

    @PostMapping
    @AccessLog
    @SaCheckLogin
    @SaCheckPermission("sys:user:new")
    public response addUser(@RequestBody User user){
        userService.addUser(user);
        return success();
    }

    @PutMapping
    @AccessLog
    @SaCheckLogin
    @SaCheckPermission("sys:user:update")
    public response changeUser(@RequestBody User user){
        userService.updateUser(user);
        return success();
    }

    @GetMapping("/kick")
    public response kickUser(@RequestParam String user_id){
        WSManager.send2Session(user_id, WSType.KICK, "踢出");
        StpUtil.kickout(user_id);
        return success();
    }

    @GetMapping("/storage")
    public response getStorage() {
        UserStorageVo storageVo = userService.getUserStorage();
        return success(storageVo);
    }
}
