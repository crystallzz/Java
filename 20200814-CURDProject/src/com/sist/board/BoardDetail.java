package com.sist.board;
import java.util.*;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.dao.*;

@WebServlet("/BoardDetail")
public class BoardDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=EUC-KR");
		 PrintWriter out=response.getWriter();
		 
		 //��ȣ�� �޴´� ?no=10 
		 String no=request.getParameter("no");
		 BoardDAO dao=new BoardDAO();
		 BoardVO vo=dao.boardDetail(Integer.parseInt(no)); // ��ȣ�� �ش��ϴ� �����Ͱ��� �����´�
		 
	     out.println("<html>");
	        out.println("<head>");
	        out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
	        out.println("<style type=text/css>");
	        out.println(".row {margin:0px auto;width:600px}");
	        out.println("h2 {text-align:center}");
	        out.println("</style>");
	        out.println("</head>");
	        out.println("<body>");
	        out.println("<div class=container>");
	        out.println("<h2>���뺸��</h2>");
	        out.println("<div class=row>");
	       
	        out.println("<table class=\"table\">");
	        out.println("<tr>");
	        out.println("<td class=\"success text-center\"width=25%>��ȣ</td>");
	        out.println("<td width=25% class= text-center>"+vo.getNo()+"</td>");
	        out.println("<td class=\"success text-center\"width=25%>�ۼ���</td>");
	        out.println("<td width=25% class= text-center>"+vo.getRegdate()+"</td>");
	        out.println("</tr>");
	        
	        out.println("<tr>");
	        out.println("<td class=\"success text-center\"width=25%>�̸�</td>");
	        out.println("<td width=25% class= text-center>"+vo.getName()+"</td>");
	        out.println("<td class=\"success text-center\"width=25%>��ȸ��</td>");
	        out.println("<td width=25% class= text-center>"+vo.getHit()+"</td>");
	        out.println("</tr>");
	        
	        out.println("<tr>");
	        out.println("<td class=\"success text-center\"width=25%>����</td>");
	        out.println("<td colspan=3>"+vo.getSubject()+"</td>");
	        out.println("</tr>");
	        
	        out.println("<tr>");
	        out.println("<td colspan=4 height=200 valign=top>"+vo.getContent()+"</td>");
	        out.println("</tr>");
	        
	        out.println("<tr>");
	        out.println("<td colspan=4 class=text-right>");
	        out.println("<a href=# class=\"btn-sm btn-success\">����</a>");
	        out.println("<a href=# class=\"btn-sm btn-warning\">����</a>");
	        out.println("<a href=BoardList class=\"btn-sm btn-danger\">���</a>");
	        out.println("</td>");
	        out.println("</tr>");
	        
	        out.println("</tr>");
	        out.println("</table>");
	        out.println("</div>");
	        out.println("</div>");
	        out.println("</body>");
	        out.println("</html>");
	}

}
