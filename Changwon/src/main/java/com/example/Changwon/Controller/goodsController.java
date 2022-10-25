package com.example.Changwon.Controller;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.example.Changwon.DAO.goodsDao;
import com.example.Changwon.DTO.goodsDto;

@Controller
public class goodsController {
	
	@Autowired
	private goodsDao dao;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//프로퍼티파일로 부터 저장 경로 참조
	@Value("${goods.imgdir}")
	String fdir;
	
	// 굿즈 컨트롤러들
		// 굿즈 전체 리스트
		@GetMapping("/goodslist")
		public String listgoods(Model m) {
			List<goodsDto> list;
			try {
				list = dao.getAll();
				m.addAttribute("goodslist", list);
			} catch (Exception e) {
				e.printStackTrace();
				logger.warn("굿즈 목록 생성 과정에서 문제 발생!!");
				m.addAttribute("error", "굿즈 목록이 정상적으로 처리되지 않았습니다!!");
			}
			return "Goods/goodslist";
		}

		// 굿즈 상세페이지
		@GetMapping("/goods{gId}")
		public String getGoods(@PathVariable int gId, Model m) {
			try {
				goodsDto g = dao.getGoods(gId);
				System.out.println("컨트롤러에 : " + gId);
				m.addAttribute("goods", g);
			} catch (SQLException e) {
				e.printStackTrace();
				logger.warn("굿즈 상세보기 가져오는 과정에서 문제 발생!!");
				m.addAttribute("error", "상세보기 과정에서 문제 발생! ");
			}
			return "Goods/goodsview";
		}

		// 굿즈 추가페이지로 이동
		@GetMapping("/goodsaddform")
		public String addform(Model m) {
			List<goodsDto> list;
			try {
				list = dao.getAll();
				m.addAttribute("goodslist", list);
			} catch (Exception e) {
				e.printStackTrace();
				logger.warn("굿즈 목록 생성 과정에서 문제 발생!!");
				m.addAttribute("error", "굿즈 목록이 정상적으로 처리되지 않았습니다!!");
			}
			return "Goods/goodsaddform";
		}

		// 굿즈 추가하기
		@PostMapping("/goodsadd")
		public String addgoods(@ModelAttribute goodsDto goods, Model m, @RequestParam("file") MultipartFile file) {
			try {
				System.out.println("add컨트롤러");
				// 저장 파일 객체 생성
				File dest = new File(fdir+"/"+file.getOriginalFilename()); 
				
				// 파일 저장
				file.transferTo(dest);
				 //객체에 파일 이름 저장
				goods.setgImg(dest.getName());
					
				dao.addGoods(goods);
				System.out.println("add컨트롤러 dao 추가 후");
			} catch (Exception e) {
				e.printStackTrace();
				logger.info(" 추가 과정에서 문제 발생!!");
				m.addAttribute("error", "정상적으로 등록되지 않았습니다!!");
			}
			return "redirect:/goodslist";
		}

		// 굿즈 삭제하기
		@GetMapping("/goodsdelete{gId}")
		public String deletegoods(@PathVariable int gId, Model m) {
			try {
				dao.delGoods(gId);
			} catch (SQLException e) {
				e.printStackTrace();
				logger.warn("뉴스 삭제 과정에서 문제 발생!");
				m.addAttribute("error", "뉴스 삭제가 정상적으로 처리되지 않았습니다.");
			}
			return "redirect:/goodslist";
		}



}
