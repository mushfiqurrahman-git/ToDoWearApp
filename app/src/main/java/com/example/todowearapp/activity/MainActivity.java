package com.example.todowearapp.activity;

import android.os.Bundle;
import android.provider.SyncStateContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.todowearapp.R;
import com.example.todowearapp.databinding.ActivityMainBinding;
import com.example.todowearapp.model.Task;
import com.example.todowearapp.utils.Constants;
import com.example.todowearapp.utils.TaskUtils;
import com.example.todowearapp.utils.ConfirmUtils;
public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mainBinding.getRoot();
        setContentView(view);
        init();
    }
    private void init() {
        //setLayout();
        initTask();
    }
    private void initTask(){
        mainBinding.edtTask.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_SEND)
                {
                    String text = v.getText().toString();
                    if(!TextUtils.isEmpty(text))
                    {
                        Task task = createTask(null,text);
                        updateTask(task,Constants.ACTION_ADD);
                        v.setText("");
                        return true;
                    }
                }
                return false;
            }
        });

    }
    private Task createTask(String id,String task){
        if(id == null){
            id = String.valueOf(System.currentTimeMillis());
        }
        return new Task(id,task);
    }
    private void updateTask(Task task,int action){
        if(action == Constants.ACTION_ADD){
            TaskUtils.saveTask(task,this);
            ConfirmUtils.showSavedMessage(getString(R.string.task_saved),this);
        }
    }


}

