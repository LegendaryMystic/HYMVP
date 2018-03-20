package com.example.dl.hymvp.http;


import com.example.dl.hymvp.bean.Gank;
import com.example.dl.hymvp.bean.Survey;


import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


/**
 * Incremental change is better than ambitious failure.
 *
 * @author : <a href="http://mysticcoder.coding.me">MysticCoder</a>
 * @date : 2018/3/15
 * @desc :
 */

public interface ApiService {

//    String BASE_URL="http://gank.io/";
//
//    @GET("api/data/Android/10/{page}")
//    Observable<Gank> getGank(@Path("page") String page);


    String BASE_URL = "http://api.medrd.com/";


    @FormUrlEncoded
    @POST("api/user/surveytitlelistNew")//获取调查问卷列表
    Observable<Survey> getSurveyList(@Field("did")String did);

}
