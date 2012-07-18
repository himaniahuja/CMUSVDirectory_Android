package edu.cmu.sv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MessageShareActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message_share);
		
		
		Button share = (Button) findViewById(R.id.share_button);
		share.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText text = (EditText) findViewById(R.building_list.share_box);
				if (text.getText().length() > 0) {
					String txt = text.getText().toString();
					
					String email = CMUSVDirectoryActivity.USER_EMAIL.split("@")[0];
					System.out.println(email);
					// Create a new HttpClient and Post Header
					HttpClient httpclient = new DefaultHttpClient();
					HttpPost httppost = new HttpPost("http://cmusvdirectory.appspot.com/Message_Post");
					
					
					try {
					    // Add your data
					    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
					    nameValuePairs.add(new BasicNameValuePair("email", email));
					    nameValuePairs.add(new BasicNameValuePair("text", txt));
					    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

					    // Execute HTTP Post Request
					    HttpResponse response = httpclient.execute(httppost);
					    Logger.getAnonymousLogger().info("response : " + response);
					} catch (ClientProtocolException e) {
					    e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					setResult(RESULT_OK);
					finish();
				}
			}
		});
	}
	
}
