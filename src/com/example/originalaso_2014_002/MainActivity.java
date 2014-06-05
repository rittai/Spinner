package com.example.originalaso_2014_002;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btn = (Button)findViewById(R.id.button3);
	    btn.setOnClickListener(new View.OnClickListener() {
	    	
	    	
	    	@Override
	    	public void onClick(View v){
	    		
	    		Intent intent = null;
	    		
	    		intent = new Intent(MainActivity.this, HitokotoActivity.class);
				startActivity(intent);

	    	}
	    });
		
	    Button btn2 = (Button)findViewById(R.id.button1);
	    btn2.setOnClickListener(new View.OnClickListener() {
		
	    	@Override
	    	public void onClick(View v){
	    		
	    		Intent intent = null;
	    		
	    		intent = new Intent(MainActivity.this, DeleteActivity.class);
	    		startActivity(intent);
	    	}
		
	    });
	}

	

	
}


