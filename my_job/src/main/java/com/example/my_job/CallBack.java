package com.example.my_job;

import com.example.my_job.bean.ResultsBean;

import java.util.List;

public interface CallBack {
    void succeed(List<ResultsBean> list);
    void failed(String str);
}
