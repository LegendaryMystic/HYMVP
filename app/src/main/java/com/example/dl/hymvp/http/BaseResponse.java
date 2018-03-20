package com.example.dl.hymvp.http;

/**
 * Incremental change is better than ambitious failure.
 *
 * @author : <a href="http://mysticcoder.coding.me/myBlog">MysticCoder</a>
 * @date : 2017/12/4
 * @desc :
 */


public class BaseResponse<T>{

    private int code;
    private String msg;
    private String data;
    private String seed;

    private T decryptedData;

    public T getDecryptedData() {
        return decryptedData;
    }

    public void setDecryptedData(T decryptedData) {
        this.decryptedData = decryptedData;
    }

    private boolean isSuccess;


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public void setSuccess(boolean isSuccess){
        this.isSuccess = isSuccess;
    }

    public boolean isSuccess(){
        return this.code >= 2000 && this.code < 4000;
    }


    @Override
    public String toString() {
        return "BaseResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data='" + data + '\'' +
                ", seed='" + seed + '\'' +
                ", decryptedData=" + decryptedData +
                ", isSuccess=" + isSuccess +
                '}';
    }
}
