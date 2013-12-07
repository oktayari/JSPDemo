<jsp:useBean id="user" class="org.oka.model.Userst" scope="request">
    <jsp:setProperty property="*" name="myInfo"></jsp:setProperty>
</jsp:useBean>

<c:if test="${myInfo!= null && myInfo.userName.length()>0}"  >
    <div id="userInfo" class="loginBox">
        <h3>Kullanici Bilgileri</h3>

        <p> User Name      : ${myInfo.userName}</p>
        <p> First Name     : ${myInfo.firstName}</p>
        <p> Last Name      : ${myInfo.lastName}</p>
        <p> PassWord       : ${myInfo.passWord}</p>

        <p> Roller         :  <br/>
            <c:forEach items="${myInfo.rolestCollection}" var="rolest">
               .......... <c:out value="${rolest.roleName}" /> <br/>
            </c:forEach>
        </p>
        <p> Email          : ${myInfo.email}</p>
        <p> Creation Date  : ${myInfo.creationDate}</p>
    </div>
</c:if>
