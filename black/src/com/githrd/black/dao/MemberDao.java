package com.githrd.black.dao;

import java.sql.*;
import java.util.ArrayList;

import com.githrd.black.db.*;
import com.githrd.black.sql.*;
import com.githrd.black.vo.*;

/**
 *  이 클래스는 회원관련 데이터베이스 작업만 전담해서 처리해주는 클래스
 * @author y
 *
 */
public class MemberDao {
	private JennieDBCP db;
//	private GitJDBC db;
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private MemberSQL mSQL;
	
	public MemberDao() {
		db = new JennieDBCP();
//		db = new GitJDBC();
		mSQL = new MemberSQL();
	}
	
	// 회원번호를 입력해서 실행하면 회원번호에 맞는 회원의 정보를 조회해서 반환해주는 함수
	public MemberVO getMnoInfo(int mno) {
		/*
			한가지 타입의 데이터 한개만 조회하는 경우 ==> 해당타입을 반환
			한가지 타입의 데이터가 여러개 조회되는 경우 ==> 배열에 담던지 컬렉션에 데이터를 쌓아서 전달
			여러가지 타입의 데이터가 한행 조회되는 경우 ==> VO 에 담아서 전달
			여러가지 타입의 데이터가 여러행 조회되는 경우 ==> vo를 리스트에 담아서 전달
		 */
		MemberVO mVO = new MemberVO();
		/*
			 SELECT
			 	name, id, mail
			 FROM
			 	member
			 WHERE
			 	isshow = 'Y'
			 	AND mno = ?
		 */
		
		con = db.getCON();
		String sql = mSQL.getSQL(mSQL.SEL_MEMBER_INFO);
		pstmt = db.getPSTMT(con, sql);
		try {
			pstmt.setInt(1, mno);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			String name = rs.getString("name");
			String id = rs.getString("id");
			String mail = rs.getString("mail");
			
			mVO.setName(name);
			mVO.setId(id);
			mVO.setMail(mail);
		} catch(Exception e) {
	//		e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		return mVO;
	}
	
	// 아이디 카운트 조회 전담 처리함수
	public int getIdCnt(String id) {
		int cnt = 0;
		
		con = db.getCON();
		String sql = mSQL.getSQL(mSQL.SEL_ID_CNT);
		pstmt = db.getPSTMT(con, sql);
		
		try {
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt("cnt");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		return cnt;
	}
	
	
	// 아이디 리스트 조회 전담 처리함수
	public ArrayList<String> getIdList(){
		ArrayList<String> list = new ArrayList<String>();
		con = db.getCON();
		String sql = mSQL.getSQL(mSQL.SEL_ID_LIST);
		stmt = db.getSTMT(con);
		try {
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				list.add(rs.getString("id"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(stmt);
			db.close(con);
		}
		return list;
	}
}
