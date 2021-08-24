package com.bw.animationdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AnimationMainActivity extends AppCompatActivity {

    private Button animationBtn;
    private MyAnimationView animationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_main);


        initView();

        animationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationView.addImageView();
            }
        });
    }

    private void initView() {
        animationBtn = findViewById(R.id.animation_btn);
        animationView = findViewById(R.id.animation_view);
    }
}