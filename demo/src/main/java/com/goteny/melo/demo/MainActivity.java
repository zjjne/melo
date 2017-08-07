package com.goteny.melo.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.goteny.melon.demo.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
//        String s = HttpTest.test();
//        Toast.makeText(this, "" + s, Toast.LENGTH_SHORT).show();
    }
}
