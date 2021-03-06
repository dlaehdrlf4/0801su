import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Dept {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;
		// select 구문의 결과를 저장하기 위한 변수
		ResultSet r = null;

		try {
			//오라클 드라이버 클래스 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//데이터베이스연결
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott", "tiger");
			//sql 실행 객체 생성
			pstmt = con.prepareStatement("select deptno, dname, loc from dept");
			//select 하는 sql 실행
			r = pstmt.executeQuery();
			//데이터 전체 읽기
			while(r.next()) {
				System.out.print(r.getInt("deptno") + ":");
				System.out.print(r.getString("dname") + ":");
				System.out.print(r.getString("loc") + "\n");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (r != null)
					r.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e1) {

			}
		}

	}

}
