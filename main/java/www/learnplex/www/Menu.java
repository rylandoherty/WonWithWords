package www.learnplex.www;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
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

public class Menu extends Activity {
	Socket requestSocket;
	ObjectOutputStream out;
	ObjectInputStream in;
	String message;
	ArrayAdapter<Button> dataAdapter = null;
	private HashMap<String, Integer> imageIdMap;
	data dd = new data();
	RelativeLayout r;
	static int width;
	static int height;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle LoadingScreen) {
		// TODO Auto-generated method stub

		super.onCreate(LoadingScreen);
		
		// displayListView();
		// enables filtering for the contents of the given ListView
		// listView.setTextFilterEnabled(true);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		setContentView(R.layout.layout);
		// displayListView();
		// enables filtering for the contents of the given ListView
		// listView.setTextFilterEnabled(true);
		// LinearLayout linearLayout = new LinearLayout(this);
		// EditText name = new EditText(this);
		Button ladder = new Button(this);
		ProgressBar lvl = new ProgressBar(this, null,
				android.R.attr.progressBarStyleHorizontal);
		lvl.setProgress(40);
		TextView tv = new TextView(this);
		TextView version = new TextView(this);
		final EditText name = new EditText(this);
		ImageView iv = new ImageView(this);
		Button b = new Button(this);
		Button b2 = new Button(this);
		PopupWindow popUp = new PopupWindow(this);
		 r = (RelativeLayout) findViewById(R.id.linlay1);
		RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		relativeParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		
		
		        
		
		
		display.getSize(size);
		 width = size.x;
		 height = size.y;
		// create top icon
		version.setX(width*2/100);
		version.setY(height*83/100);
		version.setText("Won With Words        Version:0.13          ladder Learnplex");
		ladder.setX(width*5/100);
		ladder.setY(height*70/100);
		ladder.setWidth(width * 9 / 10);
		ladder.setHeight(height*1/10);
		ladder.setText("Ladder Page");
		b2.setX(15);
		b2.setY(height*8/10);
		b2.setWidth(width * 9 / 10);
		b2.setHeight(75);
		b.setX(15);
		b.setY(15);
		b.setWidth(width * 9 / 10);
		b.setHeight(150);
		b2.setText("Logout");
		
		
		tv.setText(dd.getUser());
		tv.setX(165);
		tv.setY(30);
		tv.setTextSize(20);
		
		lvl.setX(480);
		lvl.setY(125);
		lvl.setScaleY(2);
		lvl.setScaleX(2);
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
				120, 120);
		iv.setLayoutParams(layoutParams);
		iv.setX(30);
		iv.setY(30);
		// create friend icons

		// ListView lV = new ListView(this);
		ScrollView lV = new ScrollView(this);
		RelativeLayout fLay = new RelativeLayout(this);
		lV.setLayoutParams(new LayoutParams(width * 9 / 10, 600));

		
		
		// fLay.setLayoutDirection(0);
		// fffsdfsfsd
		int butY = 250;
		Button fList = new Button(this);
		fList.setText("Friends List");
		fList.setY(0);
		fList.setHeight(100);
		fList.setWidth(width * 9 / 10);
		fList.setX(15);
		Button gList = new Button(this);
		gList.setText("Entire Game List");
		gList.setY(115);
		gList.setHeight(100);
		gList.setWidth(width * 9 / 10);
		gList.setX(15);
		fLay.addView(fList);
		fLay.addView(gList);
		Object[] gameList = dd.getRecentGames();
		Object[] gameStats = dd.getRecentStats();
		Object[] profile = dd.getProfile();
		iv.setImageResource(dd.getIcon(Integer.parseInt((String)profile[5])));
		Object[] details;
		int countforstats = 0;
		if (gameList[0].equals("0")) {

		} else {
			// populating gamelist

			for (int i = 0; i < gameList.length; i++) {
				details = (Object[]) gameList[i];

				if (details[0].equals("game")) {
					if (details[1].toString().equals("rejected")) {
						final Button b1 = new Button(this);
						ImageButton clear = new ImageButton(this);
						clear.setImageResource(R.drawable.reject);
						b1.setText(details[2].toString()
								+ " rejected your request");
						b1.setFocusable(false);
						b1.setClickable(false);
						b1.setWidth(width * 9 / 10);
						b1.setHeight(150);
						b1.setX(15);
						b1.setY(butY);
						clear.setAdjustViewBounds(true);
						clear.setY(butY + (height * 1 / 100));
						clear.setX(width * 72 / 100);

						clear.setMinimumHeight(width * 7 / 100);
						clear.setMinimumWidth(width * 7 / 100);
						clear.setMaxHeight(width * 7 / 100);
						clear.setMaxWidth(width * 7 / 100);

						clear.setLayoutParams(new LayoutParams(140, 140));

						fLay.addView(clear);
						fLay.addView(b1);
						final String x = (String) details[2];
						
						
						clear.setOnClickListener(new OnClickListener() {
							@TargetApi(Build.VERSION_CODES.GINGERBREAD)
							@SuppressLint("NewApi")
							@SuppressWarnings("deprecation")
							public void onClick(View v) {

								dd.sendData(new String[] { dd.getUser(),
										"clearRejectGame", x });
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
					} else if (details[1].equals("pending")) {
						final Button b1 = new Button(this);
						b1.setText("waiting for " + details[2].toString()
								+ " to accept request");
						b1.setTextSize(16);
						b1.setFocusable(false);
						b1.setClickable(false);
						b1.setY(butY);

						b1.setWidth(width * 9 / 10);
						b1.setHeight(150);
						b1.setX(15);
						fLay.addView(b1);

					} else if (details[1].toString().equals("incoming")) {

						final Button b1 = new Button(this);

						ImageButton rej = new ImageButton(this);

						rej.setImageResource(R.drawable.redx);
						b1.setText(details[2].toString());
						b1.setFocusable(false);
						b1.setClickable(false);

						ImageButton chk = new ImageButton(this);
						chk.setImageResource(R.drawable.chk);
						chk.setAdjustViewBounds(true);

						chk.setMinimumHeight(width * 7 / 100);
						chk.setMinimumWidth(width * 7 / 100);
						chk.setMaxHeight(width * 7 / 100);
						chk.setMaxWidth(width * 7 / 100);
						chk.setY(butY + (height * 1 / 100));
						chk.setX(width * 4 / 100);
						chk.setLayoutParams(new LayoutParams(140, 140));
						fLay.addView(chk);

						rej.setAdjustViewBounds(true);
						rej.setY(butY + (height * 1 / 100));
						rej.setX(width * 72 / 100);

						rej.setMinimumHeight(width * 7 / 100);
						rej.setMinimumWidth(width * 7 / 100);
						rej.setMaxHeight(width * 7 / 100);
						rej.setMaxWidth(width * 7 / 100);

						rej.setLayoutParams(new LayoutParams(140, 140));

						fLay.addView(rej);
						b1.setY(butY);

						b1.setWidth(width * 9 / 10);
						b1.setHeight(150);
						b1.setX(15);
						fLay.addView(b1);
						final String x = (String) details[2];
						final String y = (String) details[3];
						chk.setOnClickListener(new OnClickListener() {
							@TargetApi(Build.VERSION_CODES.GINGERBREAD)
							@SuppressLint("NewApi")
							@SuppressWarnings("deprecation")
							public void onClick(View v) {
								dd.sendData(new String[] { dd.getUser(),
										"createGame", x, y });
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
								dd.sendData(new String[] { dd.getUser(),
										"rejectGame", x });
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
					
				}else if( (  details[1].equals(dd.getUser())  &&  details[4].equals("5")  )   ||   ( details[2].equals(dd.getUser())  &&  details[5].equals("5")  )   ){        
					butY = butY-170;
				}
				else if (details[0].equals("comp")
						|| details[0].equals("versus")
						|| details[0].equals("mastermind")) {
					Object[] stat = (Object[]) gameStats[countforstats];
					ImageView chk = new ImageView(this);
					if (details[0].equals("comp"))
						chk.setImageResource(R.drawable.racing);
					else if (details[0].equals("versus"))
						chk.setImageResource(R.drawable.vs);
					else if (details[0].equals("mastermind"))
						chk.setImageResource(R.drawable.mastermind);
					chk.setAdjustViewBounds(true);
					chk.setX(width*4/100);
					chk.setY(butY + height*1/100);
					chk.setMinimumHeight(width * 15 / 100);
					chk.setMinimumWidth(width * 15 / 100);
					chk.setMaxHeight(width * 15 / 100);
					chk.setMaxWidth(width * 15 / 100);
					chk.setLayoutParams(new LayoutParams(120, 120));
					ImageView icon = new ImageView(this);
					
						icon.setImageResource(dd.getIcon(Integer.parseInt((String)stat[6])));
					icon.setAdjustViewBounds(true);
					icon.setX(width*22/100);
					icon.setY(butY + height*1/100);
					icon.setMinimumHeight(width * 15 / 100);
					icon.setMinimumWidth(width * 15 / 100);
					icon.setMaxHeight(width * 15 / 100);
					icon.setMaxWidth(width * 15 / 100);
					icon.setLayoutParams(new LayoutParams(120, 120));
					TextView score = new TextView(this);
					score.setX(width*70/100);
					score.setY(butY+height*2/100);
					score.setText("W-L-T\n"+stat[1]+"-"+stat[2]+"-"+stat[3]);
					TextView ratio = new TextView(this);
					ratio.setX(width*57/100);
					ratio.setY(butY+height*2/100);
					fLay.addView(icon);
					Float tra = Float.parseFloat((String)stat[4])/Float.parseFloat((String)stat[5]);
					
					ratio.setText("Score\n"+String.format("%.2f", tra));
					chk.setLayoutParams(new LayoutParams(120, 120));
					fLay.addView(score);
					fLay.addView(ratio);
					fLay.addView(chk);
					String other = "";
					String status = "";
					if (details[1].equals(dd.getUser())) {
						other = details[2].toString();
						status = details[4].toString();
					} else {
						other = details[1].toString();
						status = details[5].toString();
					}
					TextView turnTV = new TextView(this);
					if(dd.getPos()==1){
						turnTV.setText("Turn : " + details[3]);
					}
					else{
					turnTV.setText("Turn : " + details[8]);
					}
					turnTV.setX(width*40/100);
					turnTV.setY(butY+10);
					fLay.addView(turnTV);
					ImageView statusView = new ImageView(this);
					if (status.equals("1")||status.equals("3")||status.equals("4")) {
						statusView.setImageResource(R.drawable.redbutton);
					} else {
						statusView.setImageResource(R.drawable.greenstatus);
					}

					statusView.setAdjustViewBounds(true);
					statusView.setX(width * 83 / 100);
					statusView.setY(butY + 10);
					statusView.setMinimumHeight(width * 5 / 100);
					statusView.setMinimumWidth(width * 5 / 100);
					statusView.setMaxHeight(width * 5 / 100);
					statusView.setMaxWidth(width * 5 / 100);

					statusView.setLayoutParams(new LayoutParams(width * 8 / 100, width * 8 / 100));
					fLay.addView(statusView);
					
					
					
					Button a1 = new Button(this);
					a1.setY(butY);
					a1.setText(other);
					a1.setWidth(width * 9 / 10);
					a1.setHeight(150);
					a1.setX(15);

					final String ss = details[7].toString();
					final String type = details[0].toString();
					a1.setOnClickListener(new OnClickListener() {
						@TargetApi(Build.VERSION_CODES.GINGERBREAD)
						@SuppressLint("NewApi")
						@SuppressWarnings("deprecation")
						public void onClick(View v) {

							try {
								dd.sendData(new String[] { dd.getUser(),
										"loadGame", ss });
								dd.loadGame();
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
									"www.learnplex.www.GAME");
							startActivity(openMenu);
							// dd.createGame();

						}
					});

					//---------------------Time Settings
					
					
					
					TextView timeTV = new TextView(this);
					timeTV.setX(width * 40 / 100);
					timeTV.setY(butY + 90);
					Date date = new Date();
					String time = new SimpleDateFormat("ddMMyyyyHHmm")
							.format(date);
					String lastTime = details[6].toString();
					int[] time1 = new int[5];
					time1[0] = Integer.parseInt(details[6].toString()
							.substring(0, 2));
					time1[1] = Integer.parseInt(details[6].toString()
							.substring(2, 4));
					time1[2] = Integer.parseInt(details[6].toString()
							.substring(4, 8));
					time1[3] = Integer.parseInt(details[6].toString()
							.substring(8, 10));
					time1[4] = Integer.parseInt(details[6].toString()
							.substring(10, 12));
					int[] now = new int[5];
					now[0] = Integer.parseInt(time.toString().substring(0, 2));
					now[1] = Integer.parseInt(time.toString().substring(2, 4));
					now[2] = Integer.parseInt(time.toString().substring(4, 8));
					now[3] = Integer.parseInt(time.toString().substring(8, 10));
					now[4] = Integer
							.parseInt(time.toString().substring(10, 12));
					if (now[2] == time1[2]) {
						if (now[1] == time1[1]) {
							if (now[0] == time1[0]) {
								if (now[3] == time1[3]) {
									if (now[4] == time1[4]) {
										timeTV.setText("Less than a Minute ago");
									} else {
										timeTV.setText((now[4] - time1[4])
												+ " minutes ago");
									}

								} else {
									if (now[3] - time1[3] == 1) {
										if ((60 - time1[4]) + now[4] < 60) {
											timeTV.setText((60 - time1[4])
													+ now[4] + " minutes ago");
										} else {
											timeTV.setText("1 hour ago");
										}
									} else {
										timeTV.setText((now[3] - time1[3])
												+ " hours ago");
									}
								}
							} else {
								if (now[0] - time1[0] == 1) {
									if ((25 - time1[3]) + now[3] < 24) {
										timeTV.setText((25 - time1[3]) + now[3]
												+ " hours ago");
									} else {
										timeTV.setText("1 day ago");
									}
								} else {
									timeTV.setText((now[0] - time1[0])
											+ " days ago");
								}
							}
						} else {
							if (now[1] - time1[1] == 1) {
								if ((31 - time1[0]) + now[0] < 28) {
									timeTV.setText((31 - time1[0]) + now[0]
											+ " days ago");
								} else {
									timeTV.setText("More Than a month ago");
								}
							} else {
								timeTV.setText("More Than a month ago");
							}
						}
					} else {

					}
					countforstats++;
					fLay.addView(timeTV);
					fLay.addView(a1);
				}
				butY = butY + 170;
			}
		}
		

		lV.setLayoutParams(new LayoutParams(width * 98 / 100, height*40/100));
		fLay.setLayoutParams(new LayoutParams(width * 95 / 100, butY));
		lV.setScrollbarFadingEnabled(false);
		lV.setVerticalScrollBarEnabled(true);
		lV.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_INSET);
		
		lV.setScaleX(1);
		lV.setScrollBarSize(100);
		lV.setX(15);
		lV.setY(180);
		lV.setMinimumHeight(150 * 3);
		lV.setMinimumWidth(width * 9 / 10);
		
		fLay.setBackgroundColor(Color.rgb(255-40, 255-40, 255-30));
		
		r.setBackgroundColor(Color.rgb(255-35, 255-20, 255-35));
		lV.addView(fLay);
		r.addView(b);
		r.addView(b2);
		r.addView(iv);
		r.addView(tv);
		//r.addView(lvl);
		r.addView(lV);
		r.addView(version);
		r.addView(ladder);
//		
		b2.setOnClickListener(new OnClickListener() {
			@TargetApi(Build.VERSION_CODES.GINGERBREAD)
			@SuppressLint("NewApi")
			@SuppressWarnings("deprecation")
			public void onClick(View v) {
				SharedPreferences settings = getSharedPreferences("user", 
			    	    Context.MODE_PRIVATE);
		
				SharedPreferences.Editor editor = settings.edit();
			    editor.putString("user", "");
			    editor.commit();
	    		
				//Intent openMenu = new Intent(
						//"android.intent.category.LAUNCHER");
				//startActivity(openMenu);
	    		Intent i = getBaseContext().getPackageManager()
	    	             .getLaunchIntentForPackage( getBaseContext().getPackageName() );
	    	i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    	startActivity(i);
				// dd.createGame();
			}

		});
		ladder.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dd.sendData(new String[] {dd.getUser(),"loadLadder","","ladder"});
				try {
					dd.loadLadder();
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
				
				Intent openMenu = new Intent("www.learnplex.www.GAME");
				startActivity(openMenu);
			}
		});
		fList.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
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
			}
		});
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				Intent openMenu = new Intent("www.learnplex.www.PROFILE");
				startActivity(openMenu);
			}
		});

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}


	public void sendMessage(String msg) {
		try {
			out.writeObject(msg);
			out.flush();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}
	public void failConnect(){
		Button fail = new Button(this);
		fail.setX(width*25/100);
		fail.setY(height*25/100);
		fail.setWidth((width*50/100));
		fail.setHeight((height*50/100));
		r.addView(fail);
		 fail.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					finish();
						
				}
	}
			);
	}

}