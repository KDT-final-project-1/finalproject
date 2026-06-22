package com.pixcel.app.project.mapper;

import com.pixcel.app.project.service.ProjectVO;

public interface ProjectMapper {
	// 1. 프로젝트 마스터 정보 등록
	public int insertProject(ProjectVO projectVO);
	
	// 2. 프로젝트별 선택 모듈 다중 등록
	public int insertProjectModules(ProjectVO projectVO);
}
