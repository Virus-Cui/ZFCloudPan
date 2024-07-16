package cn.mrcsh.zfcloudpanbackend.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.mrcsh.zfcloudpanbackend.entity.po.AccessLog;
import cn.mrcsh.zfcloudpanbackend.entity.structure.PageStructure;
import cn.mrcsh.zfcloudpanbackend.service.AccessLogService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/log")
@CrossOrigin
@Slf4j
public class LogController extends ABaseController {

    @Autowired
    private AccessLogService accessLogService;

    @GetMapping
    @SaCheckLogin
    @SaCheckPermission("sys:log:select")
    public response logs(Integer page_size, Integer current_page) {
        PageStructure<AccessLog> result = accessLogService.selectPage(page_size, current_page);
        return success(result);
    }
}
