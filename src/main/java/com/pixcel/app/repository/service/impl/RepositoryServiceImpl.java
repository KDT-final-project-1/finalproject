package com.pixcel.app.repository.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pixcel.app.repository.mapper.RepositoryMapper;
import com.pixcel.app.repository.service.RepositoryService;
import com.pixcel.app.repository.service.RepositoryVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor	// lombok을 이용해 RepositoryMapper의 생성자 주입을 자동으로 처리
public class RepositoryServiceImpl implements RepositoryService{
	private final RepositoryMapper repositoryMapper;

	// 1. 자료실 전체 조회
	@Override
	public List<RepositoryVO> getRepositoryList() {
		return repositoryMapper.selectRepositoryList();
	}

	// 2. 자료실 등록한 사람의 자료 상세조회
	@Override
	public List<RepositoryVO> getRepositoryListByUserId(String uploadUserId) {
		return repositoryMapper.selectRepositoryListByUserId(uploadUserId);
	}

	// 3. 자료실 상세조회
	@Override
	public RepositoryVO getRepositoryDetail(String fileId) {
		return repositoryMapper.selectRepositoryDetail(fileId);
	}

	// 4. 자료실 파일 등록
	@Override
	public int registerRepository(RepositoryVO repositoryVO) {
		// 파일 업로드 로직이나 다중 비즈니스 처리가 필요하면 이곳에서 작성
		return repositoryMapper.insertRepository(repositoryVO);
	}

	// 5. 자료실 파일 수정
	@Override
	public int modifyRepository(RepositoryVO repositoryVO) {
		return repositoryMapper.updateRepository(repositoryVO);
	}
}
