package com.example.Changwon.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
 
import com.example.Changwon.DTO.addressDto;
import com.example.Changwon.DTO.cartDto;
import com.example.Changwon.DTO.goodsDto;
import com.example.Changwon.DTO.orderlistDto;


public class goodsDao {
	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	final String JDBC_URL = "jdbc:mysql://localhost:3306/travelDB";
	
	//db 연결
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
	
	// 목록 불러오기
	public List<goodsDto> getAll() throws Exception{
		Connection conn = open();
		List<goodsDto> goodslist = new ArrayList<>();
		
		String sql = "select * from goodslist";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		try {
			while(rs.next()) {
				goodsDto g = new goodsDto();
				g.setgId(rs.getInt("gId"));
				g.setgName(rs.getString("gName"));
				g.setgPrice(rs.getInt("gPrice"));
				g.setgImg(rs.getString("gImg"));
				g.setgStock(rs.getInt("gStock"));
				goodslist.add(g);
			}return goodslist;
		}catch (Exception ex) {
			System.out.println("getAll() 에러 :"+ex);
		} finally {
			try {
				if (rs !=null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		return null;
	}
	//상품 상세보기
	public goodsDto getGoods(int gId) throws SQLException{
		Connection conn = open();
		
		goodsDto g = new goodsDto();
		String sql = "select gId, gName, gPrice, gDescription, gCategory, gStock, gImg from goodslist where gId=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, gId);
		ResultSet rs = pstmt.executeQuery();
		System.out.println("dao에 :"+gId);
		rs.next();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				g = new goodsDto();
				g.setgId(rs.getInt("gId"));
				g.setgName(rs.getString("gName"));
				g.setgPrice(rs.getInt("gPrice"));
				g.setgDescription(rs.getString("gDescription"));
				g.setgCategory(rs.getString("gCategory"));
				g.setgStock(rs.getInt("gStock"));
				g.setgImg(rs.getString("gImg"));
				pstmt.executeQuery();
			}
			return g;
		}catch (Exception ex) {
			System.out.println("getGoods() 에러 :"+ex);
		} finally {
			try {
				if (rs !=null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		return null;
	}
	
	// 상품 추가
	public void addGoods(goodsDto g) throws Exception{
		Connection conn = open();
		System.out.println("dao 상품추가");
		String sql = "insert into goodslist(gName, gPrice, gDescription, gCategory, gStock,gImg) values(?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
			
		try {
			pstmt.setString(1,g.getgName());
			pstmt.setInt(2,g.getgPrice());
			pstmt.setString(3,g.getgDescription());
			pstmt.setString(4,g.getgCategory());
			pstmt.setLong(5, g.getgStock());
			pstmt.setString(6, g.getgImg());
			pstmt.executeUpdate();
		}catch (Exception ex) {
			System.out.println("add 굿즈() 에러 : " + ex);
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
	//상품 삭제
	public void delGoods(int gId) throws SQLException {
		Connection conn = open();
		
		String sql = "delete from goodslist where gId=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gId);
			pstmt.executeUpdate();
		}catch (Exception ex) {
			System.out.println("delNews() 에러 :"+ex);
			throw new RuntimeException(ex.getMessage());
		}
	}
	
	
	//-----------------------------cart---------------
	//카트 상품 추가
	public void addCart(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		//새로운 카트 생성
		ArrayList<cartDto> prelist = (ArrayList<cartDto>) session.getAttribute("myCart");
		System.out.println("장바구니 널이면 if실행 : "+prelist);
		
		if(prelist == null) {
			System.out.println("dao if문 안");
			ArrayList<cartDto> al = new ArrayList<cartDto>();
			session.setAttribute("myCart", al);
		}
		
		
		
		ArrayList<cartDto> al = (ArrayList<cartDto>)session.getAttribute("myCart");
		String z = request.getParameter("gId"); // 상품 아이디 가져오기
		int gid = Integer.parseInt(z);// 상품 아이디 int로 변환
		
		//장바구니 수량 
		int cnt = 0; //장바구니 수량
		cartDto goodsQnt = new cartDto(); //카트리스트 dto 재고수량 set할려고
		
		//장바구니에 담긴 상품 아이디
		for(int i =0;i<al.size();i++) { //al은 세션에서 가저온 카트
			goodsQnt = al.get(i);
			System.out.println("장바구니 수량" +i+"번째"+goodsQnt.getCartId());
			
			if(goodsQnt.getCartId() == gid) {
				cnt++;
				int orderQuantity = goodsQnt.getCartStock()+1;
				goodsQnt.setCartStock(orderQuantity);
				
			}else {
				
			}
		}
		if (cnt == 0) {
			
			cartDto cl = new cartDto();
			
			String name = request.getParameter("gName"); // 상품이름 가져오기
			String x = request.getParameter("gPrice");//상품 가격 가져옥; 
			int price = Integer.parseInt(x); // 정수로 변환
			String y = request.getParameter("gId"); // 상품 아이디 가져오기
			int ngid = Integer.parseInt(y);
			
			cl.setCartName(name);
			cl.setCartPrice(price);
			cl.setCartId(ngid);
			cl.setCartStock(1);
			
			ArrayList<cartDto> newlist = (ArrayList<cartDto>)session.getAttribute("myCart");
			newlist.add(cl);
			
			System.out.println("새롭게 데이터 넣기t "+al);
		}
		
//		Enumeration enumSession = session.getAttributeNames();
//		while(enumSession.hasMoreElements()) {
//			String getse = enumSession.nextElement()+"";
//			System.out.println("세션 확인==>session : "+getse+" : "+(String)session.getAttribute(getse));
//		}
		
	}
	
	//카트 상품 삭제하기
	public void removeCart(HttpServletRequest request, HttpSession session) {
	      String sd = request.getParameter("seldelId");
	      int seldel = Integer.parseInt(sd);
	      
	      ArrayList<cartDto> cartList = (ArrayList<cartDto>)session.getAttribute("myCart");
		      
		      
	      System.out.println("삭제 버튼이 가져온 제품 번호(in dao) : "+seldel);
		      
	      cartDto goodsQnt = new cartDto();
	      for(int i = 0;i<cartList.size();i++) {
	         goodsQnt = cartList.get(i);
	         if (goodsQnt.getCartId() == seldel) {
	            cartList.remove(goodsQnt);
	         }
	      }			
	}
	// 장바구니 전체 삭제
	public void allremove(HttpSession session) {
		session.removeAttribute("myCart");
	}
	
	// 주문 완료 후 DB에 저장
	public void addorderlist(ArrayList<orderlistDto> orderlist) throws Exception{
		ArrayList<orderlistDto> OL = orderlist;
		
		Connection conn = open();
		String sql = "insert into orderlist(getNum, userId, buygoods,orderAt) values(?,?,?,CURRENT_TIMESTAMP())";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		
		try {
			for(int i =0;i<OL.size();i++) {
				orderlistDto ol = OL.get(i);
				pstmt.setInt(1,ol.getGetNum()); //구매 재고 수량
				pstmt.setString(2,ol.getUserId());
				pstmt.setString(3,ol.getBuygoods());
				pstmt.executeUpdate();			
				System.out.println(i+"번째 db 업뎃완료");			
			}
		}
		catch (Exception ex) {
				System.out.println("add 오더리스트 에러 : " + ex);
			} 	
		finally {
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
	
	
	// 주문 완료 후 재고 수량 줄이기
	public void updatestock(ArrayList<orderlistDto> orderlist) {
			
		ArrayList<orderlistDto> OL = orderlist;
		
		Connection conn = open();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			
			for(int i =0;i<OL.size();i++) {
				orderlistDto ol = OL.get(i);
				String sql1 = "select gStock from goodslist where gName = ?";
				pstmt = conn.prepareStatement(sql1);
				pstmt.setString(1, ol.getBuygoods());
				rs= pstmt.executeQuery();
				int prestock = 0; // 원래 재고 
				int sellstock = ol.getGetNum(); // 판매 재고
				
				if(rs.next())
					prestock = rs.getInt("gStock"); 
				
				int nowstock = prestock-sellstock;
				
				String sql2 = "update goodslist set gStock = ? where gName = ? ";
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, nowstock);
				pstmt.setString(2, ol.getBuygoods());
				pstmt.executeUpdate(); 
			}	
		}catch (Exception ex) {
			System.out.println("updateStock() 오류 : " + ex);
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

	//주문 상품 조회
	public List<orderlistDto> getorder(String id) throws Exception{
		Connection conn = open();
		List<orderlistDto> orderlist = new ArrayList<>();
		
		String sql = "select * from orderlist where userId= ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		
		
		ResultSet rs = pstmt.executeQuery();
		
		try {
			while(rs.next()) {
				orderlistDto ol = new orderlistDto();
				ol.setOrderNum(rs.getInt("orderNum"));
				ol.setOrderAt(rs.getDate("orderAt"));
				ol.setGetNum(rs.getInt("getNum"));
				ol.setUserId(rs.getString("userId"));
				ol.setBuygoods(rs.getString("buygoods"));
				orderlist.add(ol);
			}return orderlist;
		}catch (Exception ex) {
			System.out.println("getorder() 에러 :"+ex);
		} finally {
			try {
				if (rs !=null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		return null;
	}
	
	// 주소록 추가
	public void addaddress(addressDto adrdto) throws SQLException{
		Connection conn = open();
		System.out.println("dao 주소추가");
		String sql = "insert into address(addressId,addressName,addressPhone,addressAdr) values(?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
			
		try {
			pstmt.setString(1,adrdto.getAddressId());
			pstmt.setString(2,adrdto.getAddressName());
			pstmt.setString(3,adrdto.getAddressPhone());
			pstmt.setString(4,adrdto.getAddressAdr());
			pstmt.executeUpdate();
		}catch (Exception ex) {
			System.out.println("add 주소() 에러 : " + ex);
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
	// 주소 목록 불러오기
	public List<addressDto> addressgetAll(String id) throws Exception{
		System.out.println("굿즈 DAO 주소록 생성 아이디 "+id);
		Connection conn = open();
		List<addressDto> addresslist = new ArrayList<>();
			
		String sql = "select * from address where addressId= ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();
		
		try {
			while(rs.next()) {
				addressDto a = new addressDto();
				a.setAddressNum(rs.getInt("addressNum"));
				a.setAddressId(rs.getString("addressId"));
				a.setAddressName(rs.getString("addressName"));
				a.setAddressPhone(rs.getString("addressPhone"));
				a.setAddressAdr(rs.getString("addressAdr"));
				
				addresslist.add(a);
			}return addresslist;
		}catch (Exception ex) {
			System.out.println("addressgetAll() 에러 :"+ex);
		} finally {
			try {
				if (rs !=null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		return null;
	}
	// 선택한 주소 불러오기
	public addressDto getaddress(int adrn) throws SQLException{
		Connection conn = open();
		addressDto ad = new addressDto();
		String sql = "select * from address where addressNum=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, adrn);
		ResultSet rs = pstmt.executeQuery();
		System.out.println("dao에 :"+adrn);
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, adrn);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ad = new addressDto();
				ad.setAddressNum(rs.getInt("addressNum"));
				ad.setAddressId(rs.getString("addressId"));
				ad.setAddressName(rs.getString("addressName"));
				ad.setAddressPhone(rs.getString("addressPhone"));
				ad.setAddressAdr(rs.getString("addressAdr"));
				pstmt.executeQuery();
				System.out.println("주소가 담겻는가??");
			}
			return ad;
			
		}catch (Exception ex) {
			System.out.println("getadr() 에러 :"+ex);
		} finally {
			try {
				if (rs !=null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		return null;
	}
}
