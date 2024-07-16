package cn.mrcsh.zfcloudpanbackend.enums;

import lombok.Getter;

@Getter
public enum WSType {
    // 正常
    NORMAL("normal"),
    // 踢出
    KICK("kick"),
    // 心跳
    LIFECYCLE("lifecycle"),
    ;
    private String type;

    WSType(String type) {
        this.type = type;
    }

}
