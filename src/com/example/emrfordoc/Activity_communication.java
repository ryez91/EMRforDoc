package com.example.emrfordoc;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;

public class Activity_communication extends Activity{
	//private Button btnsend;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.communicationdoc);
		
		Toast.makeText(getApplicationContext(), "You clicked communication", Toast.LENGTH_LONG).show();
		
		
		
		
	}
		
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
		}
	

}
