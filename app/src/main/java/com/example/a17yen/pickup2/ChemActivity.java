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
    public String [] elements;
    public TextView text;
    public Button button;
    public int index = 0;
    boolean isDone = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chem);
        text = (TextView)findViewById(R.id.chemBody);

        click();
        new ChemActivity.scrapeChem().execute();
    }

    public void click(){
        button = findViewById(R.id.chemGenerate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(elements[index].equals(" </span>")) { isDone = true; }
                if (!isDone) {
                    text.setText(elements[index]);
                    index++;
                }
                else{
                    text.setText("This is the end of BioChem Lines, check out our CompSci Lines!!");
                }
            }
        });
    }


    public class scrapeChem extends AsyncTask<Void, Void, Void>{
        String words = "";
        @Override
        protected Void doInBackground(Void... params){

            try {
                org.jsoup.nodes.Document doc = Jsoup.connect("http://www.pickuplinesgalore.com/biochem.html").get();
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
