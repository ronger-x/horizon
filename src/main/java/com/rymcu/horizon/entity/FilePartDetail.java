package com.rymcu.horizon.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * Created on 2025/2/16 10:50.
 *
 * @author ronger
 * @email ronger-x@outlook.com
 * @desc : com.rymcu.horizon.entity
 */
@Data
@TableName(value = "horizon_file_part_detail", schema = "horizon")
public class FilePartDetail {

    @TableId(value = "id")
    @TableField(value = "id")
    private Long idFilePartDetail;

    private String platform;

    private String uploadId;

    private String eTag;

    private Integer partNumber;

    private Long partSize;

    private String hashInfo;

    private Date createdTime;

}
