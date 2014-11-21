package com.example.emrfordoc;

import com.example.elecmr.RegiInfo;
import com.example.elecmr.RegiInfoSource;
import com.example.trustworthy.encryption;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_updatebydoc extends Activity{
	EditText etxtallergies;
	EditText etxthistory;
	Button btnsaveupdate;
	Button btncancel;
	
	
	private RegiInfoSource info; 
	private String ssn;
	private String key;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.docupdate);
		
		ssn = getIntent().getStringExtra("ssn");
		key = getIntent().getStringExtra("key");
		
		etxtallergies = (EditText)findViewById(R.id.etxt_allergies);
		etxthistory = (EditText)findViewById(R.id.etxt_medicalhistory);
		btnsaveupdate = (Button)findViewById(R.id.btn_saveupdate);
		btncancel = (Button) findViewById(R.id.btn_cancel);
		
		btnsaveupdate.setOnClickListener(saveupdateListener);
		btncancel.setOnClickListener(cancelListener);
		
		info = new RegiInfoSource(this);
		info.open();
		
		RegiInfo updatedata = info.searchsamePatientRegiInfo(encryption.aesEncrypt(ssn, key));
		
		etxtallergies.setText(encryption.aesDecrypt(updatedata.getAllergies(), key));
		etxthistory.setText(encryption.aesDecrypt(updatedata.getMedicalhistory(), key));
	}
	
	private OnClickListener saveupdateListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			// update database, back to specific patient record
			String Allergiesupdate=encryption.aesEncrypt(etxtallergies.getText().toString(), key);
			String MHupdate = encryption.aesEncrypt(etxthistory.getText().toString(), key);
			if(!info.updateRegiInfoUsingSSN(encryption.aesEncrypt(ssn, key), Allergiesupdate, MHupdate)){
				Toast.makeText(getApplicationContext(), "Update Failed", Toast.LENGTH_SHORT).show();
				return;
			}
			else
				Toast.makeText(getApplicationContext(), "Update Successful", Toast.LENGTH_SHORT).show();
			
			finish();
			
		}
		
	};
	
	private OnClickListener cancelListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			// back to specific patient record
			finish();		
		}
		
	};
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
