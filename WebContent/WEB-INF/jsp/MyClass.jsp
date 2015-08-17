	
	<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
		<table width = 80%>
				<tr>
					<th>Student Name</th>
					<th>Course Name</th>
					<th>Subject Name</th>
					<th>Course Duration</th>
				</tr>
   <c:forEach var="courseList" items="${mycoursList}">
               <tr>
              <td><c:out value="${courseList[5]}"/></td>
               <td><c:out value="${courseList[1]}"/></td>
               <td><c:out value="${courseList[3]}"/></td>
               <td><c:out value="${courseList[2]}"/></td>
           </tr>
      </c:forEach>		
		</table>
	