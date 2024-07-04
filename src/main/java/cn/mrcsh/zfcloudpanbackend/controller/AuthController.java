package cn.mrcsh.zfcloudpanbackend.controller;

import cn.mrcsh.zfcloudpanbackend.entity.Menu;
import cn.mrcsh.zfcloudpanbackend.service.MenuService;
import cn.mrcsh.zfcloudpanbackend.utils.TreeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth")
@Slf4j
@CrossOrigin
public class AuthController extends BaseController{

    @Autowired
    private MenuService menuService;

    @GetMapping("/menus")
    public response getMenus(){
        List<Menu> menuList = menuService.getMenuList();
        List<Menu> menus = TreeUtils.streamToTree(menuList, 0);
        return success(menus);
    }
}
