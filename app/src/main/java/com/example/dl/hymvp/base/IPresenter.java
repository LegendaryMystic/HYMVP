package com.example.dl.hymvp.base;

/**
 * Incremental change is better than ambitious failure.
 *
 * @author : <a href="http://mysticcoder.coding.me">MysticCoder</a>
 * @date : 2018/3/15
 * @desc :
 */


public interface IPresenter<V extends BaseView> {


    void attachView(V view);

    void detachView();
}
