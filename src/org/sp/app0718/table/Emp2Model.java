package org.sp.app0718.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class Emp2Model extends AbstractTableModel{
	Emp2DAO emp2DAO;
	List<Emp2DTO> list;
	String[] column= {
			"empno","ename","job","mgr","hiredate","sal","comm","deptno"
	};
	
	public Emp2Model() {
		emp2DAO = new Emp2DAO();
		
		list=emp2DAO.selectAll();
		
		/*
		for(int i=0; i<list.size();i++) {
			Emp2DTO dto=list.get(i);
			System.out.println(dto.getEname());
		}
		*/
	}

	//JTable에게 출력할 행의 수를 알려준다
	public int getRowCount() {
		return list.size();
	}

	//JTable에게 출력할 열의 수를 알려준다
	public int getColumnCount() {
		return 8;
	}
	
	public String getColumnName(int col) {
		return column[col];
	}

	//JTable에게 각 좌표의 셀마다 들어갈 데이터를 알려준다
	public Object getValueAt(int row, int col) {
		//List는 이차원 배열이 아닌 일차원 구조이므로 알아서 처리
		Emp2DTO dto=list.get(row);
		
		String value=null;
		switch(col) {
			case 0:value=Integer.toString(dto.getEmpno());break;
			case 1:value=dto.getEname();break;
			case 2:value=dto.getJob();break;
			case 3:value=Integer.toString(dto.getMgr());break;
			case 4:value=dto.getHiredate();break;
			case 5:value=Integer.toString(dto.getSal());break;
			case 6:value=Integer.toString(dto.getComm());break;
			case 7:value=Integer.toString(dto.getDeptno());break;
		}
		
		
		
		return value;
	}
	
	//필요한 메서드 더 오버라이드 하기
	//편집모드 활성화 여부(row,col)
	public boolean isCellEditable(int row, int col) {
		boolean flag=true;
		if(col==0) {
			flag=false;
		}
		return flag;
	}
	
	//지정한 좌표의 값을 반영하기(getValueAt의 반대)
	public void setValueAt(Object value, int row, int col) {
		//System.out.println(row+","+col+"을 수정하고 싶어요");
		
		//row 변수는 ArrayList의 index에 사용하고
		Emp2DTO dto=list.get(row);
		//col 변수는 DTO 안의 멤버변수들에 대한 접근정보로 활용
		
		switch(col) {
			
			case 1:dto.setEname((String)value);break;
			case 2:dto.setJob((String)value);break;
			case 3:dto.setMgr(Integer.parseInt((String)value));break;
			case 4:dto.setHiredate((String)value);break;
			case 5:dto.setSal(Integer.parseInt((String)value));break;
			case 6:dto.setComm(Integer.parseInt((String)value));break;
			case 7:dto.setDeptno(Integer.parseInt((String)value));break;
		}

	}
}
