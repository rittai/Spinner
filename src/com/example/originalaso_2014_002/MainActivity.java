package com.example.originalaso_2014_002;



import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    Button btn4 = (Button)findViewById(R.id.button4);
    btn4.setOnClickListener(this);

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
		case R.id.button1://メンテボタンが押された
			intent = new Intent(this, DeleteActivity.class);
			startActivity(intent);
			break;

		case R.id.button2://登録ボタンが押された
			//エディットテキストからの入力内容を取り出す
			EditText etv = (EditText)findViewById(R.id.editText1);
			EditText etv2 = (EditText)findViewById(R.id.editText2);
			EditText etv3 = (EditText)findViewById(R.id.editText3);

			String inputMsg = etv.getText().toString();
			Log.d(inputMsg, inputMsg);
			String inputmsg2 = etv2.getText().toString();
			Log.d(inputmsg2,inputmsg2);
			String flg = etv3.getText().toString();



			//inputMsgがnullでない、かつ、空でない場合のみ、if内容を実行
			if(inputMsg!=null && !inputMsg.isEmpty()){
				//MySQLiteOpenHelperのインサートメソッドを呼び出し
				helper.insertHitokoto(sdb, inputMsg,inputmsg2,flg);
			}
			//入力欄をクリア
			etv.setText("");
			etv2.setText("");
			etv3.setText("");
			break;

		case R.id.button3:
			//MySQLiteOpenHelperのセレクト一言メソッドを呼び出して一言をランダムに取得
			String strHitokoto = helper.selectRandomHitokoto(sdb);


			intent = new Intent(this, HitokotoActivity.class);
			intent.putExtra("hitokoto", strHitokoto);

			startActivity(intent);
			break;


		//ログインボタンを押したときの処理
		case R.id.button4:
			EditText id = (EditText)findViewById(R.id.editText1);
			EditText pass = (EditText)findViewById(R.id.editText2);

			String idmsg = id.getText().toString();
			String passmsg = pass.getText().toString();

			if(idmsg!=null && !passmsg.isEmpty()){
			//MySQLiteOpenHelperのloginに飛ぶ
			String flgString = helper.login(sdb, idmsg, passmsg);
			//Log.d(x,x);
			//int c = Integer.parseInt(idmsg);

			//エディットテキスト１に入力した値をintに変換
			//とりあえす条件式は動く
			if(flgString.equals("1")){
				intent = new Intent(this,S.class);
				startActivity(intent);
			}else if(flgString.equals("2")){
				intent = new Intent(this,T.class);
				startActivity(intent);
			}else{
				LayoutInflater inflater = getLayoutInflater();
				View layout = inflater.inflate(R.layout.iv, null);

				ImageView image = (ImageView)layout.findViewById(R.id.img);
				image.setImageResource(R.drawable.yubi);

				TextView text = (TextView)layout.findViewById(R.id.text);
				text.setText("IDとパスワードが正しくない!!");
				text.setTextColor(0xffff0000);

				Toast toast = new Toast(this);
				toast.setView(layout);
				toast.show();
			}
			}else{
				LayoutInflater inflater = getLayoutInflater();
				View layout = inflater.inflate(R.layout.iv, null);

				ImageView image = (ImageView)layout.findViewById(R.id.img);
				image.setImageResource(R.drawable.yubi);

				TextView text = (TextView)layout.findViewById(R.id.text);
				text.setText("IDとパスワードを入力しなさい!!");
				text.setTextColor(0xffff0000);

				Toast toast = new Toast(this);
				toast.setView(layout);
				toast.show();
			}
			break;


		}
	}










}


