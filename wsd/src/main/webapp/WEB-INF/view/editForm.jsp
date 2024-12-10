<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>게시글 수정</title>
    <style>
        /* 기본 페이지 스타일 */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background: #f7f7f7;
            color: #333;
        }

        /* 중앙 정렬 및 레이아웃 */
        .container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        /* 제목 스타일 */
        h1 {
            text-align: center;
            color: #4CAF50;
        }

        /* 폼 스타일 */
        .form {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }

        label {
            font-weight: bold;
        }

        input[type="text"], textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
        }

        textarea {
            height: 100px;
        }

        .btn {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        .btn:hover {
            background-color: #45a049;
        }

        .back-link {
            display: inline-block;
            margin-top: 15px;
            text-align: center;
            text-decoration: none;
            color: #4CAF50;
            font-weight: bold;
        }

        .back-link:hover {
            text-decoration: underline;
        }
    </style>
    <script>
        // 폼 검증 확인
        document.addEventListener("DOMContentLoaded", () => {
            const form = document.querySelector(".form");
            form.addEventListener("submit", (event) => {
                const title = document.querySelector("#title").value.trim();
                const content = document.querySelector("#content").value.trim();

                if (!title || !content) {
                    alert("제목과 내용을 모두 입력해주세요.");
                    event.preventDefault();
                }
            });
        });
    </script>
</head>
<body>
    <div class="container">
        <h1>게시글 수정</h1>
        <form action="/wsd/board/${board.id}/edit" method="post" class="form">
            <label for="title">제목:</label>
            <input type="text" id="title" name="title" value="${board.title != null ? board.title : ''}" required />
            <label for="content">내용:</label>
            <textarea id="content" name="content" required>${board.content != null ? board.content : ''}</textarea>
            <button type="submit" class="btn">수정</button>
        </form>
        <a href="/wsd/board" class="back-link">목록으로 돌아가기</a>
    </div>
</body>
</html>
