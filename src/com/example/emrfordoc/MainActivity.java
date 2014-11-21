package com.example.emrfordoc;


import com.example.elecmr.DocInfo;
import com.example.elecmr.DocInfoSource;
import com.example.elecmr.RegiInfo;
import com.example.elecmr.RegiInfoSource;
import com.example.trustworthy.encryption;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private EditText ID;
	private EditText password;
	private Button signin;
	

	private DocInfoSource docinfosource;
	private RegiInfoSource patientinfosource;
	private String login_name;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		ID = (EditText)findViewById(R.id.ID);
        password = (EditText)findViewById(R.id.password_setting);
        signin = (Button)findViewById(R.id.signin);
        
        signin.setOnClickListener(signlistener);
        docinfosource = new DocInfoSource(this);
        docinfosource.open();
        
        patientinfosource = new RegiInfoSource(this);
        patientinfosource.open();
        
        
        DocInfo docdata = docinfosource.searchsameNameDocInfo("doctor1");
        if(docdata==null){
        	docinfosource.createDocInfo("doctor1", encryption.MD5(encryption.MD5("12345678")));
        }
        
        /*RegiInfo patientdata = patientinfosource.searchsamePatientRegiInfo(encryption.MD5(encryption.MD5("135246357")));
        if(patientdata==null){
        patientinfosource.createBasicInfo("Spencer", "Liu", "03/01/1986", "135246357", 
        		"Female", "234567890", "11 Holley Street", "123678945", "None", "None");
        }
        RegiInfo patientdata_ = patientinfosource.searchsamePatientRegiInfo("3057789115");
        if(patientdata_ ==null){
        patientinfosource.createBasicInfo("Emily", "He", "11/29/1989", "889632447", "Female", "894209301", "New York", "678291032", "None", "None");
        }*/
        
	}
	
	private OnClickListener signlistener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			login_name = ID.getText().toString();
			String inputpw = password.getText().toString();
			DocInfo logindata = docinfosource.searchsameNameDocInfo(login_name);
			if(logindata==null){
				Toast.makeText(getApplicationContext(), "Sorry can't access!", Toast.LENGTH_LONG).show();
			}else{
				if(encryption.MD5(encryption.MD5(inputpw)).equals(logindata.getPassword())){
					String keygen = encryption.MD5("11111" + encryption.MD5(inputpw) + "22222");
					Intent myIntent = new Intent(getApplicationContext(), Activity_Homepage.class);
					Bundle bundle =new Bundle();
					bundle.putString("key",keygen);
					myIntent.putExtras(bundle);
					startActivity(myIntent);
				}else{
					Toast.makeText(getApplicationContext(), "Sorry can't access!", Toast.LENGTH_LONG).show();
				}
				

			}
			
		}
		
	};
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
