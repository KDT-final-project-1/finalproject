package com.pixcel.app.notice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {
	Page<PostRequestDTO> getPostListByBoardId(String boardId, PostSearchDTO searchDTO, Pageable pageable);
	public void createPost(PostRequestDTO dto);
}
