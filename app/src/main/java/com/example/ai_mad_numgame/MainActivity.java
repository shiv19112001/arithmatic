package com.example.ai_mad_numgame;
/*
   App will show your last performance at the start of the activity. New Tournament will start from
   all performance set to -1 again. And your new performance will be visible, when you return back to game
 */
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.gson.Gson;  //make changes at appropriate places to include this dependency
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    int matchCounter=0;
    int []performance={-1,-1,-1,-1,-1,-1}; //score of a game is updated in this array
    int []score={-1,-1,-1}; //score of each match is updated in this array. A total of three matches in a game
    String operators[]={"+","-","*","/"};
    int correctButton=0; //which button will have the correct answer (tag of that button)
    Random random=new Random(); //You will generate randdom alegebra questions
    Random random=new Random(); //You will generate random algebra questions
    int correctButton= random.nextInt(4); //which button will have the correct answer (tag of that button)
    TextView textView2;
    Button button1,button2,button3,button4;
    public void load(View view){
@@ -54,10 +54,10 @@ protected void onCreate(Bundle savedInstanceState) {
        textView2=findViewById(R.id.textView2);
        newMatch();
        sharedPreferences=this.getSharedPreferences("com.example.aiapp_2022", Context.MODE_PRIVATE);
        int[][]dataFrame=dataPrep(); //dataPrep function returns a two-dimenssional array
        int[][]dataFrame=dataPrep(); //dataPrep function returns a two-dimensional array
        double slope=LR.getSlope(dataFrame); //LR class, which provides slope on invoking getSlope
        new AlertDialog.Builder(this)
               // .setIcon() //your custom icon
                // .setIcon() //your custom icon
                .setTitle("Performance")

                .setMessage(getInterpretation(dataFrame,slope))
@@ -70,16 +70,61 @@ public void onClick(DialogInterface dialog, int which) {
    }

    public void newMatch() {  //A game is composed of three matches

        int operand1 = random.nextInt(10);
        int operand2=0;
        //check is operand2 is not zero; otherwise in case of division-divide by zero error will come
        int operand2=random.nextInt(10);
        //check is operand2 if not zero; otherwise in case of division-divide by zero error will come
        String operator = operators[random.nextInt(4)];
        int correctans=-100;
        textView2.setText(operand1 + operator + operand2);

      // Your code here, to diplay correct and incorrect options on the buttons
        if(operator.equals('+')){
            correctans = operand1+operand2;
        }
        else if(operator.equals('*')){
            correctans = operand1*operand2;
        }
        else if(operator.equals('-')){
            correctans = operand1-operand2;
        }
        else if(operator.equals('/')){
            correctans = operand1/operand2;
        }

        if(correctButton == 0 ) {
            button1.setText(correctans+"");
            button2.setText(operand1  + " ");
            button3.setText(operand1  + "");
            button4.setText(operand1  + operator + operand2);
        }
        else if(correctButton== 1 ){
            button1.setText(operand1  + " ");
            button2.setText(correctans+ " ");
            button3.setText(operand1  + "");
            button4.setText(operand1  + operator + operand2);
        }
        else if(correctButton== 2 ){
            button1.setText(operand1  + " " );
            button2.setText(operand1  + " ");
            button3.setText(correctans+ " ");
            button4.setText(operand1  + operator + operand2);
        }
        else {
            //button1.setText(ans);
            button1.setText(operand1  + " " );
            button2.setText(operand1 + " ");
            button3.setText(operand1 + "");
            button4.setText(correctans+ " ");
        }


        // Your code here, to display correct and incorrect options on the buttons

        if(matchCounter==3){    // if three matches are completed updatee the perfomrance in sharedpreferences
        button1.setText(operand1+operand2+"");
        button2.setText(operand1*operand2+"");
        button3.setText(operand1-operand2+"");
        button4.setText(operand1/operand2+"");

        if(matchCounter==3){    // if three matches are completed update the performance in sharedpreferences

            matchCounter=0;

@@ -95,7 +140,10 @@ public void newMatch() {  //A game is composed of three matches
    public int sumOfScore(){
        //Computing the sum of score array, which has the 1 or in each index,depending on correct or incorrect answers
        int sum=0;
       // your code here
        // your code here
        for(int i=0;i<score.length;i++){
            sum+=score[i];
        }
        return sum;
    }

@@ -113,8 +161,21 @@ public int[][] dataPrep() {
    }

    public String getInterpretation(int [][]dataFrame,double slope){
       //provide interpretation based on your slope analysis
        //provide interpretation based on your slope analysis
        // Your code here
        if(slope>0 && slope<0.5){
            return "you are too slow";
            //System.out.println("Oh no shit");
        }
        else if(slope>0.5){
            return "better";
            //System.out.println("Good !");
        }
        else if(slope<0){
            return "you are not sincere";
            //System.out.println("Good !");
        }

        return "Your Interpretation";
    }
} 
