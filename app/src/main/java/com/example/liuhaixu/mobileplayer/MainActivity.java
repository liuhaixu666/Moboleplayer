package com.example.liuhaixu.mobileplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private RadioGroup rg_bottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg_bottom = (RadioGroup) findViewById(R.id.rg_bottom_tag);
        rg_bottom.check(R.id.rb_video);
    }
}
