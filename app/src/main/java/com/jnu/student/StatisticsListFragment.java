package com.jnu.student;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class StatisticsListFragment extends Fragment {
    static ViewPager2 viewPager;
    // 添加顶部导航栏的标签
    private String[] taskTabTitles = {"每日任务", "每周任务", "普通任务", "副本任务"};
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_task_list, container, false);
        viewPager = rootView.findViewById(R.id.viewPager);
        TabLayout tabLayout = rootView.findViewById(R.id.tabLayout);

        PagerAdapter2 pagerAdapter = new PagerAdapter2(requireActivity().getSupportFragmentManager(), getLifecycle());
        viewPager.setAdapter(pagerAdapter);
        // 设置预加载的页面数量为 Fragment 的总数
        viewPager.setOffscreenPageLimit(pagerAdapter.getItemCount());
        // 刷新菜单
        viewPager.setCurrentItem(1);
        viewPager.setCurrentItem(0);

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("每日统计");
                            break;
                        case 1:
                            tab.setText("每周统计");
                            break;
                        case 2:
                            tab.setText("全部统计");
                            break;
//                        case 3:
//                            tab.setText("副本任务");
//                            break;
                    }
                }).attach();

        return rootView;
    }
}
