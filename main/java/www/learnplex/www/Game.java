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
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
 * Login Screen Transitions to the menu
 */

public class Game extends Activity {
	Socket requestSocket;
	ObjectOutputStream out;
	ObjectInputStream in;
	String message;
	ArrayAdapter<Button> dataAdapter = null;
	private HashMap<String, Integer> imageIdMap;
	data dd = new data();
	String Word = "";
	String adding = "";
	boolean view = true;
	RelativeLayout r3,r;
	// final int pos = dd.getPos();
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle LoadingScreen) {
		// TODO Auto-generated method stub
		
		super.onCreate(LoadingScreen);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.layout);
		final Object[] deets;
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		final int width = size.x;
		final int height = size.y;
		r = (RelativeLayout) findViewById(R.id.linlay1);
		RelativeLayout rb = new RelativeLayout(this);
		
		deets = dd.getStats();
		 
		
		System.out.println(dd.getFailed());
		final TextView tv = new TextView(this);
		final TextView tv2 = new TextView(this); 
		final TextView tv3 = new TextView(this);
		ImageView iv = new ImageView(this);
		tv.setTextSize(20);
		if(dd.getVictory())
		tv3.setText("You win");
		//Ladder Profile
		final RelativeLayout r2 = new RelativeLayout(this);
		
		r2.setLayoutParams(new LayoutParams(width, height));
		r3 = new RelativeLayout(this);
		r3.setLayoutParams(new LayoutParams(width, height));
		r.addView(r3);
		Button lp1 = new Button(this);
		Button lp2 = new Button(this);
		Button lpb = new Button(this);
		final TextView ladV1 = new TextView(this);
		final TextView ladV2 = new TextView(this);
		lp1.setX(width*25/100);
		lp2.setX(width*65/100);
		lpb.setX(width*10/100);
		lpb.setY(height*70/100);
		
		lp1.setY(height*10/100);
		lp2.setY(height*10/100);
		lpb.setText("back");
		r2.addView(lpb);
		if(dd.isLadder()){
		lp1.setText("Recent Games");
		lp2.setText("Ladder Scores");
		
		r2.addView(lp1);
		r2.addView(lp2);
		ladV1.setX(width*40/100);
		ladV2.setX(width*40/100);
		ladV2.setY(height*20/100);
		ladV1.setY(height*20/100);
		ladV1.setWidth(width*60/100);
		ladV1.setHeight(height*60/100);
		ladV2.setWidth(width*60/100);
		ladV2.setHeight(height*60/100);
		ladV1.setTextSize(20);
		ladV2.setTextSize(20);
		
		SpannableString[] ff = dd.colorScore();
		
		ladV1.setText(ff[0]+"\n"+ff[1]+"\n"+ff[2]+"\n"+ff[3]+"\n"+ff[4]+"\n");
		ladV2.setText(dd.getLadderString());
		
		lp1.setOnClickListener(new OnClickListener() {
			@TargetApi(Build.VERSION_CODES.GINGERBREAD)
			@SuppressLint("NewApi")
			@SuppressWarnings("deprecation")
			public void onClick(View v) {
				r2.removeView(ladV2);
				
				r2.removeView(ladV1);
				r2.addView(ladV1);
			}
		});
		lp2.setOnClickListener(new OnClickListener() {
			@TargetApi(Build.VERSION_CODES.GINGERBREAD)
			@SuppressLint("NewApi")
			@SuppressWarnings("deprecation")
			public void onClick(View v) {
				r2.removeView(ladV2);
				r2.removeView(ladV1);
				r2.addView(ladV2);
			}
		});
		}
		lpb.setOnClickListener(new OnClickListener() {
			@TargetApi(Build.VERSION_CODES.GINGERBREAD)
			@SuppressLint("NewApi")
			@SuppressWarnings("deprecation")
			public void onClick(View v) {
				r.removeAllViews();
				r.addView(r3);
				
			}
		});
		
		
		
		r.setClickable(true);
		
		final ScrollView sv = new ScrollView(this);
		sv.setVerticalScrollBarEnabled(true);
		sv.setX(width * 2 / 3);
		sv.setY(height * 2 / 10);
		sv.setLayoutParams(new LayoutParams(width / 3, height * 5 / 10));
		sv.setBackgroundColor(Color.GRAY);
		if(dd.getKeyWord().equals("nothing")){
			tv.setText("Enter Keyword\nThe Other player will\nguess this word");
		}
		
		else{
			tv.setText(dd.getUserdisplayGame());
		}
		
		tv3.setTextSize(25);
		tv.setTextSize(30);

		sv.addView(tv);
		r3.addView(sv);

		rb.setY(height * 2 / 10);
		rb.setX(0);
		rb.setLayoutParams(new LayoutParams(width * 2 / 3, height * 7 / 10));
		rb.setBackgroundColor(Color.rgb(180, 230, 190));
		int xLoc = width * 1 / 100;
		int yLoc = height * 1 / 100;
		int abc = 97;
		//DISPLAYS
		tv2.setTextSize(30);
		tv2.setX(width * 5 / 100);
		tv2.setText("_ _ _ _ _");
		tv3.setX(height * 5 / 100);
		tv3.setY(height * 5 / 100);
		
		
		final TextView abcView = new TextView(this);
		abcView.setWidth(width * 60 / 100);
		abcView.setHeight(height * 15 / 100);
		abcView.setX(width * 50 / 100);
		abcView.setY(height * 10 / 100);
		abcView.setHeight(height*30/100);
		abcView.setTextSize(20);
		
		final Object[] abcList =(Object[]) dd.getAbc();
		int abclen = abcList[2].toString().length();
		String habc = abcList[2].toString();
		String hold="";
		if(habc.equals("0")){
			hold = "_ _ _ _ _";
		}
		else{
		for(int a = 0; a<abclen;a++){
			hold = hold + habc.charAt(a) + " ";
		}
		for(int a = 0; a<5-abclen;a++){
			hold = hold + "_ ";
		}
			hold = hold.substring(0, hold.length()-1);	
		}
		abcView .setText("\nKNOWN "+hold);
		
		
		r3.addView(abcView);
		final Button otherView = new Button(this);
		final Button ladderProf = new Button(this);
		if(deets[0].equals("ladder")){
			ladderProf.setWidth(width * 30 / 100);
			ladderProf.setHeight(height * 10 / 100);
			ladderProf.setX(width * 75 / 100);
			ladderProf.setY(height * 75 / 100);
			ladderProf.setText("profile");
			r3.addView(ladderProf);
			
			ladderProf.setOnClickListener(new OnClickListener() {
				@TargetApi(Build.VERSION_CODES.GINGERBREAD)
				@SuppressLint("NewApi")
				@SuppressWarnings("deprecation")
				public void onClick(View v) {
					
					r.removeAllViews();
					r.addView(r2);
				}
			});
		}
		else{
		
		otherView.setWidth(width * 30 / 100);
		otherView.setHeight(height * 10 / 100);
		otherView.setX(width * 70 / 100);
		otherView.setY(height * 70 / 100);
		otherView.setText("See other");
		r3.addView(otherView);
		}
		final Button enter = new Button(this);
		enter.setWidth(width * 20 / 100);
		enter.setHeight(height * 10 / 100);
		enter.setX(width * 2 / 100);
		enter.setY(height * 10 / 100);
		enter.setText("Enter");
		final Button clear = new Button(this);
		clear.setWidth(width * 20 / 100);
		clear.setHeight(height * 10 / 100);
		clear.setX(width * 25 / 100);
		clear.setY(height * 10 / 100);
		clear.setText("Clear");
		for (int i = 1; i < 27; i++) {
			final char Letter;

			Letter = (char) abc;
			ImageButton b1 = new ImageButton(this);
			b1.setImageResource(R.drawable.chk);	
    		b1.setAdjustViewBounds(true);
    		Object[] abcs = dd.getAbc();
    		if(abcs[2].toString().indexOf(Letter)>=0){
    			b1.setImageResource(R.drawable.greenbutton1);	
    		}
    		else if(abcs[3].toString().indexOf(Letter)>=0){
    			b1.setImageResource(R.drawable.redbutton1);
    		}
    		else {
    			b1.setImageResource(R.drawable.bluebutton1);
    		}
    		TextView tx = new TextView(this);
    		tx.setX(xLoc+55);
			tx.setY(yLoc+30);
			tx.setTextSize(30);
    		tx.setText(Letter+"");
    		
    		
			b1.setMinimumHeight(width * 7 / 100);
			b1.setMinimumWidth(width * 7 / 100);
			b1.setMaxHeight(width * 7 / 100);
			b1.setMaxWidth(width * 7 / 100);
			
			b1.setLayoutParams(new LayoutParams(150, 150));
			b1.setBackground(null);
			b1.setX(xLoc);
			b1.setY(yLoc);
			xLoc = xLoc + (width * 15 / 100);
			if (i % 4 == 0) {
				xLoc = width * 1 / 100;
				yLoc = yLoc + (height * 9 / 100);

			}
			
			abc++;
			b1.setOnClickListener(new OnClickListener() {
				@TargetApi(Build.VERSION_CODES.GINGERBREAD)
				@SuppressLint("NewApi")
				@SuppressWarnings("deprecation")
				public void onClick(View v) {
					if(Word.length()==5){
						
					}
					else{
					Word = Word + Letter;
					String hold =tv2.getText().toString();
					if(Word.length()==1){hold=Letter+hold.substring(1);}
					else
						hold = hold.substring(0,Word.length()*2-2)+Letter+hold.substring(Word.length()*2-1,hold.length());
					
					tv2.setText(hold);
					}
				}
			});

			rb.addView(b1);
			rb.addView(tx);
		}
		

		otherView.setOnClickListener(new OnClickListener() {
			@TargetApi(Build.VERSION_CODES.GINGERBREAD)
			@SuppressLint("NewApi")
			@SuppressWarnings("deprecation")
			public void onClick(View v) {
				//
				if (view == false) {

					otherView.setText("See other");
					
					tv.setText(dd.getUserdisplayGame());
					if (dd.getStatus().equals("1")||dd.getStatus().equals("4")||dd.getStatus().equals("3")){
						
					}
					else{
					r3.addView(enter);
					r3.addView(clear);
					r3.addView(abcView);
					}
					view = true;
					Word = "";
				} else if (view == true && deets[0].equals("comp")){
					otherView.setText("See self");
					Object[] list = dd.getOtherWordList();
					Object[] read =(Object[]) list[0];
					String wordList = "";
					for(int i = 0; i<list.length;i++){
						read =(Object[]) list[i];
						wordList = wordList + read[1]+"\n";
					}
					tv.setText(wordList);
					r3.removeView(enter);
					r3.removeView(clear);
					view = false;
					Word = "";
				}
				else if (view == true) {
					otherView.setText("See self");
					
					tv.setText(dd.getOtherdisplayGame());
					r3.removeView(enter);
					r3.removeView(clear);
					view = false;
					Word = "";
					
				}
				
			}
		});
		
		/*
		abcView.setOnClickListener(new OnClickListener(){
			@TargetApi(Build.VERSION_CODES.GINGERBREAD)
			@SuppressLint("NewApi")
			@SuppressWarnings("deprecation")
			public void onClick(View v) {
				if(abcView.getText().equals("UNKNOWN\n"+abcList[1])){
					abcView.setText("KNOWN\n"+abcList[2]);
				}
				else if(abcView.getText().equals("KNOWN\n"+abcList[2])){
				abcView.setText("NOT IN\n"+abcList[3]);
			}
				else if(abcView.getText().equals("NOT IN\n"+abcList[3])){
					abcView.setText("UNKNOWN\n"+abcList[1]);
				}
			}
		});*/
		enter.setOnClickListener(new OnClickListener() {
			@TargetApi(Build.VERSION_CODES.GINGERBREAD)
			@SuppressLint("NewApi")
			@SuppressWarnings("deprecation")
			public void onClick(View v) {
				// f
				String in = "";
				Object[] details = dd.getStats();
				
				try {
					if(details[0].equals("ladder")){
						in = " comp game ";
						tv3.setText(dd.sendLadderGuess(Word));
						System.out.println(dd.getFailed());
						if(dd.getFailed()){
							tv3.setText("failed");
							
						}
						
					}
					else if (details[0].equals("comp") && dd.getStatus().equals("0")) {
						in = " comp game ";
						tv3.setText(dd.sendGuess(Word));
					} else if (dd.getStatus().equals("0")) {
						in = " enter target ";
						tv3.setText(dd.sendTarget(Word));
					} else if (dd.getStatus().equals("2")) {
						tv3.setText(dd.sendGuess(Word));
						// in = dd.sendGuess(Word);
					}
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
				if(tv3.getText().equals("loading")){
					//Intent openMenu = new Intent("www.learnplex.www.GAME");
					//startActivity(openMenu);
				}
				tv.setText(dd.getUserdisplayGame());
				Word = "";
				tv2.setText("_ _ _ _ _");
				
			}
		});

		clear.setOnClickListener(new OnClickListener() {
			@TargetApi(Build.VERSION_CODES.GINGERBREAD)
			@SuppressLint("NewApi")
			@SuppressWarnings("deprecation")
			public void onClick(View v) {
				if(Word.length()==0){
					
				}					
				else{
					String hold = "";
					hold = tv2.getText().toString();
					Word = Word.substring(0, Word.length() - 1);
					if(Word.length()==0){hold="_"+hold.substring(1);}
					else{
						
					
					
					hold = hold.substring(0,Word.length()*2)+"_"+hold.substring(Word.length()*2+1,hold.length());
					
					
					}
				
				
					tv2.setText(hold);
				
				}
			}
		});
		
		r3.addView(enter);
		r3.addView(clear);
		r3.addView(tv2);
		if(dd.getVictory()){
			tv3.setText("You Win");
		}
		r3.addView(tv3);
		r3.addView(rb);
		//r3.addView(abcView);
		if(dd.getStatus().equals("0")||dd.getStatus().equals("2")){
			
		}
		else if(dd.getStatus().equals("4")){
			r3.removeView(tv2);
			r3.removeView(tv3);
			r3.removeView(enter);
			r3.removeView(clear);
			tv.setText("You've already found their word.");
		}
		else if(dd.getStatus().equals("1")){
			r3.removeView(tv2);
			r3.removeView(tv3);
			r3.removeView(enter);
			r3.removeView(clear);
			tv.setText("You've already entered your word.");
		}
		else if(dd.getStatus().equals("3")){
			r3.removeView(tv2);
			r3.removeView(tv3);
			r3.removeView(enter);
			r3.removeView(clear);
			final Button b1 = new Button(this);

			ImageButton rej = new ImageButton(this);

			rej.setImageResource(R.drawable.redx);
			b1.setText("Rematch?");
			b1.setFocusable(false);
			b1.setClickable(false);

			ImageButton chk = new ImageButton(this);
			chk.setImageResource(R.drawable.chk);
			chk.setAdjustViewBounds(true);

			chk.setMinimumHeight(width * 7 / 100);
			chk.setMinimumWidth(width * 7 / 100);
			chk.setMaxHeight(width * 7 / 100);
			chk.setMaxWidth(width * 7 / 100);
			chk.setY((height * 1 / 100));
			chk.setX(width * 4 / 100);
			chk.setLayoutParams(new LayoutParams(140, 140));
			r3.addView(chk);

			rej.setAdjustViewBounds(true);
			rej.setY( (height * 1 / 100));
			rej.setX(width * 72 / 100);

			rej.setMinimumHeight(width * 7 / 100);
			rej.setMinimumWidth(width * 7 / 100);
			rej.setMaxHeight(width * 7 / 100);
			rej.setMaxWidth(width * 7 / 100);

			rej.setLayoutParams(new LayoutParams(140, 140));

			r3.addView(rej);
			b1.setY(5);

			b1.setWidth(width * 9 / 10);
			b1.setHeight(150);
			b1.setX(15);
			r3.addView(b1);
			
			chk.setOnClickListener(new OnClickListener() {
				@TargetApi(Build.VERSION_CODES.GINGERBREAD)
				@SuppressLint("NewApi")
				@SuppressWarnings("deprecation")
				public void onClick(View v) {
					if(dd.getPos()==1){
						dd.sendData(new String[]{dd.getUser(),"acceptRematch",deets[2].toString(),deets[0].toString(),deets[7].toString()});	
					}
					else
						dd.sendData(new String[]{dd.getUser(),"acceptRematch",deets[1].toString(),deets[0].toString(),deets[7].toString()});
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
					Intent openMenu = new Intent(
							"www.learnplex.www.MENU");
					startActivity(openMenu);
					// dd.createGame();

				}

			});

			rej.setOnClickListener(new OnClickListener() {
				@TargetApi(Build.VERSION_CODES.GINGERBREAD)
				@SuppressLint("NewApi")
				@SuppressWarnings("deprecation")
				public void onClick(View v) {
					if(dd.getPos()==1){
						dd.sendData(new String[]{dd.getUser(),"rejectRematch",deets[2].toString(),deets[7].toString()});	
					}
					else
						dd.sendData(new String[]{dd.getUser(),"rejectRematch",deets[1].toString(),deets[7].toString()});
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
					
					Intent openMenu = new Intent(
							"www.learnplex.www.MENU");
					startActivity(openMenu);
					// dd.createGame();

				}
			});

		}
		
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	// creation of

	public void sendMessage(String msg) {
		try {
			out.writeObject(msg);
			out.flush();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
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

}
