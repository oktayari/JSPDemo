
<div id="kayitFormu" class="loginBox" >
     
<form id="myForm" method="POST" action="<%=request.getContextPath() %>/LoginController">

    <table border="2">
        <tr>
                        <td>Kullanici Adi</td>
                        <td><input type="text" name="userName" value="${user.userName}" />
                            <c:if test="${!empty userNameError}">
                                ${userNameError}
                            </c:if>
                            </td>
                    </tr>
                    <tr>
                        <td>Ad</td>
                        <td><input type="text" name="firstName" value="${user.firstName}" />
                            <c:if test="${!empty firstNameError}">
                                ${firstNameError}
                            </c:if></td>
                    </tr>
                    <tr>
                        <td>Soyad</td>
                        <td><input type="text" name="lastName" value="${user.lastName}" />
                            <c:if test="${!empty lastNameError}">
                                ${lastNameError} 
                            </c:if></td>
                    </tr>
                    <tr>
                        <td>Sifre</td>
                        <td><input type="password" name="passWord" value="${user.passWord}" />
                            <c:if test="${!empty passWordError}">
                                ${passWordError}
                            </c:if></td>
                    </tr>

                    <tr>
                        <td>Email</td>
                        <td><input type="text" name="email" value="${user.email}" />
                            <c:if test="${!empty emailError}">
                                ${emailError}
                            </c:if></td>
                    </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" name="action" value="Register" />
                    <input type="submit" name="action" value="Cancel" />

                </td>                
            </tr>     
        
    </table>

</form>

</div>

