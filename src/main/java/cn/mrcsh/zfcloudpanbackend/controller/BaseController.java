package cn.mrcsh.zfcloudpanbackend.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

public class BaseController {
    @Data
    @AllArgsConstructor
    class response {
        private Integer code;
        private String msg;
        private Object data;
    }

    public response success(){
        return new response(200, "success", null);
    }

    public response success(Object data){
        return new response(200, "success", data);
    }
}
