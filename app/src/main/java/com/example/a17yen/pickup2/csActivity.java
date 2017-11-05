package com.example.a17yen.pickup2;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Random;

public class csActivity extends AppCompatActivity{
    TextView text;
    String [] elements;
    int index = 0;
    public Button button1;
    public Button button2;
    boolean isDone;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cs);
        text = findViewById(R.id.csBody);

        click();
        clickRandom();
        new csActivity.scrapeCS().execute();

    }

    public void clickRandom(){
        button2 = findViewById(R.id.randomizeButtonCS);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = guarenteedRandomize();
                text.setText(elements[n]);
            }
        });
    }

    public void click(){
        button1 = findViewById(R.id.csGenerate);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(elements[index].equals(" </span>")) { isDone = true; }
                if (!isDone) {
                    text.setText(elements[index]);
                    index++;
                }
                else{
                    text.setText("This is the end of CS Lines, check out our Chem Lines!!");
                }
            }
        });
    }

    public int guarenteedRandomize(){
        Random rand = new Random();
        int n = rand.nextInt(40)+1;
        return n;
    }

    public class scrapeCS extends AsyncTask<Void, Void, Void> {
        String words = "";
        @Override
        /*
        Web scraping algorithm. Had to be hard coded to a particular website because the task otherwise
        was too difficult to complete within the given amount of time. In the future, the algorithm will be able
        to scrape regardless of website.

        No return type. Scrapes data from websites and stores it inside a String array.
         */

        protected Void doInBackground(Void... params){

            try {
                org.jsoup.nodes.Document doc = Jsoup.connect("http://www.pickuplinesgalore.com/computer.html").get();
                Elements elems  = doc.getElementsByClass("paragraph-text-7");
                for(Element e: elems){
                    words +=e;
                }
                String[] stringArr = words.split("<br>\n {2,}<br>\n|<br>\n| {2,}|<br>");
                elements = new String[stringArr.length];
                int count = 0;
                for(int i = 0; i < stringArr.length; i++){
                    if(stringArr[i].charAt(0)=='<') {continue;}
                    if((!stringArr[i].equals(" "))) {
                        elements[count++] = stringArr[i];
                    }
                }



            }catch (Exception e) {e.printStackTrace();}

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
