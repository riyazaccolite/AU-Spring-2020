import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class XMLParser {

	static ArrayList<Student> studentCollection;
	public static void main(String args[]){
		try{
			File studentXml = new File("src/students.xml");
			SAXBuilder builder = new SAXBuilder();
			Document document = (Document) builder.build(studentXml);
			Element rootNode = document.getRootElement();
			List list = rootNode.getChildren("department");
			studentCollection = new ArrayList<Student>();
			Scanner sc = new Scanner(System.in);

			for (int i = 0; i < list.size(); i++) {

			   Element node = (Element) list.get(i);
			   System.out.println("Department: "+node.getAttributeValue("branch").toUpperCase());
			   
			   List studentList = node.getChildren();
			   for(int j=0; j<studentList.size(); j++){
				   Element studentNode = (Element) studentList.get(j);
				   studentCollection.add(new Student(
						   Integer.parseInt(studentNode.getChildText("rollnumber")),
						   node.getAttributeValue("branch"),
						  studentNode.getChildText("firstname"),
						  studentNode.getChildText("lastname") 
						   ));
			   }

			}
			System.out.println("XML Data Parsed");
			int userInp = 1;
			while(userInp != 3){
				System.out.println("1. Print Data\n2. Sort and print data\n3. Exit");
				userInp = sc.nextInt();
				if(userInp == 1)
					printData();
				else if(userInp == 2)
					sortAndPrintData(sc);
				else if(userInp != 3)
					System.out.println("Wrong input");
			}

			sc.close();
		}
		catch (IOException io) {
			System.out.println(io.getMessage());
		  } 
		catch (Exception e) {
			e.printStackTrace();
		 }
	}
	
	static void printData(){
		int len = studentCollection.size();
		for(int i=0;i<len;i++)
			System.out.println(studentCollection.get(i) + "\n");
	}
	
	static void sortAndPrintData(Scanner sc){
		System.out.println("Enter Sort parameter\n"
				+ "1. Roll Number\n"
				+ "2. First Name\n"
				+ "3. Last Name\n"
				+ "4. Department\n"
				);
		int sortParam = sc.nextInt();
		if(!(sortParam>=1 && sortParam<=4))
			System.out.println("Wrong input");
		else{
			Student.comparisionParameter = sortParam;
			Collections.sort(studentCollection);
			printData();
		}
	}
}

class Student implements Comparable<Student>{
	int rollNumber;
	String department, firstName, lastName;
	static int comparisionParameter = 1;
	
	Student(int rollNumber, String department, String firstName, String lastName){
		this.rollNumber = rollNumber;
		this.department = department;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String toString(){
		return "First Name : " + this.firstName + "\n" + 
		"Last Name : " + this.lastName + "\n" +
		"Roll Number : " + this.rollNumber + "\n" +
		"Department : " + this.department.toUpperCase();  
	}
	
	public int compareTo(Student s){
		if(comparisionParameter == 1)
			return (new Integer(this.rollNumber)).compareTo(new Integer(s.rollNumber));
		else if(comparisionParameter == 2)
			return this.firstName.compareTo(s.firstName);
		else if(comparisionParameter == 3)
			return this.lastName.compareTo(s.lastName);
		else
			return this.department.compareTo(s.department);
	}
}
