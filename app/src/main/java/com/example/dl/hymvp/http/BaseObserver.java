package com.example.dl.hymvp.http;

import android.text.TextUtils;
import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Incremental change is better than ambitious failure.
 *
 * @author : <a href="http://mysticcoder.coding.me/myBlog">MysticCoder</a>
 * @date : 2017/12/4
 * @desc :
 */


public abstract class BaseObserver<T> implements Observer<T> {



        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(T response) {

            onSuccess(response);
        }

        @Override
        public void onError(Throwable e) {


            if (e instanceof ResultException){
                onFailure(e.getMessage());
            }else {

                String error = ApiException.handleException(e).getMessage();

                _onError(error);
            }


        }

        @Override
        public void onComplete() {
        }

        /**
         * 请求成功
         *
         * @param response 服务器返回的数据
         */
        public abstract void onSuccess(T response);

        /**
         * 服务器返回数据，但code不在约定成功范围内
         *
         * @param msg 服务器返回的数据
         */
        public abstract void onFailure(String msg);


//        public abstract void onError(String errorMsg);




        private void _onSuccess(T responce){

        }

        private void _onFailure(String msg) {

            if (TextUtils.isEmpty(msg)) {
//                ToastUtils.show(R.string.response_return_error);
            } else {
//                ToastUtils.show(msg);
            }
        }
    private void _onError(String err ){

            Log.e("APIException",err);

    }






}
