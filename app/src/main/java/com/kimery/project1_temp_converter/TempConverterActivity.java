package com.kimery.project1_temp_converter;

import android.app.Activity;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DecimalFormat;

public class TempConverterActivity extends Activity
            implements TextView.OnEditorActionListener, View.OnClickListener

{
    private EditText fahrenheitEditTextBox;
    private TextView celsiusView;
    private String fahrenheitEditString = "";
    private String celsiusEditString = "";
    private Button fButton;
    private Button cButton;
    private TextView fahrenheitTextLabel;
    private TextView celsiusLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_converter);
        ///Get references to the widgets
        fahrenheitEditTextBox = (EditText) findViewById(R.id.fahrenheitEditTextBox);
        celsiusView = (TextView) findViewById(R.id.celsiusView);
        fButton =(Button) findViewById(R.id.fButton);
        cButton =(Button) findViewById(R.id.cButton);
        fahrenheitTextLabel = (TextView)findViewById(R.id.fahrenheitTextLabel);
        celsiusLabel = (TextView)findViewById(R.id.celsiusLabel);
        //Set Listeners
        fahrenheitEditTextBox.setOnEditorActionListener(this);
        celsiusView.setOnEditorActionListener(this);
        fahrenheitTextLabel.setOnEditorActionListener(this);
        celsiusLabel.setOnEditorActionListener(this);
        fButton.setOnClickListener(this);
        cButton.setOnClickListener(this);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_temp_converter, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
            calculateDegrees();
        }
        return false;
    }


    public void calculateDegrees(){
        if (fahrenheitTextLabel.getText().equals("Fahrenheit:")) {

            fahrenheitEditString = fahrenheitEditTextBox.getText().toString();
            Float fahrenheit;
            Float celsius;
            final String DEGREE = "°";
            if (fahrenheitEditString.equals("")) {
                fahrenheit = 32f;
            } else {
                fahrenheit = Float.parseFloat(fahrenheitEditString);
            }
            DecimalFormat dec = new DecimalFormat("#0.00");
            celsius = ((fahrenheit - 32) * 5) / 9;
            celsiusView.setText(dec.format(celsius) + DEGREE);
        }
        if (fahrenheitTextLabel.getText().equals("Celsius:")) {

            celsiusEditString = fahrenheitEditTextBox.getText().toString();
            Float fahrenheit;
            Float celsius;
            final String DEGREE = "°";
            if (celsiusEditString.equals("")) {
                celsius = 0f;
            } else {
                celsius = Float.parseFloat(celsiusEditString);
            }
            DecimalFormat dec = new DecimalFormat("#0.00");
            fahrenheit = (celsius * 9/5) + 32;
            celsiusView.setText(dec.format(fahrenheit) + DEGREE);
        }
    }

    @Override
    public void onClick(View v) {
        //final String DEGREE = "\u00B0";
        switch (v.getId()) {
            case R.id.fButton:
                fahrenheitTextLabel.setText("Celsius:");
                celsiusLabel.setText("Fahrenheit:");
                calculateDegrees();
                //celsiusView.setText("0.000" + DEGREE);
                //fahrenheitEditTextBox.setText("");
                break;
            case R.id.cButton:
                fahrenheitTextLabel.setText("Fahrenheit:");
                celsiusLabel.setText("Celsius:");
                calculateDegrees();
                //celsiusView.setText("0.000"+DEGREE);
                //fahrenheitEditTextBox.setText("");
                break;
        }
    }
}
