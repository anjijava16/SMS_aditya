<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<sf:form action="${pageContext.request.contextPath}/saveStdntDetails"
	method="post" name="studentPage" modelAttribute="student"
	enctype="multipart/form-data">
	<div class="heading">STUDENT PROFILE FORM
	</div>
	<table align="center" cellpadding="10" width=90%>

		<!----- First Name ---------------------------------------------------------->
		<tr>

			<th>FIRST NAME</th>
			<td><input type="text" name="firstName" maxlength="30"
				value="${selectStudent.firstName}" readonly="readonly"/>  </td>
		</tr>

		<!----- Last Name ---------------------------------------------------------->
		<tr>
			<th>LAST NAME</th>
			<td><input type="text" name="lastName" maxlength="30"
				value="${selectStudent.lastName}" readonly="readonly" />  </td>
		</tr>

		<!----- Date Of Birth -------------------------------------------------------->
		<tr>
			<th>DATE OF BIRTH</th>

			<td><input type="date" id="birthday" name="dobDate" size="20" />
			</td>
		</tr>

		<!----- Admission Date -------------------------------------------------------->
		<tr>
			<th>ADMISSION DATE</th>

			<td><input type="date" id="admDate" name="admDate" size="20" />
			</td>
		</tr>

		<!----- Passport picture---------------------------------------------------------->
		<tr>
			<th>PASSPORT PICTURE</th>
			<td><input type="file" name="image" /></td>
		</tr>
		<!----- SELECT CURRENT COURSE ---------------------------------------------------------->
		<tr>
			<th>SELECT CURRENT COURSE</th>
			<td><select name="course">
					<option value="" label="Select Course" />
					<c:forEach var="course" items="${userCourseList}">
						<option value="${course.courseCode}" label="${course.courseName}" />
					</c:forEach>
			</select></td>
		</tr>
		<!----- Nationality ---------------------------------------------------------->
		<tr>
			<th>NATIONALITY</th>
			<td><input type="text" name="nationality"
				value="${selectStudent.nationality}" readonly="readonly"/></td>
		</tr>
		<!----- Religion ---------------------------------------------------------->
		<tr>
			<th>RELIGION</th>
			<td><input type="text" name="religion"
				value="${selectStudent.religion}" readonly="readonly"/></td>
		</tr>
		<!----- Club ---------------------------------------------------------->
		<tr>
			<th>CLUB</th>
			<td><input type="text" name="club" value="${selectStudent.club}" readonly="readonly"/></td>
		</tr>
		<!----- Medical Condition ---------------------------------------------------------->
		<tr>
			<th>MEDICAL CONDITION</th>
			<td><input type="text" name="medCond"
				value="${selectStudent.medCond}" readonly="readonly"/></td>
		</tr>

		<!----- Gender ----------------------------------------------------------->
		<tr>
			<th>GENDER</th>
			<td>Male <input type="radio" name="gender" value="Male"
				checked="${selectStudent.gender}" /> Female <input type="radio"
				name="gender" value="Female" checked="${selectStudent.gender}" />
			</td>
		</tr>

		<!----- Residential Address  ---------------------------------------------------------->
		<tr>
			<th>Residential ADDRESS</th>
			<td><textarea name="rescAddress" rows="4" cols="30" readonly="readonly">${selectStudent.rescAddress}</textarea></td>
		</tr>
		<!----- Emergency Contact---------------------------------------------------------->
		<tr>
			<th>EMERGENCY CONTACT</th>
			<td><input type="text" name="emerContact" 
				value="${selectStudent.emerContact}" readonly="readonly"/> </td>
		</tr>
		<!----- Father name---------------------------------------------------------->
		<tr>
			<th>FATHER NAME</th>
			<td><input type="text" name="parent.fatherName"
				value="${selectStudent.parent.fatherName}" readonly="readonly"/></td>
		</tr>
		<!----- Father occupation ---------------------------------------------------------->
		<tr>
			<th>FATHER OCCUPATION</th>
			<td><input type="text" name="parent.fatherOccu"
				value="${selectStudent.parent.fatherOccu}" readonly="readonly"/></td>
		</tr>
		<!----- Father Contact ---------------------------------------------------------->
		<tr>
			<th>FATHER CONTACT</th>
			<td><input type="text" name="parent.fatherContact"
				value="${selectStudent.parent.fatherContact}"  readonly="readonly"/> (10
				digit number)</td>
		</tr>
		<!----- Father name---------------------------------------------------------->
		<tr>
			<th>MOTHER NAME</th>
			<td><input type="text" name="parent.motherName"
				value="${selectStudent.parent.motherName}" readonly="readonly"/></td>
		</tr>
		<!----- Father occupation ---------------------------------------------------------->
		<tr>
			<th>MOTHER OCCUPATION</th>
			<td><input type="text" name="parent.motherOccu"
				value="${selectStudent.parent.motherOccu}" readonly="readonly"/></td>
		</tr>
		<!----- Mother Contact ---------------------------------------------------------->
		<tr>
			<th>MOTHER CONTACT</th>
			<td><input type="text" name="parent.motherContact"
				value="${selectStudent.parent.motherContact}"  readonly="readonly"/>  </td>
		</tr>
	</table>
</sf:form>