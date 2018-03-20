package com.example.dl.hymvp.mvp.model;

import com.example.dl.hymvp.bean.Gank;
import com.example.dl.hymvp.bean.Survey;
import com.example.dl.hymvp.http.ApiEngine;
import com.example.dl.hymvp.mvp.contract.MainContract;


import io.reactivex.Observable;

/**
 * Incremental change is better than ambitious failure.
 *
 * @author : <a href="http://mysticcoder.coding.me">MysticCoder</a>
 * @date : 2018/3/16
 * @desc :
 */


public class MainModel implements MainContract.MainModel {


    @Override
    public Observable<Survey> getSurvey(String did) {
        return mApiService.getSurveyList(did);
    }

}
