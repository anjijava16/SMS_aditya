package com.sms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.sms.bean.Course;
import com.sms.bean.Parent;
import com.sms.bean.Role;
import com.sms.bean.Student;
import com.sms.bean.User;

public class SMSDaoImpl implements SMSDao {

	//@Autowired 
	// DataSource dataSource; 
	@Autowired 
	JdbcTemplate jdbcTemplate;

	public List<List<String>> validateUser(String username, String password)throws Exception {
		//List userList = new ArrayList<>();  

		String sql = "SELECT C.PROCESS_NAME ,C.PROCESS_DESC,A.USER_NAME"+
				" FROM USER_SMS A,USER_ROLE_SMS B,USER_PROCESS_SMS C "+
				" WHERE A.USER_ID = ? AND A.PASSWORD = ? "+
				" AND A.ROLE_ID = B.ROLE_ID AND B.STATUS ='Y' "+
				" AND B.ROLE_ID = C.ROLE_ID AND C.STATUS ='Y' ORDER BY C.PROCESS_ID";  
			
		List<List<String>> values = jdbcTemplate.query(sql,new Object[]{username,password}, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1)
					throws SQLException {
				List<String> list = new ArrayList<String>();
				list.add(rs.getString("PROCESS_NAME"));
				list.add(rs.getString("PROCESS_DESC"));
				list.add(rs.getString("USER_NAME"));
				return list;
			}

		});
		
		return  values;
		

	}


	public boolean saveUser(User user) throws Exception {
		boolean flag=false;
		// TODO Auto-generated method stub

		String sql = "INSERT INTO USER_sms (User_Id, User_Name , Password , Creation_Date ,Status ,Role_Id )"
				+ " VALUES (?, ?, ?, sysdate,'Y',?)";
		jdbcTemplate.update(sql, user.getUserId(), user.getUserName(),user.getPassword(),user.getSmsRole().getRoleID());
		return flag;
	}

	public boolean saveStudentProfile(Student student)throws Exception {
		boolean flag=false;
		try{
		//Parent table insert .. id - random .. inset .class .. class id .. inset 
		//select max parentid+1 and max classid +1
		String sql2 ="SELECT NVL(MAX(PARENT_ID),0)+1 FROM PARENT_SMS";
		
		int parentId = jdbcTemplate.queryForInt(sql2);
		
		String insertParent ="INSERT INTO PARENT_SMS(PARENT_ID,FATHER_NAME,MOTHER_NAME, FATHER_OCCU,MOTHER_OCCU,FATHER_CONTACT,MOTHER_CONTACT,CREATED_DATE "+
				" ) VALUES (?,?,?,?,?,?,?,SYSDATE)" ;		
		String sql = "INSERT INTO STUDENT_PROFILE_SMS (SUTDENT_ID,FIRST_NAME ,LAST_NAME ,DOB,GENDER,ADM_DATE ,COURSE_CODE,NATIONALITY,RELIGION,CLUB,MED_COND,RESC_ADDRESS," +
				"EMER_CONTACT,PARENT_ID,IMAGE,STATUS )  VALUES (?,?,?,TO_DATE(?,'YYYY-MM-DD'),?,TO_DATE(?,'YYYY-MM-DD'),?,?,?,?,?,?,?,?,?,'S')";
		Parent parent = student.getParent();
		if(parent != null){
		jdbcTemplate.update(insertParent,parentId,parent.getFatherName(),parent.getMotherName(),parent.getFatherOccu(),parent.getMotherOccu(),
				parent.getFatherContact(),parent.getMotherContact()	);
		}
		
		jdbcTemplate.update(sql,student.getStudentID(), student.getFirstName(), student.getLastName(),
				student.getDobDate(), student.getGender(),student.getAdmDate(),student.getCourse(),student.getNationality(),student.getReligion(),
				student.getClub(),student.getMedCond(),student.getRescAddress(),student.getEmerContact(),parentId,student.getFileName());
		flag = true;
		}catch(Exception e){
			
			throw e;
		}
		return flag;
	}

	public List<Role> getRoleList() throws Exception {
		
		List<Role> roleList = jdbcTemplate.query("SELECT ROLE_ID,ROLE_DESC FROM USER_ROLE_SMS WHERE STATUS='Y'",
				new RowMapper<Role>() {
			public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
				Role role=new Role(); 
				role.setRoleID(rs.getString("ROLE_ID"));
				role.setRoleDesc(rs.getString("ROLE_DESC"));
				return role;
			}
		});
		return roleList;
	}

	
