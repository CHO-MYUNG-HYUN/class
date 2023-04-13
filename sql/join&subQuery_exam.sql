--1 마당서점의고객이요구하는다음질문에대해SQL 문을작성하시오.

--(5) 박지성이구매한도서의출판사수
SELECT
    COUNT(publisher)
FROM
         book b
    JOIN (
        SELECT
            bookid
        FROM
                 orders o
            JOIN customer c USING ( custid )
        WHERE
            name = '박지성'
    ) p USING ( bookid );

SELECT
    COUNT(publisher)
FROM
    book b
WHERE
    bookid IN (
        SELECT
            bookid
        FROM
                 orders o
            JOIN customer c USING ( custid )
        WHERE
            name = '박지성'
    );
    
    
    
    
    
--(6) 박지성이구매한도서의이름, 가격, 정가와판매가격의차이
SELECT
    bookname,
    b.price - p.saleprice AS pricegap
FROM
         book b
    JOIN (
        SELECT
            o.bookid,
            o.saleprice
        FROM
                 orders o
            JOIN customer c USING ( custid )
        WHERE
            name = '박지성'
    ) p USING ( bookid );
    

    
--(7) 박지성이구매하지않은도서의이름
--from 절
SELECT
    b.bookname
FROM
         book b
    JOIN (
        SELECT
            o.bookid
        FROM
                 orders o
            JOIN customer c USING ( custid )
        WHERE
            name != '박지성'
    ) p ON b.bookid = p.bookid;
    
--where절
SELECT
    bookname
FROM
    book
WHERE
    bookid NOT IN (
        SELECT
            o.bookid
        FROM
                 orders o
            JOIN customer c USING ( custid )
        WHERE
            name = '박지성'
    );

--2 마당서점의운영자와경영자가요구하는다음질문에대해SQL 문을작성하시오.
--(8) 주문하지않은고객의이름(부속질의사용)
SELECT
    c.name
FROM
    customer c
WHERE
    c.custid NOT IN (
        SELECT
            custid
        FROM
            orders
    );


--(9) 주문금액의총액과주문의평균금액
SELECT
    SUM(saleprice) AS total,
    AVG(saleprice) AS avgsaleprice
FROM
    orders;
    

--(10) 고객의이름과고객별구매액 
SELECT
    c.name,
    SUM(o.saleprice)
FROM
         customer c
    JOIN orders o USING ( custid )
GROUP BY
    name;
    
    
--(11) 고객의이름과고객이구매한도서목록
SELECT
    c.name,
    b.bookname
FROM
         customer c
    JOIN orders o ON c.custid = o.custid
    JOIN book   b ON b.bookid = o.bookid;


--(12) 도서의가격(Book 테이블)과판매가격(Orders 테이블)의차이가가장많은주문
SELECT
    *
FROM
         orders o
    JOIN book b USING ( bookid )
WHERE
    abs(b.price - o.saleprice) = (
        SELECT
            MAX(abs(b.price - o.saleprice))
        FROM
                 book b
            JOIN orders o USING ( bookid )
    );
    
    
--(13) 도서의판매액평균보다자신의구매액평균이더높은고객의이름
select *
from orders;