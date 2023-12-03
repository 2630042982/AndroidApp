package com.jnu.student;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import android.app.Activity;
//import android.app.AlertDialog;
import android.content.DialogInterface;
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
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.jnu.student.TaskItem;
import com.jnu.student.DataBank;


public class MainActivity extends AppCompatActivity {
//    private TextView textView1, textView2;
//    private Button chang_text_button;

//    private TaskItemAdapter taskItemAdapter;
//    private ArrayList<TaskItem> taskList=new ArrayList<>();
//    private String []tabHeaderStrings = {"Shopping items","baidu maps","News"};
    private String []tabHeaderStrings  = {"图书","地图","新闻"};
//    private MyAdapter adapter;
//    private MyAdapter context_menu_adapter;

//    private List<String> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recycleview);

        ViewPager2 viewPager = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        FragmentAdapter pagerAdapter = new FragmentAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager.setAdapter(pagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(tabHeaderStrings[position])
                // 设置TabLayout的标题
        ).attach();
    }

    private class FragmentAdapter extends FragmentStateAdapter {
        private static final int NUM_TABS = 3;

        public FragmentAdapter(FragmentManager fragmentManager, Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);

        }

        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new ShoppingListFragment();
                case 1:
                    return new TencentMapFragment();
                case 2:
                    return new WebViewFragment();
                default:
                    return null;
            }
        }

        public int getItemCount() {
            return NUM_TABS;
        }
    }
//        setContentView(R.layout.main_activity);
//        //交换button
//        textView1 = findViewById(R.id.textView1);
//        textView2 = findViewById(R.id.textView2);
//        chang_text_button = findViewById(R.id.chang_text_button);
//
//        RecyclerView mainRecyclerview = findViewById(R.id.recyclerView);
//        mainRecyclerview.setLayoutManager(new LinearLayoutManager(this));
//
//
//
//        // 添加一些示例数据
//        taskList = new DataBank().LoadTaskItems(MainActivity.this);
//        if(0==taskList.size()) {
//            taskList.add(new TaskItem("学高数", R.drawable.task_1));
//            taskList.add(new TaskItem("进行Android开发", R.drawable.task_0));
//            taskList.add(new TaskItem("吃饭", R.drawable.task_1));
//            taskList.add(new TaskItem("午休", R.drawable.task_0));
//            taskList.add(new TaskItem("继续进行Android开发", R.drawable.task_1));
//        }
//        // 创建适配器并设置给 RecyclerView
//        taskItemAdapter = new TaskItemAdapter(taskList);
//        mainRecyclerview.setAdapter(taskItemAdapter);
//
//        registerForContextMenu(mainRecyclerview);
//        addItemLauncher = registerForActivityResult(
//                new ActivityResultContracts.StartActivityForResult(),
//                result -> {
//                    if (result.getResultCode() == Activity.RESULT_OK) {
//                        Intent data = result.getData();
//
//                        String name = data.getStringExtra("name");
//                        taskList.add(new TaskItem(name,R.drawable.task_0));
////                        TaskItemAdapter.notifyItemInserted(taskList.size());
//                        new DataBank().SaveTaskItems(MainActivity.this,taskList);
//                        //获取返回的数据
//                        // 在这可以根据需要进行进一步处理
//                    } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
//
//                    }
//                }
//        );
//        updateItemLauncher= registerForActivityResult(
//                new ActivityResultContracts.StartActivityForResult(),
//                result -> {
//                    if (result.getResultCode() == Activity.RESULT_OK) {
//                        Intent data = result.getData();
//                        int position = data.getIntExtra("position",0);
//                        String name = data.getStringExtra("name");
//                        TaskItem bookItem = taskList.get(position);
//                        bookItem.setName(name);
//                        taskItemAdapter.notifyItemChanged(position);
//
//                        new DataBank().SaveTaskItems(MainActivity.this,taskList);
//
//                        //获取返回的数据//在这塑可以根据需要进行进一步处理
//                    } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
//
//                    }
//                }
//        );
//
//
//
//
//    }
//    ActivityResultLauncher<Intent> addItemLauncher;
//    ActivityResultLauncher<Intent> updateItemLauncher;
//    private static final int MENU_ITEM_ADD = 0;
//    private static final int MENU_ITEM_DELETE = 1;
//    private static final int MENU_ITEM_UPDATE = 2;
//    public boolean onContextItemSelected(MenuItem item) {
//        int position = item.getOrder();  // 获取被点击的项的位置
//        switch (item.getItemId()) {
//            case MENU_ITEM_ADD:
//                Intent intent = new Intent(MainActivity.this,TaskItemDetailsActivity.class);
//                addItemLauncher.launch(intent);
//                break;
//            case MENU_ITEM_DELETE:
//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setTitle("Delete Data");
//                builder.setMessage("Are you sure you want to delete this data?");
//                builder.setPositiveButton( "确定",new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog,int which) {
//                        taskList.remove(item.getOrder());
//                        taskItemAdapter.notifyItemRemoved(item.getOrder());
//
//
//                        new DataBank().SaveTaskItems(MainActivity.this,taskList);
//                    }
//
//                });
//                builder.setNegativeButton( "取消",new DialogInterface.OnClickListener() {
//
//                    public void onClick(DialogInterface dialog, int which) {}
//                });
//                builder.create().show();
//                break;
//            case MENU_ITEM_UPDATE:
//                Intent intentUpdate = new Intent(MainActivity.this,TaskItemDetailsActivity.class);
//                TaskItem bookItem = taskList.get(item.getOrder());
//                intentUpdate.putExtra("name",bookItem.getName());
//                intentUpdate.putExtra("position",item.getOrder());
//                updateItemLauncher.launch(intentUpdate);
//                break;
//            default:
//                return super.onContextItemSelected(item);
//        }
//        return true;
//    }


