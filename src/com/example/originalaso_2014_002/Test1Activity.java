package com.example.originalaso_2014_002;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Test1Activity extends Activity implements View.OnClickListener {

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ

		switch(v.getId()) { //どのボタンが押されたか判定

		case R.id.send://登録
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

		case R.id.b1: //戻るボタンが押された
			finish();

			break;
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test1);

		Intent intent = getIntent();

		String name = intent.getStringExtra("v");

		TextView tv = (TextView)findViewById(R.id.test1);
		tv.setText("シラバスは、" + name + "です。");
	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ

		Button b1 = (Button)findViewById(R.id.b1);
		//戻るボタン変数にリスナーを登録する
		b1.setOnClickListener(this);


		super.onResume();



	}

}
