/*
This is the first View that users interact with.
It's only function is to move onto the second View.
 */
package com.example.a17yen.pickup2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    public Button buttonFirst;

    /*
    A simple onClick method that moves onto the second choiceActivity class.
     */
    public void click1(){
        buttonFirst=(Button)findViewById(R.id.buttonFirst);
        buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transition1 = new Intent(MainActivity.this,choiceActivity.class);
                startActivity(transition1);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        click1();
    }

}
