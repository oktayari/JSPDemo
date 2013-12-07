<%    

    int carpan = 1;
    String Carpan = request.getParameter("carpan");
    if (Carpan != null) {
        carpan = Integer.parseInt(Carpan);
    }
    
%>


<form action="<%=request.getContextPath()%>/users/carpalim.jsp" id="myForm" method="GET">
    <label>Carpan Degerini Giriniz : </label>
    <input type="text" name="carpan" value="<%=carpan%>"/>
    <input type="submit" value="Goster"/>
</form>

<table border='2'>
    <tr><td>Sayi</td><td>Carpan</td><td>Carpim</td></tr>
    <%  for (int j = 1; j < 11; j++) {
    %>

    <tr>
        <td><%= carpan%></td>
        <td><%= j%></td>
        <td><%= (carpan * j)%></td>
    </tr>
    <%
        }
    %>
</table>



