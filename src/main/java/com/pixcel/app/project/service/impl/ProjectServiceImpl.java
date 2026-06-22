package com.pixcel.app.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pixcel.app.project.mapper.ProjectMapper;
import com.pixcel.app.project.service.ProjectService;
import com.pixcel.app.project.service.ProjectVO;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectMapper projectMapper;
	
	@Override
	@Transactional	// 마스터 등록과 모듈 등록 중 하나라도 실패한다면 통째로 롤백되도록 함
	public int registerProject(ProjectVO projectVO) {
		
		int result = 0;
		
		// 1. PROJECT 테이블에 기본 프로젝트 정보 등록
		result += projectMapper.insertProject(projectVO);
		
		// 2. 화면에서 사용자가 선택한 모듈 코드 리스트가 존재할 때만 다중 등록 실행
		if(projectVO.getModuleCodes() != null && projectVO.getModuleCodes().size() > 0) {
			result += projectMapper.insertProjectModules(projectVO);
		}
		
		return result;
	}

}
