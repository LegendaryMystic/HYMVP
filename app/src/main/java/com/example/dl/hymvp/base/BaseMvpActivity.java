package com.example.dl.hymvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Incremental change is better than ambitious failure.
 *
 * @author : <a href="http://mysticcoder.coding.me">MysticCoder</a>
 * @date : 2018/3/15
 * @desc :
 */


public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity {

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        mPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    /**
     * create presenter
     * @return presenter
     */
    protected abstract P createPresenter();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
