package com.example.originalaso_2014_002;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener{

	@Override
	protected void onResume(){
	super.onResume();
	Button btn = (Button)findViewById(R.id.button1);
    btn.setOnClickListener(this);
    Button btn2 = (Button)findViewById(R.id.button2);
    btn2.setOnClickListener(this);
    Button btn3 = (Button)findViewById(R.id.button3);
    btn3.setOnClickListener(this);
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
			
		case R.id.button2:
			break;
			
		case R.id.button3:
			intent = new Intent(this, HitokotoActivity.class);
			startActivity(intent);
			break;
		}
	}
	
		
		



	

	
}


