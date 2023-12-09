package com.jnu.student;

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
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class StatisticsFragment extends Fragment {
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
//        int totalScore = requireActivity().getIntent().getIntExtra("total_score", 0);

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
    // 准备折线图数据
    List<Entry> entries = new ArrayList<>();
    for (int i = 0; i < scoreList.size(); i++) {
        int sum = 0;
        for(int j = 0; j <= i; j++){
            ScoreList data = scoreList.get(j);
            sum += data.getScore();
        }
        ScoreList data = scoreList.get(i);
        entries.add(new Entry(data.getTime(), sum));
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
}
