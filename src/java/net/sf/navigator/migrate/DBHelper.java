package net.sf.navigator.migrate;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.navigator.db.Environment;

public class DBHelper {
	/**
	 * 需要学习，构造方法和初始化块哪个先启用 TODO:将jdbc连接单独抽象一个类，这个类这个方法和另外一个方法都用得着,
	 * TODO:这个conn共享变量必须要考虑多线程的情况下怎么办
	 */
	private static final Log log = LogFactory.getLog(DBHelper.class);
	Connection conn ;

	public DBHelper() throws SQLException{
		conn = Environment.getConnection();
	}

	public void checkTableExist(String tableName) {
		Statement stat = null;
		try {
			stat = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		try {
//			stat.execute("select * from " + tableName);
//		} catch (SQLException e) {
			try {
				Statement createStat = conn.createStatement();
				createStat.executeUpdate("drop table "+tableName);
				String sql = "create table " + tableName+" (type_ varchar(10))";
				log.debug(sql);
				createStat.executeUpdate(sql);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

//		}

	}

	/*
	 * 所有的列都是varchar类型的吧
	 */
	public void chcekColumnExist(String tableName, String columnName,String columnType) {
		Statement stat = null;
		try {
			stat = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			stat.execute("select " + columnName + " from " + tableName);
		} catch (SQLException e) {
			Statement createStat = null;
			try {
				createStat = conn.createStatement();
				String sql  = "alter table " + tableName+" add column "+columnName+" "+columnType;
				/**
				 * 这个地方使用log解决
				 */
				log.debug(sql);
				createStat.executeUpdate(sql);
			} catch (SQLException e1) {
				e1.printStackTrace();
			} finally {
				try {
					createStat.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void insert(String tableName, Map<String, String> map) {
		String frontSql = "insert into " + tableName + " (";
		String endSql = " values(";
		for (String columnName : map.keySet()) {
			frontSql = frontSql + " ,"+columnName;
			endSql = endSql +" ," +(map.get(columnName)==null?"null":("'"+map.get(columnName)+"'"));
		}
		frontSql = frontSql.replaceFirst(",", "");
		endSql = endSql.replaceFirst(",", "");
		frontSql = frontSql + ")";
		endSql = endSql + ")";
		String sql = frontSql + endSql;
		log.debug(sql);
		Statement stat = null;
		try {
			stat = conn.createStatement();

			stat.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("插入不成功！");
			e.printStackTrace();
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String filterSql(Object value){
		if(value == null){
			return null;
		}
		String v  = value+"";
		v = v.replace("'", "\\'");
		return v;
	}
//	public void insertTypeColumn(String tableName){
//		chcekColumnExist(tableName, "type_","varchar(10)");
//	}
	public void close(){
		Environment.returnConnection(conn);
	}
}
