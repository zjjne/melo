package com.goteny.melo.demo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.goteny.melo.demo.bean.GitHubIssue;
import com.goteny.melo.demo.bean.GitHubMain;
import com.goteny.melo.demo.http.DemoHttpUtil;
import com.goteny.melo.http.interfaces.HttpCallback;
import com.goteny.melo.utils.log.LogMelo;
import com.goteny.melo.demo.R;

import java.util.List;


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
        LogMelo.i(getClass().getSimpleName(), "onClick()");


        DemoHttpUtil.getInstance().fetchIssues("closed").callback(new HttpCallback<List<GitHubIssue>>()
        {
            @Override
            public void onSuccess(List<GitHubIssue> response)
            {
                LogMelo.i(getClass().getSimpleName(), "onSuccess(List<GitHubIssue> r): ");
                Toast.makeText(MainActivity.this, "onSuccess(List<GitHubIssue> r)", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable t)
            {
                LogMelo.i(getClass().getSimpleName(), "onFailure(Throwable t): " + t, t);
                Toast.makeText(MainActivity.this, "onFailure(Throwable t)", Toast.LENGTH_SHORT).show();
            }
        }).execute();



        DemoHttpUtil.getInstance().fetchMain().callback(new HttpCallback<GitHubMain>()
        {
            @Override
            public void onSuccess(GitHubMain response)
            {
                LogMelo.i(getClass().getSimpleName(), "onSuccess(List<GitHubMain> r): ");
                Toast.makeText(MainActivity.this, "onSuccess(List<GitHubMain> r)", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable t)
            {
                LogMelo.i(getClass().getSimpleName(), "onFailure(Throwable t): " + t, t);
                Toast.makeText(MainActivity.this, "onFailure(Throwable t)", Toast.LENGTH_SHORT).show();
            }
        }).execute();


        DemoHttpUtil.getInstance().testHost().execute();

        DemoHttpUtil.getInstance().testHeader().execute();

        DemoHttpUtil.getInstance().testHeader2().execute();

        DemoHttpUtil.getInstance().fetchIssues("closed").execute();

    }
}
