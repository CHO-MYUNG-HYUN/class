<%--
  Created by IntelliJ IDEA.
  User: hi
  Date: 2023-05-18
  Time: 오후 5:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>write</title>
</head>
<body>

<h1>게시글 작성</h1>
<hr>

<form method="post" enctype="multipart/form-data">
  <table>

    <%--<tr>
    회원만 작성 가능한 게시판으로 변경 -> 삭제
      <td>작성자</td>
      <td><input type="text" name="writer" required value="${loginInfo.idx}"></td>
    </tr>--%>
    <input type="hidden" name="memidx" value="${loginInfo.idx}">
    <tr>
      <td>제목</td>
      <td><input type="text" name="title" required></td>
    </tr>
    <tr>
      <td>내용</td>
      <td><textarea name="content" cols="21" rows="7" required></textarea></td>
    </tr>
    <tr>
      <td>첨부파일(이미지)</td>
      <td><input type="file" name="file"></td>
    </tr>
    <tr>
      <td></td>
      <td><input type="reset"> <input type="submit" value="작성"></td>
    </tr>
  </table>
</form>
</body>
</html>
