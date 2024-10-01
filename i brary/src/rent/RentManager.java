package rent;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



public class RentManager {
	
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/cookdb?serverTimeZone=UTC";
	private String id = "root";
	private String pw = "1234";
	
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	
	
	  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
	  
	public RentManager() {

	}
	
	public void initDBConnect() {
		try {
			Class.forName(driver);
			this.conn = DriverManager.getConnection(this.url, this.id, this.pw);
//			this.pstmt = conn.createStatement();
			
//			stmt = conn.createStatement();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int recordCount() {
		String sql = "select count(*) as cnt from book";
		int reCount = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			if (rs.next()) {
				reCount = rs.getInt("cnt");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return reCount;
	}
	
	public Book[] allFetch() {
		
		int reCount = this.recordCount();
		Book[] bookList = new Book[reCount];
		int bookCount = 0;
	String sql = "SELECT * FROM book";
        try {
            ResultSet rs = pstmt.executeQuery(sql);
           
            while (rs.next()) {
                String id = rs.getString("id");
                long categoryId = rs.getLong("category_id");
                String title = rs.getString("title");
                boolean rented = rs.getBoolean("rented"); // 이건 어떻게 해야 되는지 잘 모르겠어요 
                String writer = rs.getString("writer");
                String publisher = rs.getString("publisher");
                String description = rs.getString("description");

                // Book 객체 생성 후 배열에 추가
                bookList[bookCount] = new Book(id, categoryId, title, rented, writer, publisher, description);
                bookCount++;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        System.out.println(bookList);
        return bookList;
    }
	

	
 public void insertRent(String bookId, String userId) {
	  String rentSql = "insert into rent values(null, ?, ?)";
	  
	 
	  try {	
	 pstmt = conn.prepareStatement(rentSql);
	 pstmt.setString(1, bookId);
	 pstmt.setString(2, userId);
	 LocalDate now = LocalDate.now();
	 pstmt.setDate(3, Date.valueOf(now));
	 LocalDate endDate = now.plusDays(7);
	 pstmt.setDate(4, Date.valueOf(endDate)); // 번호가 오토인크리먼트일 경우 null 값으로 변경
	 pstmt.executeUpdate();  

	 
	  updateBookSql(bookId);

		} catch (SQLException e) {
			e.printStackTrace();
		}
 }

 public void updateBookSql(String bookId) {
	 String updateBookSql = "UPDATE book SET rented = ? WHERE id = ?";
	 try {
	 PreparedStatement updatePstmt = conn.prepareStatement(updateBookSql);
     updatePstmt.setBoolean(1, true); // rented 상태를 true로 변경
     updatePstmt.setString(2, bookId);
     updatePstmt.executeUpdate();
     
 } catch (SQLException e) {
		e.printStackTrace();
 }
 }
 

 
public void releaseDB() {
	try {
		this.conn.close();
		this.pstmt.close();
	}catch(SQLException e) {
		e.printStackTrace();
		}
	}

}
	
	
