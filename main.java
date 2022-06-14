package com.devipriya;
import java.sql.*;
import java.util.Scanner;

public class main {
	public Connection getMySQLConnectio()
	{
		Connection con=null;
		try {
		
			
			String url="jdbc:mysql://localhost:3306/course?autoReconnect=true&useSSL=false";
			String username = "root";
			String password = "Babai@123";
			
		con = DriverManager.getConnection(url,username,password);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return con;
		 
	}
	

	public String student_registration() {
		// TODO Auto-generated method stub
		try {
			Connection conn =getMySQLConnectio();
			String createAcount = "insert into registration(username,password,email,address,contact_num,id) values(?,?,?,?,?,?) ";
			
			PreparedStatement ps=  conn.prepareStatement(createAcount);
			
			Scanner sc=new Scanner(System.in);
		
			
			System.out.println("Enter your Username");
			String username = sc.next();
			
			System.out.println("Enter your Password:-");
			String password = sc.next();
			System.out.println("Enter your email:-");
			String email = sc.next();
			System.out.println("Enter your  address:-");
			String address = sc.next();
			System.out.println("Enter your number:-");
			int contact_num = sc.nextInt();
			System.out.println("Enter your id:-");
			int id = sc.nextInt();
			//String course = sc.next();
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, email);
			ps.setString(4, address);
			ps.setInt(5, contact_num);
			ps.setInt(6,id);
			
			ps.executeUpdate();
			 System.out.println("**** STUDENT REGISTRATION IS SUCCESSFULL *****");
				conn.close();
			
	      }catch(Exception e) {
	    	  System.out.println(e);
	      }
	     return "success";
	}

	public void student_login() {
		// TODO Auto-generated method stub
		Scanner ss=new Scanner(System.in);
		System.out.println("Enter UserName");
		String username = ss.next();
		
		System.out.println("Enter password");
		String password = ss.next();

			Connection connn =getMySQLConnectio();
			String sql=  "select username,password,email,address,contact_num,id from registration where username= ?";
			PreparedStatement stmt;
			try {
				stmt = connn.prepareStatement(sql);
				stmt.setString(1, username);
				ResultSet rs=stmt.executeQuery( );
       			
			
				
				 
				if (rs.next()) {
					
					if (rs.getString(1).equals(username) && rs.getString(2).equals(password)) {
                        System.out.println("press 1 to enter your order details");
						int a=ss.nextInt();
						switch(a) {
						case 1:details();
						break;
						default:System.out.println("wrong type again");
						break;
						}
				}
				else{
				
					System.out.println("!!!!!!!!!!!!! Wrong Username And Password  !!!!!!!!!!!!!!" );
					
				}
				}
			}
					
			

				catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
	
		
	}

	private void details() {
		// TODO Auto-generated method stub
		try {
			Connection conn =getMySQLConnectio();
			String createAcount = "insert into details(course_name,course_duration,course_out_comes) values(?,?,?) ";
			
			PreparedStatement ps=  conn.prepareStatement(createAcount);
			
			Scanner sc=new Scanner(System.in);
		
			
			System.out.println("Enter your course_name");
			String course_name = sc.next();
			System.out.println("Enter your course_duration");
			int course_duration = sc.nextInt();
			System.out.println("Enter your  course_outcomes");
			String  course_outcomes= sc.next();

			//System.out.println("Enter your course");
			//String course = sc.next();
			ps.setString(1, course_name);
	 		ps.setInt(2, course_duration);
			ps.setString(3, course_outcomes);
			
			ps.executeUpdate();
			 System.out.println("***** thanks for filling order details *****");
				conn.close();
			
	      }catch(Exception e) {
	    	  System.out.println(e);
	      }
		
	}


	public void course_details() {
		// TODO Auto-generated method stub
		try{
			Connection conn =getMySQLConnectio();
			 Statement stmt=conn.createStatement();  
			 ResultSet rs=stmt.executeQuery("select * from details");
			 while(rs.next()){    
	            
				
				 System.out.println(rs.getString(1)+":     "+rs.getString(2)+":      "+rs.getString(3));    
	         }  
			 
	         // Closing connection  
	         conn.close();      
	     }catch(Exception e){ System.out.println("error connecting db"+e);}    
		
	}


	


}
