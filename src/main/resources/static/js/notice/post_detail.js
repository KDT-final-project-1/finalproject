function deletePost(projectId, boardId, postId) {
    if(confirm("本当に削除しますか？")) {
        window.location.href = `/project/${projectId}/notice/PostDelete?boardId=${boardId}&postId=${postId}`;
    }
}