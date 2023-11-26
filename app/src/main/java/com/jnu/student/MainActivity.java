package com.jnu.student;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {
    private TextView textView1, textView2;
    private Button chang_text_button;

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<String> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        chang_text_button = findViewById(R.id.chang_text_button);

        recyclerView = findViewById(R.id.recyclerView);
        dataList = new ArrayList<>();

        // 添加一些示例数据
        dataList.add("Item 10000");
        dataList.add("Item 20000");
        dataList.add("Item 30000");

        // 创建适配器并设置给 RecyclerView
        adapter = new MyAdapter(dataList);
        recyclerView.setAdapter(adapter);

        // 设置 RecyclerView 的布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void changeText(View view) {
        // 交换两个TextView的文本
        String text1 = textView1.getText().toString();
        String text2 = textView2.getText().toString();
        textView1.setText(text2);
        textView2.setText(text1);

        // 显示交换成功的Toast
        Toast.makeText(this, "交换成功", Toast.LENGTH_SHORT).show();

        // 显示交换成功的AlertDialog
        new AlertDialog.Builder(this)
                .setTitle("交换成功")
                .setMessage("文本已成功交换！")
                .setPositiveButton("确定", null)
                .show();
    }

}