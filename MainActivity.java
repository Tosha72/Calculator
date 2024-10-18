package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private String currentOperator;
    private double firstNumber, secondNumber;
    private boolean isOperatorClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                String buttonText = button.getText().toString();
                if (isOperatorClicked) {
                    display.setText(buttonText); // Начать ввод нового числа после выбора оператора
                    isOperatorClicked = false;
                } else {
                    String currentText = display.getText().toString();
                    display.setText(currentText.equals("0") ? buttonText : currentText + buttonText);
                }
            }
        };

        // Присвоение кнопкам цифр слушателей
        findViewById(R.id.button0).setOnClickListener(numberListener);
        findViewById(R.id.button1).setOnClickListener(numberListener);
        findViewById(R.id.button2).setOnClickListener(numberListener);
        findViewById(R.id.button3).setOnClickListener(numberListener);
        findViewById(R.id.button4).setOnClickListener(numberListener);
        findViewById(R.id.button5).setOnClickListener(numberListener);
        findViewById(R.id.button6).setOnClickListener(numberListener);
        findViewById(R.id.button7).setOnClickListener(numberListener);
        findViewById(R.id.button8).setOnClickListener(numberListener);
        findViewById(R.id.button9).setOnClickListener(numberListener);

        // Присвоение кнопкам операторов слушателей
        findViewById(R.id.buttonAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operatorClicked("+");
            }
        });

        findViewById(R.id.buttonSubtract).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operatorClicked("-");
            }
        });

        findViewById(R.id.buttonMultiply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operatorClicked("*");
            }
        });

        findViewById(R.id.buttonDivide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operatorClicked("/");
            }
        });

        // Обработка нажатия на кнопку "равно"
        findViewById(R.id.buttonEquals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondNumber = Double.parseDouble(display.getText().toString());
                double result = calculateResult(firstNumber, secondNumber, currentOperator);
                display.setText(String.valueOf(result));
            }
        });
    }

    // Метод для обработки нажатия операторов
    private void operatorClicked(String operator) {
        firstNumber = Double.parseDouble(display.getText().toString());
        currentOperator = operator;
        isOperatorClicked = true; // Позволяет начать ввод нового числа после оператора
    }

    // Метод для выполнения арифметических операций
    private double calculateResult(double firstNumber, double secondNumber, String operator) {
        switch (operator) {
            case "+":
                return firstNumber + secondNumber;
            case "-":
                return firstNumber - secondNumber;
            case "*":
                return firstNumber * secondNumber;
            case "/":
                if (secondNumber != 0) {
                    return firstNumber / secondNumber;
                } else {
                    return 0; // Избегаем деления на ноль
                }
            default:
                return 0;
        }
    }
}
