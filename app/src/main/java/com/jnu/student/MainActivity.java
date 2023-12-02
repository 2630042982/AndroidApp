package com.jnu.student;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.jnu.student.TaskItem;



public class MainActivity extends AppCompatActivity {
    private TextView textView1, textView2;
    private Button chang_text_button;

//    private MyAdapter adapter;
//    private MyAdapter context_menu_adapter;

    private List<String> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        //交换button
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        chang_text_button = findViewById(R.id.chang_text_button);

        RecyclerView mainRecyclerview = findViewById(R.id.recyclerView);
        mainRecyclerview.setLayoutManager(new LinearLayoutManager(this));


        ArrayList<TaskItem> taskList = new ArrayList<>();
        // 添加一些示例数据
        taskList.add(new TaskItem("学高数",R.drawable.task_2));
        taskList.add(new TaskItem("进行Android开发",R.drawable.task_2));
        taskList.add(new TaskItem("吃饭",R.drawable.task_2));
        taskList.add(new TaskItem("午休",R.drawable.task_2));
        taskList.add(new TaskItem("继续进行Android开发",R.drawable.task_2));

        // 创建适配器并设置给 RecyclerView
        TaskItemAdapter taskItemAdapter = new TaskItemAdapter(taskList);
        mainRecyclerview.setAdapter(taskItemAdapter);

        registerForContextMenu(mainRecyclerview);

        addItemLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();

                        String name = data.getStringExtra("name");
                        taskList.add(new TaskItem(name,R.drawable.task_2));
//                        TaskItemAdapter.notifyItemInserted(taskList.size());

                        //获取返回的数据
                        // 在这可以根据需要进行进一步处理
                    } else if (result.getResultCode() == Activity.RESULT_CANCELED) {

                    }
                }
        );
    }
    ActivityResultLauncher<Intent> addItemLauncher;

    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item
                .getMenuInfo();
        switch (item.getItemId()) {
            case 0:
                Intent intent = new Intent(MainActivity.this,TaskItemDetailsActivity.class);
                addItemLauncher.launch(intent);
                break;
            case 1:
                break;
            case 2:
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }







    public class TaskItemAdapter extends RecyclerView.Adapter<TaskItemAdapter.ViewHolder> {

        private ArrayList<TaskItem> taskItemArrayList;

        /**
         * Provide a reference to the type of views that you are using
         * (custom ViewHolder)
         */
            public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
                private final TextView textViewName;
                private final ImageView ImageViewNameItem;
                public void onCreateContextMenu (ContextMenu menu, View
                    v, ContextMenu.ContextMenuInfo menuInfo){
                        menu.setHeaderTitle("具体操作");
                        menu.add(0, 0, this.getAdapterPosition(), "添加" + this.getAdapterPosition());
                        menu.add(0, 1, this.getAdapterPosition(), "删除" + this.getAdapterPosition());
                        menu.add(0, 2, this.getAdapterPosition(), "修改" + this.getAdapterPosition());
                    }
            public ViewHolder(View taskitemView) {
                        super(taskitemView);
                        // Define click listener for the ViewHolder's View
                        textViewName = taskitemView.findViewById(R.id.text_view_task_title);
                        ImageViewNameItem = taskitemView.findViewById(R.id.taskView_item1);
                taskitemView.setOnCreateContextMenuListener(this);
                    }

                    public TextView getTextViewName () {
                        return textViewName;
                    }
                    public ImageView getImageViewNameItem () {
                        return ImageViewNameItem;
                    }
                }


                /**
                 * Initialize the dataset of the Adapter
                 *
                 * @param dataSet String[] containing the data to populate views to be used
                 *                by RecyclerView
                 */
                public TaskItemAdapter(ArrayList<TaskItem> dataSet) {
                    taskItemArrayList = dataSet;
                }

                // Create new views (invoked by the layout manager)
                @Override
                public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
                    // Create a new view, which defines the UI of the list item
                    View view = LayoutInflater.from(viewGroup.getContext())
                            .inflate(R.layout.task_item_row, viewGroup, false);
                    return new ViewHolder(view);
                }


                // Replace the contents of a view (invoked by the layout manager)
                @Override
                public void onBindViewHolder(ViewHolder viewHolder, final int position) {

                    // Get element from your dataset at this position and replace the
                    // contents of the view with that element
                    viewHolder.getTextViewName().setText(taskItemArrayList.get(position).getName());
                    viewHolder.getImageViewNameItem().setImageResource(taskItemArrayList.get(position).getImageId());
                }

                // Return the size of your dataset (invoked by the layout manager)
                @Override
                public int getItemCount() {
                    return taskItemArrayList.size();
                }
            }


//测试将taskitemadapter写这里
//
//
//
////
////        context_menu_adapter = new MyAdapter(dataList);
////        recyclerView.setAdapter(context_menu_adapter);
////        registerForContextMenu(recyclerView);
////
////        // 设置 RecyclerView 的布局管理器
////        recyclerView.setLayoutManager(new LinearLayoutManager(this));
////
////        // 设置 RecyclerView 的长按监听
////        recyclerView.setOnLongClickListener(new View.OnLongClickListener() {
////            @Override
////            public boolean onLongClick(View view) {
////                // 在这里弹出上下文菜单
////                openContextMenu(view);
////                return true;
////            }
////        });
////
////
////
////
////    }
//
//
//
//
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



//    private final ActivityResultLauncher<Intent> addDataLauncher = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            result -> {
//                if (result.getResultCode() == Activity.RESULT_OK) {
//                    // 处理从另一个Activity返回的数据
//                    Intent data = result.getData();
//                    if (data != null) {
//                        // 获取从另一个Activity传回的数据
//                        String newData = data.getStringExtra("newData");
//
//                        // 将数据添加到RecyclerView的Adapter中
//                        context_menu_adapter.addData(newData);
//
//                        // 刷新RecyclerView
//                        context_menu_adapter.notifyDataSetChanged();
//                    }
//                }
//            }
//    );

//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.context_menu, menu);
//    }
//
//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        // 获取点击的项目在RecyclerView中的位置
////        int position = yourAdapter.getPosition();
//        int position = recyclerView.getChildAdapterPosition((View) item.getActionView());
//        if (item.getItemId() == R.id.menu_add) {
//            // 执行添加操作，跳转到另一个Activity
//            // 使用ActivityResultLauncher启动另一个Activity
////            addDataLauncher.launch(new Intent(MainActivity.this, AddItemActivity.class));
//            context_menu_adapter.addData("1");
//            return true;
//        } else if (item.getItemId() == R.id.menu_edit) {
//            // 执行修改操作，跳转到另一个Activity
////            // 传递要编辑的数据到另一个Activity
////            Intent editIntent = new Intent(MainActivity.this, EditItemActivity.class);
////            editIntent.putExtra("dataToEdit", context_menu_adapter.getData(position));
////            // 使用ActivityResultLauncher启动另一个Activity
////            editDataLauncher.launch(editIntent);
//            context_menu_adapter.editData(position, "2");
//            // 刷新RecyclerView
//            context_menu_adapter.notifyItemChanged(position);
//            return true;
//        } else if (item.getItemId() == R.id.menu_delete) {
//            // 执行删除操作
//            context_menu_adapter.deleteData(position);
//
//            // 刷新RecyclerView
//            context_menu_adapter.notifyItemRemoved(position);
//            context_menu_adapter.notifyItemRangeChanged(position, context_menu_adapter.getItemCount());
//            return true;
//        } else {
//            return super.onContextItemSelected(item);
//        }
//    }






}