package com.example.dl.hymvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Incremental change is better than ambitious failure.
 *
 * @author : <a href="http://mysticcoder.coding.me">MysticCoder</a>
 * @date : 2018/3/15
 * @desc :
 */

/**
 * 这里采用{@link com.trello.rxlifecycle2.RxLifecycle}框架来管理RxJava订阅生命周期
 * 将Activity基类继承自{@link RxAppCompatActivity} 考虑到Java没有多继承，若随着项目需要可能会使用到其他的第三方库需要继承，
 * 最简单的方法就是把{@link RxAppCompatActivity}的源码直接对应填到BaseActivity 即可^_^
 */
public abstract class BaseActivity extends RxAppCompatActivity implements IActivity{
    private Unbinder mUnbinder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            int layoutResID = initView(savedInstanceState);
            //如果initView返回0,框架则不会调用setContentView(),当然也不会 Bind ButterKnife
            if (layoutResID != 0) {
                setContentView(layoutResID);
                //绑定到butterknife
                mUnbinder = ButterKnife.bind(this);
                //如果要使用 Eventbus 请将此方法返回 true
                if (useEventBus()) {
                    //注册 Eventbus
                    EventBus.getDefault().register(this);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        initData(savedInstanceState);

}


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
            this.mUnbinder = null;
        }

        //如果要使用 Eventbus 请将此方法返回 true
        if (useEventBus()) {
            //解除注册 Eventbus
            EventBus.getDefault().unregister(this);
        }

    }

    /**
     * 子类Activity要使用EventBus只需要重写此方法返回true即可
     * @return
     */
    @Override
    public boolean useEventBus() {
        return false;
    }
}


