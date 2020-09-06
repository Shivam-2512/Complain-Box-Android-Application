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
import android.widget.Toast;

public class Login extends Activity {

	EditText etusername, etpassword;
	Button btnlogin, btnregister;
	ProgressDialog pDialog;
	int success;
	String message, uid;
	JSONParser jsonParser = new JSONParser();
	//private static final String LOGIN_URL =  Resources.getSystem().getString(R.string.ConfigIp)+ "login.php";
	private static final String LOGIN_URL =  "http://10.0.2.2/compaint_service/login.php";
	 String TAG_SUCCESS = "success";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		etusername = (EditText) findViewById(R.id.etemail);
		etpassword = (EditText) findViewById(R.id.etpwd);
		btnlogin = (Button) findViewById(R.id.btnlogin);
		btnregister = (Button) findViewById(R.id.btnRegister);

		etusername.setText("aa@gmail.com");
		etpassword.setText("12345");
		
		
		btnlogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				if(etusername.getText().toString().length()!=0 && etpassword.getText().toString().length()!=0)
				{
					new Connect().execute();
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Fill Required Details", Toast.LENGTH_LONG).show();
				}
				
			}
		});
		
		btnregister.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent in = new Intent(getApplicationContext(), Registration.class);
				
				startActivity(in);
			}
		});
	}

	class Connect extends AsyncTask<String, Void, Void> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(Login.this);

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
			params1.add(new BasicNameValuePair("email", etusername.getText()
					.toString()));
			params1.add(new BasicNameValuePair("password", etpassword.getText()
					.toString()));

			JSONObject json = jsonParser.makeHttpRequest(LOGIN_URL,
					"POST", params1);

			try {
				success = json.getInt(TAG_SUCCESS);
				 uid = json.getString("id");
				 message = json.getString("message");
				
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
				Intent in = new Intent(getApplicationContext(), Welcome.class);
				//in.putExtra("uid", uid.toString());
				StatUtil.userid= uid;
				startActivity(in);
				finish();
			} else {
				Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_LONG).show();
				// failed to create product
			}
		}
	}
}
