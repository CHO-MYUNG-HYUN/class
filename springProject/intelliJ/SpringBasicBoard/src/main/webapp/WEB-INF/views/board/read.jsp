<%--
  Created by IntelliJ IDEA.
  User: hi
  Date: 2023-05-19
  Time: 오전 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>read</title>
    <style>
        td{
            padding: 10px 20px;
        }
    </style>
    <script src="https://code.jquery.com/jquery-1.12.4.js" integrity="sha256-Qw82+bXyGq6MydymqBxNPYTaUXXq7c8v3CwiYwLLNXU=" crossorigin="anonymous"></script>

    <script>
        $(document).ready(function(){
            $('#replyRegForm').submit(function(){

                if($('#reply').val().trim().length<1){
                    alert('댓글 내용을 입력해주세요.')
                    return false
                }

                // 서버로 보낼 때 JSON 으로 변경
                const payload = {
                    bno : $('#bno').val(),
                    memIdx : $('#mIdx').val(),
                    reply : $('#reply').val()
                }

                // 비동기 통신
                $.ajax({
                    url: 'reply', // /board/read/ , /borad/reply
                    type: 'post',
                    contentType: 'application/json',
                    data: JSON.stringify(payload), //json으로 보낸다
                    dataType: 'json', //json으로 받는다
                    success: function (data){
                        console.log(data)

                        const reply = data

                        // 화면에 동적으로 HTML 생성 추가
                        let html = ''
                        html+='<td>' +reply.memIdx + '</td>'
                        html+='<td>' +reply.reply+ '</td>'
                        html+='<td>' +reply.replyDate+'</td>'
                        html+='<td><a href="javascript:delTr( ' +reply.rno+ ')">삭제</a></td>'


                        const newTR = $('<tr></tr>').attr('tr-index', reply.rno).html(html)

                        // List 영역에 tr을 추가
                        $('#replyList').append(newTR)

                        $('#replyList').val('')

                    }
                });

                return false
            })
        })

        function delTr(index){

            //비동기
            $.ajax({
                url : 'reply/' + index,
                type : 'delete',
                success : function(data){
                    // tr 삭제
                    $('tr[tr-index="'+index+'"]').remove()
                }
            })

        }
    </script>

</head>
<body>

<h1>게시글 보기</h1>
<hr>
${loginInfo}
<hr>
<table border="1">
    <tr>
        <td>게시글 번호</td>
        <td>${article.bno}</td>
    </tr>
    <tr>
        <td>제목</td>
        <td>${article.title}</td>
    </tr>
    <tr>
        <td>내용</td>
        <td>${article.content}</td>
    </tr>
    <tr>
        <td>작성자</td>
        <td>${article.writer}</td>
    </tr>
    <c:if test="${article.file ne null}">
        <tr>
            <td>첨부파일<br>(이미지)</td>
            <td>
                <img src="/uploadfile/board/${article.file}" width="100"/>
            </td>
        </tr>
    </c:if>
    <tr>
        <td>등록일</td>
        <td>${article.regdate}</td>
    </tr>
    <tr>
        <td>수정일</td>
        <td>${article.updatedate}</td>
    </tr>
</table>

<hr>

<div id="replyArea">
    <h3>댓글</h3>
    <hr>
    <div>
        <form id="replyRegForm">
            게시물 번호<input type="text" id="bno" value="${article.bno}">
            회원 번호<input type="test" id="mIdx" value="${loginInfo.idx}"> <br>
            댓글 내용<input type="text" id="reply" required> <br>
            <input type="submit">
        </form>

        <%--댓글의 리스트--%>
        <table>
            <tbody id="replyList">


            </tbody>
        </table>
    </div>
</div>






<a href="/board/list">List</a>

<c:if test="${loginInfo.idx eq article.memidx}">
<a href="/board/modify?bno=${article.bno}">수정</a>
<a href="/board/delete?bno=${article.bno}">삭제</a>
</c:if>
</body>
</html>
