package cn.mrcsh.zfcloudpanbackend.aop;

import cn.hutool.core.util.IdUtil;
import cn.mrcsh.zfcloudpanbackend.entity.po.AccessLog;
import cn.mrcsh.zfcloudpanbackend.service.AccessLogService;
import com.alibaba.fastjson2.JSON;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Date;

@Aspect
@Component
@Slf4j
public class HttpAccessLogAspect {

    @Autowired
    private AccessLogService accessLogService;

    @Pointcut("@annotation(cn.mrcsh.zfcloudpanbackend.annotation.AccessLog)")
    public void pt() {
    }

    @Around("pt()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        Object process = null;
        String status = "成功";
        try {
            process = pjp.proceed();
        } catch (Throwable e) {
            status = "失败: " + e.getMessage();
        }
        log.info("[请求日志] - {} {} {}", request.getRequestURI(), request.getMethod(), process);
        AccessLog accessLog = new AccessLog();
        accessLog.setId(IdUtil.getSnowflakeNextIdStr());
        accessLog.setPath(request.getRequestURI());
        accessLog.setMethod(request.getMethod());
        accessLog.setCreateTime(new Date());
        accessLog.setUpdateTime(new Date());
        accessLog.setResult(status);
        // todo 测试日志
        accessLog.setAccess_from("127.0.0.1");
        accessLog.setAccess_as("admin");
        accessLog.setParams(JSON.toJSONString(pjp.getArgs()));
        accessLogService.insertLog(accessLog);
        return process;
    }
}
