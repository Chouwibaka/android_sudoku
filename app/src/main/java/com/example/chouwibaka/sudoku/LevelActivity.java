package com.example.chouwibaka.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by ChouWiBaka on 04/02/2018.
 */

public class LevelActivity extends Activity implements View.OnClickListener {

    Button lvl1;
    Button lvl2;
    Button lvl3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        lvl1 = (Button) findViewById(R.id.btLvl1);
        lvl1.setOnClickListener(this);
        lvl2 = (Button) findViewById(R.id.btLvl2);
        lvl2.setOnClickListener(this);
        lvl3 = (Button) findViewById(R.id.btLvl3);
        lvl3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        Bundle objetBundle = new Bundle();

        if(view == lvl1){
            objetBundle.putString("lvl", "1");
        }
        else if(view == lvl2){
            objetBundle.putString("lvl", "2");
        }
        else if(view == lvl3){
            objetBundle.putString("lvl", "3");
        }

        intent.putExtras(objetBundle);
        this.startActivity(intent);
    }
}
