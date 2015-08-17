<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<sf:form action="${pageContext.request.contextPath}/registerProfile"
	method="post" name="userPage" modelAttribute="user">
	<div class = "heading" align="center">
	REGISTRATION FORM
	</div>
	<br><br>
	<table align="center" cellpadding="10" width=80%>
		<tr>
			<td align="right"><label>LOGIN USER ID</label></td>
			<td align="left"><input type="text" name="userId" maxlength="30" /></td>
		</tr>

		<tr>
			<td align="right"><label>USER NAME</label></td>
			<td align="left"><input type="text" name="userName" maxlength="30" /></td>
		</tr>
		<!----- Password ---------------------------------------------------------->
		<tr>
			<td align="right"><label>PASSWORD</label></td>
			<td align="left"><input type="password" name="password" maxlength="30" /></td>
		</tr>
		<!----- Confirm Password ---------------------------------------------------------->
		<
		<!-- tr>
<td> CONFIRM  PASSWORD</td>
<td><input type="password" name="confPassword" maxlength="30" />
(max 30 characters a-z and A-Z)
</td>
</tr>
 --- User Role ------------------------------------------------------
  --><tr> 
		<td align="right"><label>USER ROLE</label></td>
		<td align="left"><select name="smsRole.roleID">
				<option value="" label="Select Role" />
				<c:forEach var="role" items="${userRoleList}">
					<option value="${role.roleID}" label="${role.roleDesc}" />
					
				</c:forEach>
		</select> <%--<sf:select path="smsRole" items="${userRoleList}" required="true" itemLabel="roleDesc" itemValue="roleID"/>    --%>
		</td>
		</tr>

		<!----- Submit and Reset ------------------------------------------------->
		<tr>
			<td colspan="2" align="center"><input type="submit"
				value="Register"> <input type="reset" value="Reset">
			</td>
		</tr>
	</table>
</sf:form>