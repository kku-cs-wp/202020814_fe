<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>게시판</title>
    <style>
        /* 테이블 스타일 */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: center;
        }

        th {
            background-color: #f2f2f2;
            color: black;
        }

        /* 버튼 스타일 */
        button, a {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 15px;
            text-decoration: none;
            cursor: pointer;
            font-size: 14px;
            border-radius: 5px;
        }

        button:hover, a:hover {
            background-color: #45a049;
        }

        .delete-btn {
            background-color: #f44336;
        }

        .delete-btn:hover {
            background-color: #d32f2f;
        }
    </style>
</head>
<body>
    <h1>게시글 목록</h1>
    <a href="/wsd/board/new">새 게시글 작성</a>
    <table>
        <thead>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>내용</th>
                <th>액션</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="board" items="${boards}">
                <tr>
                    <td>${board.id}</td>
                    <td>${board.title}</td>
                    <td>${board.content}</td>
                    <td>
                        <!-- 수정 버튼 -->
                        <a href="/wsd/board/${board.id}/edit">수정</a>
                        <!-- 삭제 버튼 -->
                        <form action="/wsd/board/${board.id}/delete" method="post" style="display: inline;">
                            <button type="submit" class="delete-btn" onclick="return confirm('정말로 삭제하시겠습니까?');">삭제</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>

