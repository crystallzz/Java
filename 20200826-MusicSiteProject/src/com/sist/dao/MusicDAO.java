package com.sist.dao;

import java.sql.*;
import java.util.*;

import com.sist.manager.MusicVO;


public class MusicDAO {
	private Connection conn;
	//sql 전송
	private PreparedStatement ps;
	//오라클 주소
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	public MusicDAO()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//드라이버를 이용해서 연결 => thin드라이버
		}catch(Exception ex) {}
	}
	public void getConnection()
	{
		try
		{
			conn=DriverManager.getConnection(URL,"hr","happy");
		}catch(Exception ex) {}
		
	}
	public void disConnection()
	{
		try {
			if(ps!=null)
				ps.close();
			if(conn!=null)
				conn.close();
		}catch(Exception ex) {}
	}
    public void musicInsert(MusicVO vo)
    {
    	try
    	{
    		getConnection();
    		String sql="INSERT INTO music VALUES("
    				+ "music_mno_seq.nextval,?,?,?,?,?)";
    		ps=conn.prepareStatement(sql);
    		//?에 값채우기
    		ps.setInt(1, vo.getCateno());
    		ps.setString(2, vo.getTitle());
    		ps.setString(3, vo.getPoster());
    		ps.setString(4, vo.getSinger());
    		ps.setString(5, vo.getAlbum());
    		
    		ps.executeUpdate(); //INSERT 문장을 실행 => COMMIT
    		
    	}catch(Exception ex)
    	{
    		System.out.println(ex.getMessage());
    	}
    	finally
    	{
    		disConnection();
    	}
    }
    public ArrayList<String> musicGenreAllData()
    {
    	ArrayList<String> list=
    			new ArrayList<String>();
    	try
    	{
    		getConnection();
    		String sql="SELECT genre FROM music_genre "
    				+ "ORDER BY no";
    		ps=conn.prepareStatement(sql);
    		ResultSet rs=ps.executeQuery();
    		while(rs.next())
    		{
    			String genre=rs.getString(1);
    			list.add(genre);
    		}
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
    public ArrayList<MusicVO> musicAllData(int cateno,int page)
    {
    	ArrayList<MusicVO> list=new
    			ArrayList<MusicVO>();
    	try {
    		//subquery
    		getConnection();
    		String sql="SELECT mno,title,poster,singer,album,RANK() OVER(ORDER BY mno ASC),num "
    				+ "FROM (SELECT mno,title,poster,singer,album,rownum as num "
    				+ "FROM (SELECT mno,title,poster,singer,album "
    				+ "FROM music WHERE cateno=? ORDER BY mno)) "
    				+ "WHERE num BETWEEN ? AND ?"; //페이징기법
    			int rowSize=30;
    			int start=(rowSize*page)-(rowSize-1);
    			//rownum=rowSize => 시작번호=1
    			int end=rowSize*page;
    			
    			ps=conn.prepareStatement(sql);
    			ps.setInt(1, cateno);
    			ps.setInt(2, start);
    			ps.setInt(3, end);
    			
    			ResultSet rs=ps.executeQuery();
    			while(rs.next())
    			{
    				MusicVO vo=new MusicVO();
    				vo.setMno(rs.getInt(1));
    				vo.setTitle(rs.getString(2));
    				vo.setPoster(rs.getString(3));
    				vo.setSinger(rs.getString(4));
    				vo.setAlbum(rs.getString(5));
    				vo.setRank(rs.getInt(6));
    				
    				
    				list.add(vo);
    			}
    			rs.close();
    	}
    	catch(Exception ex)
    	{
    		System.out.println(ex.getMessage());
    	}
    	finally
    	{
    		disConnection();
    	}
    	return list;
    }
    public String musicGetGenre(int cateno)
    {
    	String genre="";
    	try
    	{
    		getConnection();
    		String sql="SELECT genre FROM music_genre "
    				+ "WHERE no=?";
    		ps=conn.prepareStatement(sql);
    		ps.setInt(1, cateno);
    		ResultSet rs=ps.executeQuery();
    		rs.next();
    		genre=rs.getString(1);
    		rs.close();
    	}catch(Exception ex)
    	{
    		System.out.println(ex.getMessage());
    	}
    	finally
    	{
    		disConnection();
    	}
    	return genre;
    }

}
