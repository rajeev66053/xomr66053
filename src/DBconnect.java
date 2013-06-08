import java.sql.*;
import java.io.*;
import java.util.*;



 class DBConnect {
    
    private Connection con;
    private Statement st;
    private ResultSet rs;
    
    public DBConnect(){
        
        try{
             String name;
             Scanner in = new Scanner(System.in);
              System.out.println("Enter name of Database");
             name=in.nextLine(); 
            Class.forName("org.h2.Driver");
            con=DriverManager.getConnection("jdbc:h2:~/"+name,"sa","sa");
            st=con.createStatement();
            //int Result=st.executeUpdate("CREATE DATABASE yuwa");
            
            String table ="CREATE TABLE students(Name varchar(30),Roll integer, Class Varchar(30),Faculty varchar(30))";
            st.executeUpdate(table);
          } 
        catch(Exception ex){
            System.out.println("Error"+ex);
        }
    }
    public void getdata(){
        List data = new ArrayList();
        try{
            File file = new File("/home/rajeev/Directory1");
            if (!file.exists()) {
		file.mkdir();
                        }
            String query= "Select * FROM students";
            rs=st.executeQuery(query);
            System.out.println("Records from Database");
            while(rs.next())
            {
                String Name=rs.getString("Name");
                String Roll=rs.getString("Roll");
                String Class=rs.getString("Class");
                String Faculty=rs.getString("Faculty");
               // System.out.println("Name  "+Name+"  "+"Roll  "+Roll+"  "+"Class  "+Class+"  "+"Faculty  "+Faculty);
                data.add(Name+"  "+Roll+"  "+Class+"  "+Faculty);
            }
            
                 writeToFile(data, "Employee.txt");
                        rs.close();
                        st.close();
        }
        catch(Exception e){
          System.out.println(e);
        }
    }
 private static void writeToFile(java.util.List list, String path) {
                BufferedWriter out = null;
                try {
                        File file = new File("/home/rajeev/Directory1/raj");
                        out = new BufferedWriter(new FileWriter(file, true));
                        for (Object s : list) {
                                out.write((String) s);
                                out.newLine();
                        }
                        out.close();
                } catch (IOException e) {
                }
}
 }