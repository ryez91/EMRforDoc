package com.example.emrfordoc;

import java.util.ArrayList;
import java.util.HashMap;

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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Activity_Login_specificpatcient extends Activity{
	
	ListView listpatientrec;
	Button btnupdate;
	Button btnbacklist;
	
	private String ssn;
	private String key;
	
	private String[] columnname = {"First Name: ", "Last Name: ", "Gender: ", "Date of Birth: ", "SSN: ", "Insurance ID: ", "Address: ", "Phone: ", "Allergies: ", "Medical History: "};
	//list View trial
	//private String[] patientcolumns ={"Anna", "Lin", "Female", "01/01/1990", "3456789", "123123123123", "10 Cannal Street", "5229006777", "Flower", "shiwe"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.specifpatientrecord);
		
		btnupdate = (Button)findViewById(R.id.btn_update);
		btnbacklist = (Button)findViewById(R.id.btn_backlistrecords);
		btnupdate.setOnClickListener(updateListener);
		btnbacklist.setOnClickListener(backlistListener);
		
		ssn = getIntent().getStringExtra("ssn");
		key = getIntent().getStringExtra("key");
		
		/*
		RegiInfoSource info = new RegiInfoSource(this);
		info.open();
		
		RegiInfo user = info.searchsamePatientRegiInfo(ssn);
		if(user == null){
			Toast.makeText(getApplicationContext(), "Cannot find the user", Toast.LENGTH_SHORT).show();
			finish();
		}
		
		String[] patientcolumns = new String[10];
		
		patientcolumns[0] = user.getFirstname();
		patientcolumns[1] = user.getLastname();
		patientcolumns[2] = user.getGender();
		patientcolumns[3] = user.getDob();
		patientcolumns[4] = user.getSsn();
		patientcolumns[5] = user.getInid();
		patientcolumns[6] = user.getAddress();
		patientcolumns[7] = user.getPhone();
		patientcolumns[8] = user.getAllergies();
		patientcolumns[9] = user.getMedicalhistory();
		
		listpatientrec = (ListView)findViewById(R.id.listview_spcificrecord);
		ArrayList<HashMap<String,String>> patientdetail= new ArrayList<HashMap<String, String>>();
		
		for(int i=0; i<10; i++){
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("listTitle", columnname[i]);
			map.put("listContent", patientcolumns[i]);
			patientdetail.add(map);
		}
		
		SimpleAdapter specificpatient = new SimpleAdapter(this, patientdetail, R.layout.listpatientdetails, new String[] {"listTitle", "listContent"}, new int[] {R.id.txt_listTitle, R.id.txt_listcontext});
		listpatientrec.setAdapter(specificpatient);
		
		*/
		
		
	}
	
	@Override
	protected void onStart(){
		super.onStart();
		
		
		RegiInfoSource info = new RegiInfoSource(this);
		info.open();
		
		RegiInfo user = info.searchsamePatientRegiInfo(encryption.aesEncrypt(ssn, key));
		if(user == null){
			Toast.makeText(getApplicationContext(), "Cannot find the patient!", Toast.LENGTH_SHORT).show();
			finish();
			return;
		}
		
		String[] patientcolumns = new String[10];
		
		patientcolumns[0] = encryption.aesDecrypt(user.getFirstname(),key);
		patientcolumns[1] = encryption.aesDecrypt(user.getLastname(),key);
		patientcolumns[2] = encryption.aesDecrypt(user.getGender(),key);
		patientcolumns[3] = encryption.aesDecrypt(user.getDob(),key);
		patientcolumns[4] = encryption.aesDecrypt(user.getSsn(),key);
		patientcolumns[5] = encryption.aesDecrypt(user.getInid(),key);
		patientcolumns[6] = encryption.aesDecrypt(user.getAddress(),key);
		patientcolumns[7] = encryption.aesDecrypt(user.getPhone(),key);
		patientcolumns[8] = encryption.aesDecrypt(user.getAllergies(),key);
		patientcolumns[9] = encryption.aesDecrypt(user.getMedicalhistory(),key);
		
		listpatientrec = (ListView)findViewById(R.id.listview_spcificrecord);
		ArrayList<HashMap<String,String>> patientdetail= new ArrayList<HashMap<String, String>>();
		
		for(int i=0; i<10; i++){
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("listTitle", columnname[i]);
			map.put("listContent", patientcolumns[i]);
			patientdetail.add(map);
		}
		
		SimpleAdapter specificpatient = new SimpleAdapter(this, patientdetail, R.layout.listpatientdetails, new String[] {"listTitle", "listContent"}, new int[] {R.id.txt_listTitle, R.id.txt_listcontext});
		listpatientrec.setAdapter(specificpatient);
	}
	
	
	
	private OnClickListener updateListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			// jump to update allergies and history page
			Intent myIntent = new Intent(getApplicationContext(),Activity_updatebydoc.class);
			myIntent.putExtra("ssn", ssn);
			myIntent.putExtra("key", key);
			startActivity(myIntent);
		}
		
	};
	
	private OnClickListener backlistListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			// jump to list records
			Intent myIntent = new Intent(getApplicationContext(),Activity_Login_recordshow4doc.class);
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
