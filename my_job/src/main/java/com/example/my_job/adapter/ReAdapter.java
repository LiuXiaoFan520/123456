package com.example.my_job.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.my_job.BaseApp;
import com.example.my_job.R;
import com.example.my_job.bean.ResultsBean;
import com.example.xts.greendaodemo.db.ResultsBeanDao;

import java.util.ArrayList;
import java.util.List;

public class ReAdapter extends RecyclerView.Adapter<ReAdapter.ViewHodler> {
    private List<ResultsBean> list=new ArrayList<>();
    private Context context;

    public ReAdapter(Context context) {
        this.context = context;
    }
    public void initData(List<ResultsBean> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    public void delest(int i){
        list.remove(i);
    }
    @NonNull
    @Override
    public ViewHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View root= LayoutInflater.from(context).inflate(R.layout.readapteritem,null);
        return new ViewHodler(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodler viewHodler, int i) {
        final ResultsBean resultsBean = list.get(i);
        Glide.with(context).load(resultsBean.getUrl()).into(viewHodler.image);
        viewHodler.title.setText(resultsBean.getDesc());
        viewHodler.collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultsBeanDao resultsBeanDao = BaseApp.getInstance().getDaoSession().getResultsBeanDao();
                resultsBeanDao.insert(resultsBean);
                Toast.makeText(context, "收藏成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHodler extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        Button collect;
        public ViewHodler(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            title=itemView.findViewById(R.id.title);
            collect=itemView.findViewById(R.id.collect);
        }
    }
}
