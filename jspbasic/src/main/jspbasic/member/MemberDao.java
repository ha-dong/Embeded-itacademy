package jspbasic.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jspbasic.product.ConnectionUtil;

public class MemberDao implements MemberInterface {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	@Override
	public boolean getMember(Member member) throws SQLException{
		conn = ConnectionUtil.getConnection();
		String sql="SELECT * FROM MEMBER WHERE MID=? AND MPASS=? ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, member.getMid());
		pstmt.setString(2, member.getMpass());
		rs = pstmt.executeQuery();
		if(rs==null)return false;
		
		return pstmt.executeQuery().next();
		}
	
}//class
