package com.ringcentral.rcandroidsdkdemoapp;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.ringcentral.rc_android_sdk.rcsdk.SDK;
import com.ringcentral.rc_android_sdk.rcsdk.http.Transaction;
import com.ringcentral.rc_android_sdk.rcsdk.platform.Helpers;
import com.ringcentral.rc_android_sdk.rcsdk.subscription.Subscription;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class CallLogActivity extends ActionBarActivity {

    SDK sdk;
    Helpers helpers;
    Subscription subscription;
    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_log);
        Intent intent = getIntent();
        sdk = (SDK) intent.getSerializableExtra("MyRcsdk");
        helpers = sdk.getHelpers();
        subscription = helpers.getSubscription();
        textView1 = (TextView) findViewById(R.id.textView1);

        helpers.callLog(
                new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        if (!response.isSuccessful())
                            throw new IOException("Unexpected code " + response);
                        Transaction transaction = new Transaction(response);
                        String responseString = "";
                        String body = transaction.getBodyString();
                        try {
                            JSONObject jsonObject = new JSONObject(body);
                            JSONArray records = jsonObject.getJSONArray("records");
                            for (int i = 0; i < records.length(); i++) {
                                JSONObject record = records.getJSONObject(i);
                                JSONObject to = record.getJSONObject("to");
                                responseString += "To: " + to.getString("phoneNumber") + "                                       ";
                                responseString += "Duration: " + record.getString("duration");
                                responseString += "\n";
                                JSONObject from = record.getJSONObject("from");
                                responseString += "From: " + from.getString("phoneNumber") + " ";
                                responseString += "\n\n";
                            }
                            System.out.println(body);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
//                        Message msg = handler.obtainMessage();
//                        msg.what = 1;
//                        msg.obj = responseString;
//                        handler.sendMessage(msg);

                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_call_log, menu);
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
