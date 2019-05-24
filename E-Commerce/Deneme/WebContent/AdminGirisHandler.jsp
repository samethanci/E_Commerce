<%
    session.setAttribute("e_posta_yonetici",request.getParameter("eposta"));
	session.setAttribute("yonetici_turu", request.getAttribute("yonetici_turu"));
    response.sendRedirect("adminPanel");
%>