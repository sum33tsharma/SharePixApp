package com.mycompany.sumeet.sharepix;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Sumeet on 2/7/2015.
 */
public class HomePage extends Activity {

    Button homePageButton, searchButton, cameraButton, profileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homePageButton = (Button)findViewById(R.id.button_home);
        searchButton = (Button)findViewById(R.id.button_search);
        cameraButton  = (Button)findViewById(R.id.button_camera);
        profileButton = (Button)findViewById(R.id.button_profile);

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, MyCamera.class));
            }
        });
    }
}
