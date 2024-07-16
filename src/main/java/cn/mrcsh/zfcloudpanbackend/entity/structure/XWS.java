package cn.mrcsh.zfcloudpanbackend.entity.structure;

import lombok.Data;

@Data
public class XWS {
    private long timestamp;
    private String type;
    private String data;
}
