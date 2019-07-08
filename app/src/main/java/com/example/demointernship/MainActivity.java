package com.example.demointernship;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ExampleAdapter.ExampleViewHolder.OnExampleListener {

    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<ExampleItem> exampleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Demo Internship");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        ArrayList<ExampleItem> exampleList = new ArrayList<>();
        exampleList.add(new ExampleItem("Obj1","Message1","https://cdn0.iconfinder.com/data/icons/grocery-store-2/100/14-512.png"));
        exampleList.add(new ExampleItem("Obj2","Message2","https://cdn2.iconfinder.com/data/icons/animal-fill-icons-set/144/Bear-512.png"));
        exampleList.add(new ExampleItem("Obj3","Is your layout a relative layout? if the layout is defined as LinerLayout you won't be able to drap and drop.","https://cdn2.iconfinder.com/data/icons/black-animal-svg-icons/512/monkey_face_animal_ape_donkey-512.png"));

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(exampleList, this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = findViewById(R.id.floatingActionButton3);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }

    public void openDialog() {
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(),"Example dialog");
    }

    public void openActivity2(int position) {
        Intent intent = new Intent(this, Activity2.class);
        JSONObject obj;
        ExampleItem currentItem = exampleList.get(position);
        obj = currentItem.getObj();

        intent.putExtra("Product", obj.toString());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onExampleClick(int position) {
        openActivity2(position);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case 121:
                mAdapter.removeItem(item.getGroupId());
                displayMessage("Item Deleted!!");
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    private void displayMessage(String message){
        Snackbar.make(findViewById(R.id.rootView),message,Snackbar.LENGTH_SHORT).show();
    }
}
