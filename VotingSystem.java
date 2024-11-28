package com.letsVote;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class VotingSystem implements Basic{
	private static ArrayList<Integer> VoterIds = new ArrayList<>();
	private static Map<String, User> users = new HashMap<>();
	private static ArrayList<String> checkDup =new ArrayList<>();
	
	private User loggedInUser;
	
	
	public void signUp(String name, String voterId, String password, int age) {
			
	        User user = new User(name, voterId, password, age);
	        
	        users.put(voterId, user);
	        System.out.print("\n\n");
	        System.out.println("\t\t\t Registration successful!");
	        System.out.print("\n\n");
	        MainApp.opt();
	}
	
	
	public boolean login(String voterId, String password) {
			
	        if (users.containsKey(voterId)) {
	        	
	            User user = users.get(voterId);
	            
	            if (user.getPassword().equals(password)) {
	            	System.out.print("\n\n");
	            	System.out.print("\t\t\t Logged in Successfully: ");
	            	System.out.print("\n\n");
	            	MainApp.votingSystem.loggedIn(user);
	            	
	                return true;
	                
	            } else {
	            	System.out.print("\n\n");
	                System.out.println("\t\t\tIncorrect password.");
	                System.out.print("\n\n");
	                return false;
	            }
	        } else {
	        	System.out.print("\n\n");
	            System.out.println("\t\t\t Voter ID not found.");
	            System.out.print("\n\n");
	            return false;
	        }
	    }
	
	
	void loggedIn(User user) {
		loggedInUser=user;
		String option="";
//		if(MainApp.votesCasted<=2) {
		option = VotingSystem.inLogin();
//        }
//        else {
//        	VotingSystem.result();
//        	System.exit(0);
//        }
		
		switch(option) {
		case "1":
			
			MainApp.votingSystem.showProfile(loggedInUser);
			
			MainApp.votingSystem.loggedIn(loggedInUser);
			
			break;
		case "2":
			System.out.print("\n\n");
			System.out.print("\t\t\t Change your password here: ");
			System.out.print("\n\n");
			//MainApp.sc.nextLine();
            String password = MainApp.checkPass();
            MainApp.votingSystem.changePassword(password,loggedInUser );
            MainApp.votingSystem.loggedIn(loggedInUser);
            break;
		case  "3":
			MainApp.votingSystem.listCandidates();
			MainApp.votingSystem.loggedIn(loggedInUser);
            break;
		case "4":
			String vvId = loggedInUser.getVoterId();
			boolean bool=true;
			for(String i: checkDup) {
				
				if(i==vvId) {
					bool=false;
					System.out.print("\n\n");
					System.out.print("\t\t\t You have already voted");
					System.out.print("\n\n");
					MainApp.votingSystem.loggedIn(loggedInUser);
					break;
				}		
			}//checkDup.add(vvId);
			if(bool) {
				//checkDup.add(vvId);
				MainApp.votingSystem.listCandidates();
				System.out.print("\n\n");
				System.out.print("\t\t\t Enter candidate ID to vote : ");
				//MainApp.sc.nextLine();
	            String candidateId = MainApp.sc.nextLine();
	            MainApp.votingSystem.vote(candidateId,loggedInUser );
	            MainApp.votingSystem.loggedIn(loggedInUser);
			}
            break;
		case "5":
			System.out.print("\n\n");
			System.out.print("\t\t\t you are Successfully Logged out");
			System.out.print("\n\n");
			MainApp.opt();
			break;
		default:
			System.out.print("\n\n");
			System.out.print("\t\t\t Invalid Input");
			System.out.print("\n\n");
			MainApp.votingSystem.loggedIn(loggedInUser);
		}
	}
	
	public void vote(String candidateId,User x) {
		loggedInUser =x;
		
	        //boolean bool=true;
	        if (MainApp.candidates.containsKey(candidateId)) {
	        		checkDup.add(loggedInUser.getVoterId());
		            MainApp.candidates.get(candidateId).incrementVotes();
		            MainApp.votesCasted++;
		            System.out.print("\n\n");
		            System.out.println("\t\t\t Vote casted for " + MainApp.candidates.get(candidateId).getName());
		            System.out.print("\n\n");
		            
		            if(MainApp.votesCasted<=2) {
		            	
		            	MainApp.votingSystem.loggedIn(loggedInUser);
		            }
		            else
		            {
		            	VotingSystem.result();
		            	System.exit(0);
		            }
		            
	        }
	        else {
	        	System.out.print("\n\n");
	        	System.out.print("\t\t\t Invalid candidate.");
	        	System.out.print("\n\n");
	        	MainApp.votingSystem.loggedIn(loggedInUser);
	        }
		}
    
	
	private static void result() {
		String id1="1";
		String id2="2";
		int x=MainApp.candidates.get(id1).getVotes();
		int y=MainApp.candidates.get(id2).getVotes();
		if(x>y) {
			System.out.print("\n\n");
			System.out.print("\t\t\t "+MainApp.candidates.get(id1).getName()+" is the winner with votes "+MainApp.candidates.get(id1).getVotes());
			System.out.print("\n\n");
	
		}
		else if(x<y) {
			System.out.print("\n\n");
			System.out.print("\t\t\t"+MainApp.candidates.get(id2).getName()+" is the winner with votes "+MainApp.candidates.get(id2).getVotes());
			System.out.print("\n\n");
		
		}
		else {
			System.out.print("\n\n");
			System.out.print("\t\t\t It tie with votes "+MainApp.candidates.get(id2).getVotes());
			System.out.print("\n\n");
			
		}
	}
	
	public void listCandidates() {
		System.out.print("\n\n");
        System.out.println("\t\t\t Candidates for Voting:");
        System.out.print("\n\n");
        for (String id : MainApp.candidates.keySet()) {
            System.out.println("\t\t\t "+ id + ". Name: " + MainApp.candidates.get(id).getName()+ ".  Party: "+MainApp.candidates.get(id).getParty()+".  Symbol: "+MainApp.candidates.get(id).getSymbol());
        }
    }
	private  void changePassword(String newPassword ,User x) {
		loggedInUser =x;
        
            loggedInUser.setPassword(newPassword);
            System.out.print("\n\n");
            System.out.println("\t\t\t Password changed successfully!");
            System.out.print("\n\n");
            MainApp.votingSystem.loggedIn(loggedInUser);
    }
	private  void showProfile(User x) {
		loggedInUser= x;
        	System.out.print("\n\n");
            System.out.print("\t\t\t voterId: "+loggedInUser.getVoterId());
            System.out.print("\n\n");
            System.out.print("\t\t\t voterName : "+loggedInUser.getName());
            System.out.print("\n\n");
            System.out.print("\t\t\t voterAge : "+loggedInUser.getAge());
            System.out.print("\n\n");
    }
	
	private static  String inLogin(){
		System.out.print("\n\n");
		System.out.print("\t\t\t 1. Show Profile");
		System.out.print("\n\n");
        System.out.print("\t\t\t 2. Change Password");
        System.out.print("\n\n");
        System.out.print("\t\t\t 3. List Candidates");
        System.out.print("\n\n");
        System.out.print("\t\t\t 4. Cast Vote");
        System.out.print("\n\n");
        System.out.print("\t\t\t 5. LogOut");
        System.out.print("\n\n");
        System.out.print("\t\t\t Enter your Option: ");
        String k=MainApp.sc.next();
        MainApp.sc.nextLine();
        return k;
        
	}
	
	
	
	
	public int  generate()
	{
		Random random=new Random();
		
		int randomID=random.nextInt(1_000_000);
		
		if(MainApp.votingSystem.StoreId(randomID)){
			
			return randomID;
		}
		else{
			
			MainApp.votingSystem.generate();
			return 0;
		}
		
		
	}
	public boolean StoreId(int k){
		boolean bool=true;
		for(int x : VoterIds){
			if(k==x){
				bool=false;
				break;
			}
		
		}
		if(bool==true){
			VoterIds.add(k);
			
			return true;

		}
		else{
			return false;
		}
	}

}
