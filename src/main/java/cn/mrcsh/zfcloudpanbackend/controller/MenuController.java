package cn.mrcsh.zfcloudpanbackend.controller;

import cn.mrcsh.zfcloudpanbackend.annotation.AccessLog;
import cn.mrcsh.zfcloudpanbackend.entity.po.Menu;
import cn.mrcsh.zfcloudpanbackend.service.MenuService;
import cn.mrcsh.zfcloudpanbackend.utils.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/menu")
public class MenuController extends ABaseController{

    @Autowired
    private MenuService menuService;

    @GetMapping("/menu")
    @AccessLog()
    public response getMenus(){
        List<Menu> menuList = menuService.getMenuList();
        List<Menu> menus = TreeUtils.streamToTree(menuList, 0);
        return success(menus);
    }

    @PostMapping("/menu")
    public response addMenu(@RequestBody Menu menu){
        menuService.addMenu(menu);
        return success();
    }
}
