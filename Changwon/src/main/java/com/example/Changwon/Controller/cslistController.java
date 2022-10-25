package com.example.Changwon.Controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Changwon.DAO.cslistDao;
import com.example.Changwon.DTO.cslistDto;

@Controller
public class cslistController {
	
	@Autowired
	private cslistDao dao;
	
	//문의목록 페이지로 가기 + 문의목록들 보여주기
	@GetMapping("/cslist")
	public String cslist(@RequestParam String id, HttpServletRequest request, Model model) throws SQLException {
		//세션에서 admincheck(관리자) 여부 값을 들고온다.
		boolean admincheck = (boolean)request.getSession().getAttribute("admincheck");
		if(admincheck == true) {//관리자 아이디면
			List<cslistDto> dto = dao.getAllCSlist();
			model.addAttribute("cslist", dto);
			return "CS/admincslist";
		} else {//관리자 아이디가 아니라면
			List<cslistDto> dto = dao.getCSlist(id);
			model.addAttribute("cslist", dto);
			return "CS/cslist";
		}
	}
	
	//문의 삭제하기
	@RequestMapping("/deletecs")
	public String deletecs(HttpServletRequest request) {
		int num = Integer.parseInt(request.getParameter("num"));
		String id = request.getParameter("id");
		dao.delCS(num);
		return "redirect:/cslist?id="+id;
	}
		
	//문의하기 페이지로 가기
	@GetMapping("/cssend")
	public String cssend() {
		return "CS/cssend";
	}
	
	//문의보내기
	@PostMapping("/cssendProcess")
	public String cssendProcess(HttpServletRequest request, @ModelAttribute cslistDto dto) throws Exception {
		String id = request.getParameter("member_id");
		dao.sendCS(dto);
		return "redirect:/cslist?id="+id;
	}
	
	//문의 답변달기
	@PostMapping("/csreply")
	public String replyCS(HttpServletRequest request, @ModelAttribute cslistDto dto, Model m) throws Exception {
		String id = request.getParameter("reply_member_id");
		int num = Integer.parseInt(request.getParameter("num"));
		dao.replyCS(dto);
		
		return "redirect:/cslist?id="+id;
	}
	
}
