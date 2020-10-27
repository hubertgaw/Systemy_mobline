package com.example.exercise2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class CalculatorActivity extends AppCompatActivity {

    private TextView x, textFirstLine, textSecondLine, textThirdLine, functionTextView;
    private EditText a, b, c;
    private Button computeButton;
    public static DecimalFormat DECIMAL_FORMATTER;
    private double aNum, bNum, cNum;

    private void findValues() {
        a = (EditText) findViewById(R.id.aFactor);
        b = (EditText) findViewById(R.id.bFactor);
        c = (EditText) findViewById(R.id.cFactor);

        textFirstLine = (TextView) findViewById(R.id.firstText);
        textSecondLine = (TextView) findViewById(R.id.secondText);
        textThirdLine = (TextView) findViewById(R.id.thirdText);
        functionTextView = (TextView) findViewById(R.id.functionTextView);
    }

    private void isEmpty() {
        if (TextUtils.isEmpty(a.getText().toString())) {
            aNum = 0.0;
        } else {
            aNum = Integer.parseInt(a.getText().toString());
        }
        if (TextUtils.isEmpty(b.getText().toString())) {
            bNum = 0.0;
        } else {
            bNum = Integer.parseInt(b.getText().toString());
        }
        if (TextUtils.isEmpty(c.getText().toString())) {
            cNum = 0.0;
        } else {
            cNum = Integer.parseInt(c.getText().toString());
        }
    }

    @SuppressLint("SetTextI18n")
    private void performCalculations() {
        isEmpty();
        if (aNum != 0.0) {
            double delta = bNum * bNum - 4.0 * aNum * cNum;

            if (delta > 0) {
                double deltaSqrt = Math.sqrt(delta);

                double x1 = ((-bNum) - deltaSqrt) / (2.0 * aNum);
                double x2 = ((-bNum) + deltaSqrt) / (2.0 * aNum);

                textFirstLine.setText("The quadratic equation has two roots, the first root is" + DECIMAL_FORMATTER.format(x1));
                textSecondLine.setText("The second root is" + DECIMAL_FORMATTER.format(x2));
                textThirdLine.setText("The square discriminate equals " + DECIMAL_FORMATTER.format(delta));
            } else if (delta < 0) {
                textFirstLine.setText("The quadratic equation has no root");
                textSecondLine.setText("");
                textThirdLine.setText("The square discriminate equals " + DECIMAL_FORMATTER.format(delta));
            } else {
                double x = -bNum / (2.0 * aNum);
                textFirstLine.setText("The quadratic equation has one root and it is" + DECIMAL_FORMATTER.format(x));
                textSecondLine.setText("");
                textThirdLine.setText("The square discriminate equals " + DECIMAL_FORMATTER.format(delta));
            }
            if (aNum > 0) {
                functionTextView.setText("The graph of a function is directed to the up");
            } else {
                functionTextView.setText("The graph of a function is directed to the down");
            }

        } else if (aNum == 0 && bNum == 0 && cNum == 0) {
            textFirstLine.setText("It is identity equation, ");
            textSecondLine.setText("");
            textThirdLine.setText("");
            functionTextView.setText("It is not a function");
        } else if (aNum == 0 && bNum == 0) {
            textFirstLine.setText("It is contradictory equation");
            textSecondLine.setText("");
            textThirdLine.setText("");
            functionTextView.setText("It is not a function");
        } else if (aNum == 0) {
            double x = -cNum / bNum;
            textFirstLine.setText("It's linear equation and its root is" + DECIMAL_FORMATTER.format(x));
            textSecondLine.setText("");
            textThirdLine.setText("");
            if(bNum > 0) {
                functionTextView.setText("The graph of a function is directed to the up");
            } else if (bNum < 0) {
                functionTextView.setText("The graph of a function is directed to the down");
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        x = (TextView) findViewById(R.id.xSymbol1);
        x.setText(Html.fromHtml("x<sup><small>2</small></sup>"));

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setDecimalSeparator('.');
        DECIMAL_FORMATTER = new DecimalFormat("#0.000", symbols);

        computeButton = (Button) findViewById(R.id.computeButton);

        computeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findValues();
                performCalculations();
            }
        });

    }
}