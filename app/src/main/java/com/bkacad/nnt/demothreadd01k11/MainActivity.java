package com.bkacad.nnt.demothreadd01k11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnRunTask;
    private TextView tvCount;
    private BroadcastReceiver broadcastReceiver;
    private IntentFilter intentFilter;
    private TaskThread taskThread;
    private static final String MSG_FROM_THREAD_2 = "bkacad";

    // Tạo thêm luồng 2 bằng  runable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRunTask = findViewById(R.id.btn_main_run_task);
        tvCount = findViewById(R.id.tv_main_count);

        // Khai báo broadcast
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getAction()){
                    case MSG_FROM_THREAD_2:
                        // Xử lý sau
                        tvCount.setText(""+ intent.getIntExtra("count", -1));
                        break;
                }
            }
        };
        intentFilter = new IntentFilter();
        intentFilter.addAction(MSG_FROM_THREAD_2);


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
                        // Tạo ra bản tin broadcast ...
                        Intent intent = new Intent();
                        intent.setAction(MSG_FROM_THREAD_2);
                        intent.putExtra("count", i);
                        sendBroadcast(intent);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }
}