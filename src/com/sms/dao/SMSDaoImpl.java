package com.sms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
				" AND B.ROLE_ID = C.ROLE_ID AND C.STATUS ='Y'";  
			
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
				+ " VALUES (?, ?, ?, ?,?,?)";
		jdbcTemplate.update(sql, user.getUserId(), user.getUserName(),
				user.getPassword(), user.getCreationDate(),user.getStatus(),user.getSmsRole().getRoleID());
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
		String sql = "INSERT INTO Student_Profile_sms (First_Name ,Last_Name ,	DOB,Gender	,Adm_Date ,	Adm_Class ,	Class_ID,Nationality ,Religion ,Club ,Med_Cond,	Resc_Address,Emer_Contact,Parent_ID ,Image )"
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Parent parent = student.getParent();
		if(parent != null){
		jdbcTemplate.update(insertParent,parentId,parent.getFatherName(),parent.getMotherName(),parent.getFatherOccu(),parent.getMotherOccu(),
				parent.getFatherContact(),parent.getMotherContact()	);
		}
		
		jdbcTemplate.update(sql, student.getFirstName(), student.getLastName(),
				student.getDobDate(), student.getGender(),student.getAdmDate(),student.getAdmClass(),student.getSmsclass().getClassID(),student.getNationality(),student.getReligion(),
				student.getClub(),student.getMedCond(),student.getRescAddress(),student.getEmerContact(),parentId,student.getFileName());
		}catch(Exception e){
			
			throw e;
		}
		return flag;
	}


	public Student getStudentProfile(String studentId) throws Exception {
		String sql = "SELECT First_Name ,Last_Name ,	DOB,Gender	,Adm_Date ,	Adm_Class ,	Class_ID,Nationality ," +
				"Religion ,Club ,Med_Cond,	Resc_Address,Emer_Contact,Parent_ID ,Image" +
				" FROM  Student_Profile_sms WHERE contact_id=?" ;
		List<Student> students = jdbcTemplate.query(sql,new Object[]{studentId}, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1)
					throws SQLException {
				Student student = new Student();
				student.setFirstName(rs.getString("First_Name"));
				return student;
			}

		});

		return (students.size() > 0 ? students.get(0) : null);
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
}
