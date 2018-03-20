package com.example.dl.hymvp.base;

import com.example.dl.hymvp.http.ApiEngine;
import com.example.dl.hymvp.http.ApiService;

/**
 * Incremental change is better than ambitious failure.
 *
 * @author : <a href="http://mysticcoder.coding.me">MysticCoder</a>
 * @date : 2018/3/15
 * @desc :
 */


public interface BaseModel {

    ApiService mApiService = ApiEngine.getInstance().getApiService();
}
