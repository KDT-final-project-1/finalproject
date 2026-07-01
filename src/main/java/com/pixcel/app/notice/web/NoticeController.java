package com.pixcel.app.notice.web;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pixcel.app.notice.service.NoticeRequestDTO;
import com.pixcel.app.notice.service.NoticeService;
import com.pixcel.app.notice.service.PostRequestDTO;
import com.pixcel.app.notice.service.PostSearchDTO;
import com.pixcel.app.notice.service.PostService;
import com.pixcel.app.user.security.CustomUserDetails;
import com.pixcel.app.web.AllProjectController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/notice") 
@RequiredArgsConstructor
@AllProjectController
public class NoticeController {
	private final NoticeService noticeService;
	private final PostService postService;
	
	@GetMapping("/BoardCreate")
	public String createBoardForm(
				@AuthenticationPrincipal CustomUserDetails userDetails
				,@PathVariable("projectId") String projectId,
				Model model) {
		
		model.addAttribute("projectId", projectId);
		model.addAttribute("noticeRequestDTO", new NoticeRequestDTO());
		
		return "notice/BoardCreate";
	}
	
	@PostMapping("/BoardCreate")
	public String createBoardForm(
			@ModelAttribute NoticeRequestDTO noticeRequestDto,
			@AuthenticationPrincipal CustomUserDetails userDetails,
			@PathVariable("projectId") String projectId
			) {
		noticeRequestDto.setCreatedBy(userDetails.getUsername());
		noticeService.createNoticeBoard(noticeRequestDto);
		return "redirect:/project/" + projectId + "/notice/BoardList";
	}
	
	@GetMapping("/BoardList")
	public String BoardList(
			@AuthenticationPrincipal CustomUserDetails userDetails,
			@PathVariable("projectId") String projectId,
			Model model
			) {
		List<NoticeRequestDTO> boardList = noticeService.getBoardList(projectId);
		model.addAttribute("boardList", boardList);
		
		return "notice/BoardList";
	}
	
	@GetMapping("/BoardDetail")
	public String BoardDetail(
			@PathVariable("projectId") String projectId,
			@RequestParam("boardId") String boardId,
			@ModelAttribute PostSearchDTO searchDTO,
			@PageableDefault(size = 10 , sort= "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
			Model model
			) {
		NoticeRequestDTO boardDetail = noticeService.getBoardDetail(boardId);
		Page<PostRequestDTO> postPage = postService.getPostListByBoardId(boardId, searchDTO, pageable);
		
		model.addAttribute("board",boardDetail);
		model.addAttribute("postPage", postPage);
		model.addAttribute("searchDTO", searchDTO);
		model.addAttribute("projectId", projectId);
		
		return "notice/boardDetail";
	}
	
	@GetMapping("/PostCreate")
	public String PostCreate(
			@PathVariable("projectId") String projectId,
			@RequestParam("boardId") String boardId,
			Model model
			) {
		model.addAttribute("boardId", boardId);
		model.addAttribute("projectId", projectId);
		model.addAttribute("postRequestDTO", new PostRequestDTO());
		return "notice/PostCreate";
	}
	
	@PostMapping("/PostCreate")
	public String PostCreate(@ModelAttribute PostRequestDTO postDTO,
	                       @RequestParam("boardId") String boardId,
	                       @PathVariable("projectId") String projectId,
	                       @AuthenticationPrincipal CustomUserDetails userDetails) {
		postDTO.setCreatedBy(userDetails.getUsername());
		
	    postService.createPost(postDTO);
	    
	    return "redirect:/project/" + projectId + "/notice/BoardDetail?boardId=" + boardId;
	}
	
	@GetMapping("/post_detail")
	public String PostDetail(
			@PathVariable("projectId") String projectId,
			@RequestParam("boardId") String boardId,
			@RequestParam("postId") String postId,
			Model model
			) {
		PostRequestDTO postDetail = postService.getPostDetail(postId);
		
		model.addAttribute("post", postDetail);
		model.addAttribute("projectId", projectId);
		model.addAttribute("boardId", boardId);
		
		return "notice/post_detail";
		
	}
	
	
	
	
	
}
