package com.pixcel.app.notice.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pixcel.app.notice.entity.PostEntity;
import com.pixcel.app.notice.mapper.PostMapper;
import com.pixcel.app.notice.repository.PostRepository;
import com.pixcel.app.notice.service.PostRequestDTO;
import com.pixcel.app.notice.service.PostSearchDTO;
import com.pixcel.app.notice.service.PostService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
	private final PostRepository postRepository;
	private final PostMapper postMapper;
	
	@Override
    public Page<PostRequestDTO> getPostListByBoardId(String boardId, PostSearchDTO searchDTO, Pageable pageable) {
        
        // 1. 파라미터 준비
        int start = (int) pageable.getOffset() + 1;
        int end = (int) pageable.getOffset() + pageable.getPageSize();

        // 2. MyBatis에게 검색 및 페이징 위임
        List<PostRequestDTO> list = postMapper.selectPostList(boardId, searchDTO, start, end);
        long totalCount = postMapper.countPostList(boardId, searchDTO);

        // 3. Page 객체 수동 생성
        return new PageImpl<>(list, pageable, totalCount);
    }
	
	@Override
    @Transactional
    public void createPost(PostRequestDTO dto) {
        
        String currentYymm = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMM"));
        String prefix = "POST_" + currentYymm + "_";
        // 2. Repository를 통해 가장 최근 ID를 조회하고, 다음 시퀀스를 생성
        String nextPostId = postRepository.findLatestPostIdByPrefix(prefix)
            .map(post -> {
                // "POST_2607_" 이후의 숫자(예: "0001")만 잘라내기
                String lastSeqStr = post.getPostId().substring(prefix.length());
                // 숫자로 변환 후 1 증가
                int nextSeq = Integer.parseInt(lastSeqStr) + 1;
                // 다시 4자리 문자열 포맷으로 변환하여 prefix와 결합
                return prefix + String.format("%04d", nextSeq);
            })
            // 조회된 ID가 없으면 (해당 월의 첫 게시글) "0001"로 시작
            .orElse(prefix + "0001");
            
        // 3. 생성된 ID를 엔티티에 세팅
        PostEntity post = PostEntity.builder()
                .postId(nextPostId)             // 동적으로 생성된 ID 할당
                .title(dto.getTitle())
                .content(dto.getContent())      // 주의: 이전 코드의 오타(dto.getTitle()) 수정 적용
                .createdBy(dto.getCreatedBy())
                .boardId(dto.getBoardId())
                .createdAt(LocalDateTime.now())
                .build();
                
        // 4. 저장
        postRepository.save(post);
    }
	
	@Override
	public PostRequestDTO getPostDetail(String postId) {
		PostEntity post = postRepository.findById(postId).orElseThrow(()-> new RuntimeException("게시글을 찾을 수 없습니다"));
		
		return PostRequestDTO.builder()
	            .postId(post.getPostId())
	            .title(post.getTitle())
	            .content(post.getContent())
	            .createdBy(post.getCreatedBy())
	            .createdAt(post.getCreatedAt())
	            .viewCount(post.getViewCount())
	            .userName(post.getUser() != null ? post.getUser().getUserName() : "알 수 없음")
	            .build();
	}
}
