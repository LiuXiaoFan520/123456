package com.example.my_job.model;

import com.example.my_job.ApiService;
import com.example.my_job.CallBack;
import com.example.my_job.bean.FuLiBean;
import com.example.my_job.presenter.Presenterimpl;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ModelImpl {
    public void getData(final CallBack callBack) {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(ApiService.STRINGURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = build.create(ApiService.class);
        Observable<FuLiBean> data = apiService.getData();
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<FuLiBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(FuLiBean fuLiBean) {
                            callBack.succeed(fuLiBean.getResults());
                        }

                        @Override
                        public void onError(Throwable e) {
                            callBack.failed(e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
    }
}
