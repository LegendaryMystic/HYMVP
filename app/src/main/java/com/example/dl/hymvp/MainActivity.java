package com.example.dl.hymvp;

import android.support.annotation.Nullable;

import android.os.Bundle;
import android.util.Log;

import android.widget.Toast;

import com.example.dl.hymvp.base.BaseActivity;
import com.example.dl.hymvp.base.BaseMvpActivity;
import com.example.dl.hymvp.base.BasePresenter;
import com.example.dl.hymvp.bean.Gank;
import com.example.dl.hymvp.bean.Survey;
import com.example.dl.hymvp.mvp.contract.MainContract;
import com.example.dl.hymvp.mvp.presenter.MainPresenter;


/**
 * Incremental change is better than ambitious failure.
 *
 * @author : <a href="http://mysticcoder.coding.me">MysticCoder</a>
 * @date : 2018/3/15
 * @desc :
 */

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.MainView {

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        findViewById(R.id.get).setOnClickListener(v -> {
            mPresenter.getSurveyList("189ba5a013dc");
        });

    }



    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }



    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onGetSurvey(Survey survey) {

        Log.d("成功" ,survey.toString());
        Toast.makeText(this, survey.toString(), Toast.LENGTH_SHORT).show();
    }



}




