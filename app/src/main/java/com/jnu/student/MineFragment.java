package com.jnu.student;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MineFragment extends Fragment {

    private ImageView userImage;
    private TextView userName;


    // 其他代码...
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mine, container, false);

        // 找到 ImageView 和 TextView
        userImage = view.findViewById(R.id.userImage);
        userName = view.findViewById(R.id.userName);

        // 设置 ImageView 的图片资源，这里使用一个示例图片资源
        userImage.setImageResource(R.drawable.user);

        // 设置 TextView 的文本，这里使用一个示例文本
        userName.setText("不关橙猫猫的事哦");

        return view;
    }
}