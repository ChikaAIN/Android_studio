package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView display;
    String currentNumber = "";
    double num1, num2;
    char operator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
    }

    public void onButtonClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        switch (buttonText) {
            case "C":
                currentNumber = "";
                display.setText("");
                break;
            case "+":
            case "-":
            case "*":
            case "/":
            case "x^2":
            case "√":
                operator = buttonText.charAt(0);
                num1 = parseCurrentNumber();
                currentNumber = "";
                break;
            case "=":
                num2 = parseCurrentNumber();
                double result = calculate(num1, num2, operator);
                display.setText(String.valueOf(result));
                break;
            default:
                currentNumber += buttonText;
                display.setText(currentNumber);
        }
    }

    private double parseCurrentNumber() {
        if (!currentNumber.isEmpty()) {
            return Double.parseDouble(currentNumber);
        } else {
            // В случае пустой строки возвращаем 0.0 или другое значение по умолчанию
            return 0.0;
        }
    }

    private double calculate(double num1, double num2, char operator) {
        double result = 0;
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 != 0)
                    result = num1 / num2;
                else
                    display.setText(getString(R.string.error_message));
                break;
            case 'x':
                result = Math.pow(num1, 2);
                break;
            case '√':
                result = Math.sqrt(num1);
                break;
        }
        return result;
    }
}
