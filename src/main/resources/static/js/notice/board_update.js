// board-update.js
document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("boardUpdateForm");
    const btnSubmit = document.getElementById("btnSubmit");
  
    if (!form || !btnSubmit) return;
  
    const boardNameInput = document.getElementById("boardName");
    const boardNameErrorDiv = document.getElementById("boardNameError");
    
    const descInput = document.getElementById("description");
    const descErrorDiv = document.getElementById("descError");

    // 1. 에러 메시지 처리
    const errorMessage = form.getAttribute("data-error-message");
    if (errorMessage && errorMessage !== 'null' && errorMessage.trim() !== '') {
        window.PFDialog.alert(errorMessage, {
            title: 'エラー',
            icon: 'error'
        });
    }
  
    // 2. 수정 제출 버튼 처리
    btnSubmit.addEventListener("click", function () {
        let isValid = true;
  
        // 게시판 이름 검증
        if (boardNameInput.value.trim() === "") {
            boardNameInput.classList.add("border-red");
            if (boardNameErrorDiv) boardNameErrorDiv.style.display = "block";
            isValid = false;
        } else {
            boardNameInput.classList.remove("border-red");
            if (boardNameErrorDiv) boardNameErrorDiv.style.display = "none";
        }
  
        // 설명 검증
        if (descInput.value.trim() === "") {
            descInput.classList.add("border-red");
            if (descErrorDiv) descErrorDiv.style.display = "block";
            isValid = false;
        } else {
            descInput.classList.remove("border-red");
            if (descErrorDiv) descErrorDiv.style.display = "none";
        }
  
        // 검증 실패 시 포커스 이동
        if (!isValid) {
            if (boardNameInput.value.trim() === "") {
                boardNameInput.focus();
            } else {
                descInput.focus();
            }
            return;
        }
  
        // 성공 시 제출 (SweetAlert 수정 컨펌 적용)
        window.PFDialog.confirm({
            title: '編集確認',
            message: '掲示板の編集内容を保存しますか？',
            confirmText: '保存',
            icon: 'question'
        }).then(function(confirmed) {
            if (confirmed) {
                btnSubmit.disabled = true;
                form.submit();
            }
        });
    });
  
    // 3. 게시판 삭제 전 글 개수 안전 차단기 및 SweetAlert 컨펌
    const btnDeleteBoard = document.getElementById('btnDeleteBoard');
    if (btnDeleteBoard) {
        btnDeleteBoard.addEventListener('click', function(e) {
            e.preventDefault();
            const postCount = parseInt(this.getAttribute('data-post-count') || '0', 10);
            const deleteUrl = this.getAttribute('href');
            
            if (postCount > 0) {
                window.PFDialog.alert("投稿が存在する掲示板は削除できません。（現在登録されている投稿: " + postCount + "件）", {
                    title: '削除不可',
                    icon: 'warning'
                });
            } else {
                window.PFDialog.confirm({
                    title: '削除確認',
                    message: '本当にこの掲示板を削除しますか？\n削除後は復旧できません。',
                    confirmText: '削除',
                    icon: 'warning'
                }).then(function(confirmed) {
                    if (confirmed) {
                        location.href = deleteUrl;
                    }
                });
            }
        });
    }
  
    // 실시간 에러 테두리 지우기
    boardNameInput.addEventListener("input", function () {
        this.classList.remove("border-red");
        if (boardNameErrorDiv) boardNameErrorDiv.style.display = "none";
    });
  
    descInput.addEventListener("input", function () {
        this.classList.remove("border-red");
        if (descErrorDiv) descErrorDiv.style.display = "none";
    });
});
