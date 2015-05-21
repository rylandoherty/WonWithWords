package www.learnplex.www;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.net.Socket;
import java.util.HashMap;

import javax.swing.JComponent;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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


public class Profile extends Activity {
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
	 int count = 0;
	 
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
		
		final ImageView iv = new ImageView(this);
		ImageButton arrowUp = new ImageButton(this);	
		ImageButton arrowDown = new ImageButton(this);	 
		Button save = new Button(this);
		r = (RelativeLayout)findViewById(R.id.linlay1);
		RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		relativeParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		width = size.x;
		height = size.y;
		save.setX(width*25/100);
		save.setY(height*75/100);
		save.setWidth(width*50/100);
		save.setHeight(height*10/100);
		save.setText("Save");
		Object[] profile = dd.getProfile();
		
		  iv.setImageResource(dd.getIcon(Integer.parseInt((String)profile[5])));	
		  count = (Integer.parseInt((String)profile[5]));
		  iv.setAdjustViewBounds(true);
			iv.setX(width*30/100);
			iv.setY(height*45/100);
		
			iv.setLayoutParams(new LayoutParams(width*40/100, width*40/100));
		    		    
			arrowUp.setImageResource(R.drawable.arrowup);	
			  arrowUp.setAdjustViewBounds(true);
			  //arrowUp.setBackground(null);
				arrowUp.setX(width*65/100);
				arrowUp.setY(height*45/100);
			
				arrowUp.setLayoutParams(new LayoutParams(width*20/100, width*40/100));
				
				arrowDown.setImageResource(R.drawable.arrowdown);	
				  arrowDown.setAdjustViewBounds(true);
				  //arrowDown.setBackground(null);
					arrowDown.setX(width*15/100);
					arrowDown.setY(height*45/100);
					
					arrowDown.setLayoutParams(new LayoutParams(width*20/100, width*40/100));
			    
					
					
					
		  
		    r.addView(iv);
		    r.addView(arrowUp);
		    r.addView(arrowDown);
		    r.addView(save);
		    createSelection(5);
		        	
		    		
		        

		//SharedPreferences app_preferences = 
	        //	PreferenceManager.getDefaultSharedPreferences(this);
	        //String user = app_preferences.getString("user", "");
	       // if("user".equals("")){
	        	
	      //  }
		    
		    
		    
		    
		    
		    
		    
		    
		  
		    
		    
		    
		    
		    
	        arrowUp.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					int length = dd.getIconLength();
					if(count<length-1){
						count++;	
						iv.setImageResource(dd.getIcon(count));
					}
					
					
				
				
				
				}			});
	        arrowDown.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					if(count>0){
						count--;	
						iv.setImageResource(dd.getIcon(count));
					}
					
					
				
				
				
				}			});
	        save.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					dd.sendData( new String[] {dd.getUser(),"pickIcon",count+""});
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
					
				
				
				
				}			});
}



	public void createSelection(int choice){
		Graphics2D g2d = null;
		
        g2d.setColor(Color.blue);
		PieChart3 pie = new PieChart3(); 
		pie.paint(g2d);
		Button Friend = new Button(this);
	
		Friend.setX(width*5/100);
		Friend.setY(height*4/100);
		Friend.setWidth(width*30/100);
		Friend.setHeight(height*10/100);
		Friend.setText("Friends");
		
		Button Ladder = new Button(this);
		
		Ladder.setX(width*35/100);
		Ladder.setY(height*4/100);
		Ladder.setWidth(width*30/100);
		Ladder.setHeight(height*10/100);
		Ladder.setText("Ladder");
		
		Button Self = new Button(this);
		
		Self.setX(width*65/100);
		Self.setY(height*4/100);
		Self.setWidth(width*30/100);
		Self.setHeight(height*10/100);
		Self.setText("Self");
		
		
		
		r.addView(Friend);
		r.addView(Ladder);
		r.addView(Self);
		
		
		if(choice == 2){
			r.removeAllViews();
			
		}
		else if(choice == 3){
			r.removeAllViews();
			
		}
		else if(choice == 4){
			r.removeAllViews();
	
		}
		
	}
	
	
	
	
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

class Slice {
	   double value;
	   Color color;
	   public Slice(double value, Color color) {  
	      this.value = value;
	      this.color = color;
	   }
	}
	class PieChart3 extends JComponent {
	   Slice[] slices = { new Slice(5, Color.black), 
	   new Slice(33, Color.green),
	   new Slice(20, Color.yellow), new Slice(15, Color.red) };
	   PieChart3() {}
	   public void paint(Graphics g) {
	      drawPie((Graphics2D) g, getBounds(), slices);
	   }
	   void drawPie(Graphics2D g, Rectangle area, Slice[] slices) {
	      double total = 0.0D;
	      for (int i = 0; i < slices.length; i++) {
	         total += slices[i].value;
	      }
	      double curValue = 0.0D;
	      int startAngle = 0;
	      for (int i = 0; i < slices.length; i++) {
	         startAngle = (int) (curValue * 360 / total);
	         int arcAngle = (int) (slices[i].value * 360 / total);
	         g.setColor(slices[i].color);
	         g.fillArc(area.x, area.y, area.width, area.height, 
	         startAngle, arcAngle);
	         curValue += slices[i].value;
	      }
	   }
	}
