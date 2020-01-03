package com.zhuchuansheng.cmfz.commons;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhuchuansheng
 * @date: 2019/12/31
 * @time: 15:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ResultSubDto implements Serializable {

    private String thumbnail;

    private String title;

    private String author;

    private String type;

    private String set_count;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date create_date;

}
