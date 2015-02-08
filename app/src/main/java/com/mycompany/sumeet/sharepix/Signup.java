package com.mycompany.sumeet.sharepix;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sumeet on 2/6/2015.
 */
public class Signup extends Activity {

    private EditText editText_firstName, editText_lastName, editText_emailAddress, editText_password;
    private Uri imageUri = Uri.parse("android.resource://com.mycompany.sumeet.sharepix/drawable/default_user_image.png");
    private Button signUPButton;
    HttpResponse responsePOST;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editText_firstName = (EditText) findViewById(R.id.editText_firstName);
        editText_lastName = (EditText) findViewById(R.id.editText_lastName);
        editText_emailAddress = (EditText) findViewById(R.id.editText_emailAddress);
        editText_password = (EditText) findViewById(R.id.editText_password);

        signUPButton = (Button) findViewById(R.id.button_signup_submit);
        signUPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LongOperation().execute("");
            }

            class LongOperation extends AsyncTask<String, Void, String> {

                @Override
                protected String doInBackground(String... params) {
                    //dbHandler.createAccount(account);
                    try {
                        HttpClient client = new DefaultHttpClient();
                        String postURL = "http://mtljobs.com/first/test/signUp";
                        HttpPost post = new HttpPost(postURL);
                        List<NameValuePair> userDetailsList = new ArrayList<NameValuePair>();
                        userDetailsList.add(new BasicNameValuePair("FirstName", editText_firstName.getText().toString()));
                        userDetailsList.add(new BasicNameValuePair("LastName", editText_lastName.getText().toString()));
                        userDetailsList.add(new BasicNameValuePair("Email", editText_emailAddress.getText().toString()));
                        userDetailsList.add(new BasicNameValuePair("Password", editText_password.getText().toString()));
                        UrlEncodedFormEntity ent = new UrlEncodedFormEntity(userDetailsList, HTTP.UTF_8);
                        post.setEntity(ent);
                        responsePOST = client.execute(post);
                        Log.i("RESPONSE", responsePOST.toString());
                        HttpEntity resEntity = responsePOST.getEntity();
                        if (resEntity != null) {
                            Log.i("RESPONSE", EntityUtils.toString(resEntity));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return "Executed";
                }

                @Override
                protected void onPostExecute(String result) {
                    //Toast.makeText(Signup.this, " Account Created ", Toast.LENGTH_SHORT).show();
                    // might want to change "executed" for the returned string passed
                    // into onPostExecute() but that is upto you

                    //startActivity(new Intent(Signup.this, HomePage.class));
                }

                @Override
                protected void onPreExecute() {
                    //pass intent to home page

                }

                @Override
                protected void onProgressUpdate(Void... values) {}
            }
        });
    }
}