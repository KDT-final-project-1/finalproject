package com.pixcel.app.notice.mapper;

import java.util.List;

import com.pixcel.app.notice.service.PostRequestDTO;
import com.pixcel.app.notice.service.PostSearchDTO;

public interface PostMapper {
	List<PostRequestDTO> selectPostList(String boardId, PostSearchDTO searchDTO, int start, int end);
    long countPostList(String boardId, PostSearchDTO searchDTO);
}
