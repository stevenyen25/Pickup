/*
This is the second View that users interact with.
It contains a TextView object and two Button objects.
Each Button object when clicked accesses the next view in relation to that object.
 */

package com.example.a17yen.pickup2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class choiceActivity extends AppCompatActivity {
    TextView text;
    public Button chemB;
    public Button csB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        clickChem();
        clickCS();
    }



    /*
    A simple onClick method that moves onto the chemActivity class.
     */
    public void clickChem(){
        chemB=(Button)findViewById(R.id.ChemButton);
        chemB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transitionChem = new Intent(choiceActivity.this,ChemActivity.class);
                startActivity(transitionChem);
            }
        });
    }

    /*
    A simple onClick method that moves onto the csActivity class.
     */
    public void clickCS(){
        csB=(Button)findViewById(R.id.CSButton);
        csB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transitionCS = new Intent(choiceActivity.this,csActivity.class);
                startActivity(transitionCS);
            }
        });
    }
}
