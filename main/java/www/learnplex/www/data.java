package www.learnplex.www;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.net.InetSocketAddress;
import java.net.Socket;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;



public class data {
//


//Current Game Info
	static boolean victory = false;
static String gameType ;
static Object[] stats;
static String keyword ="";
static String other;
static Object [] abc = new String[4];
static Object [] abcother = new String[4];
static Object [] userWordList,ladder;
static Object [] otherWordList;
static Object[] recentLadder;
static Object[] ladderScores;
final int[] icons = {R.drawable.lego,R.drawable.buckethead,R.drawable.coal,R.drawable.earth,R.drawable.galaxy,R.drawable.house,R.drawable.universe};
static String gameNum = "";

	static Object[] inc = new Object[0];
static boolean failed = false,isLadder = false;
static int save,status,pos;
static String split1 = "", split2 = "", split3 = "", split4 = "",
split5 = "", split6 = "";
static String data = "31321",friend="",pass="";
static String classes[];
static Object [] recentGameList,recentGameStats,profile;
static String yourWords[],theirWords[];
static String readMsg = "",yourword="",yourabc="",theirabc="";
static Socket requestSocket;
static ObjectOutputStream out;
	static ObjectInputStream in;
	static String[] message;
	static int connect = 0;
	static String delivery[];
	static Object fr[];
	static String user="";
	static String score="";
	public void connect(){
		 
			try {
							failed=false;
							  requestSocket = new Socket();
							requestSocket.connect(new InetSocketAddress("24.107.229.110", 54433), 1500);
							
							  out = new ObjectOutputStream(requestSocket
										.getOutputStream());
								out.flush();
								in = new ObjectInputStream(requestSocket
										.getInputStream());
						
						  // Your database code here
					 
				 
					
			} catch (Exception e) {
				System.out.println("cant connect");
				failed=true;
				/*Timer timer = new Timer();
				timer.schedule(new TimerTask() {
					  @Override
					  public void run() {
						connect();  
						  
					  }}, 2*60*1000);
						  }*/ 
	}
			}
	public void sendData(String[] msg){
		connect();
		sendMessage(msg);
	}
	
	public boolean getFailed(){
		return failed;
	}
	//Game 
	public SpannableString colorText(Object[] wordlist, Object[]abcM) {

		int listCount = 0;
		String list ="";
		if(wordlist==null){
			
		}
		else{
		while (listCount < wordlist.length) {
			Object[] details = (Object[])wordlist[listCount];
			list = list + details[1]+" "+details[2]+'\n';
			listCount++;
		}
		
		listCount=0;
		SpannableString display = new SpannableString(list);
		while (listCount < wordlist.length) {
			Object[] details = (Object[])wordlist[listCount];
			for (int i = 0; i < 5; i++) {
				int loc = (listCount * 8) + i;
				int loc2 = (listCount * 8) + i + 1;
				if (((String) abcM[3]).indexOf(details[1].toString().charAt(i)) >= 0) {
										
					display.setSpan(new ForegroundColorSpan(Color.rgb(240, 160,160)), loc, loc2,
							0);

				} else if (((String) abcM[2]).indexOf(details[1].toString().charAt(i)) >= 0) {
					display.setSpan(new ForegroundColorSpan(Color.rgb(160, 240,160)), loc,
							loc2, 0);
				} else
					display.setSpan(new ForegroundColorSpan(Color.BLACK), loc,
							loc2, 0);

			}
			listCount++;
		}
		return display;
		}
		return null;
	}
	public String getLadderString(){
		Object[] ladder = getLadderScores();
		if(ladder==null){
			return "";
		}
		else{
			int count = 0;
			String list = "";
			Object[] details ;
			while(count<ladder.length){
				details = (Object[])ladder[count];
				if(details[0].equals(user)){
					score =(String) details[1];
				}
				list = list +details[2]+"  "+ details[0] +"  "+details[1]+"\n"; 
				count ++;
			}
			return list;
		}
		
	}
	public SpannableString[] colorScore() {
		Object[] board =(Object[]) recentLadder;
		
		
		if(board==null){
			return null;
		}
		else{
			int listCount = 0;
			String list ="";
			Object[] details;
			SpannableString display;
			SpannableString[] newBoard = new SpannableString[board.length];
		int curLen;
		double ff;
		while (listCount < board.length) {
			list = "";
			details = (Object[])board[listCount];
				list = details[0] +"#  "+ details[2]+"  "+details[1]+" ";
				
				if(Double.parseDouble((String)details[3])<0.0){
					ff  = Double.parseDouble((String)details[3])*(-1.0);
					curLen = list.length();
					list = list +" +"+ ff;
					display = new SpannableString(list);
					System.out.println(curLen+"    "+list.length());
					display.setSpan(new ForegroundColorSpan(Color.GREEN), 10, 15,
							0);
					
				}
				else if(Double.parseDouble((String)details[3])>0){
					ff  = Double.parseDouble((String)details[3])*(-1.0);
					curLen = list.length();
					list = list +" "+ ff;
					display = new SpannableString(list);
					display.setSpan(new ForegroundColorSpan(Color.RED), curLen, list.length(),
							0);
				}
				else{
					ff  = Double.parseDouble((String)details[3]);
					curLen = list.length();
					list = list +" "+ ff;
					display = new SpannableString(list);
					
				}
				
				newBoard[listCount]=(SpannableString)display;
				listCount++;
		}	
		return newBoard;
		}
		
	}
	
