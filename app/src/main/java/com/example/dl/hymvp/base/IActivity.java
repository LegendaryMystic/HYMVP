package com.example.dl.hymvp.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Incremental change is better than ambitious failure.
 *
 * @author : <a href="http://mysticcoder.coding.me">MysticCoder</a>
 * @date : 2018/3/16
 * @desc :
 */


public interface IActivity {

    /**
     * 初始化 View, 如果 {@link #initView(Bundle)} 返回 0, 则不会调用 {@link Activity#setContentView(int)}
     *
     * @param savedInstanceState
     * @return
     */
    int initView(@Nullable Bundle savedInstanceState);

    /**
     * 初始化数据
     *
     * @param savedInstanceState
     */
    void initData(@Nullable Bundle savedInstanceState);


    /**
     * 是否使用 EventBus
     *
     * @return
     */
    boolean useEventBus();




}
