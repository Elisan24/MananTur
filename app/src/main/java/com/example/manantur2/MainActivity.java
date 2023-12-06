package com.example.manantur2;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        Thread thread = new Thread() {
            @Override
            public void run(){
                try {
                    SystemClock.sleep(3000);
                    startActivity(new Intent(MainActivity.this, MainActivity2.class));
                    finish();
                }catch (Exception e){

                }
            }
        };thread.start();
    }
    public void abreActivity2(View view) {

        Intent intent = new Intent(view.getContext(), MainActivity2.class);
        view.getContext().startActivity(intent);

    }



}