package com.example.elecmr;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DocInfoSource {
	private SQLiteDatabase db;
	private InfoSQLiteHelper dbHelper;
	
	private String[] doctorColumns = {InfoSQLiteHelper.COLUMN_DOCID, InfoSQLiteHelper.COLUMN_USERNAME, InfoSQLiteHelper.COLUMN_PASSWORD};
	
	public DocInfoSource(Context context){
		dbHelper = new InfoSQLiteHelper(context);
		
	}
	
	public void open() throws SQLException{
		db = dbHelper.getWritableDatabase();
	}
	
	public void close(){
		dbHelper.close();
	}
	
	
	//search the username
	public DocInfo searchsameNameDocInfo(String username){
		//Cursor cursor = db.rawQuery("SELECT * FROM " + InfoSQLiteHelper.TABLE_REGI + " WHERE " + InfoSQLiteHelper.COLUMN_USERNAME + " ='" + username +"'", null);
		
		Cursor cursor = db.query(InfoSQLiteHelper.TABLE_DOC, doctorColumns, InfoSQLiteHelper.COLUMN_USERNAME + " = '" + username + "'", null, null, null, null);
		cursor.moveToFirst();
		
		if(cursor.getCount() == 0){
			cursor.close();
			return null;
			// search the same username
		}else{
			DocInfo docinfo = cursorToDocInfo(cursor);
			cursor.close();
			return docinfo;
		}
	}
	
	//get the username and password, return in memberInfo
		private DocInfo cursorToDocInfo(Cursor cursor){
			DocInfo memberInfo= new DocInfo();
			memberInfo.setId(cursor.getInt(0));
			memberInfo.setUsername(cursor.getString(1));
			memberInfo.setPassword(cursor.getString(2));
			return memberInfo;
			
		}
		
		public DocInfo createDocInfo(String name, String password){
			ContentValues values = new ContentValues();
			values.put(InfoSQLiteHelper.COLUMN_USERNAME, name);
			values.put(InfoSQLiteHelper.COLUMN_PASSWORD, password);
			int insertId =(int) db.insert(InfoSQLiteHelper.TABLE_DOC, null, values);
			Cursor cursor = db.query(InfoSQLiteHelper.TABLE_DOC, doctorColumns, InfoSQLiteHelper.COLUMN_DOCID + " = " + insertId, null, null, null, null);
		
			cursor.moveToFirst();
			DocInfo newMemberInfo = cursorToDocInfo(cursor);
			cursor.close();
			return newMemberInfo;
		}
		
		public void deleteDocInfo(String username){
			//System.out.println("Info deleted with id: " + id);
			db.delete(InfoSQLiteHelper.TABLE_DOC, InfoSQLiteHelper.COLUMN_USERNAME + "=" + username, null);
			
		}

}
