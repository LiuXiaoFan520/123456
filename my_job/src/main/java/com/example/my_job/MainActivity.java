package com.example.my_job;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.example.my_job.fragement.Collect;
import com.example.my_job.fragement.Home;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mContent;
    private TabLayout mTable;
    private Home home;
    private Collect collect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initFragment();
    }

    private void initFragment() {
        home = new Home();
        collect = new Collect();
        getSupportFragmentManager().beginTransaction().add(R.id.content, home).add(R.id.content, collect).commit();
    }

    private void initView() {
        mContent = (LinearLayout) findViewById(R.id.content);
        mTable = (TabLayout) findViewById(R.id.table);
        mTable.addTab(mTable.newTab().setText("首页"));
        mTable.addTab(mTable.newTab().setText("收藏"));
        mTable.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position==0){
                    getSupportFragmentManager().beginTransaction().show(home).hide(collect).commit();
                }else{
                    getSupportFragmentManager().beginTransaction().show(collect).hide(home).commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
