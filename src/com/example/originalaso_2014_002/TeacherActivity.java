package com.example.originalaso_2014_002;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.SimpleExpandableListAdapter;

public class TeacherActivity extends Activity {

	Intent intent = null;

	  private static final String KEY1 = "GROUP";
	  private static final String KEY2 = "CHILD";

	  // 表示させる文字列
	  private String[] GROUPS = {"一般教養", "国家試験対策", "プログラマの数学"};
	  private String[][][] CHILDREN = {
	      {{"メッセージ送信",""}, {"漢字",""}},
	      {{"メッセージ送信",""}, {"アルゴリズム",""}},
	      {{"メッセージ送信",""}, {"ピラミッド",""}},
	  };

	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.teacher);

	    // 設定する文字列のリスト
	    List<Map<String, String>> groupData =
	        new ArrayList<Map<String, String>>();
	    List<List<Map<String, String>>> childData =
	        new ArrayList<List<Map<String, String>>>();

	    // リストに文字列を設定していく
	    for (int i = 0; i < GROUPS.length; i++) {
	      // 親要素の追加
	      Map<String, String> curGroupMap =
	          new HashMap<String, String>();
	      groupData.add(curGroupMap);
	      curGroupMap.put(KEY1, GROUPS[i]);
	      curGroupMap.put(KEY2, "");

	      List<Map<String, String>> children =
	          new ArrayList<Map<String, String>>();
	      if (CHILDREN.length > i) {
	        for (int j = 0; j < CHILDREN[i].length; j++) {
	          // 子要素の追加
	          Map<String, String> curChildMap =
	              new HashMap<String, String>();
	          children.add(curChildMap);
	          curChildMap.put(KEY1, CHILDREN[i][j][0]);
	          curChildMap.put(KEY2, CHILDREN[i][j][1]);
	        }
	      }
	      childData.add(children);
	    }

	    // ExpandbleListAdapter の作成
	    ExpandableListAdapter adapter =
	        new SimpleExpandableListAdapter(
	            this,
	            groupData,
	            android.R.layout.simple_expandable_list_item_1,
	            new String[] { KEY1, KEY2 },
	            new int[] { android.R.id.text1, android.R.id.text2 },
	            childData,
	            android.R.layout.simple_expandable_list_item_2,
	            new String[] { KEY1, KEY2 },
	            new int[] { android.R.id.text1, android.R.id.text2 }
	        );


	    ExpandableListView listView =
	      (ExpandableListView) findViewById(R.id.ExpandableListView);
	    // Adapter を設定



	    listView.setAdapter(adapter);

	    // グループがクリックされた時に呼び出されるコールバックを登録
	    listView.setOnGroupClickListener(new OnGroupClickListener() {
	      @Override
	      public boolean onGroupClick(ExpandableListView parent,
	          View v, int groupPosition, long id) {
	        // クリックされた時の処理





	        return false;
	      }
	    });

	    // グループ内の項目がクリックされた時に呼び出されるコールバックを登録
	    listView.setOnChildClickListener(new OnChildClickListener() {
	      @SuppressWarnings("unchecked")
		@Override
	      public boolean onChildClick(ExpandableListView parent, View v,
	          int groupPosition, int childPosition, long id) {




	    	  // クリックされた時の処理

	    	  String s1=String.valueOf(id);
	    	  int a=Integer.parseInt(s1);

	    	  //↓事例１
	    	  //ExpandableListView list = (ExpandableListView)parent;
	    	  //String msg = "ItemClick : " + (String)list.getItemAtPosition(childPosition);
	    	  //Log.v("OnItemClick", msg);


	    	  //↓事例２
	    	  /*
	    	  ExpandableListView listview = (ExpandableListView)parent;//

	    	  long parentss = listview.getExpandableListPosition(groupPosition);
	    	  long parentss1 = listview.getExpandableListPosition(childPosition);

	    	  int group = ExpandableListView.getPackedPositionGroup(parentss);
	    	  int child = ExpandableListView.getPackedPositionChild(parentss1);

	    	  String title = parent.getItemAtPosition(group).toString();
	    	  */

	    	  //↓事例３
	    	// まずはAdapterを取得
             /*
	    	  ExpandableListAdapter adapter = parent.getExpandableListAdapter();

              // Adapterから子のデータMapを取得
              Map<String, Object> childMap = (Map<String, Object>)adapter.getChild(
                      groupPosition,
                      childPosition
              );

              // 取得したデータをログに出してみる
              // 日本語だから化けるけどね
              Log.d("SampleActivity", "TITLE: " + childMap.get("KEY1"));
              Log.d("SampleActivity", "SUMMARY: " + childMap.get("KEY2"));
				*/


	    	  switch(a){
	    		case 0:

	    			intent = new Intent(TeacherActivity.this, MsgActivity.class);

	    			//intent.putExtra("v", str);

	    			break;

	    		case 1:

	    			intent = new Intent(TeacherActivity.this, Test1Activity.class);

	    			//intent.putExtra("v", str);

	    			break;

	    		case 2:

	    			intent = new Intent(TeacherActivity.this, Test2Activity.class);

	    			//intent.putExtra("v", str);

	    			break;
	    	}
	    	  //intent.putExtra("name", title);
	    	//次画面のアクティビティ起動
	    	  startActivity(intent);

	        return false;
	      }
	    });






	  }

}
