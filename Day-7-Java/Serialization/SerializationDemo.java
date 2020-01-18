import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;;

public class SerializationDemo {
	
	private static ArrayList<User> listOfUsers;
	
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int inp = 0;
		SerializationDemo demoObj = new SerializationDemo();
		demoObj.readAllUsers();
		while(inp!=3){
			System.out.println("1. Add User\n2. Print all users\n3. Exit");
			inp = sc.nextInt();
			if(inp == 1)
				demoObj.createUser(sc);
			else if(inp==2)
				demoObj.printAllUsers();
			else
				demoObj.saveAllUsers();
		}
		sc.close();
	}
	
	void createUser(Scanner sc){
		System.out.println("Enter name");
		String name = sc.next();
		System.out.println("Enter age");
		int age = sc.nextInt();
		listOfUsers.add(new User(name,age));
	}
	
	void readAllUsers(){
		listOfUsers = new ArrayList<User>();
		FileInputStream userDataFile;
		ObjectInputStream objStream;
		
		try{
			userDataFile = new FileInputStream("./users.txt");
			objStream = new ObjectInputStream(userDataFile);
			User user;
			while((user = (User)objStream.readObject()) != null){
				listOfUsers.add(user);
			}
			objStream.close();
			userDataFile.close();
		}
		catch(Exception e){}
	}
	
	void printAllUsers(){
		int length = listOfUsers.size();
		if(length == 0)
			System.out.println("No Users");
		else{
			for(int i=0; i<length; i++)
				System.out.println(listOfUsers.get(i));
		}
	}
	
	void saveAllUsers(){
		try{
			FileOutputStream userDataFile = new FileOutputStream("./users.txt");
			ObjectOutputStream objStream = new ObjectOutputStream(userDataFile);
			int length = listOfUsers.size();
			for(int i=0; i<length; i++)
				objStream.writeObject(listOfUsers.get(i));
			
			userDataFile.close();
			objStream.close();
		}
		catch(Exception e){
			System.out.println("Exception occured");
			e.printStackTrace();
			System.exit(1);
		}
	}
}

class User implements java.io.Serializable{
	private String name;
	private int age;
	
	User(String name,int age){
		this.name = name;
		this.age = age;
	}
	
	int getAge(){ return this.age; }
	
	String getName(){ return this.name; }
	
	void setAge(int age){ this.age = age; }
	
	void setName(String name) { this.name = name; }
	
	public String toString(){
		return "Name: " + this.name + "\nAge: " + this.age + "\n";
	}
}
