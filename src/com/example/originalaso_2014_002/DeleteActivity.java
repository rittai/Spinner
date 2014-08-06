package com.example.originalaso_2014_002;

import android.app.Activity;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author masatoge
 */

public class DeleteActivity extends Activity implements
	View.OnClickListener,AdapterView.OnItemClickListener{

	//SQLiteデータベース空間を操作するインスタンス変数を宣言
			SQLiteDatabase sdb = null;
			//MySQLiteOpenHelperを操作するインスタンス変数を宣言
			MySQLiteOpenHelper helper = null;

			//リストにて選択したHitokotoテーブルのレコードの[_id]カラム値を保存する変数の宣言
			int selectedID = -1;
			//リストにて選択した行番号を保持する変数の宣言
			int lastPosition = -1;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.delete);
	}
	 @Override
	 protected void onResume(){
		 super.onResume();

		 //各view部品を操作するidを取得
		 Button btnDelete = (Button)findViewById(R.id.button2);
		 Button btnMainte_Back = (Button)findViewById(R.id.button1);
		 ListView lstHitokoto = (ListView)findViewById(R.id.listView1);

		 //各ButtonにOnClickListenerをセット
		 btnDelete.setOnClickListener(this);
		 btnMainte_Back.setOnClickListener(this);

		 //ListViewにOnItemClickListenerをセット
		 lstHitokoto.setOnItemClickListener(this);

		 //ListViewにDBの値をセット
		 this.setDBValuetoList(lstHitokoto);

	 }

	 /**
	  * 引数のListViewにDBのデータをセット
	  * @param lstHitokoto 対象となるListView
	  */
	 private void setDBValuetoList(ListView lstHitokoto){

		 SQLiteCursor cursor = null;

		 //クラスのフィールド変数がNULLなら、データベース空間オープン
		 if(sdb==null){
			 helper = new MySQLiteOpenHelper(getApplicationContext());
		 }
		 try{
			 sdb=helper.getWritableDatabase();
		 }catch(SQLiteException e){
			 //異常終了
			 Log.e("ERROR", e.toString());
		 }
		 //MySQLiteOpenHelperにSELECT文を実行させて結果のカーソルを受ける
		 cursor = this.helper.selectHitokotoList(sdb);

		 //dblayout:ListViewにさらにレイアウトを指定するもの
		 int db_layout = android.R.layout.simple_list_item_activated_2;
		 //form:カーソルからListViewに指定するカラムの値を指定するもの

		 String[]from = {"_id","pass"};
		 //to:Listviewの中に指定したdb_layoutに配置する、各行のview部品のid
		 int[] to = new int[]{android.R.id.text1,android.R.id.text2};

		 //ListViewにセットするアダプターを生成
		 //カーソルをもとに、fromの列から、toのViewへ値のマッピングが行われる。
		 SimpleCursorAdapter adapter =
				 new SimpleCursorAdapter(this,db_layout,cursor,from,to,0);

		 //アダプターを設定します
		 lstHitokoto.setAdapter(adapter);
	 }
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long _id) {
		// TODO 自動生成されたメソッド・スタブ

		//前に選択中の行があれば、背景色を透明にする
		if(this.selectedID!=-1){
			parent.getChildAt(this.lastPosition).setBackgroundColor(0);
		}
		//選択中の行の背景色をグレーにする
		view.setBackgroundColor(android.graphics.Color.LTGRAY);

		//選択行のレコードを指し示すカーソルを取得
		SQLiteCursor cursor = (SQLiteCursor)parent.getItemAtPosition(position);
		//カーソルのレコードから、「_id」の値を取得して記憶
		this.selectedID = cursor.getInt(cursor.getColumnIndex("_id"));
		//何行目を選択したかも記憶
		this.lastPosition = position;
	}
	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ

		switch(v.getId()){//どのボタンが押されたか判定
			case R.id.button2: //削除ボタンが押された

				//選択行があれば
				if(this.selectedID != -1){
					this.deleteFromHitokoto(this.selectedID);
					ListView lstHitokoto = (ListView)findViewById(R.id.listView1);
					//ListViewにＤＢをセット
					this.setDBValuetoList(lstHitokoto);
					//選択行を忘れる
					this.selectedID = -1;
					this.lastPosition = -1;
				}
				else{
					LayoutInflater inflater = getLayoutInflater();
					View layout = inflater.inflate(R.layout.iv, null);

					ImageView image = (ImageView)layout.findViewById(R.id.img);
					image.setImageResource(R.drawable.yubi);

					TextView text = (TextView)layout.findViewById(R.id.text);
					text.setText("さ、削除する行を選択しなさい!!");
					text.setTextColor(0xffff0000);

					Toast toast = new Toast(this);
					toast.setView(layout);
					toast.show();

					/*
					ImageView vv = new ImageView(this);
					vv.setImageResource(R.drawable.yubi);
					Toast toast = new Toast(this);

					//toast.setDuration(Toast.LENGTH_LONG);
					toast.setView(vv);

					//Toast.makeText(DeleteActivity.this, "削除する行を選びなさい", Toast.LENGTH_LONG);
					toast.show();
					*/


					/*
					//LayoutInflater inflater = getLayoutInflater();
					//View vv = inflater.inflate(R.layout.iv, null);
					//Toast toast = new Toast(this);
					//toast.setDuration(Toast.LENGTH_SHORT);
					//Toast.makeText(DeleteActivity.this, "削除する行を選んでください", Toast.LENGTH_SHORT).show();
					//toast.setView(vv);
					 */
				}
				break;
			case R.id.button1://戻るボタンが押された
				//今の画面Activityを消して、前の画面Activityに戻る
				finish();
				break;

		}

	}
	private void deleteFromHitokoto(int _id) {
		// TODO 自動生成されたメソッド・スタブ
		//クラスのフィールド変数がＮＵＬＬなら、データベース空間オープン
		if(sdb == null){
			helper = new MySQLiteOpenHelper(getApplicationContext());
		}
		try{
			sdb = helper.getWritableDatabase();
		}catch(SQLiteException e){
			//以上終了
			Log.e("ERROR",e.toString());
		}
		//MySQLiteOpenHelperにDELETE文を実行させる
		this.helper.deleteHitokoto(sdb, _id);
	}
}

