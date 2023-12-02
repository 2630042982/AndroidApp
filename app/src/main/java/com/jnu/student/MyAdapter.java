//package com.jnu.student;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.PopupMenu;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
//public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
//
//    private List<String> dataList;
//    private Context context;
//
//    public MyAdapter(List<String> dataList) {
//        this.dataList = dataList;
//    }
//
//    // 创建 ViewHolder
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
//        return new MyViewHolder(view);
//    }
//
//    // 绑定数据到 ViewHolder
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        String data = dataList.get(position);
//        holder.textViewItem.setText(data);
//        // 设置长按监听器
//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                showContextMenu(view, position);
//                return true;
//            }
//        });
//    }
//
//    // 返回数据项数量
//    @Override
//    public int getItemCount() {
//        return dataList.size();
//    }
//
////    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
////        this.onItemClickListener = onItemClickListener;
////    }
//
//
//
//
//
//    // 弹出菜单的方法
//    private void showContextMenu(View view, final int position) {
//        PopupMenu popupMenu = new PopupMenu(context, view);
//        MenuInflater inflater = popupMenu.getMenuInflater();
//        inflater.inflate(R.menu.context_menu, popupMenu.getMenu());
//        // 设置菜单项点击
//        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem menuItem) {
//                if (menuItem.getItemId() == R.id.menu_add) {
//                    Log.d("MyAdapter", "menu_add");
//                } else if (menuItem.getItemId() == R.id.menu_edit) {
//                    Log.d("MyAdapter", "menu_edit");
//            } else if (menuItem.getItemId() == R.id.menu_delete) {
//                    Log.d("MyAdapter", "menu_delete");
//            }
//                return true;
//            }
//        });
//
//        // 显示菜单
//        popupMenu.show();
//    }
//
//
//        // 添加数据
//    public void addData(String newData) {
//        dataList.add(newData);
//        notifyItemInserted(dataList.size() - 1);
//    }
//
//    // 修改数据
//    public void editData(int position, String newData) {
//        if (position >= 0 && position < dataList.size()) {
//            dataList.set(position, newData);
//            notifyItemChanged(position);
//        }
//    }
//    // 删除数据
//    public void deleteData(int position) {
//        if (position >= 0 && position < dataList.size()) {
//            dataList.remove(position);
//            notifyItemRemoved(position);
//            notifyItemRangeChanged(position, dataList.size());
//        }
//    }
//    // 获取位置
//    public int getPosition(String item) {
//        return dataList.indexOf(item);
//    }
//
//
//    public static class MyViewHolder extends RecyclerView.ViewHolder {
//        TextView textViewItem;
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            textViewItem = itemView.findViewById(R.id.textViewItem);
//        }
//    }
//}
//
