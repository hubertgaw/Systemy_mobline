package com.example.equation_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView x, result1, result2, deltaText;
    private EditText a, b, c;
    private Button computeButton;
    public static DecimalFormat DECIMAL_FORMATTER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        x = (TextView) findViewById(R.id.xSymbol1);
        x.setText(Html.fromHtml("x<sup><small>2</small></sup>"));

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setDecimalSeparator('.');
        DECIMAL_FORMATTER = new DecimalFormat("#.000", symbols);

        computeButton = (Button) findViewById(R.id.computeButton);

        computeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a = (EditText) findViewById(R.id.aFactor);
                b = (EditText) findViewById(R.id.bFactor);
                c = (EditText) findViewById(R.id.cFactor);

                result1 = (TextView) findViewById(R.id.result1);
                result2 = (TextView) findViewById(R.id.result2);
                deltaText = (TextView) findViewById(R.id.deltaTextView);

                double aNum = Integer.parseInt(a.getText().toString());
                double bNum = Integer.parseInt(b.getText().toString());
                double cNum = Integer.parseInt(c.getText().toString());

                double delta = bNum*bNum - 4.0*aNum*cNum;

                double deltaSqrt = Math.sqrt(delta);

                double x1 = (-bNum - deltaSqrt) / (2.0*aNum);
                double x2 = (-bNum + deltaSqrt) / (2.0*aNum);

                result1.setText(DECIMAL_FORMATTER.format(x1));
                result2.setText(DECIMAL_FORMATTER.format(x2));
                deltaText.setText(DECIMAL_FORMATTER.format(delta));
            }
        });

    }
}