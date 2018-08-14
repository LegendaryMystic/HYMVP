package com.example.dl.hymvp.mvp.presenter;

import android.database.Observable;

import com.example.dl.hymvp.base.BasePresenter;
import com.example.dl.hymvp.bean.Gank;
import com.example.dl.hymvp.bean.Survey;
import com.example.dl.hymvp.http.BaseObserver;
import com.example.dl.hymvp.mvp.contract.MainContract;
import com.example.dl.hymvp.mvp.model.MainModel;
import com.example.dl.hymvp.rx.RxTransformer;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Incremental change is better than ambitious failure.
 *
 * @author : <a href="http://mysticcoder.coding.me">MysticCoder</a>
 * @date : 2018/3/20
 * @desc :
 */


public class MainPresenter extends BasePresenter<MainContract.MainView,MainContract.MainModel> {

    public MainPresenter(MainContract.MainView view) {
         super(new MainModel(), view);
    }



//    public void getGank(){
//        mModel.getGank()
//                .compose(RxTransformer.transformWithLoading(mView))
//                .subscribe(new BaseObserver<Gank>() {
//                    @Override
//                    public void onSuccess(Gank response) {
//
//                    }
//
//                    @Override
//                    public void onFailure(String msg) {
//
//                    }
//                });
//
//    }

    public void getSurveyList(String did){

//        //生命周期管理，备用方案，订阅的时候添加Disposable对象到容器
//        mModel.getSurvey(did)
//                .compose(RxTransformer.transform())
//                .doOnSubscribe(this::addDisposabel)
//                .subscribe(new BaseObserver<Survey>() {
//                    @Override
//                    public void onSuccess(Survey response) {
//                        getView().onGetSurvey(response);
//                    }
//
//                    @Override
//                    public void onFailure(String msg) {
//
//                    }
//                });



//
//        Disposable disposable = mModel.getSurvey(did)
//                .compose(RxTransformer.transformWithLoading(getView()))
//                .subscribe(survey -> getView().onGetSurvey(survey));
//
//        addDisposabel(disposable);




       mModel.getSurvey(did)
                .compose(RxTransformer.transformWithLoading(getView()))
                .subscribe(new BaseObserver<Survey>() {
                    @Override
                    public void onSuccess(Survey response) {
                        getView().onGetSurvey(response);
                    }

                    @Override
                    public void onFailure(String msg) {

                    }
                });




    }
}
