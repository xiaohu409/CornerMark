package com.example.hutao.cornermark;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hutao.cornermark.CornerMarkView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private void initUI() {
        //初始化控件
        CornerMarkView toPayView = findViewById(R.id.to_pay_id);
        //设置控件的角标
        toPayView.setNum(4);
        //设置控件的点击事件
        toPayView.setOnClickListener(this);

        CornerMarkView returnBackView = findViewById(R.id.return_back_id);
        returnBackView.setNum(8);
        returnBackView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String text = ((CornerMarkView)v).getTitle();
        switch (v.getId()) {
            case R.id.to_pay_id:
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
                break;
            case R.id.return_back_id:
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
