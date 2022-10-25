package com.example.Changwon.Controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Changwon.DAO.memberDAO;
import com.example.Changwon.DTO.memberDTO;

@Controller
public class MemberController {

	@Autowired
	private memberDAO dao;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	// 회원 관련 컨트롤러들
	// 로그인 페이지로 가기
	@GetMapping("/login")
	public String login(Model m) {
		return "login/login";
	}

	// 회원가입 페이지로 가기
	@GetMapping("/addMember")
	public String addMemger() {
		return "login/addMember";
	}

	// 회원가입 회원 데이터 db삽입
	@PostMapping("/processAddMember")
	public String ProcessAddMember(memberDTO mamberdto, Model m) {
		try {
			dao.addmember(mamberdto);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("회원 가입 과정에서 문제 발생!!");
			m.addAttribute("error", "회원가입이 등록되지 않았습니다!!");
		}
		return "login/login";
	}

	// 비밀번호,아이디 같은지확인
	@PostMapping("/logincheck")
	public String logincheck(HttpServletRequest request, Model m) {
		String loginid = request.getParameter("id");
		String loginpassword = request.getParameter("password");
		memberDTO member = new memberDTO();

		HttpSession s = request.getSession();

		member = dao.loginCheck(loginid, loginpassword);

		if (member != null) {
			s.setAttribute("loginid", member.getId());
			s.setAttribute("loginpassowrd", member.getPassword());
			s.setAttribute("admincheck", member.getAdmincheck());
			return "Main";
		}

		return "login/login";
	}

	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession s) {
		s.invalidate();
		return "Main";
	}

	// 로그인 마이페이지로 가기
	@GetMapping("/mypage")
	public String mypage(@RequestParam String id, Model m) {
		try {
			// dao에서 getMember(회원정보 상세정보)함수를 실행해서 dto에 담는다.
			memberDTO dto = dao.getMember(id);
			// model값에 키값(member)에 '회원 상세정보'(dto)를 넣는다.
			m.addAttribute("member", dto);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.warn("회원 수정페이지에서 정보 들고오는 과정에소 오류 발생!!");
		}
		return "login/mypage";
	}

	// 회원 정보수정 페이지로 가기
	@GetMapping("/updatemember")
	public String UpdateMember(@RequestParam String id, Model m) {
		try {
			// dao에서 getMember(회원정보 상세정보)함수를 실행해서 dto에 담는다.
			memberDTO dto = dao.getMember(id);
			// model값에 키값(member)에 '회원 상세정보'(dto)를 넣는다.
			m.addAttribute("member", dto);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.warn("회원 수정페이지에서 정보 들고오는 과정에소 오류 발생!!");
		}
		return "login/UpdateMember";
	}

	// 회원 정보수정 완료
	@PostMapping("/processUpdatemember")
	public String ProcessUpdateMember(memberDTO memberdto) {
		try {
			dao.updateMember(memberdto);
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("회원정보 수정하는 과정에서 문제 발생!!");
		}
		return "Main";
	}

	// 회원 탈퇴하기
	@PostMapping("/deletemember")
	public String DeleteMember(@RequestParam String id, HttpSession s) {
		try {
			System.out.println("탈퇴 id값  : " + id);
			dao.delMember(id);
			s.invalidate();
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("회원탈퇴하는 과정에서 문제 발생!!");
		}
		return "Main";
	}

	// 아이디찾기 페이지로 가기
	@GetMapping("/findId")
	public String FindId() {
		return "login/findId";
	}

	// 아이디 찾기 결과
	@PostMapping("/processfindId")
	public String processfindID(@RequestParam String name, Model m) throws SQLException {
		System.out.println("아이디찾기 - 네임: " + name);
		// dao의 getId함수를 불러와서 (파라미터 값은 form으로 받은 name="name"값) dto에 담는다.
		memberDTO dto = dao.getId(name);
		// 모델에 키값(member)에 밸류값(dto)를 넣는다
		m.addAttribute("member", dto);

		if (dto != null) { // dto에 값이 잘 들어갔다면 성공페이지 리턴
			return "login/findId_success";
		} else { // dto가 null값이면 실패 페이지 리턴
			return "login/findId_fail";
		}
	}

	// 비밀번호 찾기 페이지로 가기
	@GetMapping("/findPw")
	public String FindPw() {
		return "login/findPw";
	}

	// 비밀번호 찾기 결과
	@PostMapping("/processfindPw")
	public String processfindPw(@RequestParam String id, Model m) throws SQLException {
		System.out.println("비밀번호찾기 - 아이디: " + id);
		// dao의 getPw함수를 불러와서 (파라미터 값은 form으로 받은 name="id"값) dto에 담는다.
		memberDTO dto = dao.getPw(id);
		// 모델에 키값(member)에 밸류값(dto)를 넣는다
		m.addAttribute("member", dto);

		if (dto != null) { // dto에 값이 잘 들어갔다면 성공페이지 리턴
			return "login/findPw_success";
		} else { // dto가 null값이면 실패 페이지 리턴
			return "login/findPw_fail";
		}
	}

	// 아이디 중복확인 페이지로 가기
	@GetMapping("/idCheck")
	public String idCheck() {
		return "login/id_check";
	}

	// 아이디 중복확인 결과페이지
	@PostMapping("/processid_check")
	public String processIdCheck(@RequestParam String id, Model m) throws SQLException {
		String memberId = dao.idCheck(id);
		System.out.println("멤버아이디 : " + memberId);
		if (memberId == null) {
			m.addAttribute("msg", "사용가능한 아이디입니다.");
		} else {
			m.addAttribute("msg", "사용하고 있는 아이디입니다.");
		}
		return "login/resultId_check";
	}
}
