package www.learnplex.www;


	

	import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.net.Socket;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
	/**Loading Screen
	 * 
	 * @author Rylan
	 * @version 1.0
	 */
	/**
	 * Login Screen
	 * Transitions to the menu
	 *///

	public class FriendList extends Activity {
		Socket requestSocket;
		ObjectOutputStream out;
		ObjectInputStream in;
		String message;
		ArrayAdapter<Button> dataAdapter = null;
		private HashMap<String, Integer> imageIdMap;
		data dd = new data();
		String [] delivery = {};
		Object friendList[];
		Object[] read;
		Object[] deta;
//ff
		
		ScrollView lV;
		
	    RelativeLayout fLay;
	    RelativeLayout r;
	    
	    TextView tv;
		EditText name;
		ImageView iv;
		Button b;
		int width;
		int height;
		
	@SuppressLint("NewApi")
	@Override
		protected void onCreate(Bundle LoadingScreen) {
			// TODO Auto-generated method stub
		friendList = dd.getFriendList();
			super.onCreate(LoadingScreen);
			
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
			//ProgressBar lvl = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
			//lvl.setProgress(40);
			lV = new ScrollView(this);
		    fLay = new RelativeLayout(this);
		    r = (RelativeLayout)findViewById(R.id.linlay1);
		    tv = new TextView(this);
			name= new EditText(this);
			iv = new ImageView(this);
			b = new Button(this);	 
			
			
			getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
			
			if(false){
	    		 
	    	 }
	    	 else{
			 displayFriends();
	    	 }
			  Display display = getWindowManager().getDefaultDisplay();
				Point size = new Point();
				display.getSize(size);
				 width = size.x;
				height = size.y;
			tv.setText("Friends List");
		    tv.setX(165);
		    tv.setY(30);
		    tv.setTextSize(20);
		//create top icon
		b.setX(width*7/10);
	    b.setY(height*7/10); 
	    b.setWidth(width*3/10);
	    b.setHeight(height*1/10);
	    b.setText("Add Friend");
	    name.setX(width*1/10);
	    name.setY(height*7/10); 
	    name.setWidth(width*6/10);
	    name.setHint("Enter Friends Name");
	    name.setSingleLine(true);
	    r.addView(tv);
	    r.addView(name);
	    r.addView(b);
	   
			 //Add action item
			  
			   //Friend List
			  /* Button fList = new Button(this);
			   fList.setText("Add Friend");
		    	fList.setY(50);
		    	fList.setHeight(175);
			   fList.setWidth(width*3/10);
			   fList.setX(15);
		    	*/
		    	 //String [] friendList = dd.getFriendList();
		    	
			   /*lV.setOnLongClickListener(new OnLongClickListener(){
				  
				public boolean onLongClick (View V){
					a2.setVisibility(View.INVISIBLE);
		    		return true;
				  }
			   });*/
			    
			   
			   
			  
			   
			    
			    
			    
	
			    
			    
			   // r.addView(fList);


			    //r.addView(iv);
			    
			//LinearLayout linearLayout = new LinearLayout(this);
			
			
			
			//SharedPreferences app_preferences = 
		        //	PreferenceManager.getDefaultSharedPreferences(this);
		        //String user = app_preferences.getString("user", "");
		       // if("user".equals("")){
		        	
		      //  }
		       /* else{
		        	try {
						
						
						requestSocket = new Socket("66.189.125.169", 54433);
						out = new ObjectOutputStream(requestSocket
								.getOutputStream());
						out.flush();
						in = new ObjectInputStream(requestSocket
								.getInputStream());
					} catch (UnknownHostException e) {
						
						return;
					} catch (IOException e) {
						
						return;
					}

					sendMessage(user);

					try {
						message = (String) in.readObject();
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
		        }
		        */
		        
			
		        b.setOnClickListener(new OnClickListener() {
					@TargetApi(Build.VERSION_CODES.GINGERBREAD)
					@SuppressLint("NewApi")
					@SuppressWarnings("deprecation")
					
					
					public void onClick(View v) {
						/*
						Scanner in1 = new Scanner("");
						String user = "rylan";
						
						String pass = "vaxvax";*/
						
						
						
						//dd.storeUser(user);
						if (name.getText().toString().trim().equals("")){
							name.setHint("Enter Friend Here");
						}
							
					else{
							delivery = new String[3];
							delivery[0] = dd.getUser();
							delivery[1] = "addfriend";
							delivery[2] = name.getText().toString();
							dd.sendData(delivery);
							try {
								Object [] array = dd.getMessage();
								if (array[0].equals("requestsent")){
								dd.friendList();
								Intent openMenu = new Intent("www.learnplex.www.FRIENDLIST");
								startActivity(openMenu);
								}
								else{
									name.setHint(array[0].toString());
								}
							} catch (OptionalDataException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							
						}
				
				
			
					}});

	}
	/*
	public View getView(int position, View convertView, ViewGroup parent) {

	    View v = new View(this);
	    if (convertView == null)
	        v = LayoutInflater.from(context).inflate(R.layout.row, parent, false);
	    else
	        v = convertView;

	    if(position == 0){
	        Button button = new Button(this);
	        lV.addView(button);
	    }

	   return v;
	} */
	/*
		private void displayListView(){
		imageIdMap = new HashMap<String, Integer>();

		imageIdMap.put("f1", R.drawable.buckethead);
		imageIdMap.put("f2", R.drawable.captainmurphy);
		imageIdMap.put("f3", R.drawable.kiwi);
		imageIdMap.put("f4", R.drawable.yinyangsmall);
		imageIdMap.put("f5", R.drawable.farva);
		//myIageView.setImageResource(imageIdMap.get("f" + index));
		List<Object> friendList = new ArrayList<Object>();
		String index = "4";
		friendList.add(imageIdMap.get("f" + index));
		index = "1";
		friendList.add(imageIdMap.get("f" + index));
		index = "2";
		friendList.add(imageIdMap.get("f" + index));
		//ListView listView = getListView();
		dataAdapter = new ArrayAdapter<Object>(this,
			    R.layout.splash);
			  
			  // Assign adapter to ListView
			  listView.setAdapter(dataAdapter); 
		}*/
	public void displayFriends(){
		r.setClickable(true);
		RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		relativeParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		 width = size.x;
		height = size.y;
		final ImageButton o1 = new ImageButton(this);
		final ImageButton o2 = new ImageButton(this);
		final ImageButton o3 = new ImageButton(this);
		o1.setAdjustViewBounds(true);
		o1.setImageResource(R.drawable.vs);	
		o1.setMinimumHeight(width*7/100);
		o1.setMinimumWidth(width*7/100);
		o1.setMaxHeight(width*7/100);
		o1.setMaxWidth(width*7/100);
		o1.setLayoutParams(new LayoutParams(140,140));
		
		o2.setAdjustViewBounds(true);
		o2.setImageResource(R.drawable.mastermind);	
		o2.setMinimumHeight(width*7/100);
		o2.setMinimumWidth(width*7/100);
		o2.setMaxHeight(width*7/100);
		o2.setMaxWidth(width*7/100);
		o2.setLayoutParams(new LayoutParams(140,140));
		
		o3.setAdjustViewBounds(true);
		o3.setImageResource(R.drawable.racing);		
		o3.setMinimumHeight(width*7/100);
		o3.setMinimumWidth(width*7/100);
		o3.setMaxHeight(width*7/100);
		o3.setMaxWidth(width*7/100);
		o3.setLayoutParams(new LayoutParams(140,140));
		
	
   	 	
		
		    fLay.setVerticalScrollBarEnabled(true);
		    fLay.setScrollbarFadingEnabled(false);
		    r.setClickable(true);
			  r.setFocusable(true);
			  lV.setX(15);
			    lV.setY(height*1/10);
			    lV.setMinimumHeight(150*3);
			    lV.setMinimumWidth(width*9/10);
			    
		  
	    	//Recent Game List
	    	 
	    	 int butY = 20;
	    	 
	    	
	    	 
	    	  Object[] details = new Object[0];
	    	 
		    for(int i = 0;i<friendList.length;i++){
		    	final Button tempUser = new Button(this);
		    	final Button a1 = new Button(this);
		    	details =(Object[]) friendList[i];
		    	/*ImageView im = new ImageView(this);
		    	im.setImageResource(R.drawable.buckethead);	
		    	im.setX(30);
		    	im.setY(butY+10);*/
		    	
		    	
		    	 
		    	if(details[0].equals("friend")){
		    		if(details[1].equals("rejected")){
		    			final Button b1 = new Button(this);
		    			ImageButton clear = new ImageButton(this);
		    			clear.setImageResource(R.drawable.reject);	
		    			b1.setText(details[2].toString()+" rejected your request");
		    			b1.setFocusable(false);
			    		b1.setClickable(false);
			    		b1.setWidth(width*9/10);
				    	b1.setHeight(150);
				    	b1.setX(15);
			    		b1.setY(butY);
			    		clear.setAdjustViewBounds(true);
			    		clear.setY(butY+(height*1/100));
			    		clear.setX(width*72/100);

			    		clear.setMinimumHeight(width*7/100);
			    		clear.setMinimumWidth(width*7/100);
			    		clear.setMaxHeight(width*7/100);
			    		clear.setMaxWidth(width*7/100);
			    		
			    		clear.setLayoutParams(new LayoutParams(140,140));
			    		
			    		fLay.addView(clear);
			    		fLay.addView(b1);
			    		final String x = (String)details[2];
				    	clear.setOnClickListener(new OnClickListener() {
							@TargetApi(Build.VERSION_CODES.GINGERBREAD)
							@SuppressLint("NewApi")
							@SuppressWarnings("deprecation")
							
							
							public void onClick(View v) {
								
								
								dd.sendData(new String[] {dd.getUser(),"clearReject",x});
								try {
									dd.friendList();
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
								Intent openMenu = new Intent("www.learnplex.www.FRIENDLIST");
								startActivity(openMenu);
								//dd.createGame();
								
							
							
							
			              
			              
							}
							 
					    	});
		    		}
		    		else if(details[1].equals("pending")){
		    			final Button b1 = new Button(this);
		    			b1.setText("waiting for "+details[2].toString()+" to accept request");
		    			b1.setTextSize(16);		
			    		b1.setFocusable(false);
			    		b1.setClickable(false);
			    		b1.setY(butY);
				    	
			    		b1.setWidth(width*9/10);
				    	b1.setHeight(150);
				    	b1.setX(15);
				    	fLay.addView(b1);
				    	
		    		}
		    		else if(details[1].equals("incoming")){
		    			
		    		
		    		final Button b1 = new Button(this);
		    		ImageButton chk = new ImageButton(this);
		    		ImageButton rej = new ImageButton(this);
		    		
		    		rej.setImageResource(R.drawable.redx);	
		    		b1.setText(details[2].toString());
		    		b1.setFocusable(false);
		    		b1.setClickable(false);
		    		chk.setImageResource(R.drawable.chk);	
		    		chk.setAdjustViewBounds(true);

		    		chk.setMinimumHeight(width*7/100);
		    		chk.setMinimumWidth(width*7/100);
		    		chk.setMaxHeight(width*7/100);
		    		chk.setMaxWidth(width*7/100);
		    		chk.setY(butY+(height*1/100));
		    		chk.setX(width*4/100);
		    		chk.setLayoutParams(new LayoutParams(140,140));
		    		rej.setAdjustViewBounds(true);
		    		rej.setY(butY+(height*1/100));
		    		rej.setX(width*72/100);

		    		rej.setMinimumHeight(width*7/100);
		    		rej.setMinimumWidth(width*7/100);
		    		rej.setMaxHeight(width*7/100);
		    		rej.setMaxWidth(width*7/100);
		    		
		    		rej.setLayoutParams(new LayoutParams(140,140));
		    		fLay.addView(chk);
		    		fLay.addView(rej);
		    		b1.setY(butY);
			    	
		    		b1.setWidth(width*9/10);
			    	b1.setHeight(150);
			    	b1.setX(15);
			    	fLay.addView(b1);
			    	final String x = (String)details[2];
			    	chk.setOnClickListener(new OnClickListener() {
						@TargetApi(Build.VERSION_CODES.GINGERBREAD)
						@SuppressLint("NewApi")
						@SuppressWarnings("deprecation")
						
						
						public void onClick(View v) {
							
							
							dd.sendData(new String[] {dd.getUser(),"acceptFriend",x});
							try {
								dd.friendList();
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
							Intent openMenu = new Intent("www.learnplex.www.FRIENDLIST");
							startActivity(openMenu);
							//dd.createGame();
							
						
						
						
		              
		              
						}
						 
				    	});
			    	rej.setOnClickListener(new OnClickListener() {
						@TargetApi(Build.VERSION_CODES.GINGERBREAD)
						@SuppressLint("NewApi")
						@SuppressWarnings("deprecation")
						
						
						public void onClick(View v) {
							dd.sendData(new String[] {dd.getUser(),"rejectFriend",x});
							try {
								dd.friendList();
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
							Intent openMenu = new Intent("www.learnplex.www.FRIENDLIST");
							startActivity(openMenu);
							//dd.createGame();
							
						
						
						
		              
		              
						}
						 
				    	});
		    		}
		    		
		    	}
		    	
		    	
		    	else{
		    		
		    		a1.setText(details[0].toString());
		    		a1.setY(butY);
			    	
			    	a1.setWidth(width*9/10);
			    	a1.setHeight(150);
			    	a1.setX(15);
			    	fLay.addView(a1);
		    	}
		    	
		    	String friendClick2 = details[0].toString();
		    	final String friendClick = friendClick2;
		    	
		    	a1.setOnClickListener(new OnClickListener() {
				@TargetApi(Build.VERSION_CODES.GINGERBREAD)
				@SuppressLint("NewApi")
				@SuppressWarnings("deprecation")
				
				
				public void onClick(View v) {
					 r.setOnClickListener(new OnClickListener(){
					    	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
							@SuppressLint("NewApi")
							@SuppressWarnings("deprecation")
					    public void onClick (View v){
					    		if(o1.getVisibility()==View.VISIBLE){
					    			r.removeView(tempUser);
					    			
					    			fLay.setVisibility(View.VISIBLE);
					    			r.removeView(o1);
					    		r.removeView(o2);
					    		r.removeView(o3);
					    		}
					    }
					    });
					lV.setOnTouchListener(new View.OnTouchListener() {
				    	public boolean onTouch(View v, MotionEvent event){
				    		r.removeView(o1);
				    		r.removeView(o2);
				    		r.removeView(o3);
				    		fLay.setVisibility(View.VISIBLE);
				    		r.removeView(tempUser);
				    			if(event.getAction()==MotionEvent.ACTION_UP)
				    				
				    			lV.setScrollY(((int)lV.getScrollY()/170)*170);
				    		return false;
				    	}
				    });
					fLay.setVisibility(View.INVISIBLE);
					tempUser.setY(height*15/100);
					tempUser.setWidth(width*9/10);
					tempUser.setHeight(150);
					tempUser.setX(15);
					tempUser.setVisibility(View.VISIBLE);
					tempUser.setText(friendClick);
					r.addView(tempUser);
					int[] location = new int[2];
					tempUser.getLocationOnScreen(location);
					o1.setY(location[1]);
					o1.setX(width*20/100);
					o1.setVisibility(View.VISIBLE);
					o1.setFocusable(true);
					o1.setFocusableInTouchMode(true);
					r.addView(o1);
					o2.setY(location[1]);
					o2.setX(width*50/100);
					o2.setVisibility(View.VISIBLE);
					o2.setFocusable(true);
					o2.setFocusableInTouchMode(true);
					r.addView(o2);
					o3.setY(location[1]);
					o3.setX(width*80/100);
					o3.setVisibility(View.VISIBLE);
					o3.setFocusable(true);
					o3.setFocusableInTouchMode(true);
					r.addView(o3);
					
					//dd.createGame();
					o1.setOnClickListener(new OnClickListener() {
						@TargetApi(Build.VERSION_CODES.GINGERBREAD)
						@SuppressLint("NewApi")
						@SuppressWarnings("deprecation")
						public void onClick(View v) {
							dd.sendData(new String[]{dd.getUser(),"inviteFriend",friendClick,"versus"});
							try {
								read = dd.getMessage();
								dd.login(dd.getUser());
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							//if(deta[0].equals("0")){
								Intent openMenu = new Intent("www.learnplex.www.MENU");
								startActivity(openMenu);
						}
						});
			    	o2.setOnClickListener(new OnClickListener() {
						@TargetApi(Build.VERSION_CODES.GINGERBREAD)
						@SuppressLint("NewApi")
						@SuppressWarnings("deprecation")
						public void onClick(View v) {
							dd.sendData(new String[]{dd.getUser(),"inviteFriend",friendClick,"mastermind"});
							try {
								read = dd.getMessage();
								dd.login(dd.getUser());
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							//if(deta[0].equals("0")){
								Intent openMenu = new Intent("www.learnplex.www.MENU");
								startActivity(openMenu);
						}
						});
			    	o3.setOnClickListener(new OnClickListener() {
						@TargetApi(Build.VERSION_CODES.GINGERBREAD)
						@SuppressLint("NewApi")
						@SuppressWarnings("deprecation")
						public void onClick(View v) {
							dd.sendData(new String[]{dd.getUser(),"inviteFriend",friendClick,"comp"});
							try {
								read = dd.getMessage();
								dd.login(dd.getUser());
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							//if(deta[0].equals("0")){
								Intent openMenu = new Intent("www.learnplex.www.MENU");
								startActivity(openMenu);
						}
						});
					
					
					
					
					
					
				}
				 
		    	});
		    	
		    	//goes back here
		    	//fLay.addView(im);
		    	 
		    	 
		    	 butY=butY+170;
		    }
		    
		    
		   
		    
		    lV.setLayoutParams(new LayoutParams(width*9/10, height*9/20));
		    fLay.setLayoutParams(new LayoutParams(width*9/10, butY));
		    //lV.removeAllViews();
		    
		    //r.addView(lvl);
		    r.addView(lV);
		    
		    lV.addView(fLay);
	    	}
	
	
	
		@Override
		protected void onPause() {
			// TODO Auto-generated method stub
			super.onPause();
			finish();
		}
		@Override
		public void onBackPressed() {
			try {
				dd.login(dd.getUser());
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
		//creation of 
		
		

		
		
		


		
	}

