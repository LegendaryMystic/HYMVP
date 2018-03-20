package com.example.dl.hymvp.http;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;


/**
 * Incremental change is better than ambitious failure.
 *
 * @author : <a href="http://mysticcoder.coding.me/myBlog">MysticCoder</a>
 * @date : 2017/12/6
 * @desc :
 */


public class GsonResponseBodyConverter<T> implements Converter<ResponseBody,T> {
    private final Gson gson;
    private final Type type;


    public GsonResponseBodyConverter(Gson gson, Type type){
        this.gson = gson;
        this.type = type;
    }
    @Override
    public T convert(@NonNull ResponseBody value) throws IOException {

        String response = value.string();

        BaseResponse<T> result = gson.fromJson(response, BaseResponse.class);
        if (result.isSuccess() && null!=result.getData()){
            String jsonStr = DES.decode(result.getData(), result.getSeed());
            return gson.fromJson(jsonStr,type);


        }else {

//抛一个自定义ResultException 传入失败时候的状态码，和信息
                throw new ResultException(result.getCode(),result.getMsg());

        }

    }
}

