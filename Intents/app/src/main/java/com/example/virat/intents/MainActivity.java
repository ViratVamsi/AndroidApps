package com.example.virat.intents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void go(View view) {
        EditText editText=(EditText)findViewById(R.id.ed_id);
        String str=editText.getText().toString();
        Intent i=new Intent(MainActivity.this,SettingsActivity.class);
        i.putExtra("input",str);
        startActivity(i);
    }



}
