package com.ringcentral.rcandroidsdkdemoapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.ringcentral.rc_android_sdk.rcsdk.*;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    Button button1;
    String appKey = "xhK3uzISTEaEYhFAtadVug";
    String appSecret = "1YRoPu64TeCOe_ZJy3ggLwGg-QDQd6QaWpSyIT8AxmjA";
    SDK SDK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SDK = new SDK(appKey, appSecret, "SANDBOX");
        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button1:
                Intent authIntent = new Intent(MainActivity.this, AuthActivity.class);
                authIntent.putExtra("MyRcsdk", SDK);
                startActivity(authIntent);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
