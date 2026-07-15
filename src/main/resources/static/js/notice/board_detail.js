// board_list.js

// 검색 조건 초기화 버튼 클릭 시 동작
function resetSearch() {
    // 날짜 초기화
    document.getElementById('startDate').value = '';
    document.getElementById('endDate').value = '';
    
    // Select 박스 초기화 (첫 번째 옵션으로)
    const selectBox = document.querySelector('.select-type');
    if (selectBox) selectBox.selectedIndex = 0;
    
    // 텍스트 입력창 초기화
    const keywordInput = document.querySelector('.input-keyword');
    if (keywordInput) keywordInput.value = '';
}

// 검색 폼 제출 전 간단한 검증 로직 (선택 사항)
document.addEventListener('DOMContentLoaded', function() {
    const searchForm = document.getElementById('searchForm');
    
    if(searchForm) {
        searchForm.addEventListener('submit', function(e) {
            const startDate = document.getElementById('startDate').value;
            const endDate = document.getElementById('endDate').value;
            
            if (startDate && endDate && startDate > endDate) {
                e.preventDefault(); // 전송 중단
                alert('開始日は終了日より遅くすることはできません。');
            }
        });
    }
});