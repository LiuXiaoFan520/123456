package com.example.my_job.fragement;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.my_job.R;
import com.example.my_job.adapter.ReAdapter;
import com.example.my_job.bean.ResultsBean;
import com.example.my_job.presenter.Presenterimpl;
import com.example.my_job.view.NetView;

import java.util.List;

public class Home extends Fragment implements NetView {
    private RecyclerView mHomeRecycler;
    private ReAdapter reAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.homeitem, null);
        initView(root);
        Presenterimpl presenterimpl = new Presenterimpl(this);
        presenterimpl.getData();
        return root;
    }

    private void initView(@NonNull final View itemView) {
        mHomeRecycler = (RecyclerView) itemView.findViewById(R.id.recycler_home);
        mHomeRecycler.setLayoutManager(new StaggeredGridLayoutManager(2,LinearLayout.VERTICAL));
        reAdapter = new ReAdapter(getContext());
        mHomeRecycler.setAdapter(reAdapter);
    }

    @Override
    public void setData(List<ResultsBean> list) {
        reAdapter.initData(list);
    }

    @Override
    public void strToast(String string) {
        Toast.makeText(getContext(), ""+string, Toast.LENGTH_SHORT).show();
    }
}