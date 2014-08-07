package com.example.originalaso_2014_002;


import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MsgActivity extends Activity implements View.OnClickListener {

	SQLiteDatabase sdb = null;
	MySQLiteOpenHelper helper = null;
	
	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ

				switch(v.getId()) { //どのボタンが押されたか判定

				case R.id.send://送信
					EditText etv = (EditText)findViewById(R.id.sedit);
					String inputMsg = etv.getText().toString();

					if(inputMsg!=null && !inputMsg.isEmpty()){
						//helper.insertHitokoto(sdb,  inputMsg);
					}
					etv.setText("");
					break;


				case R.id.imageButton1://登録
					finish();

					break;

				case R.id.b: //戻るボタンが押された
					finish();

					break;
				}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		setContentView(R.layout.msg);

		Intent intent = getIntent();

		String name = intent.getStringExtra("v");

		TextView tv = (TextView)findViewById(R.id.tv);
		tv.setText(name);



	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ

		Button b = (Button)findViewById(R.id.b);
		//戻るボタン変数にリスナーを登録する
		b.setOnClickListener(this);

		Button send = (Button)findViewById(R.id.send);
		//戻るボタン変数にリスナーを登録する
		send.setOnClickListener(this);

		/*
		Button imageButton1 = (Button)findViewById(R.id.imageButton1);
		//戻るボタン変数にリスナーを登録する
		imageButton1.setOnClickListener(this);
		*/

		//イメージボタンの取得
        ImageButton imageButton1 = (ImageButton)findViewById(R.id.imageButton1);
         //クリックイベントの通知先指定
        imageButton1.setOnClickListener(this);


		super.onResume();

	}

}
