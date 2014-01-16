package net.sf.navigator.migrate;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;
import net.sf.navigator.db.Config;
import net.sf.navigator.displayer.MenuDisplayerMapping;
import net.sf.navigator.menu.MenuBase;
import net.sf.navigator.menu.MenuComponent;
import net.sf.navigator.menu.MenuRepository;
import net.sf.navigator.menu.parser.XMLParser;
import net.sf.navigator.util.LoadableResourceException;

public class XmlToDatabase {
	List<Method> gettersOfMenuClass = new ArrayList();
	List<Method> gettersOfDisplayerClass = new ArrayList();
	DBHelper dbHelper = null;
	/*
	 * 1 获得所有的菜单
	 */
	public void move(Config configForInit) throws LoadableResourceException,
			IOException, SAXException, SQLException {
		this.init();
		XMLParser parse = new XMLParser();
		
		MenuRepository repository = new MenuRepository();
		parse.init(repository);
		Digester digester = parse.initDigester();
		repository = (MenuRepository) digester.parse(configForInit
				.getConfigParam());
		createTable(configForInit.getConfigProperties().getTable());
		
		List<MenuComponent> menus = repository.getTopMenus();
		setIds(menus, 1, null);
		
		insertMenuValues(configForInit.getConfigProperties().getTable(),menus);
		
		Map<String,MenuDisplayerMapping> displayerMaping = repository.getDisplayers();
		
		this.insertDisplayValues(configForInit.getConfigProperties().getTable(), displayerMaping);
		
		close();
	}

	private void insertDisplayValues(String tableName,Map<String,MenuDisplayerMapping> displayerMaping){
		for(String displayerName:displayerMaping.keySet()){
			MenuDisplayerMapping mapping =  displayerMaping.get(displayerName);
			Map map = new HashMap();
			for(Method m:gettersOfDisplayerClass){
				String value = null;
				try {
					Object obj = m.invoke(mapping, null);
					value = dbHelper.filterSql(obj);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new RuntimeException("方法解析出错 ");
				}
				map.put(m.getName().substring(3), value);
			}
			map.put("type_", "displayer");
			dbHelper.insert(tableName, map);
		}
	}
	
	public int setIds(List<MenuComponent> menus, Integer id, Integer parentId) {
		if (menus != null) {
			for (MenuComponent m : menus) {
				m.setId(id+"");
				m.setParent_id(parentId==null?null:parentId+"");
				id = setIds(m.getComponents(), id+1, id);
			}
		}
		return id;
	}

	private void createTable(String tableName) {
		dbHelper.checkTableExist(tableName);
	//	dbHelper.insertTypeColumn(tableName); 
		for (Method m : gettersOfMenuClass) {
			String name = m.getName();
			String columnName = name.substring(3);
			dbHelper.chcekColumnExist(tableName, columnName,"varchar(200)");
		}
		
		for (Method m : gettersOfDisplayerClass) {
			String name = m.getName();
			String columnName = name.substring(3);
			dbHelper.chcekColumnExist(tableName, columnName,"varchar(200)");
		}
	}

	private void insertMenuValues(String tableName, List<MenuComponent> menus){
		if (menus != null) {
			for (MenuComponent menu : menus) {
				Map map = new HashMap();
				for (Method m : gettersOfMenuClass) {
					String value = null;
					try {
						Object obj = m.invoke(menu, null);
						value = dbHelper.filterSql(obj);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						throw new RuntimeException("方法解析出错 ");
					}
					map.put(m.getName().substring(3), value);
				}
				map.put("type_", "menu");
				dbHelper.insert(tableName, map);
				insertMenuValues(tableName,menu.getComponents());
			}
		}
	}

	/**
	 * 初始化数据库连接及一些类的初始化操作，比方说准备所有的get方法（get方法必须是baseMenu的，获得的时候要经过转换），
	 * @throws SQLException 
	 */
	private void init() throws SQLException {
		// TODO Auto-generated method stub

		/**
		 * 初始化数据库
		 */
		dbHelper = new DBHelper();
		Method[] menuMethods = MenuBase.class.getDeclaredMethods();
		for (Method m : menuMethods) {
			String methodName = m.getName();
			if (methodName.startsWith("get")) {
				gettersOfMenuClass.add(m);
			}
		}
		
		Method[] displayerMethods = MenuDisplayerMapping.class.getDeclaredMethods();
		
		for (Method m : displayerMethods) {
			String methodName = m.getName();
			if (methodName.startsWith("get")) {
				gettersOfDisplayerClass.add(m);
			}
		}

	}

	/**
	 * 关闭数据库连接及一些清理操作
	 */
	private void close() {
		// TODO Auto-generated method stub
		dbHelper.close();
	}
}
