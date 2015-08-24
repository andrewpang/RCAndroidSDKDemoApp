package com.ringcentral.rcandroidsdkdemoapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.ringcentral.rc_android_sdk.rcsdk.SDK;
import com.ringcentral.rc_android_sdk.rcsdk.platform.Helpers;


public class OptionsActivity extends ActionBarActivity implements View.OnClickListener{

    Button button1, button2;
    SDK sdk;
    Helpers helpers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        Intent intent = getIntent();
        sdk = (SDK) intent.getSerializableExtra("MyRcsdk");
        helpers = sdk.getHelpers();
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button1:
                Intent optionsIntent = new Intent(OptionsActivity.this, CallStatusActivity.class);
                optionsIntent.putExtra("MyRcsdk", sdk);
                startActivity(optionsIntent);
                break;

            case R.id.button2:
                Intent logIntent = new Intent(OptionsActivity.this, CallLogActivity.class);
                logIntent.putExtra("MyRcsdk", sdk);
                startActivity(logIntent);
                break;
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_options, menu);
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
