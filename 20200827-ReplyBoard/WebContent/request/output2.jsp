<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h1>input2.jsp에서 받은 값</h1>
		<%
		try{
			request.setCharacterEncoding("UTF-8");
		}catch(Exception ex){}
		String name=request.getParameter("name");
		String sex=request.getParameter("sex");
		String Loc=request.getParameter("Loc");
		String content=request.getParameter("content");
		String[] hobby=request.getParameterValues("hobby");
		//checkbox,select(multi)일때만 배열사용
		%>
	이름 : <%=name %><br>
	성별: <%=sex %> <br>
	지역 : <%=Loc %><br>
	소개 : <%=content %><br>
	취미 :
		<ul>
		<%
		try{
		for(String s:hobby)
		{
		%>
			<li><%=s %></li>
		<%
		}
		}catch(Exception ex)
		{
		%>
		<font color=red>취미가 없습니다</font>
		<%	
		}
		%>
		</ul>
 IP : <%=request.getRemoteAddr() %> <br>
  요청경로 : <%= request.getRequestURI() %> <br>
 전체주소 : <%=request.getRequestURL() %> <br>
 컨텍스트 : <%=request.getContextPath() %><br>
 요청방식 : <%=request.getMethod() %><br>
 인코딩 : <%=request.getCharacterEncoding() %><br>
 서버주소 : <%=request.getServerName() %><br>
 서버포트 : <%=request.getServerPort() %>
	</center>
</body>
</html>