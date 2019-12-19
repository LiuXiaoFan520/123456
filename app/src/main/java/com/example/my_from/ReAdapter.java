package com.example.my_from;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ReAdapter extends RecyclerView.Adapter<ReAdapter.ViewHodler> {
    private List<ProjectBean.DataBean.DatasBean> list=new ArrayList<>();
    Context context;

    private boolean l;
    public void setB(boolean l){
        this.l=l;
    }
    public ReAdapter(Context context) {
        this.context = context;
    }
    public void initData(List<ProjectBean.DataBean.DatasBean> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public List<ProjectBean.DataBean.DatasBean> getList() {
        return list;
    }

    public void check(){
        for (int i = 0; i <list.size() ; i++) {
            list.get(i).setFlag(true);
        }
        notifyDataSetChanged();
    }
    public void delest(int i){
        list.remove(i);
    }
    @NonNull
    @Override
    public ViewHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View root= LayoutInflater.from(context).inflate(R.layout.rootitem,viewGroup,false);
        return new ViewHodler(root);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHodler viewHodler, final int i) {
        final ProjectBean.DataBean.DatasBean datasBean = list.get(i);
        viewHodler.title.setText(datasBean.getTitle());
        Glide.with(context).load(datasBean.getEnvelopePic()).into(viewHodler.image);
        if (l){
            viewHodler.che.setVisibility(viewHodler.che.VISIBLE);
        }
        viewHodler.che.setChecked(datasBean.getFlag());


        viewHodler.che.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    datasBean.setFlag(true);
                }else{
                    datasBean.setFlag(false);
                }
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
        CheckBox che;
        public ViewHodler(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            title=itemView.findViewById(R.id.title);
            che=itemView.findViewById(R.id.checkbox);
        }
    }
}
