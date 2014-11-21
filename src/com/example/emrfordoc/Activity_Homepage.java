package com.example.emrfordoc;

import com.example.test_bluetooth.BluetoothActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class Activity_Homepage extends Activity{
private final String TAG = "Base Activity";
	
	private Button communication;
	private Button records;
	private Button btnlogoff;
	
	private String key;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage);
		
		
		Bundle myBundle = this.getIntent().getExtras();
		key = myBundle.getString("key");
		
		records = (Button)findViewById(R.id.btn_record);
		communication = (Button)findViewById(R.id.btn_commun);
		btnlogoff = (Button)findViewById(R.id.btn_logoff);
		
		records.setOnClickListener(recordjumpListener);
		communication.setOnClickListener(communicationListener);
		btnlogoff.setOnClickListener(logoffListener);
	}
	
	private OnClickListener recordjumpListener = new OnClickListener(){
		@Override
		public void onClick(View arg0) {
			//jump to show Record Page
			
			Intent myIntent = new Intent(getApplicationContext(),Activity_Login_recordshow4doc.class);
			Bundle bundle = new Bundle();
			bundle.putString("key",key);
			myIntent.putExtras(bundle);
			startActivity(myIntent);
		}
	};
	
	private OnClickListener communicationListener = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			// jump to communication page
			Intent myIntent = new Intent(getApplicationContext(), BluetoothActivity.class);
			Bundle myBundle = new Bundle();
			myBundle.putString("key", key);
			myIntent.putExtras(myBundle);
			startActivity(myIntent);
			
		}
		
		
	};
	
	private OnClickListener logoffListener = new OnClickListener(){
		@Override
		public void onClick(View arg0) {
			//jump to log off page, need modification
			
			Intent myIntent = new Intent(getApplicationContext(),ActivityLogoff.class);
			startActivity(myIntent);
		}
	};
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}