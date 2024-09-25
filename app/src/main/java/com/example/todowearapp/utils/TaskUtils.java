package com.example.todowearapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.todowearapp.model.Task;

public class TaskUtils {
    public static void saveTask(Task task, Context context) {
        if (task != null) {
            SharedPreferences sharedPref = context.getSharedPreferences("task_details",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor= sharedPref.edit();
            editor.putString(task.getId(),task.getTaskDetails());
            editor.commit();
        }
    }
}
