package com.greelogix.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ArrayList<ImageView> imageViews = new ArrayList<>();
    ArrayList<Integer> clickedNumbers = new ArrayList<>();
    int[] locations = new int[9];
    final int[][] lines = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}
    };
    int currentTurn = 0; // 0 for 0 and 1 for x; 0 for user and 1 for computer
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private boolean calculateOWinner(){
        for (int i = 0; i < lines.length; i++) {
            int a = lines[i][0];
            int b = lines[i][1];
            int c = lines[i][2];
            if (locations[a] == locations[b] && locations[a] == locations[c] && locations[a] == 'o') {
                return true;
            }
        }
        return false;
    }

    private boolean calculateXWinner(){
        for (int i = 0; i < lines.length; i++) {
            int a = lines[i][0];
            int b = lines[i][1];
            int c = lines[i][2];
            if (locations[a] == locations[b] && locations[a] == locations[c] && locations[a] == 'x') {
                return true;
            }
        }
        return false;
    }

    private void showAlert(Character w, boolean won){
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Game Over");
        String msg = won ? "The player " + w + " won!" : "It is a draw";
        alertDialog.setMessage(msg);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    private void playComputer() {
        Random rn = new Random();
        int answer = rn.nextInt(9);
        while(clickedNumbers.contains(answer)){
            answer = rn.nextInt(9);
        }
        imageViews.get(answer).setImageDrawable(getResources().getDrawable(R.drawable.x));
        clickedNumbers.add(answer);
        locations[answer] = 'x';
        boolean win = calculateXWinner();
        if (win) {
            showAlert('x', true);
            return;
        }
        if(clickedNumbers.size() == 9){
            showAlert('x', false);
            return;
        }
        currentTurn = 0;
    }

    private void handleViewClick(int index){
        if(clickedNumbers.contains(index) || currentTurn == 1){
            return;
        }
        imageViews.get(index).setImageDrawable(getResources().getDrawable(R.drawable.o));
        clickedNumbers.add(index);
        locations[index]= 'o';
        boolean win = calculateOWinner();
        if(win){
            showAlert('o', true);
            return;
        }
        if(clickedNumbers.size() == 9){
            showAlert('o', false);
            return;
        }
        currentTurn = 1;
        playComputer();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void initViews(){
        imageViews.add(findViewById(R.id.box0));
        imageViews.add(findViewById(R.id.box1));
        imageViews.add(findViewById(R.id.box2));
        imageViews.add(findViewById(R.id.box3));
        imageViews.add(findViewById(R.id.box4));
        imageViews.add(findViewById(R.id.box5));
        imageViews.add(findViewById(R.id.box6));
        imageViews.add(findViewById(R.id.box7));
        imageViews.add(findViewById(R.id.box8));

        for(int i = 0; i < imageViews.size(); i++){
            int finalI = i;
            imageViews.get(i).setOnClickListener(view -> {
                handleViewClick(finalI);
            });
        }

//        box1.setOnClickListener(view -> {
//            if(clickedNumbers.contains(1) || currentTurn == 1){
//                return;
//            }
//            box1.setImageDrawable(getResources().getDrawable(R.drawable.o));
//            clickedNumbers.add(1);
//        });
//
//        box2.setOnClickListener(view -> {
//            if(clickedNumbers.contains(2) || currentTurn == 1){
//                return;
//            }
//            box2.setImageDrawable(getResources().getDrawable(R.drawable.o));
//            clickedNumbers.add(2);
//        });
//
//        box3.setOnClickListener(view -> {
//            if(clickedNumbers.contains(3) || currentTurn == 1){
//                return;
//            }
//            box3.setImageDrawable(getResources().getDrawable(R.drawable.o));
//            clickedNumbers.add(3);
//        });
//
//        box4.setOnClickListener(view -> {
//            if(clickedNumbers.contains(4) || currentTurn == 1){
//                return;
//            }
//            box4.setImageDrawable(getResources().getDrawable(R.drawable.o));
//            clickedNumbers.add(4);
//        });
//
//        box5.setOnClickListener(view -> {
//            if(clickedNumbers.contains(5) || currentTurn == 1){
//                return;
//            }
//            box5.setImageDrawable(getResources().getDrawable(R.drawable.o));
//            clickedNumbers.add(5);
//        });
//
//        box6.setOnClickListener(view -> {
//            if(clickedNumbers.contains(6) || currentTurn == 1){
//                return;
//            }
//            box6.setImageDrawable(getResources().getDrawable(R.drawable.o));
//            clickedNumbers.add(6);
//        });
//
//
//        box7.setOnClickListener(view -> {
//            if(clickedNumbers.contains(7) || currentTurn == 1){
//                return;
//            }
//            box7.setImageDrawable(getResources().getDrawable(R.drawable.o));
//            clickedNumbers.add(7);
//        });
//
//
//
//        box8.setOnClickListener(view -> {
//            if(clickedNumbers.contains(8) || currentTurn == 1){
//                return;
//            }
//            box8.setImageDrawable(getResources().getDrawable(R.drawable.o));
//            clickedNumbers.add(8);
//        });
//
//
//
//        box9.setOnClickListener(view -> {
//            if(clickedNumbers.contains(9) || currentTurn == 1){
//                return;
//            }
//            box9.setImageDrawable(getResources().getDrawable(R.drawable.o));
//            clickedNumbers.add(9);
//        });
    }
}