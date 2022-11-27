package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Boolean is_operator_applied = false;
    Boolean has_undefined = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void digitClick(View view){
        TextView display = (TextView) findViewById(R.id.tb_display);
        TextView sub_display = (TextView) findViewById(R.id.tb_subdisplay);
        Button button = (Button) view;
        String button_digit = button.getText().toString();
        String current_display_text = display.getText().toString();
        String sub_display_text = sub_display.getText().toString();
        if(!has_undefined){
            if(sub_display_text.equals("")){
                display.setText(current_display_text.equals("0") ? button_digit : current_display_text + button_digit);
            } else {
                display.setText(is_operator_applied ? button_digit : current_display_text + button_digit);
                is_operator_applied = false;
            }
        }
    }

    public void button_remove_click(View view){
        if(!has_undefined){
            TextView display = (TextView) findViewById(R.id.tb_display);
            String current_display_text = display.getText().toString();
            display.setText(current_display_text.length() != 1 ? current_display_text.substring(0,current_display_text.length()-1) : "0");
        }
    }
    public void operatorClick(View view){
        TextView display = (TextView) findViewById(R.id.tb_display);
        TextView sub_display = (TextView) findViewById(R.id.tb_subdisplay);
        Button button = (Button) view;
        String operator = button.getText().toString();
        String current_display_text = display.getText().toString();
        if(!has_undefined){
            boolean has_operator =  !Character.isDigit(current_display_text.charAt(current_display_text.length()-1));
            sub_display.setText(has_operator ? current_display_text.substring(0, current_display_text.length() -1 ) + operator : current_display_text + operator);
            is_operator_applied = true;
        }
    }

    public void clear(View view){
        TextView display = (TextView) findViewById(R.id.tb_display);
        TextView sub_display = (TextView) findViewById(R.id.tb_subdisplay);
        display.setText("0");
        sub_display.setText("");
        is_operator_applied = false;
        has_undefined = false;
    }

    public void calculate(View view){
        TextView display = (TextView) findViewById(R.id.tb_display);
        TextView sub_display = (TextView) findViewById(R.id.tb_subdisplay);
        String current_display_text = display.getText().toString();
        String sub_display_text = sub_display.getText().toString();
        if(!current_display_text.equals("") && !sub_display_text.equals("")){
            if(!is_operator_applied){
                String operator = sub_display_text.substring(sub_display.length() -1 );
                String result = performCalculation(operator , Integer.parseInt(sub_display_text.substring(0,sub_display_text.length() - 1)), Integer.parseInt(current_display_text));
                sub_display.setText("");
                display.setText(result);
            }
        }
    }

    public String performCalculation(String operator, Integer num1, Integer num2){
        String retval = "";
        Log.d("Hesapla", String.valueOf(num1) + operator + String.valueOf(num2));
        switch (operator){
            case "+":
              retval = String.valueOf(num1 + num2);
            break;
            case "-":
                retval = String.valueOf(num1 - num2);
            break;
            case "*":
                retval = String.valueOf(num1 * num2);
            break;
            case "/":

                if(num2 == 0){
                    retval = "Tanimsiz";
                    has_undefined = true;
                } else {
                    retval = String.valueOf(num1 / num2);
                }
            break;
        }
        return retval;
    }



}