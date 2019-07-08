package com.example.demointernship;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;

public class Activity2 extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try {
            JSONObject jsonObj = new JSONObject(getIntent().getStringExtra("Product"));

            System.out.println(jsonObj);
            TextView textView = findViewById(R.id.textView4);
            textView.setText(jsonObj.getString("message"));
            getSupportActionBar().setTitle(jsonObj.getString("title"));
            imageView = findViewById(R.id.imageView4);
            Picasso.get().load(jsonObj.getString("url")).into(imageView);
        } catch (JSONException e) {
        }

    }

    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }

}
