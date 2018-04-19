package com.example.dl.hymvp.mvp.contract;

import com.example.dl.hymvp.base.BaseModel;

import com.example.dl.hymvp.base.BaseView;
import com.example.dl.hymvp.bean.Gank;
import com.example.dl.hymvp.bean.Survey;

import io.reactivex.Observable;

/**
 * Incremental change is better than ambitious failure.
 *
 * @author : <a href="http://mysticcoder.coding.me">MysticCoder</a>
 * @date : 2018/3/16
 * @desc :
 */

public interface MainContract {

    interface MainView extends BaseView {

        void onGetSurvey(Survey survey);
    }

    interface MainModel extends BaseModel {

      Observable<Survey> getSurvey(String did);
    }


//    abstract class MainPresenter extends BasePresenter<MainView, MainModel> {
//        public abstract void getSurvey(String did);
//    }

}
