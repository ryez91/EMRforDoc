package com.example.elecmr;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class InfoSQLiteHelper extends SQLiteOpenHelper{

	//Table name
	public static final String TABLE_REGI= "registration_info";  //no space!
	public static final String TABLE_DOC ="doctor_info";
	
	//Column names
	
	//TABLE_DOC
	public static final String COLUMN_DOCID="_doctorid";
	public static final String COLUMN_USERNAME="_doctorname";
	public static final String COLUMN_PASSWORD="_doctorpassword";
	
	
	//TABLE_REGI
	public static final String COLUMN_ID= "_id";
	public static final String COLUMN_FIRSTNAME="_firstname";
	public static final String COLUMN_LASTNAME="_lastname";
	public static final String COLUMN_DOB="_dob";
	public static final String COLUMN_SSN="_ssn";
	public static final String COLUMN_GENDER = "_gender";
	
	public static final String COLUMN_IID ="_insuranceid";
	public static final String COLUMN_ADDRESS = "_address";
	public static final String COLUMN_PHONE = "_phone";
	public static final String COLUMN_ALLERGIES= "_allergies";
	public static final String COLUMN_MEDICALHIS ="_medicalhistory";
	
	
	
	private static final String DATABASE_NAME = "EMR";
	private static final int DATABASE_VERSION= 5;
	
	//Database creation sql statement
	private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_REGI +"(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			 + COLUMN_FIRSTNAME + " TEXT, " + COLUMN_LASTNAME +" TEXT, " + COLUMN_DOB +" TEXT, " + COLUMN_SSN 
			 + " TEXT, " + COLUMN_GENDER + " TEXT, " + COLUMN_IID + " TEXT, " + COLUMN_ADDRESS + " TEXT, " + 
			 COLUMN_PHONE + " TEXT, " + COLUMN_ALLERGIES + " TEXT, " + COLUMN_MEDICALHIS + " TEXT );";
	
	//final should be " TEXT );"
	
	private static final String DATABASE2_CREATE = "CREATE TABLE " + TABLE_DOC + "(" + COLUMN_DOCID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
	COLUMN_USERNAME + " TEXT, " + COLUMN_PASSWORD + " TEXT );";
	
	
	public InfoSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}



	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DATABASE_CREATE);
		db.execSQL(DATABASE2_CREATE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.w(InfoSQLiteHelper.class.getName(), "Upgrading database from version " + oldVersion + "to" + newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGI);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOC);
		onCreate(db);
		
	}

}
