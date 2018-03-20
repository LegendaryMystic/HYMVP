package com.example.dl.hymvp.http;

/**
 * Incremental change is better than ambitious failure.
 *
 * @author : <a href="http://mysticcoder.coding.me/myBlog">MysticCoder</a>
 * @date : 2017/12/7
 * @desc :
 */


public class ResultException extends RuntimeException{


    private  int code;
    private String message;

    public ResultException(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
