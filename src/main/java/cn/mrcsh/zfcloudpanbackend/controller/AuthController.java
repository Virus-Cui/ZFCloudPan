package cn.mrcsh.zfcloudpanbackend.controller;

import cn.hutool.crypto.SecureUtil;
import cn.mrcsh.zfcloudpanbackend.annotation.AccessLog;
import cn.mrcsh.zfcloudpanbackend.entity.dto.UserLoginDto;
import cn.mrcsh.zfcloudpanbackend.entity.dto.UserRegisterDto;
import cn.mrcsh.zfcloudpanbackend.entity.po.Menu;
import cn.mrcsh.zfcloudpanbackend.entity.po.User;
import cn.mrcsh.zfcloudpanbackend.service.MenuService;
import cn.mrcsh.zfcloudpanbackend.service.UserService;
import cn.mrcsh.zfcloudpanbackend.utils.TreeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/auth")
@Slf4j
@CrossOrigin
public class AuthController extends ABaseController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;



    @PostMapping("/login")
    @AccessLog()
    public response login(UserLoginDto userLoginDto){
        String passwd = SecureUtil.md5().digestHex(userLoginDto.getPassword());
        String passwdSource = SecureUtil.sha256(passwd);
        log.info("SHA_密码, {}", passwdSource);
        return success();
    }

    @PostMapping("/register")
    @AccessLog()
    public response register(@RequestBody UserRegisterDto userRegisterDto){
        User user = new User();
        user.setUserName(userRegisterDto.getUsername());
        user.setPassword(userRegisterDto.getPassword());
        user.setEmail(userRegisterDto.getEmail());
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setSettings("{}");
        userService.addUser(user);
        return success();
    }
}
