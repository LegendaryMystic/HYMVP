package com.example.dl.hymvp.http;

import com.google.gson.Gson;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Incremental change is better than ambitious failure.
 *
 * @author : <a href="http://mysticcoder.coding.me/myBlog">MysticCoder</a>
 * @date : 2017/12/7
 * @desc :
 */


public class ResponseConverterFactory extends Converter.Factory {
    public static ResponseConverterFactory create() {
        return create(new Gson());
    }


    public static ResponseConverterFactory create(Gson gson) {
        return new ResponseConverterFactory(gson);
    }

    private final Gson gson;

    private ResponseConverterFactory(Gson gson) {
        if (gson == null) {throw new NullPointerException("gson == null");}
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
//返回我们自定义的Gson响应体变换器
        return new GsonResponseBodyConverter<>(gson, type);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
//返回我们自定义的Gson响应体变换器
        return new GsonResponseBodyConverter<>(gson,type);
    }
}
