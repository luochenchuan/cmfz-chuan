package com.zhuchuansheng.cmfz.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuchuansheng
 * @date: 2019/12/31
 * @time: 15:23
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ResultDto implements Serializable {

    private List<Object> header = new ArrayList<>();

    private List<Object> body = new ArrayList<>();

}
