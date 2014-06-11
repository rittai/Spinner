package com.example.originalaso_2014_002;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HitokotoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hitokoto);

	}
	
	@Override
	protected void onResume(){
		super.onResume();
		
		//画面に渡されたインテントを取得
		Intent intent = this.getIntent();
		//intentから、Extraに混入させた値をキーワードで取得
		String strHitokoto = intent.getStringExtra("hitokoto");
		
		//取得したStringを、TxtViewにセット
		TextView txtHITOKOTO = (TextView)findViewById(R.id.textView1);
		txtHITOKOTO.setText(strHitokoto);
	}

}
