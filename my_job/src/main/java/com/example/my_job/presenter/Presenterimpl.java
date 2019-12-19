package com.example.my_job.presenter;

import com.example.my_job.CallBack;
import com.example.my_job.bean.ResultsBean;
import com.example.my_job.model.ModelImpl;
import com.example.my_job.view.NetView;

import java.util.List;

public class Presenterimpl implements CallBack {
    private NetView netView;
    private ModelImpl model;

    public Presenterimpl(NetView netView) {
        this.netView = netView;
        model=new ModelImpl();
    }
    public void getData(){
        model.getData(this);
    }
    @Override
    public void succeed(List<ResultsBean> list) {
        netView.setData(list);
    }

    @Override
    public void failed(String str) {
        netView.strToast(str);
    }
}
