package com.example.hz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView formula, formulares;
    private Button one, two, three, four, five, six, seven, eight, nine, zero;
    private Button plus, minus, mult, div, result, sqrt, percent, square, clear;
    private char Action;
    private double valueFirst = Double.NaN;
    private double valueSecond;
    private boolean actionPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        setupView();

        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                formula.append(button.getText().toString());
                actionPressed = false;
            }
        };

        View.OnClickListener operatorClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (formula.getText().length() != 0 && !actionPressed) {
                    Button button = (Button) view;
                    Action = button.getText().toString().charAt(0);
                    if (Double.isNaN(valueFirst)) {
                        valueFirst = Double.parseDouble(formula.getText().toString());
                    }
                    formula.append(button.getText().toString());
                    actionPressed = true;
                }
            }
        };

        View.OnClickListener resultClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (formula.getText().length() != 0) {
                    calculate(Action);
                    formulares.setText(String.valueOf(valueFirst));
                    formula.setText(null);
                    actionPressed = false;
                }
            }
        };

        View.OnClickListener clearClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                formula.setText(null);
                formulares.setText(null);
                valueFirst = Double.NaN;
                actionPressed = false;
            }
        };

        View.OnClickListener sqrtClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (formula.getText().length() != 0 && !actionPressed) {
                    Action = '√';
                    if (Double.isNaN(valueFirst)) {
                        valueFirst = Double.parseDouble(formula.getText().toString());
                    }
                    formula.append("√");
                    actionPressed = true;
                }
            }
        };

        View.OnClickListener squareClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (formula.getText().length() != 0 && !actionPressed) {
                    Action = '^';
                    if (Double.isNaN(valueFirst)) {
                        valueFirst = Double.parseDouble(formula.getText().toString());
                    }
                    formula.append("^");
                    actionPressed = true;
                }
            }
        };

        View.OnClickListener percentClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (formula.getText().length() != 0 && !actionPressed) {
                    Action = '%';
                    if (Double.isNaN(valueFirst)) {
                        valueFirst = Double.parseDouble(formula.getText().toString());
                    }
                    formula.append("%");
                    actionPressed = true;
                }
            }
        };

        one.setOnClickListener(numberClickListener);
        two.setOnClickListener(numberClickListener);
        three.setOnClickListener(numberClickListener);
        four.setOnClickListener(numberClickListener);
        five.setOnClickListener(numberClickListener);
        six.setOnClickListener(numberClickListener);
        seven.setOnClickListener(numberClickListener);
        eight.setOnClickListener(numberClickListener);
        nine.setOnClickListener(numberClickListener);
        zero.setOnClickListener(numberClickListener);

        plus.setOnClickListener(operatorClickListener);
        minus.setOnClickListener(operatorClickListener);
        mult.setOnClickListener(operatorClickListener);
        div.setOnClickListener(operatorClickListener);

        result.setOnClickListener(resultClickListener);
        clear.setOnClickListener(clearClickListener);
        sqrt.setOnClickListener(sqrtClickListener);
        square.setOnClickListener(squareClickListener);
        percent.setOnClickListener(percentClickListener);
    }

    private void setupView() {
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        zero = findViewById(R.id.zero);

        minus = findViewById(R.id.minus);
        plus = findViewById(R.id.plus);
        mult = findViewById(R.id.mult);
        div = findViewById(R.id.div);
        result = findViewById(R.id.result);
        sqrt = findViewById(R.id.sqrt);
        square = findViewById(R.id.square);
        percent = findViewById(R.id.percent);
        clear = findViewById(R.id.clear);

        formula = findViewById(R.id.formula);
        formulares = findViewById(R.id.formulares);
    }

    private void calculate(char action) {
        if (action == '√') {
            valueFirst = Math.sqrt(valueFirst);
        } else if (action == '^') {
            valueFirst = Math.pow(valueFirst, 2);
        } else if (action == '%') {
            valueFirst = valueFirst / 100;
        } else {
            if (!Double.isNaN(valueFirst)) {
                String textFormula = formula.getText().toString();
                int indexAction = textFormula.indexOf(Action);
                if (indexAction != -1) {
                    String numberValue = textFormula.substring(indexAction + 1);
                    if (!numberValue.isEmpty()) {
                        valueSecond = Double.parseDouble(numberValue);

                        switch (action) {
                            case '/':
                                if (valueSecond == 0) {
                                    valueFirst = 0.0;
                                } else {
                                    valueFirst /= valueSecond;
                                }
                                break;
                            case '*':
                                valueFirst *= valueSecond;
                                break;
                            case '+':
                                valueFirst += valueSecond;
                                break;
                            case '-':
                                valueFirst -= valueSecond;
                                break;
                        }
                    }
                }
            } else {
                valueFirst = Double.parseDouble(formula.getText().toString());
            }
        }
    }
}
