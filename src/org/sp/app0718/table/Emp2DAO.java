package org.sp.app0718.table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Emp2에 대한 CRUD를 담당하는 DAO를 정의한다.
public class Emp2DAO {
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="java";
	String pass="1234";
	
	//모든 레코드 가져오기
	public List selectAll() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<Emp2DTO> list=new ArrayList<Emp2DTO>();//사이즈가 0인 리스트 생성
		
		try {
			//1단계 : 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2단계: 접속
			con=DriverManager.getConnection(url, user, pass);
			if(con==null) {
				System.out.println("접속 실패");
			}else {
				System.out.println("접속 성공");
				String sql="select * from emp2 order by empno asc";
				pstmt=con.prepareStatement(sql);//쿼리문 준비
				rs=pstmt.executeQuery();//쿼리실행
				
				//rs는 곧 close될 예정이므로 rs를 대체할 매개체에 옮겨담자
				//어제는 배열에 담았지만 오늘은 보다 객체지향 적인 방법을 이용
				//레코드 1건 --> 클래스로부터 생성된 인스턴스 1건
				
				while(rs.next()){
				
					Emp2DTO dto= new Emp2DTO();//빈 dto생성
					
					dto.setEmpno(rs.getInt("empno"));
					dto.setEname(rs.getString("ename"));
					dto.setJob(rs.getString("job"));
					dto.setMgr(rs.getInt("mgr"));
					dto.setHiredate(rs.getString("hiredate"));
					dto.setSal(rs.getInt("sal"));
					dto.setComm(rs.getInt("comm"));
					dto.setDeptno(rs.getInt("deptno"));
					list.add(dto);
				}
				System.out.println("채워진 사원 수는 "+list.size());
				
				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	//레코드 1건 수정하기
	public void update(Emp2DTO dto) {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection(url, user, pass);
			if(con==null) {
				System.out.println("접속 실패");
			}else {
				//한줄을 업데이트하자
				String sql="update emp2 set ename='"+dto.getEname()+"', job='"+dto.getJob()+"' ";
				sql+=", mgr="+dto.getMgr()+", hiredate='"+dto.getHiredate()+"', sal="+dto.getSal()+", comm="+dto.getComm()+", deptno="+dto.getDeptno()+"";
				sql+=" where empno="+dto.getEmpno()+"";
				
				//System.out.println(sql);
				pstmt=con.prepareStatement(sql);//쿼리문 준비
				int result=pstmt.executeUpdate();//쿼리수행
				if(result>0) {
					System.out.println("성공");
				}else {
					System.out.println("실패");
				}
				
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		
	}
}












