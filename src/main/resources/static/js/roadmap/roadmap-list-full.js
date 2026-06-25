document.addEventListener('DOMContentLoaded', function() {
    
    // 1. 아코디언 토글 및 상세 페이지 이동 (이벤트 위임 방식)
    const roadmapListArea = document.querySelector('.roadmap-list-area');
    
    if (roadmapListArea) {
        roadmapListArea.addEventListener('click', function(e) {
            
            // [로드맵] 영역 클릭 시
            const roadmapHeader = e.target.closest('.clickable-roadmap');
            if (roadmapHeader) {
                const roadmapItem = roadmapHeader.closest('.roadmap-item');
                const body = roadmapItem.querySelector('.roadmap-body');
                
                // 접기/펴기 로직
                roadmapHeader.classList.toggle('active');
                if (body.style.display === 'none') {
                    body.style.display = 'block';
                } else {
                    body.style.display = 'none';
                }

                // 💡 [수정 포인트] 로드맵 상세 페이지 이동
                const versionId = roadmapItem.getAttribute('data-roadmap-id');
                console.log('로드맵 상세보기 이동:', versionId);
                // location.href = '/roadmap/detail?versionId=' + versionId;
                return;
            }

            // [마일스톤] 영역 클릭 시
            const milestoneHeader = e.target.closest('.clickable-milestone');
            if (milestoneHeader) {
                // 💡 [수정 포인트] 마일스톤 상세 페이지 이동
                const milestoneItem = milestoneHeader.closest('.milestone-item');
                const milestoneId = milestoneItem.getAttribute('data-milestone-id');
                console.log('마일스톤 상세보기 이동:', milestoneId);
                // location.href = '/milestones/detail?milestoneId=' + milestoneId;
                return;
            }

            // [일감] 영역 클릭 시
            const issueItem = e.target.closest('.clickable-issue');
            if (issueItem) {
                // 💡 [수정 포인트] 일감 상세 페이지 이동
                const issueId = issueItem.getAttribute('data-issue-id');
                console.log('일감 상세보기 이동:', issueId);
                // location.href = '/issues/detail?issueId=' + issueId;
                return;
            }
        });
    }

    // 2. JS 기반 실시간 필터링 기능
    const searchInput = document.getElementById('searchKeyword');
    const btnToggleCompleted = document.getElementById('btnToggleCompleted');
    let isCompletedHidden = false;

    // 필터 적용 함수
    function applyFilters() {
        const keyword = searchInput.value.toLowerCase().trim();
        const roadmapItems = document.querySelectorAll('.roadmap-item');

        roadmapItems.forEach(item => {
            let isVisible = true;
            
            // 텍스트 검색 조건 (로드맵 제목 또는 마일스톤 제목에 포함되는지)
            const textContent = item.innerText.toLowerCase();
            if (keyword && !textContent.includes(keyword)) {
                isVisible = false;
            }

            // 완료 숨기기 조건 (로드맵 상태가 a003인지 확인)
            const status = item.getAttribute('data-status');
            if (isCompletedHidden && status === 'a003') {
                isVisible = false;
            }

            // 최종 보이기/숨기기 처리
            item.style.display = isVisible ? '' : 'none';
        });
    }

    // 검색어 입력 시 즉시 필터링
    if (searchInput) {
        searchInput.addEventListener('input', applyFilters);
    }

    // 완료된 항목 토글 버튼 클릭 시
    if (btnToggleCompleted) {
        btnToggleCompleted.addEventListener('click', function() {
            isCompletedHidden = !isCompletedHidden;
            this.innerHTML = isCompletedHidden ? '🔓 완료된 버전 보기' : '🔒 완료된 버전 숨기기';
            
            // 시각적 피드백 (버튼 색상 변경)
            if (isCompletedHidden) {
                this.classList.replace('btn-default', 'btn-primary');
            } else {
                this.classList.replace('btn-primary', 'btn-default');
            }
            
            applyFilters();
        });
    }
});