package com.example.my_job.fragement;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.my_job.BaseApp;
import com.example.my_job.R;
import com.example.my_job.adapter.CollectAdapter;
import com.example.my_job.bean.ResultsBean;
import com.example.xts.greendaodemo.db.ResultsBeanDao;

import java.util.List;

public class Collect extends Fragment implements View.OnClickListener {
    private RecyclerView mRecycler;
    private CheckBox mCheckbox;
    private TextView mTitle;
    private CollectAdapter collectAdapter;
    private Button mButon;

    private List<ResultsBean> resultsBeans;
    private ResultsBeanDao resultsBeanDao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = LayoutInflater.from(getContext()).inflate(R.layout.collect, null);
        initView(root);
        initData();
        return root;
    }

    private void initData() {
        resultsBeanDao = BaseApp.getInstance().getDaoSession().getResultsBeanDao();
        resultsBeans = resultsBeanDao.loadAll();
        collectAdapter.initData(resultsBeans);
    }

    private void initView(@NonNull final View itemView) {
        mRecycler = (RecyclerView) itemView.findViewById(R.id.recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycler.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        collectAdapter = new CollectAdapter(getContext());
        mRecycler.setAdapter(collectAdapter);
        mCheckbox = (CheckBox) itemView.findViewById(R.id.checkbox);
        mTitle = (TextView) itemView.findViewById(R.id.title);
        mCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                collectAdapter.check(isChecked);
                if (isChecked) {
                    mCheckbox.setText("取消");
                } else {
                    mCheckbox.setText("全选");
                }
                int num=0;
                List<ResultsBean> list = collectAdapter.getList();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getFlag()==true){
                        num++;
                    }
                    mTitle.setText(num+""+"/"+list.size());
                }
            }
        });

        mButon = (Button) itemView.findViewById(R.id.buton);
        mButon.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buton:
                // TODO 19/12/19
                List<ResultsBean> list = collectAdapter.getList();
                for (int i = 0; i < list.size(); i++) {
                    ResultsBean resultsBean = list.get(i);
                    if (resultsBean.getFlag()){
                        collectAdapter.delect(i);
                        resultsBeanDao.delete(resultsBeans.get(i));
                        i--;
                    }
                    collectAdapter.notifyDataSetChanged();
                }

                break;
            default:
                break;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            resultsBeans.clear();
            initData();
        }
    }
}
