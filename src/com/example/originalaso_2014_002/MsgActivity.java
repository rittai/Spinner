package com.example.originalaso_2014_002;


import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MsgActivity extends Activity implements View.OnClickListener {

	SQLiteDatabase sdb = null;
	MySQLiteOpenHelper helper = null;
	private Spinner selectSpinner;
	
	
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

	
       // ArrayAdapter<CharSequence> adapter =
         //   ArrayAdapter.createFromResource(this, R.id.spinner1,
           //                                 android.R.layout.simple_spinner_item);
        
		//spinnerの設定
		
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item);
        adapter.add("一般教養");
        adapter.add("国家試験対策");
        adapter.add("プログラマの数学");
        adapter.add("開発");
        adapter.add("設計");
        
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        selectSpinner = (Spinner) findViewById(R.id.spinner1);
        selectSpinner.setAdapter(adapter);
        selectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner) parent;
                //配列の番号が表示される
                showToast(Integer.toString(spinner.getSelectedItemPosition()));
            }
 
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        
     // ボタンの設定
        Button buttonCheckSelected;
        buttonCheckSelected = (Button)findViewById(R.id.send);
        buttonCheckSelected.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
            	//spinnerの登録文字が表示される
                showToast((String)selectSpinner.getSelectedItem());
                
                EditText etv = (EditText)findViewById(R.id.sedit);
                //コメント内容
				String inputMsg = etv.getText().toString();
				//学科番号
				String inputnum = (String) selectSpinner.getSelectedItem();
				Log.d(inputnum,inputnum);
				Log.d(inputMsg,inputMsg);
				
				
				//swich文書けませんでした
				if(inputnum.equals("一般教養")){
					inputnum = "0";
				}
				if(inputnum.equals("国家試験対策")){
					inputnum = "1";
				}
				if(inputnum.equals("プログラマの数学")){
					inputnum = "2";
				}
				if(inputnum.equals("開発")){
					inputnum = "3";
				}
				if(inputnum.equals("設計")){
					inputnum = "4";
				}
				//学科番号とコメントは変数に格納されてます！！
				Log.d(inputnum,inputnum);

				if(inputMsg!=null && !inputMsg.isEmpty()){
					//helper.insertmsg(sdb, inputnum, inputMsg);
				}
				etv.setText("");
            }
        });
    }
        
	private void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    
        
        /**

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
