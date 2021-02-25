<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <% response.setStatus(HttpServletResponse.SC_OK); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>404 Error Page</title>
  </head>
  <body>
    <div class="details">
      <h2>요청하신 페이지를 찾을 수 없습니다.</h2>
      <!-- <p>
        <a href="${contextPath}"> [HOME] </a>을 클릭하시면, 홈으로 돌아갑니다.
      </p> -->
      Exception= ${exception}<br /><br />
    </div>
  </body>
</html>
