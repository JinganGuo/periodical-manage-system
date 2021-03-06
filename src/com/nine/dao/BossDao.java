package com.nine.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BossDao {
	
	private ComDao cd = new ComDao();
	//返回管理员总人数
	public String managerCount() {
		String managerCount = null;
		String sql = "select count(1) from employeetb";
		Connection con = cd.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				managerCount = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cd.closeAll(con, pstmt, rs);
		}
		return managerCount;
	}
	//返回读者总人数
	public String readerCount() {
		String readerCount = null;
		String sql = "select count(1) from readertb";
		Connection con = cd.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				readerCount = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cd.closeAll(con, pstmt, rs);
		}
		return readerCount;
	}
	//返回期刊总数
	public String periodicalCount() {
		String periodicalCount = null;
		String sql = "select count(1) from periodicalstb";
		Connection con = cd.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				periodicalCount = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cd.closeAll(con, pstmt, rs);
		}
		return periodicalCount;
	}
	//返回男，女人数
	public JSONObject sexCount() {
		JSONObject sex_json = new JSONObject();
		String sql = "SELECT sex,count(1) FROM readertb group by sex;";
		Connection con = cd.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				sex_json.put(rs.getString(1), rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cd.closeAll(con, pstmt, rs);
		}
		return sex_json;
		
	}
	//返回部门人数
		public JSONArray departmentCount() {
			JSONArray department_jsonArray = new JSONArray();
			
			String sql = "SELECT department,count(1) FROM readertb group by department";
			Connection con = cd.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					JSONObject department_json = new JSONObject();
					department_json.put("department",rs.getString(1));
					department_json.put("number", rs.getString(2));
					department_jsonArray.add(department_json);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				cd.closeAll(con, pstmt, rs);
			}
			return department_jsonArray;
			
		}
	//验证管理员帐号是否存在
	public boolean validateEmployee(String employeeID) {
		boolean istrue = false;
//		SELECT * FROM jichen.employeetb where employeePD=MD5('jc_admin') and employeeID='20150101';
		String sql = "select employeeID from employeetb where employeeID=?";
		Connection con = cd.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		System.out.println(readerID);
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, employeeID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				istrue = true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			cd.closeAll(con,pstmt,rs);
		}
		return istrue;
	}
	//添加管理员
	public boolean addEmployee(String employeeID, String employeeName,String sex) {
//		insert into employeetb values ('20150104','谭思颖','女','管理员',MD5('tsy_admin'));
		String sql = "insert into employeetb values (?,?,?,'管理员',MD5('gly_admin')) ";
		Connection con = cd.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, employeeID);
			pstmt.setString(2, employeeName);
			pstmt.setString(3, sex);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			cd.closeAll(con, pstmt);
		}
		return true;
	}
	//修改管理员
	//修改读者信息
	public boolean modifyEmployee(String employeeName, String employeeID) {
//		update employeetb set employeeName='纪臣',sex='女' where employeeID='20150101';
		String sql = "update employeetb set employeeName=?  where employeeID=?";
		Connection con = cd.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt =con.prepareStatement(sql);
			pstmt.setString(1, employeeName);
			pstmt.setString(2, employeeID);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			cd.closeAll(con, pstmt);
		}
		return true;
	}
	public boolean deleteEmployee(String employeeID) {
//		DELETE FROM `periodicaldb`.`employeetb` WHERE `employeeID`='20150002';
		String sql = "delete from employeetb where employeeID=?";
		Connection con = cd.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, employeeID);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
