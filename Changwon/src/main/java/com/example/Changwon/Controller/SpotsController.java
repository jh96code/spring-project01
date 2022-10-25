package com.example.Changwon.Controller;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.Changwon.DAO.SpotsDAO;
import com.example.Changwon.DTO.Spots;
import com.example.Changwon.DTO.score;
import com.example.Changwon.DTO.memberDTO;


@Controller
public class SpotsController {

	@Autowired
	private SpotsDAO dao;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${spots.imgdir}")
	String imgdir;
	
	@RequestMapping("/spotslists")
	public String listspots(Model m, HttpServletRequest request) {
		List<Spots> list;
		memberDTO member = new memberDTO();
		
		try {
			
			int PageNum = 1;
			final int limit = 5;

			if (request.getParameter("pageNum") != null)
				PageNum = Integer.parseInt(request.getParameter("pageNum"));

			System.out.println("spotslists에서 pageNum확인"+request.getParameter("pageNum"));
			String item = request.getParameter("item");
			String search = request.getParameter("spotsearch");

			System.out.println("PageNum controller넘어옴 확인" + PageNum);

			int searchcount = dao.getListCount(item, search);
			System.out.println("searchcount : " + searchcount);
			list = dao.getAll(PageNum, limit, item, search);

			int totalPage;

			if (searchcount % limit == 0) {
				totalPage = searchcount / limit;
				Math.floor(totalPage);
			} else {
				totalPage = searchcount / limit;
				Math.floor(totalPage);
				totalPage = totalPage + 1;
			}
			System.out.println("Controller totalPage확인 " + totalPage);
			request.setAttribute("pageNum", PageNum);
			request.setAttribute("total_page", totalPage);
			request.setAttribute("total_record", searchcount);

			m.addAttribute("spotslist", list);

			System.out.println("list성공적으로 받아옴 " + list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("관광지 목록 생성 과정에서 문제 발생!!");
			m.addAttribute("error", "관광지 목록이 정상적으로 처리되지 않았습니다!!");
		}
		return "Spots/spots";
	}

	// 관광지 상세페이지
	@GetMapping("/spot{aid}")
	public String getspot(@PathVariable int aid, Model m) throws Exception {
		try {
			System.out.println("넘어왔다.");
			Spots s = dao.getSpot(aid);
			m.addAttribute("Spot", s);
			System.out.println("modle 주소값: " + m);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.warn("관광지 상세보기를 가져오는 과정에서 문제 발생!!");
			m.addAttribute("error", "상세정보를 정상적으로 가져오지 못했습니다!!");
		}
		return "Spots/spot";
	}

	// 관광지 추가
	@GetMapping("/addspots")
	public String addspots() {
		System.out.println("addspots넘어옴");
		return "Spots/addspots";

	}

	// 관광지추가 과정
	@PostMapping("/processaddspot")
	public String processaddspot(@ModelAttribute Spots spots, Model m, @RequestParam("file") MultipartFile file) { // @RequestParam("file") MultipartFile file

		System.out.println("addprocess넘어옴");
		try {
			
			  System.out.println("start"); 
			  File dest = new File(imgdir+"/"+file.getOriginalFilename());
			 
			  System.out.println("파일경로 : " + dest);
			  
			  //파일 저장 
			  file.transferTo(dest); 
			  System.out.println("파일저장이 되었나?" + dest.getName());
			  
			  // News 객체에 파일 이름 저장 
			  System.out.print(spots.getName()); 
			  System.out.println("파일저장가지고 와져?" +  dest.getName());
			 
			spots.setImg(dest.getName());
			dao.addspots(spots);
			System.out.println("return");

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("관광지 추가 과정에서 문제 발생!!");
			m.addAttribute("error", "관광지가 정상적으로 등록되지 않았습니다!!");
		}
		return "redirect:spotslists";
	}

	// 관광지수정
	@GetMapping("/updatespotsView")
	public String updatespots(@RequestParam int aid, Model m) {
		System.out.println("updatespots 시작");
		try {
			Spots s = dao.getSpot(aid);
			m.addAttribute("updatespots", s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "Spots/updatespots";
	}

	// 관광지수정 처리
	@PostMapping("/updatespotsprocess")
	public String updateprocess(@ModelAttribute Spots updatespots, @RequestParam("file") MultipartFile file) {
		try {
			System.out.println("updatespots로 넘어왔다");
			System.out.println("Spots도 넘어왔어? " + updatespots.getAid());
			System.out.println("file도 넘어왔어?" + updatespots.getImg());	
	
			if(!file.getOriginalFilename().isEmpty()) {
			File dest = new File(imgdir+"/"+file.getOriginalFilename());
			file.transferTo(dest); 			
			updatespots.setImg(dest.getName()); }
			dao.updatespots(updatespots);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("게시판 수정 과정에서 문제 발생!!");
		}
		return "redirect:spotslists";
	}

	// 관광지삭제
	@GetMapping("/delspots")
	public String delspots(HttpServletRequest request, Model m) {
		System.out.println("delspots시작");
		int aid = Integer.parseInt(request.getParameter("aid"));
		System.out.println("aid확인 : " + aid);
		try {
			dao.delspots(aid);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.warn("관광지 삭제 과정에서 문제 발생!!");
			m.addAttribute("error", "관광지가 정상적으로 삭제되지 않았습니다!!");
		}
		return "redirect:spotslists";
	}
	
	@PostMapping("/score")
	public String score(@RequestParam int aid, @ModelAttribute score s, HttpServletRequest request) throws ServletException, SQLException {
		
		String name = null;			

		if(request.getParameter("name")!=null) {
			name = request.getParameter("name");
			s.setName(name);}
		else {
			return "redirect:spot?aid="+aid;
		}	
		dao.score(s);	
	
		return "redirect:spot"+aid;
	}

	@RequestMapping("/Questionview")
	public String Questionview(@ModelAttribute score s, HttpServletRequest request, Model m) {
		
		String item="";
		List<score> list;
		
		if(request.getParameter("name")!=null) 
			item = request.getParameter("name");
			
		
		
		list = dao.Questionview(item);
		m.addAttribute("Qlist", list);
		
		
		return "Spots/Question";
	}

}
