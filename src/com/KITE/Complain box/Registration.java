package com.KITE.compaintbox;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Registration extends Activity {

	EditText etname,etpwd,etadd,eteid,etemail,etcontact;
	String gender;
	RadioButton male,female;
	Button register;
	ProgressDialog pDialog;
	int success;
	String message, uid;
	JSONParser jsonParser = new JSONParser();
	private static final String LOGIN_URL =  Resources.getSystem().getString(R.string.ConfigIp)+ "reg.php";
	final String TAG_SUCCESS = "success";

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		
		etname = (EditText) findViewById(R.id.editText1);
		etemail = (EditText) findViewById(R.id.editText2);
		etpwd = (EditText) findViewById(R.id.editText3);
		etadd = (EditText) findViewById(R.id.editText4);
		eteid = (EditText) findViewById(R.id.editText5);
		etcontact = (EditText) findViewById(R.id.editText6);
		
		male = (RadioButton) findViewById(R.id.rbmale);
		female = (RadioButton) findViewById(R.id.rbfemale);
		register = (Button) findViewById(R.id.button1);
		
		register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				if(male.isChecked())
					gender="Male";
				else if(female.isChecked())
					gender="Female";
				
				
				new Connect().execute();
				
			}
		});
		
	}
	class Connect extends AsyncTask<String, Void, Void> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(Registration.this);

			pDialog.setMessage("Please wait...");
			pDialog.setIndeterminate(false);

			pDialog.setCancelable(true);

			pDialog.show();

		}

		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			JSONParser jsonParser = new JSONParser();
			
			List<NameValuePair> params1 = new ArrayList<NameValuePair>();
			params1.add(new BasicNameValuePair("name", etname.getText()
					.toString()));
			params1.add(new BasicNameValuePair("email", etemail.getText()
					.toString()));
			params1.add(new BasicNameValuePair("password", etpwd.getText()
					.toString()));

			params1.add(new BasicNameValuePair("eid", eteid.getText()
					.toString()));
			params1.add(new BasicNameValuePair("add", etadd.getText()
					.toString()));
			
			params1.add(new BasicNameValuePair("contact", etcontact.getText()
					.toString()));
			
			params1.add(new BasicNameValuePair("gen", gender));
			
			JSONObject json = jsonParser.makeHttpRequest(LOGIN_URL,
					"GET", params1);

			try {
				success = json.getInt(TAG_SUCCESS);
				
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub

			pDialog.dismiss();
			if (success > 0) {
				Toast.makeText(getApplicationContext(),"Registration Successfull Now Login",Toast.LENGTH_LONG).show();
				Intent in = new Intent(getApplicationContext(), Login.class);
				//in.putExtra("uid", uid.toString());
				startActivity(in);
				finish();
			} else {
				Toast.makeText(getApplicationContext(),"Registration Failed",Toast.LENGTH_LONG).show();
				// failed to create product
			}
		}
	}
}

