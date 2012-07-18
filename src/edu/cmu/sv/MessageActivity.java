package edu.cmu.sv;

import java.util.ArrayList;

import edu.cmu.sv.model.Message;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

public class MessageActivity extends ListActivity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message_list);
		
		@SuppressWarnings("unchecked")
		ArrayList<Message> messageList = 
				(ArrayList<Message>) getIntent().getSerializableExtra("message_list");
		
		ArrayList<String> stringList = new ArrayList<String>();
		for(Message m : messageList) {
			stringList.add(m.getText());
		}
		ListAdapter adapter = 
				new ArrayAdapter<String>(this, R.layout.message_item, stringList);
		getListView().setAdapter(adapter);
		
		getListView().setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
				     int position, long id) {
					   
				String itemText = getListView().getItemAtPosition(position).toString();	
				Intent intent = new Intent(MessageActivity.this, MessageShareActivity.class);
				startActivityForResult(intent, RESULT_OK);
			}
		});
	}
}
