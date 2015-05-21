package www.learnplex.www;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.net.Socket;
import java.util.HashMap;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
/**Loading Screen
 * 
 * @author Rylan
 * @version 1.0
 */
/**
 * Login Screen
 * Transitions to the menu
 */


public class Login extends Activity {
	Socket requestSocket;
	ObjectOutputStream out;
	ObjectInputStream in;
	RelativeLayout r;
	ArrayAdapter<Object> dataAdapter = null;
	private HashMap<String, Integer> imageIdMap;
	data dd = new data();
	EditText name;
	static int width;
	static int height;
@Override
	protected void onCreate(Bundle LoadingScreen) {
		// TODO Auto-generated method stub
	if(Integer.valueOf(android.os.Build.VERSION.SDK)>8){
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()   // or .detectAll() for all detectable problems
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());
	}
		super.onCreate(LoadingScreen);
		
	       // int saver = app_preferences.getInt("save", 0);
	     
		//displayListView();			
			  //enables filtering for the contents of the given ListView
			  //listView.setTextFilterEnabled(true);
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); 
		
		setContentView(R.layout.layout);
		//displayListView();			
			  //enables filtering for the contents of the given ListView
			  //listView.setTextFilterEnabled(true);
			 
		
		
		
		//LinearLayout linearLayout = new LinearLayout(this);
		//EditText name = new EditText(this);
		name= new EditText(this);
		ImageView iv = new ImageView(this);
		Button b = new Button(this); //login	 
		
		PopupWindow popUp = new PopupWindow(this);
		r = (RelativeLayout)findViewById(R.id.linlay1);
		RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		relativeParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		width = size.x;
		height = size.y;

		iv.setImageResource(R.drawable.wwwtitle);	
		  iv.setX(100);
		    iv.setY(50);
		    name.setX(width/4);
		    name.setY(600);
		    name.setTextColor(Color.GREEN);
		    name.setWidth(width/2);
		    name.setHint("UserName");
		    name.setSingleLine(true);
		    
		    b.setX(width/3);
		    b.setY(700); 
		    b.setWidth(width/3);
		    b.setHeight(150);
		    b.setText("Login");
		    
		    r.addView(name);
		    r.addView(iv);
		    r.addView(b);
		   final SharedPreferences settings = getSharedPreferences("user", 
		    	    Context.MODE_PRIVATE);
		    	String user = settings.getString("user", "");
		    
		/*
		    final SharedPreferences app_preferences = 
		        	PreferenceManager.getDefaultSharedPreferences(this);
		        String user = app_preferences.getString("user", "");*/
		   
		    
		    
		    //
		        if(!user.equals("")){
		        	try {
		        		
						dd.login(user);
						//System.out.println(dd.getFailed());
						
					} catch (OptionalDataException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	
		    		
		        	
		        	Intent openMenu = new Intent("www.learnplex.www.MENU");
					startActivity(openMenu);
		        }
		
		//SharedPreferences app_preferences = 
	        //	PreferenceManager.getDefaultSharedPreferences(this);
	        //String user = app_preferences.getString("user", "");
	       // if("user".equals("")){
	        	
	      //  }
	        b.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					String user = name.getText().toString();
					if (user.trim().equals("")){
						name.setHint("Enter Name");
					}
						
				else{
							try {
								dd.login(user);
							} catch (OptionalDataException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							SharedPreferences.Editor editor = settings.edit();
						    editor.putString("user", user);
						    editor.commit();
							
							/*
							SharedPreferences.Editor editor = app_preferences.edit();
				    		editor = app_preferences.edit();
				    		editor.putString("user", user);
				    		
				            editor.commit(); // Very Very important*/
							Intent openMenu = new Intent("www.learnplex.www.MENU");
							startActivity(openMenu);
						
				}
	}
			});
}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	public void sendMessage(String[] msg) {
		try {
			out.writeObject(msg);
			out.flush();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}
	

	}


