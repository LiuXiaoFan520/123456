package com.example.my_job.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.my_job.R;
import com.example.my_job.bean.ResultsBean;

import java.util.ArrayList;
import java.util.List;

public class CollectAdapter extends RecyclerView.Adapter<CollectAdapter.ViewHodler> {
    private Context context;
    private List<ResultsBean> list=new ArrayList<>();

    public CollectAdapter(Context context) {
        this.context = context;
    }
    public void initData(List<ResultsBean> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public List<ResultsBean> getList() {
        return list;
    }
    public void delect(int i){
        list.remove(i);
    }
    public void check(boolean b){
        for (int i = 0; i <list.size() ; i++) {
            list.get(i).setFlag(b);
        }
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View root=LayoutInflater.from(context).inflate(R.layout.collectitem,viewGroup,false);
        return new ViewHodler(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodler viewHodler, int i) {
        final ResultsBean resultsBean = list.get(i);
        Glide.with(context).load(resultsBean.getUrl()).into(viewHodler.image);

        viewHodler.checkBox.setChecked(resultsBean.getFlag());
        viewHodler.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    resultsBean.setFlag(isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHodler extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        ImageView image;
        public ViewHodler(@NonNull View itemView) {
            super(itemView);
            checkBox=itemView.findViewById(R.id.checkbox);
            image=itemView.findViewById(R.id.image);
        }
    }

}
