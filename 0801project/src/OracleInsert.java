import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class OracleInsert {

	public static void main(String[] args) {
		//데이터베이스 연결 변수
		Connection con = null;
		//SQL 실행 변수
		PreparedStatement pstmt = null;
		try {
			//드라이버 클래스 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//데이터베이스 연결
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott", "tiger");
			//SQL 실행 객체를 생성 : SQL을 매개변수로 해서 생성
			//pstmt = con.prepareStatement("insert into dept(deptno,dname,loc)" + "values(60,'영업','부산')");
			//pstmt = con.prepareStatement("delete from dept where deptno = 50");
			pstmt = con.prepareStatement("update dept set dname = '기획' where deptno = 60");
			//SQL을 실행 - select를 제외한 구문 실행
			//r에 저장되는 값은 영향받은 행의 개수
			int r = pstmt.executeUpdate();
			//성공여부 출력
			if(r>0) {
				System.out.println("삽입 성공");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try { //닫을 떄는 거꾸로 닫는다.
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
		
	}catch(Exception e) {
		
	}
			}
		}
}
