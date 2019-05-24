<%
    session.setAttribute("eposta",request.getParameter("eposta"));
    response.sendRedirect("list");
%>