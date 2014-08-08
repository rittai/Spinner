package com.example.originalaso_2014_002;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
	/**
	 * @param context 呼び出しコンテクスト
	 * @param name 利用DB名
	 * @param factory カーソルファクトリー
	 * @param version DBバージョン
	 */

	public MySQLiteOpenHelper(Context context){
		super(context,"20140021201762.sqlite3",null,8);
	}



	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO 自動生成されたメソッド・スタブ
		db.execSQL("CREATE TABLE IF NOT EXISTS " +
				"Hitokoto(_id integer primary key  not null , pass text, flg integer)");

		db.execSQL("CREATE TABLE IF NOT EXISTS " +
				"Kind(num integer not null , class text)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO 自動生成されたメソッド・スタブ
		db.execSQL("drop table Hitokoto;");
		onCreate(db);

	}

	public void insertHitokoto(SQLiteDatabase db,String inputMsg,String inputmsg2,String flg){
		Log.d(inputMsg,inputMsg);
		Log.d(inputmsg2,inputmsg2);
		String sqlstr = " insert into Hitokoto (_id,pass,flg) values('" + inputMsg +"','" +inputmsg2 + "','" + flg + "');";
			try{
				//トランザクション開始
				db.beginTransaction();
				db.execSQL(sqlstr);
				//トランザクション成功
				db.setTransactionSuccessful();
			}catch(SQLException e){
				Log.e("ERROR", e.toString());
			}finally{
				//トランザクション終了
				db.endTransaction();
			}
			return;
	}
	//メッセージの内容を登録するSQL
	/**
	public void insertmsg(SQLiteDatabase db,String inputnum,String inputMsg){

		String sqlstr2 = " insert into kind (num,class) values('" + inputnum +"','" +inputMsg + "');";
			try{
				//トランザクション開始
				db.beginTransaction();
				db.execSQL(sqlstr2);
				//トランザクション成功
				db.setTransactionSuccessful();
			}catch(SQLException e){
				Log.e("ERROR", e.toString());
			}finally{
				//トランザクション終了
				db.endTransaction();
			}
			return;
	}
	**/
/**
	public String selectRandomHitokoto(SQLiteDatabase db){

		String rtString = null;

		String sqlstr = "SELECT _id, pass FROM Hitokoto ORDER BY RANDOM();";
			try{
				//トランザクション開始
				SQLiteCursor cursor = (SQLiteCursor)db.rawQuery(sqlstr,null);
				if(cursor.getCount()!=0){
					//カーソル開始位置を先頭にする
					cursor.moveToFirst();
					rtString = cursor.getString(1);
				}
				cursor.close();
			}catch(SQLException e){
				Log.e("ERROR",e.toString());
			}finally{
				//すでにカーソルもcloseしてあるので、何もしない
			}
			return rtString;
	}
	**/

	public String login(SQLiteDatabase db,String idmsg,String passmsg){

		String flgString = null;
		//学籍番号とDBの_idを比較
		String sqllogin = "SELECT _id,pass FROM Hitokoto WHERE '"+ idmsg +"' = _id ;";

		try{
			//トランザクション開始
			SQLiteCursor cursor = (SQLiteCursor)db.rawQuery(sqllogin,null);
			cursor.moveToFirst();
			//DB 指定列のインデックス取得
			int index_id    =   cursor.getColumnIndex("_id");
			int indexpass    =   cursor.getColumnIndex("pass");
			//DB インデックスに対応した値を取得
			String id = cursor.getString(index_id);
			String pass = cursor.getString(indexpass);


			if(idmsg.equals(id) && passmsg.equals(pass)){
				flgString = "SELECT flg FROM Hitokoto WHERE _id = '" + id + "' ;";
				SQLiteCursor cursor2 = (SQLiteCursor)db.rawQuery(flgString,null);
				if(cursor2.getCount()!=0){
					//カーソル開始位置を先頭にする
					cursor2.moveToFirst();
					flgString = cursor2.getString(0);
				}
			}else{
				flgString = "miss";
			}
			cursor.close();

		}catch(SQLException e){
			Log.e("ERROR", e.toString());
		}finally{
			//すでにカーソルもcloseしてあるので、何もしない
		}
		return flgString ;

	}


	/**
	 * Hitokotoテーブルからデータをすべて取得
	 * @param SQLいてDatabase SELECTアクセスするDBのインスタンス変数
	 * @return 取得したデータの塊の表（導出表）のレコードをポイントするカーソル
	 **/
	public SQLiteCursor selectHitokotoList(SQLiteDatabase db){

		SQLiteCursor cursor = null;

		String sqlstr = "SELECT num, class FROM Kind ORDER BY num;";
		try{
			//トランザクション開始
			cursor = (SQLiteCursor)db.rawQuery(sqlstr, null);
			if(cursor.getCount()!=0){
				//カーソル開始位置を先頭にする
				cursor.moveToFirst();
			}
			//cursorlは呼び出し元へ返すからここではcloseしない
			//cursor.close();

		}catch(SQLException e){
			Log.e("ERROR", e.toString());
		}finally{

		}
		return cursor;

	}

	/**
	 * Hitokoto表から引数（id）で指定した値とカラム「_id」の値が等しいレコードを削除
	 * @param SQLiteDatabase DELETEアクセスするDBのインスタンス変数
	 * @param id カラム「_id」と比較するために指定する削除条件の値
	 */
	public void deleteHitokoto(SQLiteDatabase db, int _id){

		String sqlstr = "DELETE FROM Hitokoto where _id="+ _id +";";
		try{
			//トランザクション開始
			db.beginTransaction();
			db.execSQL(sqlstr);
			//トランザクション成功
			db.setTransactionSuccessful();
		}catch(SQLException e){
			Log.e("ERROR", e.toString());
		}finally{
			//トランザクション終了
			db.endTransaction();
		}

	}


}
