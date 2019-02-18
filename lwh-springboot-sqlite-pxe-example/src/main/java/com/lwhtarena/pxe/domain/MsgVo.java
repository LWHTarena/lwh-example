package com.lwhtarena.pxe.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * <h2>简述：</h2>
 * <ol></ol>
 * <h2>功能描述：</h2>
 * <ol></ol>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public class MsgVo implements Serializable {

    /**
     * 返回消息代码
     */
    private Integer code;

    /**
     * 返回消息信息
     */
    private String msg;

    /**
     * 数据详细信息
     */
    private Map<String,Object> detail =new HashMap<String,Object>();

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getDetail() {
        return detail;
    }

    public void setDetail(Map<String, Object> detail) {
        this.detail = detail;
    }
}
