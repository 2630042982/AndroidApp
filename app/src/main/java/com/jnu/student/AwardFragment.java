
package com.jnu.student;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class AwardFragment extends Fragment {
    private ViewPager2 taskViewPager;
    private TabLayout taskTabLayout;
    public static TextView coinsTextView;
    private RecyclerView mainRecyclerView;

    public AwardFragment() {
        // Required empty public constructor
    }

    public static AwardFragment newInstance() {
        AwardFragment fragment = new AwardFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_daily_task_list, container, false);

        RecyclerView mainRecyclerview = rootview.findViewById(R.id.recycler_view);// 创建布局管理器
        mainRecyclerview.setLayoutManager(new LinearLayoutManager(requireActivity()));

        //定义一个Arraylist
        taskList = new ArrayList<>();

        taskList = new DataBank_reward().LoadTaskItems(requireActivity());//静态
        taskItemAdapter = new TaskItemAdapter(taskList);
        mainRecyclerview.setAdapter(taskItemAdapter);

        registerForContextMenu(mainRecyclerview);

        addItemLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();

                        String name = data.getStringExtra("name");
                        int score = data.getIntExtra("score",0);
                        taskList.add(new TaskItem(name, R.drawable.task_1,score));
                        taskItemAdapter.notifyItemInserted(taskList.size());


                        new DataBank_reward().SaveTaskItems(requireActivity(), taskList);

                        //获取返回的数据//在这塑可以根据需要进行进一步处理
                    } else if (result.getResultCode() == Activity.RESULT_CANCELED) {

                    }
                }
        );
        updateItemLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        int position = data.getIntExtra("position", 0);
                        int score = data.getIntExtra("score",0);
                        String name = data.getStringExtra("name");
                        TaskItem taskItem = taskList.get(position);
                        taskItem.setName(name);
                        taskItem.setScore(score);
                        taskItemAdapter.notifyItemChanged(position);

                        new DataBank_reward().SaveTaskItems(requireActivity(), taskList);

                        //获取返回的数据//在这塑可以根据需要进行进一步处理
                    } else if (result.getResultCode() == Activity.RESULT_CANCELED) {

                    }
                }
        );
        coinsTextView = rootview.findViewById(R.id.textView_coins);
        // 获取传递过来的总和
        int totalScore = 0;
        scoreList = new DataBank_total().LoadTaskItems(requireActivity());
        for (ScoreList score : scoreList) {
            totalScore += score.getScore();
        }
        if (Coins.coins < 0) {
            coinsTextView.setTextColor(getResources().getColor(R.color.light_red, requireContext().getTheme()));
        }
        else {
            coinsTextView.setTextColor(getResources().getColor(R.color.black, requireContext().getTheme()));
        }
//        coinsTextView.setText(String.valueOf(Coins.coins));
        coinsTextView.setText(String.valueOf(totalScore));
        return rootview;
    }

    //将 taskItems 和 taskItemAdapter 定义为类的成员变量
    private ArrayList<TaskItem> taskList = new ArrayList<>();
    private ArrayList<ScoreList> scoreList = new ArrayList<>();
    private TaskItemAdapter taskItemAdapter;
    ActivityResultLauncher<Intent> addItemLauncher;
    ActivityResultLauncher<Intent> updateItemLauncher;
    private static final int MENU_ITEM_ADD = 0;
    private static final int MENU_ITEM_DELETE = 1;
    private static final int MENU_ITEM_UPDATE = 2;
    private static final int MENU_ITEM_FINISH = 3;

    public boolean onContextItemSelected(MenuItem item) {
        int position = item.getOrder();  // 获取被点击的项的位置
        switch (item.getItemId()) {
            case MENU_ITEM_ADD:

                Intent intent = new Intent(requireActivity(), TaskItemDetailsActivity.class);
                addItemLauncher.launch(intent);
                break;

            case MENU_ITEM_DELETE:
                AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
                builder.setTitle("Delete Data");
                builder.setMessage("Are you sure you want to delete this data?");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        taskList.remove(item.getOrder());
                        taskItemAdapter.notifyItemRemoved(item.getOrder());


                        new DataBank_reward().SaveTaskItems(requireActivity(), taskList);
                    }

                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.create().show();
                break;
            case MENU_ITEM_UPDATE:
                Intent intentUpdate = new Intent(requireActivity(), TaskItemDetailsActivity.class);
                TaskItem taskItem = taskList.get(item.getOrder());
                intentUpdate.putExtra("name", taskItem.getName());
                intentUpdate.putExtra("score", taskItem.getscore());
                intentUpdate.putExtra("position", item.getOrder());
                updateItemLauncher.launch(intentUpdate);
                break;
            case MENU_ITEM_FINISH:
                //以下为扣除积分
                scoreList = new DataBank_total().LoadTaskItems(requireActivity());
                scoreList.add(new ScoreList(taskList.get(item.getOrder()).getName(),-taskList.get(item.getOrder()).getscore(),System.currentTimeMillis()));
                new DataBank_total().SaveTaskItems(requireActivity(), scoreList);
                //以下为删除任务
                taskList.remove(item.getOrder());
                taskItemAdapter.notifyItemRemoved(item.getOrder());


                new DataBank_reward().SaveTaskItems(requireActivity(), taskList);
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }
    public void onViewCreated( View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 获取Button对象
        Button myButton = view.findViewById(R.id.menu_add);

        // 为Button设置点击事件监听器
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), TaskItemDetailsActivity.class);
                addItemLauncher.launch(intent);
            }
        });
    }
    public class TaskItemAdapter extends RecyclerView.Adapter<TaskItemAdapter.ViewHolder> {

        private ArrayList<TaskItem> taskItemArrayList;

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
            private final TextView textViewName;
            private final ImageView ImageViewNameItem;
            private final TextView textViewScore;

            public void onCreateContextMenu(ContextMenu menu, View
                    v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.setHeaderTitle("具体操作");
                menu.add(0, 0, this.getAdapterPosition(), "添加" + this.getAdapterPosition());
                menu.add(0, 1, this.getAdapterPosition(), "删除" + this.getAdapterPosition());
                menu.add(0, 2, this.getAdapterPosition(), "修改" + this.getAdapterPosition());
                menu.add(0, 3, this.getAdapterPosition(), "完成" + this.getAdapterPosition());
            }

            public ViewHolder(View taskitemView) {
                super(taskitemView);
                // Define click listener for the ViewHolder's View
                textViewName = taskitemView.findViewById(R.id.text_view_task_title);
                textViewScore = taskitemView.findViewById(R.id.text_view_task_score);
                ImageViewNameItem = taskitemView.findViewById(R.id.taskView_item1);
                taskitemView.setOnCreateContextMenuListener(this);
            }

            public TextView getTextViewName() { return textViewName; }
            public TextView getTextViewScore(){ return textViewScore; }
            public ImageView getImageViewNameItem() { return ImageViewNameItem; }
        }


        public TaskItemAdapter(ArrayList<TaskItem> dataSet) { taskItemArrayList = dataSet; }

        // Create new views (invoked by the layout manager)
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            // Create a new view, which defines the UI of the list item
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.task_item_row, viewGroup, false);
            return new ViewHolder(view);
        }


        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int position) {

            viewHolder.getTextViewName().setText(taskItemArrayList.get(position).getName());
            viewHolder.getTextViewScore().setText('-'+Integer.toString(taskItemArrayList.get(position).getscore()));
            viewHolder.getImageViewNameItem().setImageResource(taskItemArrayList.get(position).getImageId());
        }

        @Override
        public int getItemCount() {
            return taskItemArrayList.size();
        }
    }
}