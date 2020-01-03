package com.zhuchuansheng.cmfz.util;

import lombok.experimental.Accessors;

/**
 * @author zhuchuansheng
 * @date: 2019/12/31
 * @time: 15:01
 */
@Accessors(chain = true)
public class ResultBuild {


    StringBuffer content = new StringBuffer();

    public void startObject(){
        this.content.append("{");
    }

    public void endObject(){
        this.content.append("}");
    }

    public void setStartObject(Object key, Object value){
        this.content.append("\""+key+"\":\""+value+"\"");
    }


    public StringBuffer getContent() {
        return content;
    }

    public void setContent(StringBuffer content) {
        this.content = content;
    }
}
