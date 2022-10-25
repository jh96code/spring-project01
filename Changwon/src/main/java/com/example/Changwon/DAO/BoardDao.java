package com.example.Changwon.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.*;
import org.springframework.stereotype.Component;

import com.example.Changwon.DTO.BoardDto;
import com.example.Changwon.DTO.board_replyDto;


public class BoardDao {
	
	//DB연결은 위한 변수 생성
	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	final String JDBC_URL = "jdbc:mysql://localhost:3306/traveldb";
	
	//DB연결 메소드
	public Connection open() {
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL,"root","0000");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
		//board 테이블의 레코드 개수, 게시판 검색 기능
		public int getListCount(String items, String text) {
			Connection conn = open();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			//System.out.println("게시판 검색 분류 :" + items);
			//System.out.println("게시판 검색어 :" + text);
			
			int x = 0; //count 개수
			String sql;
			if (items == null && text == null)	//검색에 입력된값이 없다면 게시판 전체갯수를 들고온다.(default)
				sql = "select count(*) from board";
			else	//검색에 입력된값(items, text)를 통해 sql문에 해당하는 게시판만 들고온다.
				sql = "SELECT count(*) FROM board where " + items + " like'%" + text +"%'";
			//System.out.println("sql= " +sql);
			
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();

				if (rs.next()) //rs.next()값이 true(ResultSet에 값이 들어있다면)이면 rs에 있는값을 int타입으로 받아온다 = count(*)
					x = rs.getInt(1);
			} catch (Exception ex) {
				System.out.println("getListCount() 오류: " + ex);
			} finally {			
				try {				
					if (rs != null) 
						rs.close();							
					if (pstmt != null) 
						pstmt.close();				
					if (conn != null) 
						conn.close();												
				} catch (Exception ex) {
					throw new RuntimeException(ex.getMessage());
				}		
			}		
			return x;//검색된 게시판의 총 갯수(int x)를 리턴한다 (=하는 이유는 검색된 게시판의 수를 알아야 페이징처리 가능)
		}
		
		//게시판 전체 목록 가져오기
		public List<BoardDto> getAll(int page, int limit, String items, String text) throws Exception {
			Connection conn = open();
			//dto데이터 형식의 List배열 생성
			List<BoardDto> boardList = new ArrayList<>();
			
			int total_record = getListCount(items, text);
			int start = (page - 1) * limit;				
			int index = start + 1;
				
			//페이징 처리 sql
			String sql;
			if (items == null && text == null) //검색에 입력된값이 없다면 게시판 전체갯수를 들고온다.(default)
				sql = "select num,title,id,bdate,hit from board ORDER BY num DESC";
			else //검색에 입력된값(items, text)를 통해 sql문에 해당하는 게시판만 들고온다.
				sql = "SELECT num,title,id,bdate,hit from board where " + items + " like'%" + text + "%' ORDER BY num DESC ";
				//System.out.println("불러올때 sql문= " + sql);
			PreparedStatement pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			//select문이기 때문에 executeQuery() 실행
			ResultSet rs = pstmt.executeQuery();
				
			try/*(conn; pstmt; rs) <= 맨마지막 클로즈랑 같은 역할이라 생략*/{
				while(rs.absolute(index)) { //while문을 써서 DB에 있는값 전체를 ResultSet값에 들어간 값을 차례로 dto에 담는 과정
					BoardDto dto = new BoardDto();
					dto.setNum(rs.getInt("num"));
					dto.setTitle(rs.getString("title"));
					dto.setId(rs.getString("id"));
					dto.setBdate(rs.getDate("bdate"));
					dto.setHit(rs.getInt("hit"));
					//dto에 차례대로 넣은값을 boardList(dto형식의 List배열)에 담는다
					boardList.add(dto);
					
					if (index < (start + limit) && index <= total_record)
						index++;
					else
						break;
				}
				return boardList;			
			} catch(Exception e) {
				e.printStackTrace();
			} finally { //마지막 한번 실행 : 역순으로 종료해주기
				try {		
					if (rs != null) 
						rs.close();
					if (pstmt != null) 
						pstmt.close();				
					if (conn != null) 
						conn.close();
				} catch (Exception ex) {
					throw new RuntimeException(ex.getMessage());
				}	
			}
			//try문에서 예외가 생기면 catch로 바로 가기 때문에 리턴값(boardList)을 받을 수 없다.
			//이미 예외가 생겨서 리턴값이 없으므로 오류가 생기기 때문에 return값을 null로 해준다.
			return null;
		}
		
	
		//클릭한 게시판 상세목록 들고오기
		public BoardDto getBoard(int num) throws SQLException {
			Connection conn = open();
			
			BoardDto dto = new BoardDto();
			String sql = "select * from board where num=?";
		
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			try{
				dto.setId(rs.getString("id"));
				dto.setNum(rs.getInt("num"));
				dto.setTitle(rs.getString("title"));
				dto.setBdate(rs.getDate("bdate"));
				dto.setContent(rs.getString("content"));
				dto.setHit(rs.getInt("hit"));
				dto.setImg(rs.getString("img"));
				pstmt.executeQuery();
				return dto;
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {		
					if (rs != null) 
						rs.close();
					if (pstmt != null) 
						pstmt.close();				
					if (conn != null) 
						conn.close();
				} catch (Exception ex) {
					throw new RuntimeException(ex.getMessage());
				}	
			}
			return null;
		}
		
		//게시판 추가 메소드
		public void addBoard(BoardDto dto) throws Exception {
			Connection conn = open();
			
			String sql = "insert into board(title,id,bdate,img,content) values(?,?,CURRENT_TIMESTAMP(),?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			try{ // sql문의 파라미타(?)값에 데이터 대입
				pstmt.setString(1, dto.getTitle());
				pstmt.setString(2, dto.getId());
				pstmt.setString(3, dto.getImg());
				pstmt.setString(4, dto.getContent());
				//exectueUpdate()문을 사용
				pstmt.executeUpdate();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {										
					if (pstmt != null) 
						pstmt.close();				
					if (conn != null) 
						conn.close();
				} catch (Exception ex) {
					throw new RuntimeException(ex.getMessage());
				}	
			}
		}
		
		//게시판 수정 메소드
		public void updateBoard(BoardDto dto) {

			Connection conn = open();
			PreparedStatement pstmt = null;
			
			try {
				String sql = "update board set title=?, img=?, content=? where num=?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, dto.getTitle());
				pstmt.setString(2, dto.getImg());
				pstmt.setString(3, dto.getContent());
				pstmt.setInt(4, dto.getNum());
				pstmt.executeUpdate();			

			} catch (Exception ex) {
				System.out.println("updateBoard() 오류 : " + ex);
			} finally {
				try {										
					if (pstmt != null) 
						pstmt.close();				
					if (conn != null) 
						conn.close();
				} catch (Exception ex) {
					throw new RuntimeException(ex.getMessage());
				}		
			}
		} 
		
		//게시판 삭제 메소드
		public void delBoard(int num){
			Connection conn = open();
			PreparedStatement pstmt = null;
			
			String sql = "delete from board where num=?";
			
			try{
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				// 삭제된 뉴스 기사가 없을 경우
				if(pstmt.executeUpdate() == 0) {
					throw new SQLException("DB에러");
				}
			}catch (Exception ex) {
				System.out.println("delBoard() 오류 : " + ex);
			} finally {
				try {										
					if (pstmt != null) 
						pstmt.close();				
					if (conn != null) 
						conn.close();
				} catch (Exception ex) {
					throw new RuntimeException(ex.getMessage());
				}		
			}
		}
		
		//게시판 조회수 증가시키기
		public void updateHit(int num) {

			Connection conn = open();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "select hit from board where num = ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				int hit = 0;

				if (rs.next())
					hit = rs.getInt("hit") + 1;

				sql = "update board set hit=? where num=?";
				pstmt = conn.prepareStatement(sql);		
				pstmt.setInt(1, hit);
				pstmt.setInt(2, num);
				pstmt.executeUpdate();
			} catch (Exception ex) {
				System.out.println("updateHit() 오류 : " + ex);
			} finally {
				try {
					if (rs != null) 
						rs.close();							
					if (pstmt != null) 
						pstmt.close();				
					if (conn != null) 
						conn.close();
				} catch (Exception ex) {
					throw new RuntimeException(ex.getMessage());
				}			
			}
		}
		
		//게시판 댓글 불러오는 메소드
		public List<board_replyDto> getReply(int num) throws SQLException{
			List<board_replyDto> replylist = new ArrayList<>();
			Connection conn = open();
			String sql = "select * from board_reply where board_num=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			ResultSet rs = pstmt.executeQuery();
			
			try{ 
				while(rs.next()) { //while문을 써서 DB에 있는값 전체를 ResultSet값에 들어간 값을 차례로 dto에 담는 과정
					board_replyDto dto = new board_replyDto();
					dto.setNum(rs.getInt("num"));
					dto.setContent(rs.getString("content"));
					dto.setRegist_day(rs.getDate("regist_day"));
					dto.setMember_id(rs.getString("member_id"));
					//dto에 차례대로 넣은값을 replyList(dto형식의 List배열)에 담는다
					replylist.add(dto);
				}
				return replylist;
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {										
					if (pstmt != null) 
						pstmt.close();				
					if (conn != null) 
						conn.close();
					if (rs != null) 
						rs.close();
				} catch (Exception ex) {
					throw new RuntimeException(ex.getMessage());
				}	
			}
			
			return null;
		}
		
		
		//게시판 댓글 추가하는 메소드
		public void addBoardReply(board_replyDto dto) throws Exception {
			Connection conn = open();
			
			String sql = "insert into board_reply(num, content, regist_day, board_num, member_id) values(?,?,CURRENT_TIMESTAMP(),?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			try{ // sql문의 파라미타(?)값에 데이터 대입
				pstmt.setInt(1, dto.getNum());
				pstmt.setString(2, dto.getContent());
				pstmt.setInt(3, dto.getBoard_num());
				pstmt.setString(4, dto.getMember_id());
				
				//exectueUpdate()문을 사용
				pstmt.executeUpdate();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {										
					if (pstmt != null) 
						pstmt.close();				
					if (conn != null) 
						conn.close();
				} catch (Exception ex) {
					throw new RuntimeException(ex.getMessage());
				}	
			}
		}
		
		//게시판 댓글 삭제하는 메소드
		public void delBoardReply(int num){
			Connection conn = open();
			PreparedStatement pstmt = null;
					
			String sql = "delete from board_reply where num=?";
					
			try{
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				// 삭제된 댓글이 없을 경우
				if(pstmt.executeUpdate() == 0) {
					throw new SQLException("DB에러");
				}
			}catch (Exception ex) {
				System.out.println("delBoard() 오류 : " + ex);
			} finally {
				try {										
					if (pstmt != null) 
						pstmt.close();				
					if (conn != null) 
						conn.close();
				} catch (Exception ex) {
					throw new RuntimeException(ex.getMessage());
				}		
			}
		}
		
		
		
}
