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

public class ChemActivity extends AppCompatActivity{
    String [] elements;
    TextView text;
    public Button button;
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chem);
        text = (TextView)findViewById(R.id.chemBody);

        new ChemActivity.scrapeChem().execute();
        click();




    }

    public void click(){
        button = findViewById(R.id.chemGenerate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText(""+elements[index]);
                index++;
            }
        });
    }


    public class scrapeChem extends AsyncTask<Void, Void, Void>{
        String words = "";
        @Override
        protected Void doInBackground(Void... params){

            try {
                org.jsoup.nodes.Document doc = Jsoup.connect("http://pickuplinesguru.com/chemistry/").get();
                Elements elems  = doc.getElementsByClass("lines");

                for(Element e: elems){
                    words +=e;
                }
                String[] stringArr = words.split("<li>");
                words="";
                for(String s: stringArr){
                    words +=s;
                }
                stringArr = words.split("</li>");
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
