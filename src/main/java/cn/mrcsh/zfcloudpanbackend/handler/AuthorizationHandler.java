package cn.mrcsh.zfcloudpanbackend.handler;

import cn.dev33.satoken.stp.StpInterface;
import cn.mrcsh.zfcloudpanbackend.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorizationHandler implements StpInterface {

    @Autowired
    private MenuService menuService;

    @Override
    public List<String> getPermissionList(Object o, String s) {
        List<String> authList = menuService.getAuthList((String) o);
        return authList;
    }

    @Override
    public List<String> getRoleList(Object o, String s) {
        return List.of();
    }
}
