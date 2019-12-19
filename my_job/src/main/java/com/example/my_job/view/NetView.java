package com.example.my_job.view;

import com.example.my_job.bean.ResultsBean;

import java.util.List;

public interface NetView {
    void setData(List<ResultsBean> list);
    void strToast(String string);
}
