import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataSaveMap {
	public static void main(String[] args) {
		//데이터 베이스에서 데이터 읽기를 위한 변수
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//읽어온 데이터 저장을 위한 자료구조
		ArrayList<Map<String,Object>> list = new ArrayList<>();  //map을 쓰기로 했으니까 map
		
		try {
			//데이터 베이스 드라이버를 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//데이터 베이스 연결
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
			//sql 실행 객체 생성
			pstmt = con.prepareStatement("select deptno, dname, loc from dept");
			//sql 실행
			rs = pstmt.executeQuery();
			//데이터 읽기
			// 행 단위로 읽기
			while(rs.next()) {
				// 하나의 행을 저장할 맵 객체 생성
				Map<String, Object> map = new HashMap<>();
				map.put("deptno", rs.getInt("deptno"));
				map.put("dname", rs.getString("dname"));
				map.put("loc", rs.getString("loc"));
				
				//읽은 하나의 행을 리스트에 저장
				list.add(map);
			}
			System.out.printf("%10s", "부서번호");
			System.out.printf("%20s", "부서이름");
			System.out.printf("%30s", "부서위치");
			System.out.println();
			for(Map map : list) {
				System.out.printf("%10s", map.get("deptno"));
				System.out.printf("%20s", map.get("dname"));
				System.out.printf("%30s", map.get("loc"));
				System.out.println();
			}
		}catch(Exception e) {
			//예외 내용을 출력
			System.out.println(e.getMessage());
			//예외 내용을 역추적
			e.printStackTrace();
		}finally {
			//사용을 다한 경우 닫기
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(con != null) {
					con.close();
				}
			}catch(Exception e) {}
		}
		
	}
}
