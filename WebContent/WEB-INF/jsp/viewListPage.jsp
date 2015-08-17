<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<sf:form action="${pageContext.request.contextPath}/listPage" method="post" name="listPage" >
<div class="heading">STUDENT LIST FORM
</div>
<table align="center" cellpadding = "10" width =80%>
              <tr>
                  		<th width="10%" style="text-align: left" >Student Id</th>
						<th width="10%" style="text-align: left" >Student Name<br></th>
						<th width="10%" style="text-align: left" >Father Name<br></th>
						<th width="10%" style="text-align: left" >Mother Name<br></th>
						<th width="10%" style="text-align: left" >Status<br></th>
              </tr>
              
             <tr>
              <c:forEach var="student" items="${profileViewList}">
						<td width="10%" style="text-align: left" >
						<a href="${pageContext.request.contextPath}/viewProfile?profileView=view&studentId=${student.studentID}">${student.studentID}</a>
						</td>
						<td width="10%" style="text-align: left" >${student.firstName}<br></td>
						<td width="10%" style="text-align: left" >${student.parent.fatherName}<br></td>
						<td width="10%" style="text-align: left" >${student.parent.motherName}<br></td>
						<td width="10%" style="text-align: left" >${student.status}<br></td>
					</c:forEach>
					</tr>
              </table>
</sf:form>