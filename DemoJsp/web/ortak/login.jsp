
<div id="loginForm" class="loginBox">

    <form id="loginForm" action="<%=request.getContextPath()%>/LoginController" method="post" >
        <table border="2">
            <tr><td>Kullanici Adi :</td><td><input type="text" name="userName" ></td></tr>
            <tr><td>Sifre :</td><td><input type="password" name="passWord" ></td></tr>
            <tr><td colspan="2" ><input type="submit" name="action" value="Login" /> </td></tr>
        </table>
    </form>

    <a href="<%=request.getContextPath()%>/ortak/register.jsp">Yeni Kullanici</a>

</div>