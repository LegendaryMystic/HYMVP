package com.example.dl.hymvp.rx;


import com.example.dl.hymvp.base.BasePresenter;
import com.example.dl.hymvp.base.BaseView;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Incremental change is better than ambitious failure.
 *
 * @author : <a href="http://mysticcoder.coding.me">MysticCoder</a>
 * @date : 2018/3/15
 * @desc :
 */

public class RxTransformer {

    /**
     * 无参数  仅做切换线程
     *
     * @param <T> 泛型
     * @return 返回Observable
     */
    public static <T> ObservableTransformer<T, T> transform() {
        return upstream -> upstream
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 界面请求，不需要加载和隐藏loading时调用 使用RxLifeCycle
     * 传入view接口，Activity，Fragment等实现了view接口，Activity，Fragment继承于{@link com.trello.rxlifecycle2.components.support.RxAppCompatActivity}
     * 也就实现了bindToLifecycle方法
     * @param view View
     * @param <T> 泛型
     * @return
     */
    public static <T> ObservableTransformer<T, T> transform(final BaseView view) {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(view.bindToLifecycle());
    }


    /**
     * 界面请求，需要加载和隐藏loading时调用,使用RxLifeCycle
     * 传入view接口，Activity，Fragment等实现了view接口，Activity，Fragment继承于{@link com.trello.rxlifecycle2.components.support.RxAppCompatActivity}
     * 也就实现了bindToLifecycle方法
     * @param view View
     * @param <T> 泛型
     * @return
     */
    public static <T> ObservableTransformer<T, T> transformWithLoading(final BaseView view) {
        //隐藏进度条
        return observable -> observable.subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> {
                    view.showLoading();//显示进度条
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(view::hideLoading).compose(view.bindToLifecycle());
    }


}
