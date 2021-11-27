package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter  extends  RecyclerView.Adapter<Adapter.TaskViewHolder>{
    List<TaskModel> allTasks = new ArrayList<TaskModel>();

    public Adapter(List<TaskModel> allTasks) {
        this.allTasks = allTasks;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_blank, parent,false);
        TaskViewHolder taskViewHolder= new TaskViewHolder(view);
        return taskViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {

        holder.taskModel= allTasks.get(position);
        TextView title = holder.itemView.findViewById(R.id.Title);
        TextView body = holder.itemView.findViewById(R.id.Body);;
        TextView state= holder.itemView.findViewById(R.id.State);

        title.setText(holder.taskModel.title);
        body.setText(holder.taskModel.body);
        state.setText(holder.taskModel.state);





    }

    @Override
    public int getItemCount() {
        return allTasks.size();
    }


    public static class TaskViewHolder extends RecyclerView.ViewHolder{

        public TaskModel taskModel;

        public View itemView;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView= itemView;
        }
    }
}
