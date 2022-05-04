package com.githrd.black.sql;

public class MemberSQL {
	public final int SEL_MEMBER_INFO = 1001;
	public final int SEL_ID_CNT = 1002;
	public final int SEL_ID_LIST = 1003;
	
	public String getSQL(int code) {
		StringBuffer buff = new StringBuffer();
		switch(code) {
		case SEL_MEMBER_INFO:
			
			break;
		case SEL_ID_CNT:
			buff.append("SELECT ");
			buff.append("	COUNT(*) cnt ");
			buff.append("FROM ");
			buff.append("	member ");
			buff.append("WHERE ");
			buff.append("	id = ? ");
			break;
		case SEL_ID_LIST:
			buff.append("SELECT ");
			buff.append("	id ");
			buff.append("FROM ");
			buff.append("	member ");
			buff.append("WHERE ");
			buff.append("	isshow = 'Y' ");
			break;
		}
		return buff.toString();
	}
}
