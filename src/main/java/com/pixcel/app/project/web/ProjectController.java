package com.pixcel.app.project.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pixcel.app.project.service.ProjectService;
import com.pixcel.app.project.service.ProjectVO;

@Controller
@RequestMapping("/project")	// 비동기(JSON) 데이터 통신을 위한 컨트롤러 선언
// 프로젝트 관련 기본 URL 경로 설정 (/API는 안붙였는데 나중에 오류 생기면 추가하기)
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	/*
	 * 신규 프로젝트 및 선택 모듈 등록 API 
	 * @param projectVO 화면(vue)에서 JSON 형태로 넘어오는 프로젝트 데이터
	 * @return 등록 성공한 행(row)의 총 개수
	 */
	
	@GetMapping("/register")
	public String registerForm() {
		// src/main/resources/templates/project/register.html 파일을 찾아감
		return "project/project";	// 팀 폴더 구조에 맞게 경로 수정
	}
	
	// 프로젝트 등록 실행 (데이터 저장 처리)
	@PostMapping("/register")
	@ResponseBody
	public int registerProject(ProjectVO projectVO) {
		return projectService.registerProject(projectVO);
	}
}
