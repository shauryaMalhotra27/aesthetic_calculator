package com.example.aesthetic_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private double firstNumber;
    String operations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //calling TextView
        TextView showScreen = findViewById(R.id.resultBox);

        //calling EditText
        EditText calcscreen = findViewById(R.id.calculationBox);

        //here we are disabling the soft keyboard of device
        calcscreen.setShowSoftInputOnFocus(false);

        //calling numeric buttons
        Button btn_dot = findViewById(R.id.btndot);
        Button btn0 = findViewById(R.id.btn0);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);

        //calling function button
        Button multiply = findViewById(R.id.multiply);
        Button divide = findViewById(R.id.divide);
        Button subtract = findViewById(R.id.btnsub);
        Button add = findViewById(R.id.btnadd);
        Button equals = findViewById(R.id.btnequals);
        Button percentage = findViewById(R.id.percentage);
        Button all_clear = findViewById(R.id.all_clear);
        Button delete = findViewById(R.id.del);

        /*adding all numeric button in an arrayList so that a
        common function could be implemented on all through iterating*/
        ArrayList<Button> numbers = new ArrayList<>();
        numbers.add(btn0);
        numbers.add(btn1);
        numbers.add(btn2);
        numbers.add(btn3);
        numbers.add(btn4);
        numbers.add(btn5);
        numbers.add(btn6);
        numbers.add(btn7);
        numbers.add(btn8);
        numbers.add(btn9);

        //implementing the function
        for (Button b : numbers){
            b.setOnClickListener(view -> {
                if(!calcscreen.getText().toString().equals(getString(R.string.calc_box)) && !calcscreen.getText().toString().equals("0")){
                    calcscreen.setText(calcscreen.getText().toString() + b.getText().toString());
                }
                else{
                    calcscreen.setText((b.getText().toString()));
                }
            });
        }


        //adding basic functionality function in the array
        ArrayList<Button> opers = new ArrayList<>();
        opers.add(add);
        opers.add(subtract);
        opers.add(divide);
        opers.add(multiply);

        //adding function to each basic function button
        for(Button b: opers ){
            b.setOnClickListener(view -> {
                firstNumber = Double.parseDouble(calcscreen.getText().toString());
                operations = b.getText().toString();
                calcscreen.setText("");
            });
        }


        //adding function to the delete or backspace button
        delete.setOnClickListener(view -> {
            String num = calcscreen.getText().toString();
            int numLength = num.length();

            if(numLength>1){
                calcscreen.setText(num.substring(0, numLength-1));
            }
            else if(numLength == 1 && !num.equals("0")){
                calcscreen.setText("0");
            }
        });

        //adding function to the allclear button
        all_clear.setOnClickListener(view -> {
            calcscreen.setText("0");
            showScreen.setText(R.string.result_box);
        });

        //making the switch case for the calculations
        equals.setOnClickListener(view -> {

            double secondNumber = Double.parseDouble(calcscreen.getText().toString());
            double result = 0;

            switch (operations){

                case "÷":
                    result = firstNumber/secondNumber;
                    break;

                case "×":
                    result = firstNumber*secondNumber;
                    break;

                case "+":
                    result = firstNumber + secondNumber;
                    break;

                case "-":
                    result=firstNumber-secondNumber;
                    break;
            }
            showScreen.setText(String.valueOf(result));
            double result1 = Double.parseDouble(showScreen.getText().toString());
            firstNumber = result1;
        });
    }
}