public List<Course> getCourseList() throws Exception {

		List<Course> courseList = jdbcTemplate.query("SELECT DISTINCT COURSE_CODE,COURSE_NAME FROM COURSE_SMS WHERE STATUS ='Y' ORDER BY COURSE_CODE",
				new RowMapper<Course>() {
			public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
				Course course=new Course(); 
				course.setCourseCode(rs.getString("COURSE_CODE"));
				course.setCourseName(rs.getString("COURSE_NAME"));
				return course;
			}
		});
		return courseList;
	}

	
	

@Override
public List<List<String>> getCourseDetails(String studentId) {

	try{ 

	String sql = "SELECT A.COURSE_CODE,A.COURSE_NAME,A.COURSE_DURATION,B.SUBJECT_NAME,B.SUBJECT_DESC,C.FIRST_NAME "+
			"FROM COURSE_SMS A,SUBJECT_SMS B,STUDENT_PROFILE_SMS C WHERE A.COURSE_CODE = B.COURSE_CODE and C.COURSE_CODE=A.COURSE_CODE "+ 
			"AND A.STATUS ='Y' AND B.STATUS ='Y' AND C.SUTDENT_ID = ? ORDER BY A.COURSE_CODE,B.SUBJECT_ID";  
		
	List<List<String>> values = jdbcTemplate.query(sql,new Object[]{studentId}, new RowMapper() {
		@Override
		public Object mapRow(ResultSet rs,int arg1)
				throws SQLException {
			List<String> list = new ArrayList<String>();
			list.add(rs.getString("COURSE_CODE"));
			list.add(rs.getString("COURSE_NAME"));
			list.add(rs.getString("COURSE_DURATION"));
			list.add(rs.getString("SUBJECT_NAME"));
			list.add(rs.getString("SUBJECT_DESC"));
			list.add(rs.getString("FIRST_NAME"));
			return list;
		}

	});
	
	return  values;
	

	}catch(Exception e){
		throw new RuntimeException(e.getMessage());
	}
}

	@Override
	public List<Student> getSearchedProfile(String code, String status)
			throws Exception {

		try{ 

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT SUTDENT_ID,FIRST_NAME,LAST_NAME,TO_CHAR(DOB,'DD-MM-YYYY') DOB,GENDER,TO_CHAR(ADM_DATE,'DD-MM-YYYY') ADM_DATE, ");
			sql.append(" COURSE_CODE,NATIONALITY,RELIGION,CLUB,MED_COND,RESC_ADDRESS,EMER_CONTACT,IMAGE, ");
			sql.append(" DECODE(A.STATUS,'A','Approved','Submitted') STATUS, ");
			sql.append(" FATHER_NAME,MOTHER_NAME,FATHER_OCCU,MOTHER_OCCU,FATHER_CONTACT,MOTHER_CONTACT ");
		    sql.append("from STUDENT_PROFILE_SMS a,PARENT_SMS B ");
			sql.append(" WHERE A.PARENT_ID = B.PARENT_ID AND COURSE_CODE = ? ");
			if(null !=status && !"".equals(status)){
				sql.append(" And STATUS ='"+status+"'");
			}
			sql.append(" ORDER BY COURSE_CODE,FIRST_NAME");  

			List<Student> values = jdbcTemplate.query(sql.toString(),new Object[]{code}, new RowMapper() {
				@Override
				public Student mapRow(ResultSet rs, int arg1)
						throws SQLException {					
					Parent parent = new Parent();
					parent.setFatherName(rs.getString("FATHER_NAME"));
					parent.setMotherName(rs.getString("MOTHER_NAME"));
					parent.setFatherOccu(rs.getString("FATHER_OCCU"));
					parent.setMotherOccu(rs.getString("MOTHER_OCCU"));
					parent.setFatherContact(rs.getString("FATHER_CONTACT"));
					parent.setMotherContact(rs.getString("MOTHER_CONTACT"));
					
					Student student = new Student();
					student.setParent(parent);
					student.setFirstName(rs.getString("FIRST_NAME"));
					student.setStudentID(rs.getString("SUTDENT_ID"));
					student.setLastName(rs.getString("LAST_NAME"));
					student.setDobDate(rs.getString("DOB"));
					student.setGender(rs.getString("GENDER"));
					student.setAdmDate(rs.getString("ADM_DATE"));
					student.setCourse(rs.getString("COURSE_CODE"));
					student.setNationality(rs.getString("NATIONALITY"));
					student.setReligion(rs.getString("RELIGION"));
					student.setClub(rs.getString("CLUB"));
					student.setMedCond(rs.getString("MED_COND"));
					student.setRescAddress(rs.getString("RESC_ADDRESS"));
					student.setEmerContact(rs.getString("EMER_CONTACT"));
					student.setFileName(rs.getString("IMAGE"));
					student.setStatus(rs.getString("STATUS"));
					return student;
				}

			});

			return  values;


		}catch(Exception e){
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public List<Student> getStudentProfile(String studentId, String parentId) throws Exception {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT SUTDENT_ID,FIRST_NAME,LAST_NAME,TO_CHAR(DOB,'DD-MM-YYYY') DOB,GENDER,TO_CHAR(ADM_DATE,'DD-MM-YYYY') ADM_DATE, ");
		sql.append(" COURSE_CODE,NATIONALITY,RELIGION,CLUB,MED_COND,RESC_ADDRESS,EMER_CONTACT,IMAGE, ");
		sql.append(" DECODE(A.STATUS,'A','Approved','Submitted') STATUS, ");
		sql.append(" FATHER_NAME,MOTHER_NAME,FATHER_OCCU,MOTHER_OCCU,FATHER_CONTACT,MOTHER_CONTACT ");
		sql.append("from STUDENT_PROFILE_SMS a,PARENT_SMS B ");
		sql.append(" WHERE A.PARENT_ID = B.PARENT_ID AND ");
		
		String pathValue ="0";
		if(null !=studentId && !"".equals(parentId)){
		sql.append(" SUTDENT_ID = ? ");
		pathValue = studentId;
		}else if(null != parentId && !"".equals(parentId)){
		sql.append(" B.PARENT_ID = ? AND A.STATUS ='A' ");
		//pathValue = parentId;
		pathValue = "1";
		}
		try{
			List<Student> values = jdbcTemplate.query(sql.toString(),new Object[]{pathValue}, new RowMapper() {
				@Override
				public Student mapRow(ResultSet rs, int arg1)
						throws SQLException {					
					Parent parent = new Parent();
					parent.setFatherName(rs.getString("FATHER_NAME"));
					parent.setMotherName(rs.getString("MOTHER_NAME"));
					parent.setFatherOccu(rs.getString("FATHER_OCCU"));
					parent.setMotherOccu(rs.getString("MOTHER_OCCU"));
					parent.setFatherContact(rs.getString("FATHER_CONTACT"));
					parent.setMotherContact(rs.getString("MOTHER_CONTACT"));

					Student student = new Student();
					student.setParent(parent);
					student.setFirstName(rs.getString("FIRST_NAME"));
					student.setStudentID(rs.getString("SUTDENT_ID"));
					student.setLastName(rs.getString("LAST_NAME"));
					student.setDobDate(rs.getString("DOB"));
					student.setGender(rs.getString("GENDER"));
					student.setAdmDate(rs.getString("ADM_DATE"));
					student.setCourse(rs.getString("COURSE_CODE"));
					student.setNationality(rs.getString("NATIONALITY"));
					student.setReligion(rs.getString("RELIGION"));
					student.setClub(rs.getString("CLUB"));
					student.setMedCond(rs.getString("MED_COND"));
					student.setRescAddress(rs.getString("RESC_ADDRESS"));
					student.setEmerContact(rs.getString("EMER_CONTACT"));
					student.setFileName(rs.getString("IMAGE"));
					student.setStatus(rs.getString("STATUS"));
					return student;
				}

			});

			return values;

		}catch(Exception e){
			throw new RuntimeException(e.getMessage());
		}
}


	@Override
	public boolean approveProfiles(String[] studentIds) {
		try{
			StringBuffer sql = new StringBuffer("UPDATE STUDENT_PROFILE_SMS SET STATUS ='A' WHERE SUTDENT_ID IN (");
			for(int i=0;i<studentIds.length;i++){
				sql.append("'"+studentIds[i]+"'");
				if( i+1<studentIds.length){
					sql.append(",");
				}
			}
			sql.append(")");
			int update = jdbcTemplate.update(sql.toString());
			boolean flag = true;

			return flag;
		}catch(Exception e){
			throw new RuntimeException(e.getMessage());
		}
	}


}
