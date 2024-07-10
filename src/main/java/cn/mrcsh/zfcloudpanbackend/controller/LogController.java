package cn.mrcsh.zfcloudpanbackend.controller;

import cn.mrcsh.zfcloudpanbackend.entity.po.AccessLog;
import cn.mrcsh.zfcloudpanbackend.entity.structure.PageStructure;
import cn.mrcsh.zfcloudpanbackend.service.AccessLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
@CrossOrigin
public class LogController extends ABaseController{

    @Autowired
    private AccessLogService accessLogService;

    @GetMapping
    public response logs(Integer page_size, Integer current_page){
        PageStructure<AccessLog> result = accessLogService.selectPage(page_size, current_page);
        return success(result);
    }
}
