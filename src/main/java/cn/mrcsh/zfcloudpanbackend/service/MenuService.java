package cn.mrcsh.zfcloudpanbackend.service;

import cn.mrcsh.zfcloudpanbackend.entity.po.Menu;

import java.util.List;

public interface MenuService {
    void addMenu(Menu menu);
    void updateMenu(Menu menu);
    void deleteMenu(Integer id);
    List<Menu> getMenuList(Integer parentId);
    List<Menu> getMenuList();
}
