package com.jnu.student.statistics;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.jnu.student.DataBank_total;
import com.jnu.student.R;
import com.jnu.student.ScoreList;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WeeklyStatistics extends Fragment {
    private ArrayList<ScoreList> scoreList = new ArrayList<>();
    private LineChart lineChart;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistics, container, false);

        // 获取传递过来的总和
        int totalScore = 0;
        scoreList = new DataBank_total().LoadTaskItems(requireActivity());
        for (ScoreList score : scoreList) {
            totalScore += score.getScore();
        }

        // 在 TextView 中显示总和
        TextView totalScoreTextView = view.findViewById(R.id.total_score);
        totalScoreTextView.setText(String.valueOf(totalScore));

        //折线图
        // 找到 LineChart 控件
        lineChart = view.findViewById(R.id.lineChart);

        // 设置折线图
        setupLineChart();

        // 更新折线图数据
        updateLineChart();
        return view;
    }

    // 其他代码...
    private void setupLineChart() {
        // 基本配置
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        // X 轴配置
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        // 图例配置
        lineChart.getLegend().setEnabled(true);
        // 描述配置
        Description description = new Description();
        description.setText("Score over Time");
        lineChart.setDescription(description);
    }
    private void updateLineChart() {
        //获取近8周日期
        int[] daysArray ={1,2,3,4,5,6,7,8};
        int[] scoresArray ={0,0,0,0,0,0,0,0};
        // 准备折线图数据
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < scoreList.size(); i++) {
            ScoreList data = scoreList.get(i);
            int sum = data.getScore();
            long timestamp = data.getTime();
            // 计算时间差
            long timeDifference = System.currentTimeMillis() - timestamp;
            // 根据时间差返回相应的值
            int result = calculateResult(timeDifference);
            if(result/24/7<8){
                scoresArray[(result/24/7)]+=sum;
            }

        }
        for(int i=0;i<8;i++){
            entries.add(new Entry(daysArray[i], scoresArray[i]));
        }
        // 创建折线数据集
        LineDataSet dataSet = new LineDataSet(entries, "Score");
        dataSet.setColor(Color.BLUE);
        dataSet.setValueTextColor(Color.RED);
        // 创建折线数据
        LineData lineData = new LineData(dataSet);
        // 设置折线图数据
        lineChart.setData(lineData);
        // 刷新图表
        lineChart.invalidate();
    }
    public static int calculateResult(long timeDifference) {
        // 计算经过的24小时的数量
        long hoursPassed = TimeUnit.MILLISECONDS.toHours(timeDifference);

        // 返回经过的24小时的数量
        return (int) hoursPassed;
    }
}