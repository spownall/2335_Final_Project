package com.example.csromero.a2335_final_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class LightControl extends AppCompatActivity {

    SeekBar lightBar;
    TextView displayText;
    DatabaseClass databaseClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int progress = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_control);
        
        // get the database object, read any previous entries, and update the UI as needed
        progress = databaseClass.readEntry("light");
        
        SeekBar lightBar = (SeekBar)findViewById(R.id.lightBar);
        final TextView displayText = (TextView)findViewById(R.id.displayText);

        lightBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
                Toast.makeText(getApplicationContext(), "Changing seekbar's progress", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "Started tracking seekbar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                displayText.setText("Luminosity " + progress + "%");
                Toast.makeText(getApplicationContext(), "Stopped tracking seekbar", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

