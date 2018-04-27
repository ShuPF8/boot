package com.spf.model.user;


import lombok.Data;

import java.io.Serializable;

/**
 * @author ShuPF
 * @类说明： 返回数据实体类型
 * @date 2018-04-13 12:09
 */
@Data
public class ResultMessage implements Serializable {
    public static final String SUCCESS = "0";// 成功
    public static final String FAIL="-1";

    /** 状态 */
    private String code;

    /** 消息 */
    private String msg;

    /** 异常信息 */
    private String exception;

    /** 数据 */
    private Object data;

    public ResultMessage(){}

    public ResultMessage(String code, String msg, Object data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultMessage(String code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public void setSuccess(){
        this.code = SUCCESS;
        this.msg = "success";
    }

    public void setSuccess(String msg){
        this.code = SUCCESS;
        this.msg = msg;
    }

   public void setFail() {
        this.code = FAIL;
        this.msg = "fail";
   }

    public void setFail(String msg) {
        this.code = FAIL;
        this.msg = msg;
    }
}
