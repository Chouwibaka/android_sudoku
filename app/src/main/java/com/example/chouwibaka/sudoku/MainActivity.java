package com.example.chouwibaka.sudoku;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    Grille g;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        g = (Grille) findViewById(R.id.grille);

        Bundle objetbundle = this.getIntent().getExtras();
        switch(objetbundle.getString("lvl")){
            case "1":
                g.values = "001700509573024106800501002700295018009400305652800007465080071000159004908007053";
                break;
            case "2":
                g.values = "058004021060853007039020005800001006003700210106082500670200180900400050080916702";
                break;
            case "3":
                g.values = "034060901700012680080009000023050790007020005500078030010590000000000413078130020";
                break;
            default:
                g.values = "001700509573024106800501002700295018009400305652800007465080071000159004908007053";
                break;
        }


        Button b1 = (Button) findViewById(R.id.bt1);
        b1.setOnClickListener(this);
        Button b2 = (Button) findViewById(R.id.bt2);
        b2.setOnClickListener(this);
        Button b3 = (Button) findViewById(R.id.bt3);
        b3.setOnClickListener(this);
        Button b4 = (Button) findViewById(R.id.bt4);
        b4.setOnClickListener(this);
        Button b5 = (Button) findViewById(R.id.bt5);
        b5.setOnClickListener(this);
        Button b6 = (Button) findViewById(R.id.bt6);
        b6.setOnClickListener(this);
        Button b7 = (Button) findViewById(R.id.bt7);
        b7.setOnClickListener(this);
        Button b8 = (Button) findViewById(R.id.bt8);
        b8.setOnClickListener(this);
        Button b9 = (Button) findViewById(R.id.bt9);
        b9.setOnClickListener(this);
        Button bX = (Button) findViewById(R.id.btSupp);
        bX.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(g.caseSelect != null){
            Button b = (Button) v;
            String txt = b.getText().toString();

        if(g.caseSelect.numsudok.equals(""))
            if (txt.equals("X")) {
                g.caseSelect.numgiven = "";
            }
            else{
                g.caseSelect.numgiven = txt;
            }
        }
        g.refresh();
    }
}
