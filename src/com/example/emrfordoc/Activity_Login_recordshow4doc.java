package com.example.emrfordoc;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.elecmr.RegiInfo;
import com.example.elecmr.RegiInfoSource;
import com.example.trustworthy.encryption;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_Login_recordshow4doc extends Activity{
	
		private final String TAG = "Base Activity";
			
		ListView listrecord;
		
		private RegiInfoSource infosource;
		public static int id;
		
		private String key;
		
		//click list trial
		//private String[] clickFN={"Aria", "Ben", "Carrie", "David", "Andy"};
		//private String[] clickLN ={"Wu", "Lee", "Green", "He", "Zhang"};
		
			
			@Override
			protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				setContentView(R.layout.showpatienctsrec);
				
				listrecord = (ListView)findViewById(R.id.listView_record);
				Bundle myBundle = this.getIntent().getExtras();
				key = myBundle.getString("key");
				
				/*
				//get the number of rows of the lists
				ArrayList<HashMap<String, String>> patientnameslist = new ArrayList<HashMap<String,String>>();
				//here to test the listview, just simply show 15 rows
				for(int i = 0; i<5; i++){
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("FirstName", clickFN[i]);
					map.put("LastName", clickLN[i]);
					patientnameslist.add(map);
				}
				
				SimpleAdapter namelist = new SimpleAdapter(this, patientnameslist, R.layout.listpatientname,new String[]{"FirstName", "LastName"}, new int[] {R.id.txt_patientfirstname,R.id.txt_patientlastname});
				listrecord.setAdapter(namelist);
				
				listrecord.setOnItemClickListener(listListener);
				
				
				
				infosource = new RegiInfoSource(this);
				infosource.open();
				
				List<RegiInfo> values = infosource.getBasicInfoaslist(id);
				
				ArrayAdapter<RegiInfo> adapter = new ArrayAdapter<RegiInfo>(this, android.R.layout.simple_list_item_1,values);
				*/

				
				
			}
			
			@Override
			protected void onStart(){
				super.onStart();
				
				
				infosource = new RegiInfoSource(this);
				infosource.open(); 
				
				
				List<RegiInfo> information = infosource.getNamelist();
				//RegiInfo info = information.get(0);
				int patientnum =information.size();
				
			
				
				
				ArrayList<HashMap<String, String>> patientnameslist = new ArrayList<HashMap<String,String>>();
				//i should be the number of rows in that information list
				
				for(int i=0; i<patientnum; i++){
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("firstname", encryption.aesDecrypt(information.get(i).getFirstname(), key));
					map.put("lastname", encryption.aesDecrypt(information.get(i).getLastname(),key));
					map.put("patientssn", encryption.aesDecrypt(information.get(i).getSsn(),key));
					/*map.put("firstname", information.get(i).getFirstname());
					map.put("lastname", information.get(i).getLastname());
					map.put("patientssn", information.get(i).getSsn());*/
					patientnameslist.add(map);
				}
				
				SimpleAdapter namelist = new SimpleAdapter(this, patientnameslist, R.layout.listpatientname,new String[]{"firstname", "lastname"}, new int[] {R.id.txt_patientfirstname,R.id.txt_patientlastname});
				listrecord.setAdapter(namelist);
				
				listrecord.setOnItemClickListener(listListener);
				listrecord.setAdapter(namelist);
				
				
			}
			
			private OnClickListener backhomeListener = new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					// jump to homepage
					Intent myIntent = new Intent(getApplicationContext(),Activity_Homepage.class);
					startActivity(myIntent);
					
				}
				
			};	
			
			private OnItemClickListener listListener = new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					ListView listView = (ListView)parent;
					HashMap<String, String> map = (HashMap<String, String>)listView.getItemAtPosition(position);
					String ssn = map.get("patientssn");
					
					Intent myIntent = new Intent(getApplicationContext(), Activity_Login_specificpatcient.class);
					myIntent.putExtra("ssn", ssn);
					myIntent.putExtra("key", key);
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
