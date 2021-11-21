package com.example.myapplication;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter  extends  RecyclerView.Adapter<Adapter.TaskViewHolder>{
    List<Task> allTasks = new ArrayList<Task>();
    public OnNoteListener mOnNoteListener;

    public Adapter(List<Task> allTasks, OnNoteListener onNoteListener) {
        this.allTasks = allTasks;
        this.mOnNoteListener= onNoteListener;
    }
    public static class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public Task task;
        public View itemView;
        public OnNoteListener onNoteListener;

        public TaskViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);

            itemView.setOnClickListener(this);
            this.onNoteListener = onNoteListener;
            this.itemView = itemView;
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition(),task);
        }
    }


    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_blank, parent,false);
        TaskViewHolder taskViewHolder= new TaskViewHolder(view, mOnNoteListener);
        return taskViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {

        holder.task = allTasks.get(position);
        TextView title = holder.itemView.findViewById(R.id.fragmentTitle);
        TextView body = holder.itemView.findViewById(R.id.body);;
        TextView state= holder.itemView.findViewById(R.id.state);

        title.setText(holder.task.getTitle());
        body.setText(holder.task.getBody());
        state.setText(holder.task.getState());





    }

    @Override
    public int getItemCount() {
        return allTasks.size();
    }



public interface OnNoteListener{
    void onNoteClick(int position,Task task);
}

}
