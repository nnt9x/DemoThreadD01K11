package com.bkacad.nnt.demothreadd01k11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnRunTask;
    private TextView tvCount;

    private TaskThread taskThread;

    // Tạo thêm luồng 2 bằng  runable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRunTask = findViewById(R.id.btn_main_run_task);
        tvCount = findViewById(R.id.tv_main_count);
        // Tạo luồng
        taskThread = new TaskThread();

        btnRunTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khởi chạy luồng
                taskThread.start();
            }
        });
        // Truyền dữ liệu giữa luồng phụ và luồng chính (Main Thread UI thread) ??

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 100; i <=200; i++){
                    try {
                        Thread.sleep(1000);
                        Log.d("thread 2", String.valueOf(i));
//                        tvCount.setText(String.valueOf(i));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}