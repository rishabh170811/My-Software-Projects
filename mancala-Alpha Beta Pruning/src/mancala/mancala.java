package mancala;
/*learning agent here */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.InputStreamReader;
import java.util.ArrayList;
class Player{
	int playa=0;//no of public balls
	String name="";
	Player(int assign){
		playa=assign;
	}
}

class State implements Cloneable{                       
	//game state info of the game
	public static int count=0;
	public static int saveme=Integer.MIN_VALUE;
	public static State myanswer;
	public static int depth1;
	public static int turn1;
	public static int infichk;
	int flag=0;
	int eval=0;
	int alpha=Integer.MIN_VALUE;
   	int beta=Integer.MAX_VALUE;
	public static int eval_tot=0;
	String name="";
	ArrayList<Player>player1=new ArrayList<Player>();
	ArrayList<Player>player2=new ArrayList<Player>();
	int visited=0;
	int turn=0;
	int Mancala1=0;
	int Mancala2=0;
	int free_turn=0;
	int curr_chance=0;
	int x=0; //test
public State(State x){
	this.eval=x.eval;
	this.visited=x.visited;
	this.Mancala1=x.Mancala1;
	this.Mancala2=x.Mancala2;
	this.free_turn=x.free_turn;
	this.curr_chance=0;
	this.turn=x.turn;
	this.name=x.name;
	//this.x=x.x; //test
	//this.player1=(ArrayList<Player>)x.player1.clone();
	//this.player2=(ArrayList<Player>)x.player2.clone();
	for(int i=0;i<x.player1.size();i++){
		this.player1.add(new Player(x.player1.get(i).playa));
		this.player2.add(new Player(x.player2.get(i).playa));
		String a=Integer.toString(i+2);
		this.player1.get(i).name="B"+a;
		this.player2.get(i).name="A"+a;;
	}
}

public ArrayList<Player> input_players_state(String playerstr){ //taking input of the number of balls to be stored at each position for the players
	ArrayList<Player>player=new ArrayList<Player>();
	int i=0;
	for(i=0;i<playerstr.length();i++){
		if(playerstr.charAt(i)==' '){
			player.add(new Player(Integer.parseInt(playerstr.substring(0,i))));
			playerstr=playerstr.substring(i+1);
			i=0;
		}
	}
	//bw1.appendln(playerstr);
	player.add(new Player(Integer.parseInt(playerstr)));
	return player;
}
	public State(BufferedReader br) throws IOException{          
		String player2str=br.readLine();
		String player1str=br.readLine();
		player2=input_players_state(player2str);
		for(int i=0;i<player2.size();i++){
			String a=Integer.toString(i+2);
			player2.get(i).name="A"+a;
		}
		player1=input_players_state(player1str);
		for(int i=0;i<player1.size();i++){
			String a=Integer.toString(i+2);
			player1.get(i).name="B"+a;                  
		}
		Mancala1=Integer.parseInt(br.readLine());
		Mancala2=Integer.parseInt(br.readLine());
	}
public State shiftplaya1(State player1chance,int value,int chance,int turn,int amwhere){
	int clone_index=chance;
	//clockwise shifting
	while(value!=0){														//shift for player 1								
		if(player1chance.player1.size()>clone_index)                        //if index less than player1s array 	
		{	
			value--;
			player1chance.player1.get(clone_index).playa+=1; //if index =player1 l..adding1 bead to mancala
			if(value==0&&turn%2==1&&player1chance.free_turn==0&&player1chance.player1.get(clone_index).playa==1&&amwhere==0){
				//capture
				Mancala1+=player1chance.player2.get(clone_index).playa+1;
				player1chance.player1.get(clone_index).playa=0;
				player1chance.player2.get(clone_index).playa=0;
			}
			clone_index++;
		}
		else if(player1chance.player1.size()==clone_index&&turn%2==1){                    //invoking player2 shift opereation
			value--;
			player1chance.Mancala1++;
			if(value==0){
				player1chance.free_turn=1;
			}
			clone_index++;
		}
		else {
			player1chance=shiftplaya2(player1chance,value,player1chance.player1.size()-1,turn,amwhere);  //passing clone_index to state the starting point
			value=0;
			amwhere=1; //to confirm that beads got over in player2s box..hence it returned and no capture will be triggered
		}
							//reducing element value by 1 each time
	}
	if(value==0&&turn%2==1){                                            //player 1s chance Bs chance
		player1chance.eval=player1chance.Mancala1-player1chance.Mancala2;
	}
	return player1chance;
}
public State shiftplaya2(State player2chance,int value,int chance,int turn,int amwhere){       //chance denotes the index from which shifting will take place
	int clone_index=chance;                             									  //anticlockwise shifting
	while(value!=0){															  			 //shift for player 2
	if(clone_index>=0){				                //to see if index is greater than 0 											
		value--;	
		player2chance.player2.get(clone_index).playa+=1;
		if(value==0&&turn%2==0&&player2chance.free_turn==0&&player2chance.player2.get(clone_index).playa==1&&amwhere==1){
			//capture
			Mancala2+=player2chance.player1.get(clone_index).playa+1;
			player2chance.player2.get(clone_index).playa=0;
			player2chance.player1.get(clone_index).playa=0;
		}
		clone_index--;
	}
	else if(clone_index==-1&&turn%2==0){
			value--;
			player2chance.Mancala2++;                     
			if(value==0){
				player2chance.free_turn=1;
			}
			clone_index--;
	}
	else{
			player2chance=shiftplaya1(player2chance,value,clone_index=0,turn,amwhere);
			value=0;
			amwhere=0;
			//to confirm that beads got over in player2s box..hence it returned and capture won't be triggered
	}
	if(value==0&&turn%2==0){                                 //player 2z chance As chance
		player2chance.eval=player2chance.Mancala2-player2chance.Mancala1;
	}
	//	clone_index--;		
	}
	return player2chance;
}
public State update_State(State playing,int i,int turn){           // updating a state 	
	if(turn%2==1){												//Player 1s whose turn...updating the state
		//call shift clockwise
		int value=playing.player1.get(i).playa;
		if(value==0)
		{
			playing.curr_chance=1;
		}
		playing.player1.get(i).playa=0;
		playing=shiftplaya1(playing,value,i+1,turn,0);
	}
	else{
		int value=playing.player2.get(i).playa;
		if(value==0)
		{
			playing.curr_chance=1;
		}
		//call shift anticlockwise
		value=playing.player2.get(i).playa;					//Player 2s turn updating the state
		playing.player2.get(i).playa=0;
		playing=shiftplaya2(playing,value,i-1,turn,1);
	}
	if(check_arr1(playing,turn)==1){
		for(int k=0;k<playing.player1.size();k++){
		playing.Mancala2+=playing.player2.get(k).playa;
		playing.player2.get(k).playa=0;
		}
	}
	if(check_arr2(playing,turn)==1){
		for(int k=0;k<playing.player1.size();k++){
		playing.Mancala1+=playing.player1.get(k).playa;
		playing.player1.get(k).playa=0;
		}
	}
	//compute eval diff and send the state
	if(turn1%2==1)
		playing.eval=Mancala1-Mancala2;
	else
		playing.eval=Mancala2-Mancala1;
	return playing; //returning the updated state
}
void display(State output,BufferedWriter bw) throws IOException{      
	for(int i=0;i<output.player1.size();i++){
	bw.append(output.player2.get(i).playa+" ");
	}//to print the state
	bw.append(System.lineSeparator());
	for(int i=0;i<output.player1.size();i++){
	bw.append(output.player1.get(i).playa+" ");
	}
	bw.append(System.lineSeparator());
	bw.append(output.Mancala2+" "); //mancala of B
	bw.append(System.lineSeparator());
	bw.append(output.Mancala1+" ").append(System.lineSeparator()); //mancala of A
}

int check_arr1(State playing,int turn){
	int i;
	for( i=0;i<playing.player1.size();i++){
		if(playing.player1.get(i).playa!=0){
			break;
		}
		}
if(i<playing.player1.size())
return 0;	
else
return 1;
}
int check_arr2(State playing,int turn){
	int i;
	for( i=0;i<playing.player1.size();i++){
		if(playing.player2.get(i).playa!=0){
			break;
		}
		}
if(i<playing.player1.size())
return 0;	
else
return 1;
}


int minmaxB(BufferedWriter bw,BufferedWriter bw1,int task,State playing,int turn,int depth,int j,State previous,int real_depth,int eval,State y,int depth_cut,int dummy,int prev) throws CloneNotSupportedException, IOException{ //player 1 chance
	//display(previous);
	if(depth==depth_cut+1||check_arr1(playing,turn)==1||check_arr2(playing,turn)==1){
		if(check_arr1(playing, turn)==1||check_arr2(playing, turn)==1){
		for(int k=0;k<playing.player1.size();k++){
			if(check_arr1(playing,turn)==1){
			playing.Mancala2+=playing.player2.get(k).playa;
			playing.player2.get(k).playa=0;
			}
			else if(check_arr2(playing,turn)==1){
		    playing.Mancala1+=playing.player1.get(k).playa;
		    playing.player1.get(k).playa=0;
			}
			}
			if(turn1%2==1)
			playing.eval=playing.Mancala1-playing.Mancala2; //for updating the eval 
			else
			playing.eval=playing.Mancala2-playing.Mancala1; //for updating the eval 	
	}
		infichk=1;
		return playing.eval;
		}

	int i;
	previous=new State(playing);
	State playing2=new State(playing);
	playing2.free_turn=0;
	for(i=0;i<playing.player1.size();i++){	
		prev=eval; //6
		State playing1=new State(playing2);		
		playing1=playing1.update_State(playing1, i, turn);
		//display(playing1);
		playing1.name=playing1.player1.get(i).name;
				playing1.turn=1;//turn for b
				if(playing1.curr_chance==1){
					if(real_depth==0&&i==playing.player1.size()-1){
						display(myanswer,bw);
					}						
					playing1.curr_chance=0;
					continue;
				}
				if(playing1.free_turn==1){
					   //printing the player B's chance as the tree explores downwards	
						//playing1.free_turn=0;					
					//if(!(eval<Integer.MAX_VALUE&&eval>Integer.MIN_VALUE))
					if(turn1%2==1)
						eval=Integer.MIN_VALUE;
						else
						eval=Integer.MAX_VALUE;
						
					if(task==2){
/*change*/					if((check_arr1(playing1, turn)==1||check_arr2(playing1, turn)==1)&&depth!=depth_cut){
							bw1.append(playing1.player1.get(i).name+","+depth+",");
							if(eval==Integer.MIN_VALUE)
							bw1.append("-Infinity").append(System.lineSeparator());
							else 
							bw1.append("Infinity").append(System.lineSeparator());;
						}
						bw1.append(playing.player1.get(i).name+","+depth+",");
						if(eval==Integer.MIN_VALUE&&check_arr1(playing1, turn)==0&&check_arr2(playing1, turn)==0)
							bw1.append("-Infinity").append(System.lineSeparator());
			       else	    if(eval==Integer.MAX_VALUE&&check_arr1(playing1, turn)==0&&check_arr2(playing1, turn)==0)
							bw1.append("Infinity").append(System.lineSeparator());	
			       else		bw1.append(String.valueOf(playing1.eval)).append(System.lineSeparator());
					}
					dummy=eval;
					playing1.name=playing1.player1.get(i).name;
					State playing3=new State(playing1);
					int eval1=minmaxB(bw,bw1,task,playing3,turn,depth,j,previous,real_depth+1,eval,y,depth_cut,dummy,prev);					
					if(infichk==1){
						eval=prev;
						infichk=0;
					}
					eval=prev;
					dummy=prev;
					if(real_depth>0&&playing3.free_turn==1&&previous.free_turn==0||real_depth>0&&playing3.free_turn==1&&previous.free_turn==1){
						if(turn1%2==1){
							if(eval1>eval)
						{
							eval=eval1;
							dummy=eval1;
						}
					}
						else{
							if(eval>eval1)
								eval=eval1;
								dummy=eval1;
						}
						if(previous.free_turn==0)
							bw1.append(previous.name+","+(depth-1)+","+eval).append(System.lineSeparator());
							else
							bw1.append(previous.name+","+depth+","+eval).append(System.lineSeparator());	
					}
					if(real_depth==0){                      //depth 0 print chance
						if(eval1>eval){
							eval=eval1;
							y=playing3;
							if(depth==1&&playing3.free_turn==0){
								if(eval>saveme){
									myanswer=playing3;
									saveme=eval;
								}
							}
							if(myanswer==null)
								myanswer=playing3;
							if(i==playing.player1.size()-1){
								display(myanswer,bw);
							}
						}
							if(task==2)
							bw1.append("root"+","+0+","+eval).append(System.lineSeparator());	
					}
						//bw1.appendln(playing1.player1.get(i).name+" "+depth);
				}
				else{     
						if(depth%2==1&&(depth!=depth_cut)){
						
							eval=Integer.MAX_VALUE; //TO MINIMIZE
							//dummy=eval;
						
						}
					else{
						if((depth!=depth_cut))
						eval=Integer.MIN_VALUE;    
					//	dummy=eval;
					}
				//		if(check_arr1(playing1, turn)==1)
					//		eval=-eval;
						if(task==2){
						if((check_arr1(playing1, turn)==1||check_arr2(playing1, turn)==1)&&depth!=depth_cut){
							bw1.append(playing1.player1.get(i).name+","+depth+",");
							if(eval==Integer.MIN_VALUE)
							bw1.append("-Infinity").append(System.lineSeparator());
							else 
							bw1.append("Infinity").append(System.lineSeparator());;
						}
						}
								if(task==2){
								bw1.append(playing.player1.get(i).name+","+depth+",");   //printing the player B's chance as the tree explores downwards	
								if(eval==Integer.MIN_VALUE&&depth!=depth_cut&&check_arr1(playing1, turn)==0&&check_arr2(playing1, turn)==0)
								bw1.append("-Infinity").append(System.lineSeparator());
				       else	    if(eval==Integer.MAX_VALUE&&depth!=depth_cut&&check_arr1(playing1, turn)==0&&check_arr2(playing1, turn)==0)
								bw1.append("Infinity").append(System.lineSeparator());	
				       else		bw1.append(String.valueOf(playing1.eval)).append(System.lineSeparator());	
							}
							
						int eval1=minmaxA(bw,bw1,task,playing1,turn+1,depth+1,i,previous,real_depth+1,eval,y,depth_cut,dummy,prev);	//recursiveeee @depth 1		
				//if(State.depth1+State.turn1%2==0)//if(size==1)//eval=eval1;																
						if(infichk==1){
							eval=prev;
							infichk=0;
						}
						eval=prev;
						dummy=prev;
						if(previous.turn==2&&real_depth!=0){  //see if its player 2's chance woww..hope it works
										//player 1 chance 2..4..6..8..10 
				 //printing the player A's node upon which the child nodes were invoked
				if(turn1%2==1){ //B's chance is 1st...max a
					if(eval<eval1)     //B-A MAX
						eval=eval1;       
					if(depth==1&&playing1.free_turn==0){
						if(eval>saveme){
							myanswer=playing1;
							saveme=eval;
						}
					}
				}
				
				else{
						if(eval>eval1)   //B-A MIN
						eval=eval1;
					}
				if(task==2)
				bw1.append(playing1.player2.get(j).name+","+(depth-1)+","+eval).append(System.lineSeparator());
				}
				else{
				if(real_depth!=0){      	//fix
					if(turn1%2==1){
						if(dummy<eval1){   //B-B MAX/MIN depends
							eval=eval1;
							dummy=eval;
						}
						if(depth==1&&playing1.free_turn==0){
							if(eval>saveme){
								myanswer=playing1;
								saveme=eval;
							}
						}
					}
					else{
						if(dummy>eval1){
							eval=eval1;
							dummy=eval;
						}
					}
					eval=dummy;
				if(task==2){
					if(previous.free_turn==1)
					bw1.append(previous.name+","+(depth)+","+eval).append(System.lineSeparator());
					else
					bw1.append(previous.name+","+(depth-1)+","+eval).append(System.lineSeparator());	
				}
				}
				else{								//A-ROOT MAX
					if(eval<eval1)  	 //Max
					{                        
						eval=eval1;
						y=playing1;
						if(depth==1&&playing1.free_turn==0){
							if(eval>saveme){
								myanswer=playing1;
								saveme=eval;
							}
						}
					}
					if(i==playing.player1.size()-1){       //depth 0 print chance
						display(myanswer,bw);
					}
					if(task==2)
					bw1.append("root"+","+0+","+eval).append(System.lineSeparator());
					}
				}
				}
	
}
	//turn1--;
	if(playing.player1.size()==i)
		infichk=1;
	return eval;
}

int minmaxA(BufferedWriter bw, BufferedWriter bw1,int task,State playing,int turn,int depth,int j,State previous,int real_depth,int eval,State y,int depth_cut,int dummy,int prev) throws CloneNotSupportedException, IOException{ //player 2 chance
	if(depth==depth_cut+1||check_arr1(playing,turn)==1||check_arr2(playing,turn)==1){
		if(check_arr1(playing, turn)==1||check_arr2(playing, turn)==1){
		for(int k=0;k<playing.player1.size();k++){
			if(check_arr1(playing,turn)==1){
			playing.Mancala2+=playing.player2.get(k).playa;
			playing.player2.get(k).playa=0;
			}
	   else if(check_arr2(playing,turn)==1){
		    playing.Mancala1+=playing.player1.get(k).playa;
		    playing.player1.get(k).playa=0;
	   }
	   }
			if(turn1%2==1)
			playing.eval=playing.Mancala1-playing.Mancala2; //for updating the eval 
			else
			playing.eval=playing.Mancala2-playing.Mancala1; //for updating the eval 	
	}
		infichk=1;
		return playing.eval;
		}
		previous=new State(playing);
		State playing2=new State(playing);
		playing2.free_turn=0;
		int i;
	for(i=0;i<playing.player1.size();i++){			                               //creating a duplicate state to be edited
		prev=eval;
		State playing1=new State(playing2);
		playing1=playing1.update_State(playing1, i, turn);
		
		playing1.name=playing1.player2.get(i).name;
		//display(playing1);
		playing1.turn=2;
		if(playing1.curr_chance==1){
			if(real_depth==0&&i==playing.player1.size()-1){
				display(myanswer,bw);
			}
			playing1.curr_chance=0;         	//if no moves possible for current state
			continue;
		}	
		
		if(playing1.free_turn==1){
		//	playing1.free_turn=0;			
			playing1.name=playing1.player2.get(i).name;
			if(turn1%2==0)
			eval=Integer.MIN_VALUE;
			else
			eval=Integer.MAX_VALUE;	
			if(task==2){
				if((check_arr1(playing1, turn)==1||check_arr2(playing1, turn)==1)&&depth!=depth_cut){
					bw1.append(playing1.player2.get(i).name+","+depth+",");
					if(eval==Integer.MIN_VALUE)
					bw1.append("-Infinity").append(System.lineSeparator());
					else 
					bw1.append("Infinity").append(System.lineSeparator());;
				}
				}
			if(task==2){
				bw1.append(playing1.player2.get(i).name+","+depth+",");
				if(eval==Integer.MIN_VALUE&&check_arr1(playing1, turn)==0&&check_arr2(playing1, turn)==0)
					bw1.append("-Infinity").append(System.lineSeparator());
	       else	    if(eval==Integer.MAX_VALUE&&check_arr1(playing1, turn)==0&&check_arr2(playing1, turn)==0)
	    	   		bw1.append("Infinity").append(System.lineSeparator());	
	       else		bw1.append(String.valueOf(playing1.eval)).append(System.lineSeparator());
			}
			dummy=eval;
			State playing3=new State(playing1);
			int eval1=minmaxA(bw,bw1,task,playing3,turn,depth,j,previous,real_depth+1,eval,y,depth_cut,dummy,prev);
			if(infichk==1){
				eval=prev;
				infichk=0;
			}
			eval=prev;
			dummy=prev;
			if(real_depth>0&&playing3.free_turn==1&&previous.free_turn==0||real_depth>0&&playing3.free_turn==1&&previous.free_turn==1){
				eval=prev;
				if(turn1%2==0){ 
				if(eval1>eval)   //prev a or prev b
				{
					eval=eval1;
					dummy=eval1;
				}
			}
				else{
					
					if(eval>eval1){
						eval=eval1;
						dummy=eval1;
					}
				}
				//eval=dummy;
				///madahaha ajjwsalkdjlsakjalkdjadlkjalkdjaslkdjaslkjdlaks
				if(previous.free_turn==0)
				bw1.append(previous.name+","+(depth-1)+","+eval).append(System.lineSeparator());
				else
				bw1.append(previous.name+","+depth+","+eval).append(System.lineSeparator());	
			}
			if(real_depth==0){                   //depth 0
				if(eval1>eval)       
					{
					eval=eval1;
					y=playing3;
					if(depth==1&&playing3.free_turn==0){
						if(eval>saveme){
							myanswer=playing1;
							saveme=eval;
						}
					}
					if(i==playing.player1.size()-1){
						display(myanswer,bw);                      
					}
					}
				if(task==2)
					bw1.append("root"+","+0+","+eval).append(System.lineSeparator());	
			}

			//bw1.appendln(playing1.player1.get(i).name+" "+depth);
		}
		else{
			if(depth%2==1 && depth!=depth_cut){
				eval=Integer.MAX_VALUE;		
			}
			else{
				if(depth!=depth_cut)
				eval=Integer.MIN_VALUE;				//dummy=eval;
			}
		//	if(check_arr2(playing1, turn)==1)
			//	eval=-eval;
			if(task==2){
				if((check_arr1(playing1, turn)==1||check_arr2(playing1, turn)==1)&&depth!=depth_cut){
					bw1.append(playing1.player2.get(i).name+","+depth+",");
					if(eval==Integer.MIN_VALUE)
					bw1.append("-Infinity").append(System.lineSeparator());
					else 
					bw1.append("Infinity").append(System.lineSeparator());;
				}
				}
					if(task==2){
					bw1.append(playing1.player2.get(i).name+","+depth+",");
					if(eval==Integer.MIN_VALUE&&check_arr1(playing1, turn)==0&&check_arr2(playing1, turn)==0&&depth!=depth_cut)
						bw1.append("-Infinity").append(System.lineSeparator());
		       else	    if(eval==Integer.MAX_VALUE&&check_arr1(playing1, turn)==0&&check_arr2(playing1, turn)==0&&depth!=depth_cut)
						bw1.append("Infinity").append(System.lineSeparator());	
		       else		bw1.append(String.valueOf(playing1.eval)).append(System.lineSeparator());
			}
				int eval1=minmaxB(bw,bw1,task,playing1,turn+1,depth+1,i,previous,real_depth+1,eval,y,depth_cut,dummy,prev);	//recursiveeee @depth 1 //printing the player B's chance as the tree explores downwards		
				eval=prev;
				if(infichk==1){
					eval=prev;
					infichk=0;
				}
				eval=prev;
				dummy=prev;
				if(real_depth!=0&&previous.turn==1)  //player A is at 5th 3rd or 1st level and turn
				{							//umm player 1 chance 1 3 5 7...
					x=depth-1;
					if(turn1%2==0){ //A's chance is 1st...max a
						if(eval<eval1){     					//A-B MAX
							if(depth==1&&playing1.free_turn==0){
								if(eval>saveme){
									myanswer=playing1;
									saveme=eval;
								}
							}
							eval=eval1;
						}
					}
					else{
							if(eval>eval1)   //A-B MIN
							  eval=eval1;	
					}
					if(task==2)
					bw1.append(playing1.player1.get(j).name+","+(depth-1)+","+eval).append(System.lineSeparator());
					}
				else {
					if(real_depth!=0){
							//fix FREE CHANCE?
						if(turn1%2==0){
							if(dummy<eval1){   //A-A MAX/MIN depends
								eval=eval1;
								dummy=eval;
							}
							if(depth==1&&playing1.free_turn==0){
								if(eval>saveme){
									myanswer=playing1;
									saveme=eval;
								}
							}
						}
						else{
							if(dummy>eval1){
								eval=eval1;
								dummy=eval;
							}
						}
							eval=dummy;
							if(task==2){								
								if(previous.free_turn==1)
								bw1.append(previous.name+","+depth+","+eval).append(System.lineSeparator());
								else
								bw1.append(previous.name+","+depth+","+eval).append(System.lineSeparator());	
							}
							}
					else{
						if(eval<eval1)              //depth 0 print chance
						{
							y=playing1;
							eval=eval1;	
							if(depth==1&&playing1.free_turn==0){
								if(eval>saveme){
									myanswer=playing1;
									saveme=eval;
								}
							}
						}
						if(task==2)
						bw1.append("root"+","+0+","+eval).append(System.lineSeparator());
						if(i==playing.player1.size()-1){
							display(myanswer,bw);
						}
											//B-ROOT MAX
					}
				}
		}
				
	}
	if(playing.player1.size()==i)
		infichk=1;
	return eval;
}
	/*
*/

//eval


int Max(int x,int y){
	if(x>y)
		return x;
	else
		return y;
}
int Min(int x,int y){
	if(x<y)
		return x;
	else 
		return y;
}
int BETA(BufferedWriter bw,BufferedWriter bw1,int task,State playing,int turn,int depth,int j,State previous,int real_depth,int eval,State y,int depth_cut,int dummy,int prev,int alpha,int beta) throws CloneNotSupportedException, IOException{ //player 1 chance
	//display(previous);
	if(depth==depth_cut+1||check_arr1(playing,turn)==1||check_arr2(playing,turn)==1){
		//count
		if(check_arr1(playing, turn)==1||check_arr2(playing, turn)==1){
		for(int k=0;k<playing.player1.size();k++){
			if(check_arr1(playing,turn)==1){
			playing.Mancala2+=playing.player2.get(k).playa;
			playing.player2.get(k).playa=0;
			}
			else if(check_arr2(playing,turn)==1){
		    playing.Mancala1+=playing.player1.get(k).playa;
		    playing.player1.get(k).playa=0;
			}
			}
			if(turn1%2==1)
			playing.eval=playing.Mancala1-playing.Mancala2; //for updating the eval 
			else
			playing.eval=playing.Mancala2-playing.Mancala1; //for updating the eval 	
	}
		infichk=1;
		return playing.eval;
		}

	int i;
	previous=new State(playing);
	State playing2=new State(playing);
	playing2.free_turn=0;
	for(i=0;i<playing.player1.size();i++){	
		//count
		prev=eval; //6
		State playing1=new State(playing2);		
		playing1=playing1.update_State(playing1, i, turn);
		//display(playing1);
		playing1.name=playing1.player1.get(i).name;
				playing1.turn=1;//turn for b
				if(playing1.curr_chance==1){
					if(real_depth==0&&i==playing.player1.size()-1){
						display(myanswer,bw);
					}						
					playing1.curr_chance=0;
					continue;
				}
				if(playing1.free_turn==1){
					   //printing the player B's chance as the tree explores downwards	
						//playing1.free_turn=0;					
					//if(!(eval<Integer.MAX_VALUE&&eval>Integer.MIN_VALUE))
					if(turn1%2==1)
						eval=Integer.MIN_VALUE;
						else
						eval=Integer.MAX_VALUE;
				if((check_arr1(playing1, turn)==1||check_arr2(playing1, turn)==1)&&depth!=depth_cut){
					bw1.append(playing1.player1.get(i).name+","+depth+",");
					if(eval==Integer.MIN_VALUE)
					bw1.append("-Infinity,");
					else 
					bw1.append("Infinity,");
					if(alpha==Integer.MIN_VALUE)
					bw1.append("-Infinity,");
		   else	    if(alpha==Integer.MAX_VALUE)
			   		bw1.append("Infinity,");	
	       else		bw1.append(alpha+",");
					if(beta==Integer.MIN_VALUE)
					bw1.append("-Infinity").append(System.lineSeparator());
		   else	    if(beta==Integer.MAX_VALUE)
			   		bw1.append("Infinity").append(System.lineSeparator());	
		   else		bw1.append(String.valueOf(beta)).append(System.lineSeparator());
				}		
						bw1.append(playing.player1.get(i).name+","+depth+",");
						if(eval==Integer.MIN_VALUE&&check_arr1(playing1, turn)==0&&check_arr2(playing1, turn)==0)
						bw1.append("-Infinity,");
		       else	    if(eval==Integer.MAX_VALUE&&check_arr1(playing1, turn)==0&&check_arr2(playing1, turn)==0)
		    	        bw1.append("Infinity,");	
		       else		bw1.append(playing1.eval+",");
						if(alpha==Integer.MIN_VALUE)
						bw1.append("-Infinity,");
			   else	    if(alpha==Integer.MAX_VALUE)
				   		bw1.append("Infinity,");	
		       else		bw1.append(alpha+",");
						if(beta==Integer.MIN_VALUE)
						bw1.append("-Infinity").append(System.lineSeparator());
			   else	    if(beta==Integer.MAX_VALUE)
				   		bw1.append("Infinity").append(System.lineSeparator());	
			   else		bw1.append(String.valueOf(beta)).append(System.lineSeparator());
						dummy=eval;
					playing1.name=playing1.player1.get(i).name;
					State playing3=new State(playing1);
					int eval1=BETA(bw,bw1,task,playing3,turn,depth,j,previous,real_depth+1,eval,y,depth_cut,dummy,prev,alpha,beta);					
					if(infichk==1){
						eval=prev;
						infichk=0;
					}
					eval=prev;
					dummy=prev;
					if(depth%2==1&&eval1>=beta){
						infichk=1;
						if(previous.free_turn==0)
						bw1.append(previous.name+","+(depth-1)+",");
	    	   else
						bw1.append(previous.name+","+(depth)+",");
						bw1.append(eval1+",");
						if(alpha==Integer.MIN_VALUE)
						bw1.append("-Infinity,");
			   else	    if(alpha==Integer.MAX_VALUE)
				   		bw1.append("Infinity,");	
		       else		bw1.append(alpha+",");
						if(beta==Integer.MIN_VALUE)
						bw1.append("-Infinity").append(System.lineSeparator());
			   else	    if(beta==Integer.MAX_VALUE)
				   		bw1.append("Infinity").append(System.lineSeparator());	
			   else		bw1.append(String.valueOf(beta)).append(System.lineSeparator());
						return eval1;
					}	
					if(depth%2==0&&eval1<=alpha){
						infichk=1;
						if(previous.free_turn==0)
							bw1.append(previous.name+","+(depth-1)+",");
							else
							bw1.append(previous.name+","+(depth)+",");
						bw1.append(eval1+",");
						if(alpha==Integer.MIN_VALUE)
						bw1.append("-Infinity,");
			   else	    if(alpha==Integer.MAX_VALUE)
						bw1.append("Infinity,");	
		       else		bw1.append(alpha+",");
						if(beta==Integer.MIN_VALUE)
						bw1.append("-Infinity").append(System.lineSeparator());
			   else	    if(beta==Integer.MAX_VALUE)
						bw1.append("Infinity").append(System.lineSeparator());	
			   else		bw1.append(String.valueOf(beta)).append(System.lineSeparator());
						return eval1;
					}
					if(real_depth>0&&playing3.free_turn==1&&previous.free_turn==0||real_depth>0&&playing3.free_turn==1&&previous.free_turn==1){
						if(turn1%2==1){
							if(eval1>eval)
						{
							eval=eval1;
							dummy=eval1;
							alpha=Max(alpha,eval1); 					//max
						}
					}
						else{
							if(eval>eval1)
								eval=eval1;
								dummy=eval1;
								beta=Min(beta,eval1);	
						}
				if(previous.free_turn==0)
					bw1.append(previous.name+","+(depth-1)+",");	
				else
					bw1.append(previous.name+","+depth+",");
				if(eval==Integer.MIN_VALUE)
						bw1.append("-Infinity,");
		       else	    if(eval==Integer.MAX_VALUE)
						bw1.append("Infinity,");	
		       else		bw1.append(eval+",");
						if(alpha==Integer.MIN_VALUE)
						bw1.append("-Infinity,");
			   else	    if(alpha==Integer.MAX_VALUE)
						bw1.append("Infinity,");	
		       else		bw1.append(alpha+",");
						if(beta==Integer.MIN_VALUE)
						bw1.append("-Infinity").append(System.lineSeparator());
			   else	    if(beta==Integer.MAX_VALUE)
						bw1.append("Infinity").append(System.lineSeparator());	
			   else		bw1.append(String.valueOf(beta)).append(System.lineSeparator());

					}
					if(real_depth==0){                      //depth 0 print chance
						if(eval1>eval){
							eval=eval1;
							alpha=Max(alpha,eval1);
							y=playing3;
							if(depth==1&&playing3.free_turn==0){
								if(eval>saveme){
									myanswer=playing3;
									saveme=eval;
								}
							}
							if(myanswer==null)
								myanswer=playing3;
							if(i==playing.player1.size()-1){
								display(myanswer,bw);
							}
						}
							bw1.append("root"+","+0+",");	
							if(eval==Integer.MIN_VALUE)
								bw1.append("-Infinity,");
				       else	    if(eval==Integer.MAX_VALUE)
								bw1.append("Infinity,");	
				       else		bw1.append(eval+",");
								if(alpha==Integer.MIN_VALUE)
								bw1.append("-Infinity,");
					   else	    if(alpha==Integer.MAX_VALUE)
								bw1.append("Infinity,");	
				       else		bw1.append(alpha+",");
								if(beta==Integer.MIN_VALUE)
								bw1.append("-Infinity").append(System.lineSeparator());
					   else	    if(beta==Integer.MAX_VALUE)
								bw1.append("Infinity").append(System.lineSeparator());	
					   else		bw1.append(String.valueOf(beta)).append(System.lineSeparator());

					}
						//bw1.appendln(playing1.player1.get(i).name+" "+depth);
				}
				else{     
						if(depth%2==1&&(depth!=depth_cut)){
						
							eval=Integer.MAX_VALUE; //TO MINIMIZE
							//dummy=eval;
						
						}
					else{
						if((depth!=depth_cut))
						eval=Integer.MIN_VALUE;    
					//	dummy=eval;
					}
				//		if(check_arr1(playing1, turn)==1)
					//		eval=-eval;
						if((check_arr1(playing1, turn)==1||check_arr2(playing1, turn)==1)&&depth!=depth_cut){
							bw1.append(playing1.player1.get(i).name+","+depth+",");
							if(eval==Integer.MIN_VALUE)
							bw1.append("-Infinity,");
						else 
							bw1.append("Infinity,");
							if(alpha==Integer.MIN_VALUE)
							bw1.append("-Infinity,");
				   else	    if(alpha==Integer.MAX_VALUE)
					   		bw1.append("Infinity,");	
			       else		bw1.append(alpha+",");
							if(beta==Integer.MIN_VALUE)
							bw1.append("-Infinity").append(System.lineSeparator());
				   else	    if(beta==Integer.MAX_VALUE)
					   		bw1.append("Infinity").append(System.lineSeparator());	
				   else		bw1.append(String.valueOf(beta)).append(System.lineSeparator());
						}
							bw1.append(playing.player1.get(i).name+","+depth+",");   //printing the player B's chance as the tree explores downwards	
							if(eval==Integer.MIN_VALUE&&check_arr1(playing1, turn)==0&&check_arr2(playing1, turn)==0&&depth!=depth_cut)
								bw1.append("-Infinity,");
				       else	    if(eval==Integer.MAX_VALUE&&check_arr1(playing1, turn)==0&&check_arr2(playing1, turn)==0&&depth!=depth_cut)
								bw1.append("Infinity,");	
				       else		bw1.append(playing1.eval+",");
								if(alpha==Integer.MIN_VALUE)
								bw1.append("-Infinity,");
					   else	    if(alpha==Integer.MAX_VALUE)
								bw1.append("Infinity,");	
				       else		bw1.append(alpha+",");
								if(beta==Integer.MIN_VALUE)
								bw1.append("-Infinity").append(System.lineSeparator());
					   else	    if(beta==Integer.MAX_VALUE)
								bw1.append("Infinity").append(System.lineSeparator());	
					   else		bw1.append(String.valueOf(beta)).append(System.lineSeparator());
						
						int eval1=ALPHA(bw,bw1,task,playing1,turn+1,depth+1,i,previous,real_depth+1,eval,y,depth_cut,dummy,prev,alpha,beta);	//recursiveeee @depth 1		
				//if(State.depth1+State.turn1%2==0)//if(size==1)//eval=eval1;																
						if(infichk==1){
							eval=prev;
							infichk=0;
						}
						eval=prev;
						dummy=prev;
						if(depth%2==1&&eval1>=beta){
							infichk=1;
							if(previous.free_turn==0)
							bw1.append(previous.name+","+(depth-1)+",");
							else
							bw1.append(previous.name+","+(depth)+",");	
							bw1.append(eval1+",");
							if(alpha==Integer.MIN_VALUE)
							bw1.append("-Infinity,");
				   else	    if(alpha==Integer.MAX_VALUE)
							bw1.append("Infinity,");	
			       else		bw1.append(alpha+",");
							if(beta==Integer.MIN_VALUE)
							bw1.append("-Infinity").append(System.lineSeparator());
				   else	    if(beta==Integer.MAX_VALUE)
							bw1.append("Infinity").append(System.lineSeparator());	
				   else		bw1.append(String.valueOf(beta)).append(System.lineSeparator());
							return eval1;
						}	
						if(depth%2==0&&eval1<=alpha){
							infichk=1;
							if(previous.free_turn==0)
							bw1.append(previous.name+","+(depth-1)+",");
							else
							bw1.append(previous.name+","+(depth)+",");
							bw1.append(eval1+",");
							if(alpha==Integer.MIN_VALUE)
							bw1.append("-Infinity,");
				   else	    if(alpha==Integer.MAX_VALUE)
							bw1.append("Infinity,");	
			       else		bw1.append(alpha+",");
							if(beta==Integer.MIN_VALUE)
							bw1.append("-Infinity").append(System.lineSeparator());
				   else	    if(beta==Integer.MAX_VALUE)
							bw1.append("Infinity").append(System.lineSeparator());	
				   else		bw1.append(String.valueOf(beta)).append(System.lineSeparator());
							return eval1;
				}
						if(previous.turn==2&&real_depth!=0){  //see if its player 2's chance woww..hope it works
										//player 1 chance 2..4..6..8..10 
				 //printing the player A's node upon which the child nodes were invoked
				if(turn1%2==1){ //B's chance is 1st...max a
					if(eval<eval1){     //B-A MAX
						eval=eval1;       
						alpha=Max(alpha,eval1);
					}
						if(depth==1&&playing1.free_turn==0){
						if(eval>saveme){
							myanswer=playing1;
							saveme=eval;
						}
					}
				}
				
				else{
						if(eval>eval1){   //B-A MIN
						eval=eval1;
						beta=Min(beta,eval1);
					}
						}
				bw1.append(playing1.player2.get(j).name+","+(depth-1)+",");
				if(eval==Integer.MIN_VALUE)
					bw1.append("-Infinity,");
	       else	    if(eval==Integer.MAX_VALUE)
					bw1.append("Infinity,");	
	       else		bw1.append(eval+",");
					if(alpha==Integer.MIN_VALUE)
					bw1.append("-Infinity,");
		   else	    if(alpha==Integer.MAX_VALUE)
					bw1.append("Infinity,");	
	       else		bw1.append(alpha+",");
					if(beta==Integer.MIN_VALUE)
					bw1.append("-Infinity").append(System.lineSeparator());
		   else	    if(beta==Integer.MAX_VALUE)
					bw1.append("Infinity").append(System.lineSeparator());	
		   else		bw1.append(String.valueOf(beta)).append(System.lineSeparator());

						}
				else{
				if(real_depth!=0){      	//fix
					if(turn1%2==1){
						if(dummy<eval1){   //B-B MAX/MIN depends
							alpha=Max(alpha,eval1);
							eval=eval1;
							dummy=eval;
						}
						if(depth==1&&playing1.free_turn==0){
							if(eval>saveme){
								myanswer=playing1;
								saveme=eval;
							}
						}
					}
					else{
						if(dummy>eval1){
							beta=Min(beta,eval1);
							eval=eval1;
							dummy=eval;
						}
					}
					eval=dummy;
				bw1.append(previous.name+","+(depth)+",");
				if(eval==Integer.MIN_VALUE)
					bw1.append("-Infinity,");
	       else	    if(eval==Integer.MAX_VALUE)
					bw1.append("Infinity,");	
	       else		bw1.append(eval+",");
					if(alpha==Integer.MIN_VALUE)
					bw1.append("-Infinity,");
		   else	    if(alpha==Integer.MAX_VALUE)
					bw1.append("Infinity,");	
	       else		bw1.append(alpha+",");
					if(beta==Integer.MIN_VALUE)
					bw1.append("-Infinity").append(System.lineSeparator());
		   else	    if(beta==Integer.MAX_VALUE)
					bw1.append("Infinity").append(System.lineSeparator());	
		   else		bw1.append(String.valueOf(beta)).append(System.lineSeparator());

				}
				else{								//A-ROOT MAX
					if(eval<eval1)  	 //Max
					{                        
						alpha=Max(alpha,eval1);
						eval=eval1;
						y=playing1;
						if(depth==1&&playing1.free_turn==0){
							if(eval>saveme){
								myanswer=playing1;
								saveme=eval;
							}
						}
					}
					if(i==playing.player1.size()-1){       //depth 0 print chance
						display(myanswer,bw);
					}
					bw1.append("root"+","+0+",");
					if(eval==Integer.MIN_VALUE)
						bw1.append("-Infinity,");
		       else	    if(eval==Integer.MAX_VALUE)
						bw1.append("Infinity,");	
		       else		bw1.append(eval+",");
						if(alpha==Integer.MIN_VALUE)
						bw1.append("-Infinity,");
			   else	    if(alpha==Integer.MAX_VALUE)
						bw1.append("Infinity,");	
		       else		bw1.append(alpha+",");
						if(beta==Integer.MIN_VALUE)
						bw1.append("-Infinity").append(System.lineSeparator());
			   else	    if(beta==Integer.MAX_VALUE)
						bw1.append("Infinity").append(System.lineSeparator());	
			   else		bw1.append(String.valueOf(beta)).append(System.lineSeparator());
					}
				}
				}
	
}
	//turn1--;
	if(playing.player1.size()==i)
		infichk=1;
	return eval;
}

int ALPHA(BufferedWriter bw,BufferedWriter bw1,int task,State playing,int turn,int depth,int j,State previous,int real_depth,int eval,State y,int depth_cut,int dummy,int prev,int alpha,int beta) throws CloneNotSupportedException, IOException{ //player 2 chance
	if(depth==depth_cut+1||check_arr1(playing,turn)==1||check_arr2(playing,turn)==1){
		if(check_arr1(playing, turn)==1||check_arr2(playing, turn)==1){
		for(int k=0;k<playing.player1.size();k++){
			if(check_arr1(playing,turn)==1){
			playing.Mancala2+=playing.player2.get(k).playa;
			playing.player2.get(k).playa=0;
			}
	   else if(check_arr2(playing,turn)==1){
		    playing.Mancala1+=playing.player1.get(k).playa;
		    playing.player1.get(k).playa=0;
	   }
	   }
			if(turn1%2==1)
			playing.eval=playing.Mancala1-playing.Mancala2; //for updating the eval 
			else
			playing.eval=playing.Mancala2-playing.Mancala1; //for updating the eval 	
	}
		infichk=1;
		return playing.eval;
		}
		previous=new State(playing);
		State playing2=new State(playing);
		playing2.free_turn=0;
		int i;
	for(i=0;i<playing.player1.size();i++){			                               //creating a duplicate state to be edited
		prev=eval;
		//count
		State playing1=new State(playing2);
		playing1=playing1.update_State(playing1, i, turn);
		playing1.name=playing1.player2.get(i).name;
		//display(playing1);
		playing1.turn=2;
		if(playing1.curr_chance==1){
			if(real_depth==0&&i==playing.player1.size()-1){
				display(myanswer,bw);
			}
			playing1.curr_chance=0;         	//if no moves possible for current state
			continue;
		}	
		
		if(playing1.free_turn==1){
		//	playing1.free_turn=0;			
			playing1.name=playing1.player2.get(i).name;
			if(turn1%2==0)
			eval=Integer.MIN_VALUE;
			else
			eval=Integer.MAX_VALUE;	
			if((check_arr1(playing1, turn)==1||check_arr2(playing1, turn)==1)&&depth!=depth_cut){
				bw1.append(playing1.player2.get(i).name+","+depth+",");
				if(eval==Integer.MIN_VALUE)
				bw1.append("-Infinity,");
			else 
				bw1.append("Infinity,");
				if(alpha==Integer.MIN_VALUE)
				bw1.append("-Infinity,");
	   else	    if(alpha==Integer.MAX_VALUE)
		   		bw1.append("Infinity,");	
       else		bw1.append(alpha+",");
				if(beta==Integer.MIN_VALUE)
				bw1.append("-Infinity").append(System.lineSeparator());
	   else	    if(beta==Integer.MAX_VALUE)
		   		bw1.append("Infinity").append(System.lineSeparator());	
	   else		bw1.append(String.valueOf(beta)).append(System.lineSeparator());
			}	
			bw1.append(playing1.player2.get(i).name+","+depth+",");
				if(eval==Integer.MIN_VALUE&&check_arr1(playing1, turn)==0&&check_arr2(playing1, turn)==0)
					bw1.append("-Infinity,");
	       else	    if(eval==Integer.MAX_VALUE&&check_arr1(playing1, turn)==0&&check_arr2(playing1, turn)==0)
					bw1.append("Infinity,");	
	       else		bw1.append(playing1.eval+",");
					if(alpha==Integer.MIN_VALUE)
					bw1.append("-Infinity,");
		   else	    if(alpha==Integer.MAX_VALUE)
					bw1.append("Infinity,");	
	       else		bw1.append(alpha+",");
					if(beta==Integer.MIN_VALUE)
					bw1.append("-Infinity").append(System.lineSeparator());
		   else	    if(beta==Integer.MAX_VALUE)
					bw1.append("Infinity").append(System.lineSeparator());	
		   else		bw1.append(String.valueOf(beta)).append(System.lineSeparator());

			dummy=eval;
			State playing3=new State(playing1);
			int eval1=ALPHA(bw,bw1,task,playing3,turn,depth,j,previous,real_depth+1,eval,y,depth_cut,dummy,prev,alpha,beta);
			if(infichk==1){
				eval=prev;
				infichk=0;
			}
			eval=prev;
			dummy=prev;
			if(depth%2==1&&eval1>=beta){
				infichk=1;
				if(previous.free_turn==0)
				bw1.append(previous.name+","+(depth-1)+",");
				else
				bw1.append(previous.name+","+(depth)+",");	
				bw1.append(eval1+",");
				if(alpha==Integer.MIN_VALUE)
				bw1.append("-Infinity,");
	   else	    if(alpha==Integer.MAX_VALUE)
				bw1.append("Infinity,");	
       else		bw1.append(alpha+",");
				if(beta==Integer.MIN_VALUE)
				bw1.append("-Infinity").append(System.lineSeparator());
	   else	    if(beta==Integer.MAX_VALUE)
				bw1.append("Infinity").append(System.lineSeparator());	
	   else		bw1.append(String.valueOf(beta)).append(System.lineSeparator());
				return eval1;
			}
				if(depth%2==0&&eval1<=alpha){
					infichk=1;
					if(previous.free_turn==0)
						bw1.append(previous.name+","+(depth-1)+",");
						else
						bw1.append(previous.name+","+(depth)+",");
					bw1.append(eval1+",");
					if(alpha==Integer.MIN_VALUE)
					bw1.append("-Infinity,");
		   else	    if(alpha==Integer.MAX_VALUE)
					bw1.append("Infinity,");	
	       else		bw1.append(alpha+",");
					if(beta==Integer.MIN_VALUE)
					bw1.append("-Infinity").append(System.lineSeparator());
		   else	    if(beta==Integer.MAX_VALUE)
					bw1.append("Infinity").append(System.lineSeparator());	
		   else		bw1.append(String.valueOf(beta)).append(System.lineSeparator());
					return eval1;
				}
			if(real_depth>0&&playing3.free_turn==1&&previous.free_turn==0||real_depth>0&&playing3.free_turn==1&&previous.free_turn==1){
				if(turn1%2==0){
				if(eval1>eval)
				{
					alpha=Max(alpha,eval1);
					eval=eval1;
					dummy=eval1;
				}
			}
				else{
					if(eval>eval1){
						beta=Min(beta,eval1);
						eval=eval1;
						dummy=eval1;
					}
				}
			if(previous.free_turn==0)
			bw1.append(previous.name+","+(depth-1)+",");
	else	bw1.append(previous.name+","+(depth)+",");
			if(eval==Integer.MIN_VALUE)
				bw1.append("-Infinity,");
       else	    if(eval==Integer.MAX_VALUE)
				bw1.append("Infinity,");	
       else		bw1.append(eval+",");
				if(alpha==Integer.MIN_VALUE)
				bw1.append("-Infinity,");
	   else	    if(alpha==Integer.MAX_VALUE)
				bw1.append("Infinity,");	
       else		bw1.append(alpha+",");
				if(beta==Integer.MIN_VALUE)
				bw1.append("-Infinity").append(System.lineSeparator());
	   else	    if(beta==Integer.MAX_VALUE)
				bw1.append("Infinity").append(System.lineSeparator());	
	   else		bw1.append(String.valueOf(beta)).append(System.lineSeparator());

			}
			if(real_depth==0){                   //depth 0
				if(eval1>eval)       
					{
					alpha=Max(alpha,eval1);
					eval=eval1;
					y=playing3;
					if(depth==1&&playing3.free_turn==0){
						if(eval>saveme){
							myanswer=playing1;
							saveme=eval;
						}
					}
					if(i==playing.player1.size()-1){
						display(myanswer,bw);                      
					}
					}
				bw1.append("root"+","+0+",");	
					if(eval==Integer.MIN_VALUE)
						bw1.append("-Infinity,");
		       else	    if(eval==Integer.MAX_VALUE)
		    	   bw1.append("Infinity,");	
		       else		bw1.append(eval+",");
						if(alpha==Integer.MIN_VALUE)
							bw1.append("-Infinity,");
			   else	    if(alpha==Integer.MAX_VALUE)
				   bw1.append("Infinity,");	
		       else		bw1.append(alpha+",");
						if(beta==Integer.MIN_VALUE)
							bw1.append("-Infinity").append(System.lineSeparator());
			   else	    if(beta==Integer.MAX_VALUE)
				   bw1.append("Infinity").append(System.lineSeparator());	
			   else		bw1.append(String.valueOf(beta)).append(System.lineSeparator());

			}

			//bw1.appendln(playing1.player1.get(i).name+" "+depth);
		}
		else{
			if(depth%2==1 && depth!=depth_cut){
				eval=Integer.MAX_VALUE;		
			}
			else{
				if(depth!=depth_cut)
				eval=Integer.MIN_VALUE;				//dummy=eval;
			}
		//	if(check_arr2(playing1, turn)==1)
			//	eval=-eval;
			
			if((check_arr1(playing1, turn)==1||check_arr2(playing1, turn)==1)&&depth!=depth_cut){
				bw1.append(playing1.player2.get(i).name+","+depth+",");
				if(eval==Integer.MIN_VALUE)
				bw1.append("-Infinity,");
		else 
				bw1.append("Infinity,");
				if(alpha==Integer.MIN_VALUE)
				bw1.append("-Infinity,");
	   else	    if(alpha==Integer.MAX_VALUE)
		   		bw1.append("Infinity,");	
       else		bw1.append(alpha+",");
				if(beta==Integer.MIN_VALUE)
				bw1.append("-Infinity").append(System.lineSeparator());
	   else	    if(beta==Integer.MAX_VALUE)
		   		bw1.append("Infinity").append(System.lineSeparator());	
	   else		bw1.append(String.valueOf(beta)).append(System.lineSeparator());
			}
					bw1.append(playing1.player2.get(i).name+","+depth+",");
					if(eval==Integer.MIN_VALUE&&check_arr1(playing1, turn)==0&&check_arr2(playing1, turn)==0&&depth!=depth_cut)
						bw1.append("-Infinity,");
			   else	    if(eval==Integer.MAX_VALUE&&check_arr1(playing1, turn)==0&&check_arr2(playing1, turn)==0&&depth!=depth_cut)
				   		bw1.append("Infinity,");	
		       else		bw1.append(playing1.eval+",");
						if(alpha==Integer.MIN_VALUE)
						bw1.append("-Infinity,");
			   else	    if(alpha==Integer.MAX_VALUE)
				   		bw1.append("Infinity,");	
		       else		bw1.append(alpha+",");
						if(beta==Integer.MIN_VALUE)
						bw1.append("-Infinity").append(System.lineSeparator());
			   else	    if(beta==Integer.MAX_VALUE)
				   		bw1.append("Infinity").append(System.lineSeparator());	
			   else		bw1.append(String.valueOf(beta)).append(System.lineSeparator());

			
				int eval1=BETA(bw,bw1,task,playing1,turn+1,depth+1,i,previous,real_depth+1,eval,y,depth_cut,dummy,prev,alpha,beta);	//recursiveeee @depth 1 //printing the player B's chance as the tree explores downwards		
				if(infichk==1&&depth!=depth_cut){
					eval=prev;
					infichk=0;
				}
				eval=prev;
				dummy=prev;
				if(depth%2==1&&eval1>=beta){
				infichk=1;
				if(previous.free_turn==0)
				bw1.append(previous.name+","+(depth-1)+",");
				else
				bw1.append(previous.name+","+(depth)+",");	
				bw1.append(eval1+",");
				if(alpha==Integer.MIN_VALUE)
				bw1.append("-Infinity,");
	   else	    if(alpha==Integer.MAX_VALUE)
		   		bw1.append("Infinity,");	
       else		bw1.append(alpha+",");
				if(beta==Integer.MIN_VALUE)
				bw1.append("-Infinity").append(System.lineSeparator());
	   else	    if(beta==Integer.MAX_VALUE)
		   		bw1.append("Infinity").append(System.lineSeparator());	
	   else		bw1.append(String.valueOf(beta)).append(System.lineSeparator());	
				return eval1;
				}			
				if(depth%2==0&&eval1<=alpha){
				infichk=1;
				if(previous.free_turn==0)
				bw1.append(previous.name+","+(depth-1)+",");
				else
				bw1.append(previous.name+","+(depth)+",");
				bw1.append(eval1+",");
					if(alpha==Integer.MIN_VALUE)
				bw1.append("-Infinity,");
		   else	    if(alpha==Integer.MAX_VALUE)
			   	bw1.append("Infinity,");	
	       else	bw1.append(alpha+",");
					if(beta==Integer.MIN_VALUE)
				bw1.append("-Infinity").append(System.lineSeparator());
		   else	    if(beta==Integer.MAX_VALUE)
			   	bw1.append("Infinity").append(System.lineSeparator());	
		   else	bw1.append(String.valueOf(beta)).append(System.lineSeparator());
					return eval1;
				}
				if(real_depth!=0&&previous.turn==1)  //player A is at 5th 3rd or 1st level and turn
				{							//umm player 1 chance 1 3 5 7...
					x=depth-1;
					if(turn1%2==0){ //A's chance is 1st...max a
						if(eval<eval1){     //A-B MAX
							if(depth==1&&playing1.free_turn==0){
								if(eval>saveme){
									myanswer=playing1;
									saveme=eval;
								}
							}
							eval=eval1;
							alpha=Max(alpha,eval1);
						}
					}
					else{
							if(eval>eval1){   //A-B MIN
							beta=Min(beta,eval1);
							eval=eval1;	
							}
							}
						bw1.append(playing1.player1.get(j).name+","+(depth-1)+",");
					if(eval==Integer.MIN_VALUE)
						bw1.append("-Infinity,");
		       else	    if(eval==Integer.MAX_VALUE)
		    	   		bw1.append("Infinity,");	
		       else		bw1.append(eval+",");
						if(alpha==Integer.MIN_VALUE)
						bw1.append("-Infinity,");
			   else	    if(alpha==Integer.MAX_VALUE)
				   		bw1.append("Infinity,");	
		       else		bw1.append(alpha+",");
						if(beta==Integer.MIN_VALUE)
						bw1.append("-Infinity").append(System.lineSeparator());
			   else	    if(beta==Integer.MAX_VALUE)
				   		bw1.append("Infinity").append(System.lineSeparator());	
			   else		bw1.append(String.valueOf(beta)).append(System.lineSeparator());

				}
				else {
					if(real_depth!=0){
							//fix FREE CHANCE?
						if(turn1%2==0){
							if(dummy<eval1){   //A-A MAX/MIN depends
								alpha=Max(alpha,eval1);
								eval=eval1;
								dummy=eval;
							}
							if(depth==1&&playing1.free_turn==0){
								if(eval>saveme){
									myanswer=playing1;
									saveme=eval;
								}
							}
						}
						else{
							if(dummy>eval1){
								beta=Min(beta,eval1);
								eval=eval1;
								dummy=eval;
							}
						}
							eval=dummy;
							bw1.append(previous.name+","+depth+",");
							if(eval==Integer.MIN_VALUE)
								bw1.append("-Infinity,");
				       else	    if(eval==Integer.MAX_VALUE)
				    	   bw1.append("Infinity,");	
				       else		bw1.append(eval+",");
								if(alpha==Integer.MIN_VALUE)
									bw1.append("-Infinity,");
					   else	    if(alpha==Integer.MAX_VALUE)
						   bw1.append("Infinity,");	
				       else		bw1.append(alpha+",");
								if(beta==Integer.MIN_VALUE)
									bw1.append("-Infinity").append(System.lineSeparator());
					   else	    if(beta==Integer.MAX_VALUE)
						   bw1.append("Infinity").append(System.lineSeparator());	
					   else		bw1.append(String.valueOf(beta)).append(System.lineSeparator());

					}
					else{
						if(eval<eval1)              //depth 0 print chance
						{
							alpha=Max(alpha,eval1);
							y=playing1;
							eval=eval1;	
							if(depth==1&&playing1.free_turn==0){
								if(eval>saveme){
									myanswer=playing1;
									saveme=eval;
								}
							}
						}
							bw1.append("root"+","+0+",");
							if(eval==Integer.MIN_VALUE)
							bw1.append("-Infinity,");
			       else	    if(eval==Integer.MAX_VALUE)
			    	   		bw1.append("Infinity,");	
			       else		bw1.append(eval+",");
							if(alpha==Integer.MIN_VALUE)
							bw1.append("-Infinity,");
				   else	    if(alpha==Integer.MAX_VALUE)
					   		bw1.append("Infinity,");	
			       else		bw1.append(alpha+",");
							if(beta==Integer.MIN_VALUE)
							bw1.append("-Infinity").append(System.lineSeparator());
				   else	    if(beta==Integer.MAX_VALUE)
					   		bw1.append("Infinity").append(System.lineSeparator());	
				   else		bw1.append(String.valueOf(beta)).append(System.lineSeparator());

						if(i==playing.player1.size()-1){
							display(myanswer,bw);
						}
											//B-ROOT MAX
					}
				}
		}
				
	}
	if(playing.player1.size()==i)
		infichk=1;
	return eval;
}
}

