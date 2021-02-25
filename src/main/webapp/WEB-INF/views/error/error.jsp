<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Error Page</title>
  </head>
  <body>
    <h2>존재하지 않는 게시물입니다.</h2>
    <p>
      <a href="${contextPath}"> [HOME] </a>을 클릭하시면, 홈으로 돌아갑니다.
    </p>
    Requested URL= ${url}<br /><br />
    Exception= ${exception}<br /><br />
    contextPath = ${contextPath}<br /><br />
    queryString = ${queryString}<br /><br />
  </body>
</html>
