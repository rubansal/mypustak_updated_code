package com.mypushtak.app.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mypushtak.app.R;

public class ProfileDetailsActivity extends AppCompatActivity {

    Button change_password;

    Toolbar mToolbar;
    //    private ImageView back_button;
    TextView mTextview;

    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);

        change_password = findViewById(R.id.details_change_password);
        mToolbar = findViewById(R.id.profile_details_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTextview = findViewById(R.id.change_password);
        mTextview.setText("PROFILE");
//        back_button=findViewById(R.id.navigationnn);


//        //*********************SENT TO PREVIOUS ACTIVITY*********************************************************************8
//        back_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                sendToBooksActivity();
//            }
//        });


        change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileDetailsActivity.this, ChangePassword.class);
                startActivity(i);
            }
        });
    }


    private void sendToBooksActivity() {
        Intent i = new Intent(ProfileDetailsActivity.this, Fictionbooks.class);
        startActivity(i);

    }
}
