<head>
  <link type="text/css" href="resources/css/datepicker.css" rel="stylesheet" />
  <link type="text/css" href="resources/css/normalize.css" rel="stylesheet" />
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
		<script type="text/javascript">
		$(function(){
			$('#datepicker').datepicker({
				inline: true,
				showOtherMonths: true,
				dayNamesMin: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
			});
		});
	</script>
	</head>
		<form action="${pageContext.request.contextPath}/saveStdntDetails"
			method="post" name="studentPage" modelAttribute="student"
			enctype="multipart/form-data">
			<div class = "heading" align="center">
			STUDENT PROFILE FORM
			</div>
			<br><br>
			<table align="center" cellpadding="10" width=80%>

				<!----- First Name ---------------------------------------------------------->
				<tr>

					<td align="right"><label>FIRST NAME</label></td>
					<td align="left"><input type="text" name="firstName" maxlength="30" /></td>
				</tr>

				<!----- Last Name ---------------------------------------------------------->
				<tr>
					<td align="right"><label>LAST NAME</label></td>
					<td align="left"><input type="text" name="lastName" maxlength="30" /></td>
				</tr>

				<!----- Date Of Birth -------------------------------------------------------->
				<tr>
					<td align="right"><label>DATE OF BIRTH</label></td>

					<td align="left"><input type="text" id="datepicker" name="dobDate" size="20" />
					</td>
				</tr>

				<!----- Admission Date -------------------------------------------------------->
				<tr>
					<td align="right"><label>ADMISSION DATE</label></td>

					<td align="left"><input type="date" id="admDate" name="admDate" size="20" />
					</td>
				</tr>

				<!----- Passport picture---------------------------------------------------------->
				<tr>
					<td align="right"><label>PASSPORT PICTURE</label></td>
					<td align="left"><input type="file" name="image" /></td>
				</tr>

				<!----- Admitted Class ---------------------------------------------------------->
				<tr>
					<td align="right"><label>ADMITTED CLASS</label></td>
					<td align="left"><input type="text" name="admClass" /></td>
				</tr>
				<!----- Current Class ---------------------------------------------------------->
				<tr>
					<td align="right"><label>CURRENT CLASS</label></td>
					<td align="left"><input type="text" name="smsclass.className" /></td>
				</tr>
				<!----- Nationality ---------------------------------------------------------->
				<tr>
					<td align="right"><label>NATIONALITY</label></td>
					<td align="left"><input type="text" name="nationality" /></td>
				</tr>
				<!----- Club ---------------------------------------------------------->
				<tr>
					<td align="right"><label>CLUB</td>
					<td align="left"><input type="text" name="club" /></td>
				</tr>
				<!----- Medical Condition ---------------------------------------------------------->
				<tr>
					<td align="right"><label>MEDICAL CONDITION</label></td>
					<td align="left"><input type="text" name="medCond" /></td>
				</tr>

				<!----- Gender ----------------------------------------------------------->
				<tr>
					<td align="right"><label>GENDER</label></td>
					<td align="left">Male <input type="radio" name="gender" value="Male" /><br>
						Female <input type="radio" name="gender" value="Female" />
					</td>
				</tr>

				<!----- Residential Address  ---------------------------------------------------------->
				<tr>
					<td align="right"><label>Residential ADDRESS</label> <br /> <br /> <br /></td>
					<td align="left"><textarea name="rescAddress" rows="4" cols="30"></textarea></td>
				</tr>
				<!----- Emergency Contact---------------------------------------------------------->
				<tr>
					<td align="right"><label>EMERGENCY CONTACT</label></td>
					<td align="left"><input type="text" name="emerContact" maxlength="10" /></td>
				</tr>
				<!----- Father name---------------------------------------------------------->
				<tr>
					<td align="right"><label>FATHER NAME</label></td>
					<td align="left"><input type="text" name="parent.fatherName" /></td>
				</tr>
				<!----- Father occupation ---------------------------------------------------------->
				<tr>
					<td align="right"><label>FATHER OCCUPATION</label></td>
					<td align="left"><input type="text" name="parent.fatherOccu" /></td>
				</tr>
				<!----- Father Contact ---------------------------------------------------------->
				<tr>
					<td align="right"><label>FATHER CONTACT</label></td>
					<td align="left"><input type="text" name="parent.fatherContact"
						maxlength="10" /></td>
				</tr>
				<!----- Father name---------------------------------------------------------->
				<tr>
					<td align="right"><label>MOTHER NAME</label></td>
					<td align="left"><input type="text" name="parent.motherName" /></td>
				</tr>
				<!----- Father occupation ---------------------------------------------------------->
				<tr>
					<td align="right"><label>MOTHER OCCUPATION</label></td>
					<td align="left"><input type="text" name="parent.motherOccu" /></td>
				</tr>
				<!----- Mother Contact ---------------------------------------------------------->
				<tr>
					<td align="right"><label>MOTHER CONTACT</label></td>
					<td align="left"><input type="text" name="parent.motherContact"
						maxlength="10" /></td>
				</tr>
				<!----- Submit and Reset ------------------------------------------------->
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Submit"> <input type="reset" value="Reset"></td>
				</tr>
			</table>
		</form>
	