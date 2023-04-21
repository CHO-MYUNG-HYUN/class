package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.DeptDao;
import domain.Dept;
import utill.ConnectionProvider;

public class DeptSearchService {

	DeptDao dao;

	private DeptSearchService() {
		this.dao = DeptDao.getInstance();
	}
	
	private static DeptSearchService service = new DeptSearchService();
	
	public static DeptSearchService getInstance() {
		return service;
	}

//	검색 번호를 받고 Dept 정보를 저장하고 있는 객체를 반환
	public Dept searchDept(int deptno) {

		Connection conn = null;
		Dept dept = null;

		try {
			conn = ConnectionProvider.getConnection();
			dept = dao.selectByDeptno(conn, deptno);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dept;

	}

}
