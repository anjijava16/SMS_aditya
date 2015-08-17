<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<sf:form action="${pageContext.request.contextPath}/getAllProfile" method="post" name="searchProfile" >
<table align="center" cellpadding = "10" width =80%>
<tr>
<td>SELECT COURSE</td>
<td>

<select name="courseCode">
	    <option value="" label="Select Course" />
	    <c:forEach var="course" items="${userCourseList}">
	            <option value="${course.courseCode}" label="${course.courseName}" />    
	        </option>
	    </c:forEach>
	</select> 
	
</td>
</tr>
 
 <tr>
<td>SELECT PROFILE</td>
 <td>
	<select name="status">
	    <option value="" label="All" />
	      <option value="S" label="Submited" /> 
	        <option value="A" label="Approved" /> 
	</select> 
</td>
</tr>


<!----- Submit and Reset ------------------------------------------------->
<tr>
<td align="center">
<input type="submit"   value="Search">
</td>
</tr>
</table>
</sf:form>