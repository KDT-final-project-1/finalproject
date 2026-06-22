package com.pixcel.app.repository.service;

import java.util.List;

public interface RepositoryService {
	// 1. 자료실 전체 조회
	public List<RepositoryVO> getRepositoryList();
	// 2. 자료실 등록한 사람의 자료 상세조회
	public List<RepositoryVO> getRepositoryListByUserId(String uploadUserId);
	// 3. 자료실 상세조회
	public RepositoryVO getRepositoryDetail(String fileId);
	// 4. 자료실 파일 등록
	public int registerRepository(RepositoryVO repositoryVO);
	// 5. 자료실 파일 수정
	public int modifyRepository(RepositoryVO repositoryVO);
}
