package cn.mrcsh.zfcloudpanbackend.controller;

import cn.mrcsh.zfcloudpanbackend.annotation.AccessLog;
import cn.mrcsh.zfcloudpanbackend.entity.po.User;
import cn.mrcsh.zfcloudpanbackend.entity.structure.PageStructure;
import cn.mrcsh.zfcloudpanbackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usr")
@CrossOrigin
@Slf4j
public class UserController extends ABaseController{

    @Autowired
    private UserService userService;

    @GetMapping("/load_users")
    @AccessLog()
    public response getAllUsers(Integer page_size, Integer current_page, String username){
        if(username == null){
            username = "";
        }
        PageStructure<User> userPageStructure = userService.selectByPage(current_page, page_size, username);
        return success(userPageStructure);
    }

    @DeleteMapping("/del_user/{user_id}")
    public response deleteUser(@PathVariable String user_id){
        if(user_id == null){
            return error("用户ID不能为空");
        }
        userService.deleteUser(user_id);
        return success();
    }

    @PostMapping("/new_user")
    public response addUser(@RequestBody User user){
        userService.addUser(user);
        return success();
    }
}
