package com.githrd.black.controller;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.annotation.*;
import javax.servlet.http.*;

import com.githrd.black.dao.MemberDao;

@WebServlet("/idList.pink")
public class IdList extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse resp) {
		MemberDao mDao = new MemberDao();
		
		ArrayList<String> list = mDao.getIdList();
		
		PrintWriter pw = null;
		try {
			pw = resp.getWriter();
			String str = "";
			for(String name : list) {
				str += "\"" + name + "\",";
			}
			str = str.substring(0, str.lastIndexOf(','));
			pw.println("[");
			pw.println(str);
			pw.println("]");
		} catch(Exception e) {}
		
	}
}
