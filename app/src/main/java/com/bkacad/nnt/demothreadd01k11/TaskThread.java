package com.bkacad.nnt.demothreadd01k11;

import android.util.Log;

public class TaskThread extends  Thread{

    // Khi đè lại phương thức run

    @Override
    public void run() {
        super.run();
        for(int i = 1; i <= 100; i++){
            try {
                Thread.sleep(1000);
                Log.d("i", " "+i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
