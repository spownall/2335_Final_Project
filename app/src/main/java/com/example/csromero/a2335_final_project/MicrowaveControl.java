package com.example.csromero.a2335_final_project;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MicrowaveControl extends AppCompatActivity implements View.OnClickListener{

    Button button1, button2, button3, button4, button5, button6, button7, button8, button9;
    Button cookButton;
    Button resetButton;
    TextView displayView;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_microwave_control);

        // retrieves the ui objects from the layout xml
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);

        cookButton = (Button) findViewById(R.id.cookButton);
        resetButton = (Button) findViewById(R.id.resetButton);

        displayView = (TextView) findViewById(R.id.displayText);



        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);

        cookButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Handles each button press, identified by ID
            switch(v.getId())
            {
                case R.id.button1:
                    addDigit(1);
                    break;
                case R.id.button2:
                    addDigit(2);
                    break;
                case R.id.button3:
                    addDigit(3);
                    break;
                case R.id.button4:
                    addDigit(4);
                    break;
                case R.id.button5:
                    addDigit(5);
                    break;
                case R.id.button6:
                    addDigit(6);
                    break;
                case R.id.button7:
                    addDigit(7);
                    break;
                case R.id.button8:
                    addDigit(8);
                    break;
                case R.id.button9:
                    addDigit(9);
                    break;
                case R.id.cookButton:
                    startTimer();
                    break;
                case R.id.resetButton:
                    stopTimer();
                    break;
                default:
                    throw new RuntimeException("How did you get here?");
            }
    }

    public void addDigit(int digit) {
        if (displayView.length() > 3) {
            return;
        } else {
            displayView.setText(displayView.getText()+ Integer.toString(digit));
        }
    }

    public void startTimer() {
        timer = new CountDownTimer(Long.valueOf((displayView.getText().toString())), 1000) {

            public void onTick(long millisUntilFinished) {
                displayView.setText(Long.toString(millisUntilFinished / 1000));
            }

            public void onFinish() {
                displayView.setText("eat it.");
                //TODO make the phone vibrate
            }
        }.start();
    }

    public void stopTimer() {
        timer.cancel();
        displayView.setText("");
    }
}
