package cn.mrcsh.zfcloudpanbackend.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@TableName("t_file_info")
public class FileInfo {
    // 文件ID
    @TableId
    private String fileId;
    // 文件名称
    private String fileName;
    // 文件大小 单位 byte
    private Long fileSize;
    // 文件位置
    private String fileAbsPath;
    // 文件类型
    private String fileType;
    // 文件MD5值
    private String fileMd5;
    // 文件所属
    private String fileOwner;
    // 文件父ID
    private String filePid;
    // 文件是否放入回收站
    private boolean deleted;
    // 视频文件缩略图
    private String fileAvatar;
    // 上传状态
    private String status;
    // 分片数
    private Integer chunkNum;
    // 断点续传分片id
    private Integer chunkIndex;
    // 文件
    @TableField(exist = false)
    MultipartFile file;
}