	public Object getStatus(){
		return stats[getPos()+3];
	}
	
	public void storePos(){
		String user1 = stats[1].toString();
		if(user1.equals(user)){
			pos = 1;
			
		}
		else{
			pos = 2;
		}
		System.out.println(pos);
		}
	
	public SpannableString getUserdisplayGame(){
		if(userWordList==null)
			return null;
		return colorText(userWordList, abc);
	}
	public SpannableString getOtherdisplayGame(){
		return colorText(otherWordList, abcother);
	}
	public Object[] getStats(){
		return stats;
	}
	public void storeStats(Object[] msg){
		stats = (Object[]) msg;
	}
	public int getPos(){
		return pos;
	}
	
	public Object[] fixAbc(Object[] msg){
		for(int i = 1;i<msg.length;i++){
			for(int b = 0;msg[i].toString().length()>b;b++){
				if(msg[i].toString().charAt(b)<=0){
					
				}
			}
		}
		
		return msg;
	}
	
	public String getKeyWord(){
		return keyword;
	}
	public Object[] getAbc(){
		return abc;
	}
	public Object[] getAbcOther(){
		return abcother;
	}
	public Object[] getRecentLadder(){
		return recentLadder;
	}
	public Object[] getLadderScores(){
		return ladderScores;
	}
	public boolean getVictory(){
		return victory;
	}
	public boolean isLadder(){
		return isLadder;
	}
	public void loadLadder() throws OptionalDataException, ClassNotFoundException, IOException{
		isLadder = true;
		Object[] game = getMessage();						
		recentLadder = getMessage();
		ladderScores = getMessage();
	int count = 0;
	Object[] details =(Object[]) game[count];
	gameNum = details[4].toString();
	storeStats(details);
	gameType = details[0].toString();
	count++;
	details =(Object[]) game[count];

				
	userWordList= new Object[0];
	abc = details;
	details =(Object[]) game[count];
	
	count++;
	
	while(count<game.length){
		details =(Object[]) game[count];
			userWordList = expand(userWordList);
			userWordList[userWordList.length-1] = details; 
		count++;
	}
}
	public void loadGame() throws OptionalDataException, ClassNotFoundException, IOException{
		isLadder = false;
		Object[] game = getMessage();
		int count = 0;
		Object[] details =(Object[]) game[count];
		gameNum = details[7].toString();
		storeStats(details);
		storePos();
		gameType = details[0].toString();
		count++;
		details =(Object[]) game[count];
		if (gameType.equals("comp")){
			
		}
		
		else{			
			
		
			keyword=details[0].toString();
			count++;
		}
		userWordList= new Object[0];
		otherWordList= new Object[0];
		details =(Object[]) game[count];
		abc = details;
		count++;
		if(gameType.equals("comp")){
			
		}
		else{
		details =(Object[]) game[count];
		abcother = details;
		count++;
		}
		while(count<game.length){
			details =(Object[]) game[count];
			if(details[0].equals(user)){
				userWordList = expand(userWordList);
				userWordList[userWordList.length-1] = details; 
				
			}
			else{
				otherWordList = expand(otherWordList);
				otherWordList[otherWordList.length-1] = details; 
			}
			count++;
		}
	}
	public String sendTarget(String input) throws OptionalDataException, ClassNotFoundException, IOException{
		connect();
		sendMessage(new String[]{user,"enterTarget",gameNum,input});
		Object[] details = getMessage();
		
if(Integer.parseInt(details[0].toString())==0){
			
		}
		else if((Integer.parseInt(details[0].toString())==1)){
			
			return "loading";
			
		}
		else if((Integer.parseInt(details[0].toString())==2)){
			
		}
		else if(details[0].equals("3")){
			
		}
		return "didnt load";
	}
	
