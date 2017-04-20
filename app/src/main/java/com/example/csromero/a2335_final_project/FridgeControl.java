package com.example.csromero.a2335_final_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FridgeControl extends AppCompatActivity implements OnItemSelectedListener{
    
    Spinner fridgeSpinner;
    Spinner freezerSpinner;
    TextView fridgeTempDisplay;
    TextView freezerTempDisplay;
    int fridgeTemp;
    int freezerTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge_control);
        
        // defines UI objects
      	fridgeSpinner = (Spinner) findViewById(R.id.fridgeSpinner);
      	freezerSpinner = (Spinner) findViewById(R.id.fridgeSpinner);
      
      	// set the item selected listener for each spinner
      	fridgeSpinner.setOnItemSelectedListener(this);
      	freezerSpinner.setOnItemSelectedListener(this);
      
		// creates an ArrayAdapter using a given string array and passing in a default spinner layout
		ArrayAdapter<CharSequence> fridgeAdapter = ArrayAdapter.createFromResource(this,
        R.array.fridge_temp_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// apply the adapter to the spinner
		fridgeSpinner.setAdapter(fridgeAdapter);
      
      	// creates an ArrayAdapter using a given string array and passing in a default spinner layout
		ArrayAdapter<CharSequence> freezerAdapter = ArrayAdapter.createFromResource(this,
        R.array.fridge_temp_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// apply the adapter to the spinner
		freezerSpinner.setAdapter(freezerAdapter);
        
        // get the database object and check for previous entries
        databaseClass = new DatabaseClass();
        fridgeTemp = databaseClass.read("fridge");
        fridgeTempDisplay.setText(String.valueOf(fridgeTemp));
        
        freezerTemp = databaseClass.read("freezer");
        freezerTempDisplay.setText(String.valueOf(fridgeTemp));
    }
    
    protected void onPause(Bundle savedInstanceState){
        // write the current values to the database
        databaseClass.write("fridge", fridgetemp);
        databaseClass.write("freezer", freezertemp);
    }
    
    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
    	int pos, long id) {
        // handle the selection of an item in either spinner, updating the temperature values and displays
         if (parent == R.id.fridgeSpinner) {
         	fridgeTemp = String.valueOf(String.valueOf(parent.getItemAtPosition(pos)));
         	fridgeTempDisplay.setText(String.valueOf(parent.getItemAtPosition(pos)));
         } 
         else {
            freezerTemp = String.valueOf(String.valueOf(parent.getItemAtPosition(pos)));
            freezerTempDisplay = String.valueOf(String.valueOf(parent.getItemAtPosition(pos)));
         }
    }
  
	@Override
   	public void onNothingSelected(AdapterView<?> parent) {
    	// this function needs to be implemented, but we don't have to do anything here 	
    }
    
}