//    public class TaskItemAdapter extends RecyclerView.Adapter<TaskItemAdapter.ViewHolder> {
//
//        private ArrayList<TaskItem> taskItemArrayList;
//
//        /**
//         * Provide a reference to the type of views that you are using
//         * (custom ViewHolder)
//         */
//            public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
//                private final TextView textViewName;
//                private final ImageView ImageViewNameItem;
//                public void onCreateContextMenu (ContextMenu menu, View
//                    v, ContextMenu.ContextMenuInfo menuInfo){
//                        menu.setHeaderTitle("具体操作");
//                        menu.add(0, 0, this.getAdapterPosition(), "添加" + this.getAdapterPosition());
//                        menu.add(0, 1, this.getAdapterPosition(), "删除" + this.getAdapterPosition());
//                        menu.add(0, 2, this.getAdapterPosition(), "修改" + this.getAdapterPosition());
//                    }
//            public ViewHolder(View taskitemView) {
//                        super(taskitemView);
//                        // Define click listener for the ViewHolder's View
//                        textViewName = taskitemView.findViewById(R.id.text_view_task_title);
//                        ImageViewNameItem = taskitemView.findViewById(R.id.taskView_item1);
//                taskitemView.setOnCreateContextMenuListener(this);
//                    }
//
//                    public TextView getTextViewName () {
//                        return textViewName;
//                    }
//                    public ImageView getImageViewNameItem () {
//                        return ImageViewNameItem;
//                    }
//                }
//
//
//                /**
//                 * Initialize the dataset of the Adapter
//                 *
//                 * @param dataSet String[] containing the data to populate views to be used
//                 *                by RecyclerView
//                 */
//                public TaskItemAdapter(ArrayList<TaskItem> dataSet) {
//                    taskItemArrayList = dataSet;
//                }
//
//                // Create new views (invoked by the layout manager)
//                @Override
//                public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
//                    // Create a new view, which defines the UI of the list item
//                    View view = LayoutInflater.from(viewGroup.getContext())
//                            .inflate(R.layout.task_item_row, viewGroup, false);
//                    return new ViewHolder(view);
//                }
//
//
//                // Replace the contents of a view (invoked by the layout manager)
//                @Override
//                public void onBindViewHolder(ViewHolder viewHolder, final int position) {
//
//                    // Get element from your dataset at this position and replace the
//                    // contents of the view with that element
//                    viewHolder.getTextViewName().setText(taskItemArrayList.get(position).getName());
//                    viewHolder.getImageViewNameItem().setImageResource(taskItemArrayList.get(position).getImageId());
//                }
//
//                // Return the size of your dataset (invoked by the layout manager)
//                @Override
//                public int getItemCount() {
//                    return taskItemArrayList.size();
//                }
//            }


//    public void changeText(View view) {
//        // 交换两个TextView的文本
//        String text1 = textView1.getText().toString();
//        String text2 = textView2.getText().toString();
//        textView1.setText(text2);
//        textView2.setText(text1);
//
//        // 显示交换成功的Toast
//        Toast.makeText(this, "交换成功", Toast.LENGTH_SHORT).show();
//
//        // 显示交换成功的AlertDialog
//        new AlertDialog.Builder(this)
//                .setTitle("交换成功")
//                .setMessage("文本已成功交换！")
//                .setPositiveButton("确定", null)
//                .show();
//    }



}