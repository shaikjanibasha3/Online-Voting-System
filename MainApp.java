package com.letsVote;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;



public class MainApp {
	
	static Scanner sc=new Scanner(System.in);
	static VotingSystem votingSystem =new VotingSystem();
	static Map<String, Candidate> candidates = new HashMap<>();
	static int votesCasted=0;
	static {
        // Predefined candidates
        candidates.put("1", new Candidate("Alice", "Democratic", "Eagle"));
        candidates.put("2", new Candidate("Bob", "Republican", "Lion"));
    }
	public static void main(String[] args) {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t Welcome To Online Voting System: \n\n");
		
		MainApp.opt();
	}
	public static String home(){
		System.out.print("\t\t\t Home: \n\n ");
		
		System.out.print("\t\t\t 1. SignUp \n\n");
		
		System.out.print("\t\t\t 2. Login \n\n");
		
		System.out.print("\t\t\t 3. Eligibility Criteria\n\n");
		
		System.out.print("\t\t\t Enter your option: " );
		
		return MainApp.sc.next();
		
    }
		public static void opt() {
			
			String choice = MainApp.home();
		
			switch( choice ) {
			case "1":
				System.out.println();
				System.out.println();
				System.out.print("\t\t\t SignUp Here: ");
				System.out.print("\n\n");
				
	            System.out.print("\t\t\t Enter your name: ");
	            sc.nextLine();
	            String name = MainApp.sc.nextLine();
	            
	            
	            System.out.println();
	            
	           // System.out.print("\t\t\t Enter your age: ");
	            int age=printAge();
	            
//	            try {
//	            	 age=sc.nextInt();
//	            }
//	            catch(java.util.InputMismatchException e){
//	            	System.out.print("\n\n");
//	            	System.out.print("\t\t\t Invalid Input: ");
//	            	System.out.print("\n\n");
//	            	System.out.print("\t\t\t Enter Age in Integer: ");
//	            	sc.nextLine();
//	            	age=sc.nextInt();
//	            	System.out.print("\n\n");
//	            }
	  
	            String vId=null;
	            if(age>=18) {
	            	int x= votingSystem.generate();
	            	vId = String.valueOf(x);
	            	System.out.println();
	            	System.out.println("\t\t\t Your Voter Id: "+vId);
	            }
	            else {
	            	System.out.print("\n\n");
	            	
	            	System.out.println("\t\t\t Your age is less than 18. Your not Eligible: ");
	            	System.out.println();
	            	System.out.println();
	            	MainApp.opt();
	            }
	            //System.out.println();
	            //System.out.print("\t\t\t Set your password: ");
	            //sc.nextLine();
	            String password = checkPass();
	            
	            MainApp.votingSystem.signUp(name, vId, password, age);
	          
	            break;
	            
			case "2":
				System.out.print("\n\n");
				System.out.print("\t\t\t Login Here: ");
				System.out.print("\n\n");
				System.out.print("\t\t\t Enter your Voter ID: ");
	            vId = sc.next();
	            System.out.print("\n\n");
	            System.out.print("\t\t\t Enter your password: ");
	            sc.nextLine();
	            password = sc.nextLine();
	            if (votingSystem.login(vId, password)) {
	            	System.out.print("");
	            }
	            else {
	            	MainApp.opt();
	            }
				break;
			
			case "3":
				System.out.print("\n\n");
				System.out.print("\t\t\t Your age must be greater than 18 ");
				System.out.print("\n\n");
		        MainApp.opt();
		    default:
		    	System.out.print("\n\n");
		    	System.out.println("\t\t\t Invalid Input");
		    	System.out.print("\n\n");
		    	MainApp.opt();
			}
	}
	public static int printAge() {
		//int age;
		try {
			System.out.print("\t\t\t Enter your age: ");
			
	       	int age=sc.nextInt();
	       	sc.nextLine();
	       	 return age;
       }
       catch(java.util.InputMismatchException e){
       	System.out.print("\n\n");
       	System.out.print("\t\t\t Invalid Input: ");
       	System.out.print("\n\n");
       	sc.nextLine();
       	int age=printAge();
       	return age;
       }
		
	}
	public static String checkPass() {
		int uppercase=0;
		int lowercase=0;
		int digit=0;
		int specialchar=0;
		System.out.println();
        System.out.print("\t\t\t Set your password: ");
        String password=sc.nextLine();
		if(password.length()>=8) {
			for(int i=0;i<password.length();i++) {
				if((password.charAt(i)) >= 'A' && password.charAt(i)<='Z') {
					uppercase++;
				}
				else if(password.charAt(i)>='a' && password.charAt(i)<='z') {
					lowercase++;
				}
				else if(password.charAt(i)>='0' && password.charAt(i)<='9') {
					digit++;
				}
			}
			if((uppercase+lowercase+digit)!=password.length()) {
				specialchar++;
			}
			if(uppercase>0 && lowercase>0 && digit>0 && specialchar>0) {
				return password;
			}
			else {
				System.out.print("\n\n");
				System.out.print("\t\t\t It should have atleast ONE uppercase");
				System.out.println();
				System.out.print("\t\t\t It should have atleast ONE lowercase");
				System.out.println();
				System.out.print("\t\t\t It should have atleast ONE digit");
				System.out.println();
				System.out.print("\t\t\t It should have atleast ONE special character");
				System.out.print("\n\n");
				password=checkPass();
			}
		}
		else {
			System.out.print("\n\n");
			System.out.print("\t\t\t Password should have 8 or more than 8 characters: ");
			System.out.print("\n\n");
			password=checkPass();
		}
		return password;
	}
}

