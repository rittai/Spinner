package com.example.originalaso_2014_002;



import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements View.OnClickListener{
	
	SQLiteDatabase sdb = null;
	MySQLiteOpenHelper helper = null;

	@Override
	protected void onResume(){
	super.onResume();
	Button btn = (Button)findViewById(R.id.button1);
    btn.setOnClickListener(this);
    Button btn2 = (Button)findViewById(R.id.button2);
    btn2.setOnClickListener(this);
    Button btn3 = (Button)findViewById(R.id.button3);
    btn3.setOnClickListener(this);
    
    //クラスのフィールド変数がnullなら、データベース空間オープン
    if(sdb == null){
    	helper = new MySQLiteOpenHelper(getApplicationContext());
    }
    try{
    	sdb = helper.getWritableDatabase();
    }catch(SQLiteException e){
    	//異常終了
    	return;
    }
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	@Override
	public void onClick(View v){
		Intent intent = null;
		switch(v.getId()){
		case R.id.button1:
			intent = new Intent(this, DeleteActivity.class);
			startActivity(intent);
			break;
			
		case R.id.button2://登録ボタンが押された
			//エディットテキストからの入力内容を取り出す
			EditText etv = (EditText)findViewById(R.id.editText1);
			String inputMsg = etv.getText().toString();
			
			//inputMsgがnullでない、かつ、空でない場合のみ、if内容を実行
			if(inputMsg!=null && !inputMsg.isEmpty()){
				//MySQLiteOpenHelperのインサートメソッドを呼び出し
				helper.insertHitokoto(sdb, inputMsg);
			}
			//入力欄をクリア
			etv.setText("");
			break;
			
		case R.id.button3:
			//MySQLiteOpenHelperのセレクト一言メソッドを呼び出して一言をランダムに取得
			String strHitokoto = helper.selectRandomHitokoto(sdb);
			
			
			intent = new Intent(this, HitokotoActivity.class);
			intent.putExtra("hitokoto", strHitokoto);
			
			startActivity(intent);
			break;
		}
	}
	

		
		



	

	
}


