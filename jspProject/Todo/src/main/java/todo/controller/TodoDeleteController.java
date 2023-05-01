package todo.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/todo/delete")
public class TodoDeleteController extends HttpServlet {

	protected void doPost(
			HttpServletRequest request,
			HttpServletResponse response) 
					throws ServletException, IOException {
		
		System.out.println("TodoDeleteController...doPost()...");
		
//		사용자로 부터 no 받기
		String noStr = request.getParameter("no");
		int no = Integer.parseInt(noStr);
		
//		서비스로 no 전달 -> 삭제처리
		System.out.println(no + "번 할일이 삭제 되었습니다.");
		
//		결과
		
//		redirect (list)
		response.sendRedirect("list");
		
	}

}