package com.example.my_from;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBianji;
    private RecyclerView mMainRecycler;
    private Button mQuanxuan;
    private Button mShanchu;
    private ReAdapter reAdapter;
    int l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(ApiService.PRO_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = build.create(ApiService.class);
        Observable<ProjectBean> datas = apiService.getDatas(294);
        datas.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ProjectBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(ProjectBean projectBean) {
                            reAdapter.initData(projectBean.getData().getDatas());
                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(MainActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
    }

    private void initView() {
        mBianji = (Button) findViewById(R.id.bianji);
        mBianji.setOnClickListener(this);
        mMainRecycler = (RecyclerView) findViewById(R.id.recycler_main);
        mQuanxuan = (Button) findViewById(R.id.quanxuan);
        mQuanxuan.setOnClickListener(this);
        mShanchu = (Button) findViewById(R.id.shanchu);
        mShanchu.setOnClickListener(this);
        mMainRecycler.setLayoutManager(new LinearLayoutManager(this));
        mMainRecycler.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        reAdapter = new ReAdapter(this);
        mMainRecycler.setAdapter(reAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bianji:
                // TODO 19/12/17
                String string = mBianji.getText().toString();
                if ("编辑".equals(string)) {
                    mBianji.setText("完成");
                    mQuanxuan.setVisibility(View.VISIBLE);
                    mShanchu.setVisibility(View.VISIBLE);
                }else{
                    mBianji.setText("编辑");
                    mQuanxuan.setVisibility(View.INVISIBLE);
                    mShanchu.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.quanxuan:// TODO 19/12/17
                reAdapter.check();
                break;
            case R.id.shanchu:// TODO 19/12/17
                reAdapter.setB(true);
                List<ProjectBean.DataBean.DatasBean> list = reAdapter.getList();
                for (int i = 0; i < list.size(); i++) {
                    ProjectBean.DataBean.DatasBean datasBean = list.get(i);
                    if (datasBean.getFlag()){
                        reAdapter.delest(i);
                        i--;
                    }
                }
                reAdapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
    }
}
