package com.jnu.student;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.jnu.student.statistics.DailyStatistics;
import com.jnu.student.statistics.MonthlyStatistics;
import com.jnu.student.statistics.WeeklyStatistics;

public class PagerAdapter2 extends FragmentStateAdapter {
    public PagerAdapter2(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        //获得Tab对应的Fragment
        switch (position) {
            case 0:
                return new DailyStatistics(); //
            case 1:
                return new WeeklyStatistics(); //
            case 2:
                return new MonthlyStatistics(); //
            case 3:
                return new StatisticsFragment(); //
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        //Tab数目
        return 4;
    }
}