public class mancala {

	public static void main(String args[]) throws Exception{
	FileReader fr=new FileReader("input.txt");
	FileWriter f=new FileWriter("next_state.txt");
	FileWriter fw=new FileWriter("traverse_log.txt");
	BufferedWriter bw=new BufferedWriter(f);
	BufferedWriter bw1=new BufferedWriter(fw);
	BufferedReader br=new BufferedReader(fr);
	int task=Integer.parseInt(br.readLine());
//	Scanner sc=new Scanner(System.in;
	int turn=Integer.parseInt(br.readLine());
	int depth=Integer.parseInt(br.readLine());
	State gameinputstate=new State(br);
	State.depth1=depth;
	State.turn1=turn;
	//gameinputstate.display(gameinputstate);
	//gameinputstate.greedy(gameinputstate,0,turn,Mancala1,Mancala2,0,gameinputstate);
	
	int eval=Integer.MIN_VALUE;
	int dummy=0;
	int prev=Integer.MIN_VALUE;	
	int alpha=Integer.MIN_VALUE;
	int beta=Integer.MAX_VALUE;
	if(task==1){
		if(turn%2==0){ 
			gameinputstate.minmaxA(bw,bw1,task,gameinputstate,turn,1,0,gameinputstate,0,eval,gameinputstate,1,dummy,prev);//player 2s chance
			// state //turn //depth //i,j //state prev //real depth //eval //y to store state //depth //dummy //prev eval
		}
		else{
			gameinputstate.minmaxB(bw,bw1,task,gameinputstate,turn,1,0,gameinputstate,0,eval,gameinputstate,1,dummy,prev);//player 1s chance
		}
	}
	else if(task==2){
		bw1.append("Node,Depth,Value").append(System.lineSeparator());
		bw1.append("root"+","+0+",-Infinity").append(System.lineSeparator());
	if(turn%2==0){ 
		gameinputstate.minmaxA(bw,bw1,task,gameinputstate,turn,1,0,gameinputstate,0,eval,gameinputstate,depth,dummy,prev);//player 2s chance
	}
	else{
		gameinputstate.minmaxB(bw,bw1,task,gameinputstate,turn,1,0,gameinputstate,0,eval,gameinputstate,depth,dummy,prev);//player 1s chance
	}
					}
	else if(task==3){
		bw1.append("Node,Depth,Value,Alpha,Beta").append(System.lineSeparator());
		bw1.append("root,0,-Infinity,-Infinity,Infinity").append(System.lineSeparator());
		if(turn%2==0){ 
			gameinputstate.ALPHA(bw,bw1,task,gameinputstate,turn,1,0,gameinputstate,0,eval,gameinputstate,depth,dummy,prev,alpha,beta);//player 2s chance
		}
		else{
			gameinputstate.BETA(bw,bw1,task,gameinputstate,turn,1,0,gameinputstate,0,eval,gameinputstate,depth,dummy,prev,alpha,beta);//player 1s chance
		}
						}
bw.close();
bw1.close();
	}
}