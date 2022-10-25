package com.example.Changwon.Controller;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.Changwon.DAO.BoardDao;
import com.example.Changwon.DTO.BoardDto;
import com.example.Changwon.DTO.board_replyDto;

@Controller
public class BoardController {

	@Autowired
	private BoardDao dao;
	
	//프로퍼티파일로 부터 저장 경로 참조
	@Value("${board.imgdir}")
	String fdir;
	
	//게시판 전체목록 불러오기
	@RequestMapping("/boardlist")
	public String BoardList(HttpServletRequest request, Model model) {
		List<BoardDto> dto;
		try {
			int pageNum = 1; // 게시판 페이지 갯수
			final int limit = 5; // 페이지 내에 최대 게시판 수

			// 페이지 번호 값을 받아온다??(BoardList.jsp에서 jstl태그 이용)
			if (request.getParameter("pageNum") != null)
				pageNum = Integer.parseInt(request.getParameter("pageNum"));

			String items = request.getParameter("items"); // 게시판 검색 타이틀(form태그의 값을 리퀘스트로 받아온다)
			String text = request.getParameter("text"); // 게시판 검색 내용(form태그의 값을 리퀘스트로 받아온다)

			int total_record = dao.getListCount(items, text);// 검색된 게시판의 갯수(레코드=행)
			dto = dao.getAll(pageNum, limit, items, text);

			int total_page; // 총 페이지수

			if (total_record % limit == 0) { // 총 게시판 수를 limit으로 나누어 나머지를 계산(나머지값이 0이라면 페이지 수 별도로 추가 안함)
				total_page = total_record / limit;
				Math.floor(total_page);
			} else { // 총 게시판 수를 limit으로 나누어 나머지를 계산(나머지값이 0이 아니라면 페이지수 + 1)
				total_page = total_record / limit;
				Math.floor(total_page);
				total_page = total_page + 1;
			}
			System.out.println("bord list totalpage확인 : " + total_page);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("total_page", total_page);
			request.setAttribute("total_record", total_record);
			// model에 키값(boardlist)에 '게시판 전체'(List<dto>)를 넣는다.
			model.addAttribute("boardlist", dto);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", "뉴스 목록이 정상적으로 처리되지 않았습니다!!");
		}
		return "Board/BoardList";
	}

	// 클릭한 게시판 상세정보(+댓글) 보여주는 매핑
	@GetMapping("/boardview")
	public String getBoard(@RequestParam int num, Model model) {
		List<board_replyDto> replyDto;
		try {
			// dao에서 getBoard(게시판 상세정보)함수를 실행해서 dto에 담는다.
			BoardDto dto = dao.getBoard(num);
			// model값에 키값(board)에 '게시판 상세정보'(dto)를 넣는다.
			model.addAttribute("board", dto);
			//dao에서 getReply(댓글 목록)함수를 실행해서 List(replyDto)에 담는다.
			replyDto = dao.getReply(num);
			// model값에 키값(replylist)에 '댓글 목록'(replyDto)를 넣는다.
			model.addAttribute("replylist", replyDto);
			// 조회수 증가시키는 함수 실행
			dao.updateHit(num);
		} catch (SQLException e) {
			e.printStackTrace();
			model.addAttribute("error", "게시판을 정상적으로 가져오지 못했습니다!!");
		}
		return "Board/BoardView";
		}
	
	// 게시판 추가페이지로 가기
	@GetMapping("/boardwrite")
	public String BoardWrite() {
		return "Board/BoardWrite";
	}

	// 게시판 추가하기
	@PostMapping("/boardwriteProcess")
	public String BoardWriteProcess(@ModelAttribute BoardDto dto, @RequestParam("file") MultipartFile file) {
		try {
			if(!file.isEmpty()){
				//저장 파일 객체 생성
				File dest = new File(fdir+"/"+file.getOriginalFilename());
				//파일 저장
				file.transferTo(dest);
				//객체에 파일 이름 저장
				dto.setImg(dest.getName());
			}
			dao.addBoard(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/boardlist";
	}

	// 게시판 수정 페이지로 가기
	@GetMapping("/boardupdate")
	public String BoardUpdate(@RequestParam int num, Model model) {
		try {
			BoardDto dto = dao.getBoard(num);
			model.addAttribute("board", dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Board/BoardUpdate";
	}

	// 게시판 수정하기
	@PostMapping("/boardupdateProcess")
	public String BoardUpdateProcess(@ModelAttribute BoardDto dto, @RequestParam("file") MultipartFile file) {
		try {
			if(!file.isEmpty()){
				//저장 파일 객체 생성
				File dest = new File(fdir+"/"+file.getOriginalFilename());
				//파일 저장
				file.transferTo(dest);
				//객체에 파일 이름 저장
				dto.setImg(dest.getName());
			}
			dao.updateBoard(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/boardlist";
	}

	// 게시판 삭제하기
	@RequestMapping("/boarddelete")
	public String BoardDelete(@RequestParam int num) {
		try {
			dao.delBoard(num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/boardlist";
	}
	//게시판 댓글 추가하기
	@PostMapping("/board_reply_write")
	public String BoardReplyWrite(@ModelAttribute board_replyDto dto, HttpServletRequest request, Model m) {
		String board_num = request.getParameter("board_num");
		String member_id = request.getParameter("member_id");
		System.out.println("보드넘버는 : " + board_num);
		try {
			dao.addBoardReply(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/boardview?num="+board_num;
		}
		
	//게시판 댓글 삭제하기
	@GetMapping("/board_reply_delete")
	public String BoardReplyDelete(HttpServletRequest request) {
		String board_num = request.getParameter("board_num");
		int reply_num = Integer.parseInt(request.getParameter("reply_num"));
		try {
			dao.delBoardReply(reply_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/boardview?num="+board_num;
	}
}
