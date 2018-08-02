package org.zhx.rounprogress;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private RoundProgress progress;
    CountDownTimer timer;
    private int max=50*1000;
    private boolean isprogress=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress=findViewById(R.id.roundProgress);
        progress.setMax(max);
        startTask();
        findViewById(R.id.restart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isprogress)
                startTask();
                else
                    Toast.makeText(MainActivity.this, "加载中....", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 模拟进度加载
     */
    private void startTask() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        timer = new CountDownTimer(max, 500) {
            @Override
            public void onTick(long millisUntilFinished) {
                isprogress=true;
               progress.setProgress((int)(max-millisUntilFinished));
            }

            @Override
            public void onFinish() {
                progress.setProgress(max);
                isprogress=false;
            }
        };
        timer.start();
        isprogress=true;
    }
}
