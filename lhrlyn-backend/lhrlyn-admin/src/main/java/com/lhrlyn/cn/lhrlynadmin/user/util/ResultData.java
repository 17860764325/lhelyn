package com.lhrlyn.cn.lhrlynadmin.user.util;

import com.lhrlyn.cn.lhrlynadmin.user.enity.User;
import lombok.Data;

@Data
public class ResultData<t> {
    private int status;
    private String message;
    private t data;
    private long timestamp ;


    public ResultData (){
        this.timestamp = System.currentTimeMillis();
    }


    public static <t> ResultData<t> success(t data) {
        ResultData<t> resultData = new ResultData<>();
        resultData.setStatus(ReturnCode.RC100.getCode());
        resultData.setMessage(ReturnCode.RC100.getMessage());
        resultData.setData(data);
        return resultData;
    }

    public static <t> ResultData<t> fail(int code, String message) {
        ResultData<t> resultData = new ResultData<>();
        resultData.setStatus(code);
        resultData.setMessage(message);
        return resultData;
    }

}
