package net.sf.navigator.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.navigator.menu.parser.DBParse;

/**
 * this class is used to support the {@link DBParse} 
 * 
 * @author roger han
 * @date : 2014-1-6 下午02:39:24
 */
public class ConnectionTool {
	//~instance filed
	Connection conn ;
	
	public ConnectionTool() throws SQLException{
		conn = Environment.getConnection();
	}
	/**
	 * get data throw a sql sentence;
	 * @param sql
	 * @return
	 */
	public List<Map<String,Object>> getData(String sql){
		List<Map<String,Object>> returnList = new ArrayList<Map<String,Object>>();
		Statement stat = null;
		try {
			stat = conn.createStatement();
			ResultSet res = stat.executeQuery(sql);
			List<String> columnNameList = getColumnName(res.getMetaData());
			
			while(res.next()){
				
				Map<String,Object> m = new HashMap<String,Object>();
				
				for(String name:columnNameList){
					m.put(name, res.getObject(name));
				}
				returnList.add(m);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return returnList;
	}
	
	/**
	 * get column name from a ResultSet
	 * @param rsmd
	 * @return
	 * @throws SQLException
	 */
	private List<String> getColumnName(ResultSetMetaData rsmd) throws SQLException{
		List<String> columnNameList = new ArrayList<String>();
		int count=rsmd.getColumnCount();
		
		for(int i=1;i<=count;i++){
			columnNameList.add(rsmd.getColumnName(i));
		}
		
		return columnNameList;
	}
	
	public void distroy(){
			Environment.returnConnection(conn);
	}
}
