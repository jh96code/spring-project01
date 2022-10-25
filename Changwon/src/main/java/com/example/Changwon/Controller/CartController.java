package com.example.Changwon.Controller;

import java.io.File;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.naming.ldap.Rdn;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Changwon.DAO.goodsDao;
import com.example.Changwon.DAO.memberDAO;
import com.example.Changwon.DTO.addressDto;
import com.example.Changwon.DTO.cartDto;
import com.example.Changwon.DTO.goodsDto;
import com.example.Changwon.DTO.memberDTO;
import com.example.Changwon.DTO.orderlistDto;

@Controller
public class CartController {
	
	@Autowired
	private goodsDao dao;
	@Autowired
	private memberDAO memberdao;
	
	// 굿즈 장바구니 페이지로 가기
	@GetMapping("goodscart")
	public String goodscart() {
		return "Cart/goodscart";
	}

	// 굿즈 장바구니에 담기
	@RequestMapping("goodsaddcart")
	public String addcart(HttpServletRequest request, RedirectAttributes ra, Model m) {
		System.out.println("컨트롤러 addcart " + request.getParameter("gName"));
		dao.addCart(request);
						
		return "Cart/goodscart";
	}
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 장바구니 삭제
	@RequestMapping("seldel")
	public String removecart(HttpServletRequest request, HttpSession session) {
		String seldel = request.getParameter("seldelId");
		System.out.println("컨트로러 리퀘스트 ==>" + seldel);
		dao.removeCart(request, session);

		return "Cart/goodscart";
	}

	// 장바구니 전체 삭제
	@RequestMapping("/cartdel")
	public String allremovecart(HttpSession session) {
		dao.allremove(session);
		System.out.println(session);
		return "redirect:/goodslist";
	}

	// 주문확인 페이지로 이동
	@RequestMapping("/orderinfo")
	public String orderinfo(@RequestParam String id, Model m) throws SQLException {
		
		try {
			// dao에서 getMember(회원정보 상세정보)함수를 실행해서 dto에 담는다.
			memberDTO dto = memberdao.getMember(id);
			if (memberdao.getMember(id) == null) {
				return "login/login";
			}
			// model값에 키값(member)에 '회원 상세정보'(dto)를 넣는다.
			m.addAttribute("member", dto);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.warn("주문확인 페이지 이동 오류 발생!!");
		}
		return "Goods/goodsorderinfo";		
	}

	// 굿즈 주문확인 페이지 ( 주문완료 db저장, 현 재고 수량 줄이기, 장바구니 전체 삭제)
	@GetMapping("goodsorderOK")
	public String goodsorderOK(@RequestParam String id,HttpServletRequest request, HttpSession session) throws Exception {
				
		
		//주문완료 상품 orderlistdto에 저장
		ArrayList<cartDto> cartList = (ArrayList<cartDto>)session.getAttribute("myCart");
		ArrayList<orderlistDto> orderlist = new ArrayList<orderlistDto>();
		
		
		for(int i =0;i<cartList.size(); i++) {
			cartDto dto = cartList.get(i);
			System.out.println("카트["+i+"번째]"+dto.getCartId()+dto.getCartName()+dto.getCartStock());
			orderlistDto ol = new orderlistDto();
			ol.setGetNum(dto.getCartStock()); //구매 수량 오더리스트에 저장
			ol.setBuygoods(dto.getCartName());//구매 상품 이름
			ol.setUserId(id);//구매 아이디 저장	
			
			orderlist.add(ol);
		}
		//세션에 있는 리스트를 오더리스트dto에 옮겨담아서 dao로 보내기
		dao.addorderlist(orderlist); 

		//판매상품 재고 수량 줄이기
		dao.updatestock(orderlist);
		
		//주문 완료 했으니까 장바구니 전체 삭제
		dao.allremove(session); 
		
		return "Cart/goodsorderOK";
	}

	// 주문 목록 불러오기
	@RequestMapping("myorderlist")
	public String getorderlist(@RequestParam String id,Model m) {
		List<orderlistDto> list = null;
		System.out.println("주문 아이디 : "+id);
		try {
			dao.getorder(id);
			list = dao.getorder(id);
			m.addAttribute("orderlist",list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("주문 목록 생성과정에서 문제 발생");
		}		
		for(int i =0;i<list.size(); i++) {
			orderlistDto dto = list.get(i);
			System.out.println("주문["+i+"번째]"+dto.getBuygoods()+dto.getOrderAt()+dto.getGetNum());	
		}		
		return "Cart/orderlist";
	}
	
	//배송 주소 변경 페이지 이동
	@RequestMapping("addresssel")
	public String addresssel(@RequestParam String id,Model m) {
		
		// 아이디로 회원정보 가져오기
		try {
			// dao에서 getMember(회원정보 상세정보)함수를 실행해서 dto에 담는다.
			memberDTO dto = memberdao.getMember(id);
			if (memberdao.getMember(id) == null) {
				return "login/login";
			}
			// model값에 키값(member)에 '회원 상세정보'(dto)를 넣는다.
			m.addAttribute("member", dto);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.warn("배송 정보 확인 오류 발생!!");
		}		
		
		//db에서 리스트 들고오기
		List<addressDto> list;
		try {
			list = dao.addressgetAll(id);
			m.addAttribute("addresslist", list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("주소 목록 생성 과정에서 문제 발생!!");			
		}
		
		return "Cart/addressform";
	}
	
	// 주소 추가
	@PostMapping("addressform")
	public String addressform(@ModelAttribute addressDto adrdto, Model m) {
		try {
			dao.addaddress(adrdto);
			System.out.println("주소 등록 완료(컨트롤러)");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(" 추가 과정에서 문제 발생!!");
			m.addAttribute("error", "정상적으로 등록되지 않았습니다!!");
		}
			String id = adrdto.getAddressId();
			return "redirect:/addresssel?id="+id;
	}
	
	// 주소 선택
	@RequestMapping("selectaddress")
	public String selectaddress(@RequestParam int adrn, Model m) throws SQLException {
		addressDto ad = dao.getaddress(adrn);
		m.addAttribute("adrselect",ad);
		
		System.out.println("선택 주소"+ad.getAddressAdr());
		
		
		return "Goods/goodsorderinfoad";
	}
	
}
