package com.example.dl.hymvp.base;

import android.app.Activity;

import com.example.dl.hymvp.util.Preconditions;
import com.trello.rxlifecycle2.RxLifecycle;

import org.reactivestreams.Subscription;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


/**
 * Incremental change is better than ambitious failure.
 *
 * @author : <a href="http://mysticcoder.coding.me">MysticCoder</a>
 * @date : 2018/3/15
 * @desc :
 */

public class BasePresenter<V extends BaseView,M extends BaseModel> implements IPresenter {

    protected V mView;
    protected M mModel;

    protected CompositeDisposable mCompositeDisposable;

    /**
     * 如果当前页面同时需要 Model 层和 View 层,则使用此构造函数(默认)
     *
     * @param model
     * @param view
     */
    public BasePresenter(M model, V view) {
        Preconditions.checkNotNull(model, "%s cannot be null", BaseModel.class.getName());
        Preconditions.checkNotNull(view, "%s cannot be null", BaseView.class.getName());
        this.mModel = model;
        this.mView = view;
        attachView();
    }

    /**
     * 如果当前页面不需要操作数据,只需要 View 层,则使用此构造函数
     *
     * @param view
     */
    public BasePresenter(V view) {
        Preconditions.checkNotNull(view, "%s cannot be null", BaseView.class.getName());
        this.mView = view;
        attachView();
    }

    public BasePresenter() {
        attachView();
    }



    /**
     * 将 {@link Disposable} 添加到 {@link CompositeDisposable} 中统一管理
     * 可在 {@link Activity#onDestroy()} 中使用 {@link #unDispose()} 停止正在执行的 RxJava 任务,避免内存泄漏
     * 目前已使用 {@link RxLifecycle} 避免内存泄漏,此方法作为备用方案
     *
     * @param disposable
     */
    protected void addDisposabel(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        //将所有 Disposable 放入集中处理
        mCompositeDisposable.add(disposable);
    }

    public void unDispose(){
        if (mView != null) {
            mView = null;
        }
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();//保证 Activity 结束时取消所有正在执行的订阅
        }
    }


    @Override
    public void attachView() {

    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView = null;
        }
        unDispose();

    }
}
