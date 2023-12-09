package com.jnu.student;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class StatisticsFragment extends Fragment {
    private ArrayList<ScoreList> scoreList = new ArrayList<>();
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

        return view;
    }

    // 其他代码...
}