	public String sendLadderGuess(String input) throws OptionalDataException, ClassNotFoundException, IOException{
		connect();
		sendMessage(new String[]{user,"enterLadderGuess",gameNum,input});
		victory=false;
		Object[] details = getMessage();
		Object[] details2 = getMessage();
		
		if(Integer.parseInt(details[0].toString())==0){
			return "Word not found";
		}
		else if((Integer.parseInt(details[0].toString())==1)){
			
			if(Integer.parseInt(details2[0].toString())==6)
				victory=true;
			
			loadLadder();
			if(Integer.parseInt(details2[0].toString())==6)
				return "victory";
			
			return "Word Accepted";
			
		}
		else if((Integer.parseInt(details[0].toString())==2)){
			return "Must enter 5 letters";
		}
		else if((Integer.parseInt(details[0].toString())==3)){
			return "You worded your self";
		}
		else if(details[0].equals("6")){
			return "You win";
		}
		return "Must enter 5 letters";
	}
	
	public String sendGuess(String input) throws OptionalDataException, ClassNotFoundException, IOException{
		connect();
		sendMessage(new String[]{user,"enterGuess",gameNum,input});
		Object[] details = getMessage();
		if(Integer.parseInt(details[0].toString())==0){
			
		}
		else if((Integer.parseInt(details[0].toString())==1)){
			
			loadGame();
			return "loading";
			
		}
		else if((Integer.parseInt(details[0].toString())==2)){
			
		}
		else if(details[0].equals("3")){
			
		}
		return "Must use known Letters";
	}
	public Object[] getOtherWordList(){
		return otherWordList;
	}
	public void friendList() throws OptionalDataException, ClassNotFoundException, IOException{
		connect();
		String msga[] = {user,"friendlist"};
		
		sendMessage(msga);
		
	
		
		fr = (Object[]) in.readObject();
	}
	public Object[] getFriendList(){
		
		return fr;
	}
	public String getUser(){
		return user;
	}
	
//
	public Object[] getProfile(){
		return profile;
	}
	public Object[] getRecentStats(){
		return recentGameStats;
	}
	public Object[] getRecentGames(){
		return recentGameList;
	}
	public void login(String msg) throws OptionalDataException, ClassNotFoundException, IOException{
		
		connect();
		String [] delivery = {msg};
		
		sendMessage(delivery);
		user = msg;
		
		recentGameList = (Object[]) getMessage();
		recentGameStats = (Object[]) getMessage();
		profile = (Object[]) getMessage();
		ladder = (Object[]) getMessage();
		
		}
		//try{
		//message = (String[]) in.readObject();
		/*if (message[0].equals("User Not Found")){
			
		}
		else{
			
		}
		} catch (Exception e) {
		// TODO Auto-generated catch block
	} 
		*/
	
	public void sendMessage(String[] msg)
	{
		try{
			out.writeObject(msg);
			out.flush();
		}
		catch(IOException ioException){
			return;
		}
	}
	public Object[] trimArray(Object[] msg){
		int x= 0;
		Object[] details = new Object[0];
		details = (Object[]) msg[x];
		do{
			x++;
			details = (Object[]) msg[x];
				
				
			    

			    //an alternative to using System.arraycopy would be a for-loop:
			    // for(int i = 0; i < OrigArray.length; i++)
			    //     newArray[i] = OrigArray[i];
			    
			    
			}while(details[0]!=null);
		Object[] newArray = new Object[x];
		System.arraycopy(msg, 0, newArray, 0, x);
		return newArray;
	}
	public int getIconLength(){
		return icons.length;
	}
	public int getIcon(int count) {
		
		int icon = icons[count];
		
		return icon;
		
	}
	
	public Object[] getMessage() throws OptionalDataException, ClassNotFoundException, IOException{
		
		
		return (Object[]) in.readObject();
	}
	public Object[] expand(Object[] array) {
		Object[] newArray = new Object[array.length + 1];
	    System.arraycopy(array, 0, newArray, 0, array.length);

	    //an alternative to using System.arraycopy would be a for-loop:
	    // for(int i = 0; i < OrigArray.length; i++)
	    //     newArray[i] = OrigArray[i];
	    
	    return newArray;
	}
	}







