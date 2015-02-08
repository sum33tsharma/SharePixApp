package com.mycompany.sumeet.sharepix;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
 * Created by Sumeet on 2/7/2015.
 */
public class Login extends Activity {

    private EditText editText_emailAddress, editText_password;
    private Button loginButton;
    HttpResponse responsePOST;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText_emailAddress = (EditText) findViewById(R.id.editText_emailAddress);
        editText_password = (EditText) findViewById(R.id.editText_password);

        loginButton = (Button) findViewById(R.id.button_login_submit);

        loginButton.setOnClickListener(new View.OnClickListener() {
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
                        String postURL = "http://mtljobs.com/first/test/login";
                        HttpPost post = new HttpPost(postURL);
                        List<NameValuePair> userDetailsList = new ArrayList<NameValuePair>();
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
