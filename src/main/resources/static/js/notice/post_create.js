document.addEventListener("DOMContentLoaded", function() {
    const fileBox = document.getElementById('fileBox');
    const realFile = document.getElementById('realFile');
    const fileName = document.getElementById('fileName');

    // 파일 박스 클릭 시 실제 input 실행
    fileBox.addEventListener('click', function() {
        realFile.click();
    });

    // 파일 선택 시 이름 표시
    realFile.addEventListener('change', function() {
        if (realFile.files.length > 0) {
            fileName.innerText = realFile.files[0].name;
        } else {
            fileName.innerText = "파일을 선택하세요";
        }
    });
});