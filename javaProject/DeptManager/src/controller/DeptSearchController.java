package controller;

import domain.Dept;
import main.DeptManagerMain;
import service.DeptSearchService;

public class DeptSearchController implements Controller{

//	Service
	DeptSearchService searchService;

	private DeptSearchController() {
		this.searchService = DeptSearchService.getInstance();
	}
	
	private static DeptSearchController controller = new DeptSearchController();
	
	public static DeptSearchController getInstance() {
		return controller;
	}

	public void process() {

//		view : 검색할 부서번호를 사용자로부터 받는 화면
		int deptno = getDeptNo();

		Dept dept = searchService.searchDept(deptno);
		
		printData(dept);
	}
	
//	결과를 출력하는 화면
	public void printData(Dept dept) {
		System.out.println("검색결과");
		System.out.println("--------------------");
		System.out.println("부서번호 : " + dept.getDeptno());
		System.out.println("부서이름 : " + dept.getDname());
		System.out.println("부서위치 : " + dept.getLoc());
	}

//	사용자로 부터 번호를 입력받는 화면
	public int getDeptNo() {
		System.out.println("검색을 시작합니다.");
		System.out.print("검색할 부서번호를 입력하세요. >>");

		return Integer.parseInt(DeptManagerMain.sc.nextLine());
	}

}
