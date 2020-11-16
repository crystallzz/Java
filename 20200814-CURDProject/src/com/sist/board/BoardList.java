package com.sist.board;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.BoardDAO;
import com.sist.dao.BoardVO;

import java.util.*;

@WebServlet("/BoardList")
public class BoardList extends HttpServlet {
   private static final long serialVersionUID = 1L;

   
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //���������� �����ϴ� ȭ�� => HTML
      //�������� �˸� => HTML������ ������ ���̴�
      response.setContentType("text/html; charset=EUC-KR"); //���������� ������ Ÿ���� �����ΰ� xml, html �Ľ��� Ʋ���� ������
      //HTML�� �������� ���� ����
      
      PrintWriter out=response.getWriter();
      out.println("<html>");
         out.println("<head>");
         out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
         out.println("<style type=text/css>");
         out.println(".row {margin:0px auto;width:700px}");
         out.println("h2 {text-align:center}");
         out.println("</style>");
         out.println("</head>");
         out.println("<body>");
         out.println("<div class=container>");
         out.println("<h2>�����Խ���</h2>");
         out.println("<div class=row>");
         
         //���� ��ư
         out.println("<table class=\"table\">");
         out.println("<tr>");
         out.println("<td>");
         // [ũ��]xs sm lg   //[����]success, danger
         out.println("<a href=BoardInsert class=\"btn btn-xs btn-warning\">����</a>");   
         out.println("</td>");
         out.println("</tr>");
         out.println("</table>");
         
                 
         out.println("<table class=\"table table-hover\">"); //hover:���콺Ŀ�� on�� ������
         out.println("<tr class=warning>");
         out.println("<th class=text-center width=10%>��ȣ</th>");
         out.println("<th class=text-center width=45%>����</th>");
         out.println("<th class=text-center width=15%>�̸�</th>");
         out.println("<th class=text-center width=20%>�ۼ���</th>");
         out.println("<th class=text-center width=10%>��ȸ��</th>");
         out.println("</tr>");
         // ��� 
         BoardDAO dao=new BoardDAO();
         ArrayList<BoardVO> list=dao.boardListData();
         for(BoardVO vo:list)
         {
            out.println("<tr>");
            out.println("<td class=text-center width=10%>"+vo.getNo()+"</td>");
            out.println("<td class=text-left width=45%>"
            +"<a href=BoardDetail?no="+vo.getNo()+">"
            		+vo.getSubject()+"</a></td>");
            out.println("<td class=text-center width=15%>"+vo.getName()+"</td>");
            out.println("<td class=text-center width=20%>"+vo.getRegdate().toString()+"</td>");
            out.println("<td class=text-center width=10%>"+vo.getHit()+"</td>");
            out.println("</tr>");
         }
         out.println("</table>");
         
         //����
         out.println("<hr>"); 
         //��ư
         out.println("<table class=\"table\">");
         out.println("<tr>");
         
         out.println("<td class=text-left>");
         out.println("Search:");   
         out.println("<select class=input-sm>");   
         out.println("<option>�̸�</option>");   
         out.println("<option>����</option>");   
         out.println("<option>����</option>");   
         out.println("</select>");   
         out.println("<input type=text size=15 class=input-sm>");   
         out.println("<input type=button value=ã�� class=\"btn-sm btn-danger\">");   
         out.println("</td>");
         
         out.println("<td class=text-right>");
         out.println("<a href=BoardInsert class=\"btn btn-xs btn-primary\">����</a>");
         out.println("0page / 0pages");
         out.println("<a href=BoardInsert class=\"btn btn-xs btn-primary\">����</a>");
         out.println("</td>");
         
         out.println("</tr>");
         out.println("</table>");
         
         out.println("</div>");
         out.println("</div>");
         out.println("</body>");
         out.println("</html>");

      
      
      
      
   }

}