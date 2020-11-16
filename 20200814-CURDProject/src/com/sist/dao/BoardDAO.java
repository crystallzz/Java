package com.sist.dao;
import java.sql.*;
import java.util.*;

import jdk.nashorn.internal.ir.ForNode;
public class BoardDAO {
	//연결
	private Connection conn;
	//sql문장 전송
	private PreparedStatement ps;
	//오라클 주소 첨부
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";	
	//연결준비
	//1.드라이버 등록
	public BoardDAO()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	public void getConnection() {
		try
		{
			conn=DriverManager.getConnection(URL,"hr","happy");
		
		}catch(Exception ex) {}
	}
	public void disConnection()
	{
		try
		{
			if(ps!=null)
				ps.close();
			if(conn!=null)
				conn.close();
		}catch(Exception ex) {}
	}
	//기능
	//1. 목록(게시판) => SELECT
	public ArrayList<BoardVO> boardListData()
	{
		ArrayList<BoardVO> list=new ArrayList<BoardVO>();
		try {
			//연결
			getConnection();
			//sql문장전송
			String sql="SELECT no,subject,name,regdate,hit "
						+"FROM freeboard "
						+"ORDER BY no DESC";// 최신 등록된 게시물 먼저 출력
					// ORDER BY => 단점(속도가 늦다) => INDEX 
			ps=conn.prepareStatement(sql);
			//sql실행 후 결과값받기
			ResultSet rs=ps.executeQuery();
			//결과값을 ArrayList에 첨부
		while(rs.next())
		{
			BoardVO vo=new BoardVO();
			vo.setNo(rs.getInt(1));
			vo.setSubject(rs.getString(2));
			vo.setName(rs.getString(3));
			vo.setRegdate(rs.getDate(4));
			vo.setHit(rs.getInt(5));
			list.add(vo);
		}
		rs.close();
		}catch(Exception ex) 
		{
			System.out.println(ex.getMessage());
		}
		finally
		{
			disConnection();
		}
		return list;
		
	}
	//2. 내용보기 
	public BoardVO boardDetail(int no)
	{
		BoardVO vo=new BoardVO();
		try
		{
		  getConnection();	
		  //조회수 증가 쿼리
		  String sql="Update freeboard SET "
		  		+ "hit=hit+1 "
				 + "WHERE no=?";
		  ps=conn.prepareStatement(sql);
		  ps.setInt(1, no); //?에 값을 채워줌
		  // 실행
		  ps.executeUpdate();
		  // 내용물 데이터값을 가지고 온다
		  sql="SELECT no,name,subject,content,regdate,hit "
			+ "FROM freeboard "
			+ "WHERE no=?";
		  ps=conn.prepareStatement(sql);
		  ps.setInt(1, no);
		  
		  ResultSet rs=ps.executeQuery();
		  rs.next();
		  
		  vo.setNo(rs.getInt(1));
		  vo.setName(rs.getString(2));
		  vo.setSubject(rs.getString(3));
		  vo.setContent(rs.getString(4));
		  vo.setRegdate(rs.getDate(5));
		  vo.setHit(rs.getInt(6));
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		finally
		{
			disConnection();
		}
		return vo;
	}
	//3. 글쓰기 => INSERT
	public void boardInsert(BoardVO vo)
	{
		try
		{
			getConnection();
			String sql="INSERT INTO freeboard (no,name,subject,content,pwd) "
					+ "VALUES((SELECT NVL(MAX(no)+1,1) FROM freeboard),?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			
			ps.executeUpdate(); // update=> auto commit;
			
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		finally
		{
			disConnection();
		}
		
	}
	//4. 글수정 => UPDATE
	//5. 글삭제 => DELETE
	//6. 찾기  => SELECT (LIKE)

}
