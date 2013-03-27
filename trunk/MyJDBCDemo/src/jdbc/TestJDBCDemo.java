package jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class TestJDBCDemo {
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	/**
	 * 获取指定的文件并解析，
	 * 获取Properties键值并赋值给全局变量driver,url,user,password
	 * @param filename 文件名
	 */
	public static void getParam(String filename){
		File file = new File(filename);
		try {
			//打开文本输入流
			FileInputStream fis = new FileInputStream(file);
			Properties propers = new Properties();
			//加载配置文件
			propers.load(fis);
			driver=propers.getProperty("driver");
			url=propers.getProperty("url");
			user=propers.getProperty("user");
			password=propers.getProperty("password");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void getEmpData(){
		Connection con =null;//创建链接对象
		PreparedStatement stmt=null;//创建sql封装对象
		ResultSet rs =null;//创建sql返回回结果集对象
		Scanner input = new Scanner(System.in);
		String sql=input.nextLine();
		try {
			Class.forName("driver");//加载驱动实现类
			con=DriverManager.getConnection(url,user,password);
			stmt=con.prepareStatement(sql);
			rs=stmt.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString(1)+" "+rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception{
		getParam("src/jdbc/db.properties");
		getEmpData();
	}
	
}
