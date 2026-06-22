package com.pixcel.app.repository.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pixcel.app.repository.service.RepositoryService;
import com.pixcel.app.repository.service.RepositoryVO;

import lombok.RequiredArgsConstructor;

@Controller						// 화면 경로를 반환함
@RequiredArgsConstructor		// Service 객체 생성을 위한 생성자 주입 처리
@RequestMapping("/repository")	// repository의 기본 URL 주소를 설정함.
public class RepositoryController {
	private final RepositoryService repositoryService;
	
	// 1. 자료실 전체 조회 (요청주소 : localhost:8080/repository)
	@GetMapping
	public String getRepositoryList(Model model) {
		List<RepositoryVO> list = repositoryService.getRepositoryList();
		model.addAttribute("repositoryList", list);
		return "repository/list";
	}
	
	// 2. 자료실 등록한 사람의 자료 상세조회 (요청주소 : localhost:8080/repository/user?uploadUserId=유저ID)
	@GetMapping("/user")
	public String getRepositoryListByUserId(@RequestParam String uploadUserId, Model model) {
		List<RepositoryVO> list = repositoryService.getRepositoryListByUserId(uploadUserId);
		model.addAttribute("repositoryList", list);
		model.addAttribute("uploadUserId", uploadUserId);
		return "repository/list";
	}
	
	// 3. 자료실 상세조회 (요청주소 : localhost:8080/repository/detail?fileId=파일ID)
	@GetMapping("/detail")
	public String getRepositoryDetail(@RequestParam String fileId, Model model) {
		RepositoryVO detail = repositoryService.getRepositoryDetail(fileId);
		model.addAttribute("repository", detail);
		return "repository/detail";
	}
	
	// 4-1. 자료실 파일 등록 화면 이동페이지 (요청 주소 : localhost:8080/repository/new)
	@GetMapping("/new")
	public String createForm(Model model) {
		model.addAttribute("repository", new RepositoryVO());
		return "repository/form";
	}
	// 4-2. 자료실 파일 등록 처리 후 해당 유저의 목록으로 리다이렉트(요청 주소 : localhost:8080/repository/new)
	@PostMapping("/new")
	public String create(RepositoryVO repositoryVO) {
		repositoryService.registerRepository(repositoryVO);
		return "redirect:/repository?uploadUserId=" + repositoryVO.getUploadUserId();
	}
	
	// 5-1. 자료실 파일 수정 화면 이동페이지 (요청 주소 : localhost:8080/repository/edit?fileId=파일ID)
	@GetMapping("/edit")
	public String editForm(@RequestParam String fileId, Model model) {
		RepositoryVO detail = repositoryService.getRepositoryDetail(fileId);
		model.addAttribute("repository", detail);
		return "repository/form";
	}
	// 5-2. 자료실 파일 수정 처리 후 해당 유저의 목록으로 리다이렉트
	@PostMapping("/edit")
	public String edit(RepositoryVO repositoryVO) {
		repositoryService.modifyRepository(repositoryVO);
		return "redirect:/repository?uploadUserId=" + repositoryVO.getUploadUserId();
	}
}
