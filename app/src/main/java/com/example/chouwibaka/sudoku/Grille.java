package com.example.chouwibaka.sudoku;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class Grille extends View implements View.OnTouchListener {
    Square[][] grille;
    int marge = 20;
    int separator = 10;
    Square caseSelect;
    int caseLargeur;
    int caseHauteur;
    public static String values;

    public Grille(Context context, AttributeSet attrs) {
        super(context, attrs);
        grille = new Square[9][9];
        this.setOnTouchListener(this);

        for(int i=0; i< 9; i++){
            for(int j=0; j< 9; j++){
                grille[i][j] = new Square("", false);
            }
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int x = (int)event.getX(); int y = (int)event.getY();

        int indexX = (x-20)/(caseLargeur);
        int indexY = (y-20)/(caseHauteur);

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                caseSelect = grille[indexY][indexX];

                boolean setFalse = false;
                if(caseSelect.selected){
                    setFalse = true;
                }

                for(Square[] square : grille){
                    for(Square s : square){
                        s.selected = false;
                    }
                }

                if(!caseSelect.display && !setFalse)
                    caseSelect.selected = true;

                this.invalidate();
                break;
        }

        return true;
    }

    public boolean refresh(){
        this.invalidate();
        return true;
    }

    @Override
    public void onDraw(Canvas canvas){
        canvas.drawColor(Color.BLACK);

        String[] t = values.split("(?!^)");
        int k = 0;

        for(int i=0; i< 9; i++){
            for(int j=0; j< 9; j++) {
                String number = t[k];
                if (!number.equals("0")) {
                    grille[i][j].numsudok = number;
                    grille[i][j].display = true;
                }
                k++;
            }
        }

        int largeur = canvas.getWidth();
        int hauteur = canvas.getHeight();
        caseLargeur = (largeur - 2*marge) / 9;
        caseHauteur = (hauteur - 2*marge) / 9;

        checkAll();

        for(int i=0; i< 9; i++){
            for(int j=0; j< 9; j++){
                Square s = grille[i][j];
                s.left = marge+j*caseLargeur+separator;
                s.top = marge+i*caseHauteur+separator;
                s.right = marge+j*caseLargeur+caseLargeur;
                s.bottom = marge+i*caseHauteur+caseHauteur;
                if( (!s.numsudok.equals("")) && (!s.good) ){
                    verify(s);
                }
                s.draw(canvas);
            }
        }
    }

    public void verify(Square caseSelect) {
        int indexI = (caseSelect.top-20)/(caseHauteur);
        int indexJ = (caseSelect.left-20)/(caseLargeur);

        boolean ok = true;

        if(!caseSelect.numgiven.equals("")){
            for(int i=0; i<9; i++){
                if(i!=indexI){
                    if( caseSelect.numgiven.equals(grille[i][indexJ].numgiven) || caseSelect.numgiven.equals(grille[i][indexJ].numsudok) ){
                        ok = false;
                        if(!grille[i][indexJ].display)
                            grille[i][indexJ].good = false;
                    }
                }
            }
            for(int j=0; j<9; j++){
                if(j!=indexJ){
                    if( caseSelect.numgiven.equals(grille[indexI][j].numgiven) || caseSelect.numgiven.equals(grille[indexI][j].numsudok) ){
                        ok = false;
                        if(!grille[indexI][j].display)
                            grille[indexI][j].good = false;
                    }
                }
            }
            for(int k=0; k<3; k++){
                for(int l=0; l<3; l++){
                    int indexVerifI = (indexI/3)*3+k;
                    int indexVerifJ = (indexJ/3)*3+l;
                    if(indexVerifI!=indexI && indexVerifJ!=indexJ){
                        if( caseSelect.numgiven.equals(grille[indexVerifI][indexVerifJ].numgiven) || caseSelect.numgiven.equals(grille[indexVerifI][indexVerifJ].numsudok) ){
                            ok = false;
                            if(!grille[indexVerifI][indexVerifJ].display)
                                grille[indexVerifI][indexVerifJ].good = false;
                        }
                    }
                }
            }
            if(ok){
                caseSelect.good = true;
            }
            else{
                caseSelect.good = false;
            }
        }
    }

    public void checkAll(){
        for(int i=0; i< 9; i++){
            for(int j=0; j< 9; j++){
                verify(grille[i][j]);
            }
        }
    }
}

class Square{

    int left;
    int top;
    int right;
    int bottom;
    String numsudok;
    boolean display;
    String numgiven = "";
    boolean selected = false;
    boolean good = true;

    private Paint paint;
    public Square(String number, boolean v){
        numsudok = number; display = v;
        paint = new Paint();
        paint.setColor(Color.WHITE);
    }
    public void draw(Canvas canvas){
        if(selected){
            paint.setColor(Color.YELLOW);
            canvas.drawRect(left, top, right, bottom, paint);
        }
        else{
            paint.setColor(Color.WHITE);
            canvas.drawRect(left, top, right, bottom, paint);
        }

        if(display && numsudok!=null){
            paint.setColor(Color.BLACK);
            paint.setTextSize(80);
            canvas.drawText( numsudok, left+((right-left)/3), top+(3*(bottom-top)/4), paint);
        }
        if(numgiven != null && good){
            paint.setColor(Color.GREEN);
            paint.setTextSize(80);
            canvas.drawText( numgiven, left+((right-left)/3), top+(3*(bottom-top)/4), paint);
        }
        else if(numgiven != null && !good){
            paint.setColor(Color.RED);
            paint.setTextSize(80);
            canvas.drawText( numgiven, left+((right-left)/3), top+(3*(bottom-top)/4), paint);
        }
    }
    public void onTouch(){

    }
}