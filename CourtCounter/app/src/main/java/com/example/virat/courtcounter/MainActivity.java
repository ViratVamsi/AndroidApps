package com.example.virat.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    int a_points=0,b_points=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void a_increment(View view){
        int inc;
        if(view.getId()==R.id.points3_id){
            inc=3;
        }
        else if(view.getId()==R.id.points2_id){
            inc=2;
        }
        else
            inc=1;
        a_points+=inc;
        TextView points=(TextView)findViewById(R.id.a_points_id);
        points.setText(""+a_points);
    }
    public void b_increment(View view){
        int inc;
        if(view.getId()==R.id.b_points3_id){
            inc=3;
        }
        else if(view.getId()==R.id.b_points2_id){
            inc=2;
        }
        else
            inc=1;
        b_points+=inc;
        TextView points=(TextView)findViewById(R.id.b_points_id);
        points.setText(""+b_points);
    }
    public void reset(View view){
        a_points=0;
        b_points=0;
        TextView points=(TextView)findViewById(R.id.a_points_id);
        points.setText(""+a_points);
        TextView bpoints=(TextView)findViewById(R.id.b_points_id);
       bpoints.setText(""+b_points);
    }

    public void decrement(View view) {
        if(view.getId()==R.id.adecrement) {
            a_points -= 1;
            TextView points=(TextView)findViewById(R.id.a_points_id);
            points.setText(""+a_points);
        }
        else{
            b_points-=1;
            TextView points=(TextView)findViewById(R.id.b_points_id);
            points.setText(""+b_points);
        }
    }
}
