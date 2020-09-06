package com.KITE.compaintbox;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Welcome extends Activity {

	ListView lv;
	String[] items = { "Complaint", "History of City","Services","Response", "About App", "FAQs","Help", "Feedback" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);

		lv = (ListView) findViewById(R.id.listView1);

		ArrayAdapter<String> ard = new ArrayAdapter<String>(
				getApplicationContext(), R.layout.welcome_list_item,R.id.tvname,items);
		lv.setAdapter(ard);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				
				switch (position) {
				case 0: 
						Intent i0 = new Intent(getApplicationContext(),Send_Complaint.class);
						startActivity(i0);
						break;

				case 1: 
					Intent i1 = new Intent(getApplicationContext(),Historycity.class);
					startActivity(i1);
					break;

					
				case 2: 
					Intent i2 = new Intent(getApplicationContext(),Activity_services.class);
					startActivity(i2);
					break;
					
				case 3: 
					Intent i3 = new Intent(getApplicationContext(),Activity_response.class);
					startActivity(i3);
					break;

					
				case 4: 
					Intent i4 = new Intent(getApplicationContext(),Activity_about_app.class);
					startActivity(i4);
					break;


				case 5: 
					Intent i5 = new Intent(getApplicationContext(),Activity_faqs.class);
					startActivity(i5);
					break;

						
				case 6: 
					Intent i6 = new Intent(getApplicationContext(),Activity_help.class);
					startActivity(i6);
					break;
					
				case 7: 
					Intent i7 = new Intent(getApplicationContext(),Feedback.class);
					startActivity(i7);
					break;


				}
				
			}
		});
		
	}

	
}
