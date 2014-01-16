package net.sf.navigator.menu.parser;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import net.sf.navigator.db.ConnectionTool;
import net.sf.navigator.db.Environment;
import net.sf.navigator.displayer.MenuDisplayerMapping;
import net.sf.navigator.menu.MenuBase;
import net.sf.navigator.menu.MenuComponent;
import net.sf.navigator.util.LoadableResourceException;

/**
 * Parse database config to {@link MenuComponent} object
 * 
 * @author roger han
 * @date : 2014-1-6 下午02:40:21
 */

public class DBParse extends Parser {
	// ~ log
	private static final Log log = LogFactory.getLog(DBParse.class);
	// ~ Instance fields
	ConnectionTool connectionTool;
	private String menuSql;
	private String displayerSql;
	private List<Map<String, Object>> menuDataMap;
	private List<Map<String, Object>> displayerDataMap;
	private List<MenuComponent> menuComponent = new ArrayList();
	private Map<Method, String> menuSetMap = new HashMap();
	private Map<Method, String> displayerSetMap = new HashMap();
	private Map<String, MenuComponent> idToMenu = new HashMap();

	public DBParse() throws SQLException {
		// init connection to the databse
		connectionTool = new ConnectionTool();

		// create sql to select menu config data from database
		this.menuSql = "select ";
		for (Method m : MenuBase.class.getDeclaredMethods()) {
			String methodName = m.getName();
			if (m.getName().startsWith("set")) {
				String propertiesName = methodName.substring(3);
				menuSetMap.put(m, propertiesName);
				menuSql = menuSql + "," + propertiesName;
			}
		}
		menuSql = menuSql + " from " + Environment.table
				+ " where type_='menu'";
		menuSql = menuSql.replaceFirst(",", "");

		// create sql to select displayer data from database
		this.displayerSql = "select ";
		for (Method m : MenuDisplayerMapping.class.getDeclaredMethods()) {
			String methodName = m.getName();
			if (m.getName().startsWith("set")) {
				String propertiesName = methodName.substring(3);
				displayerSetMap.put(m, propertiesName);
				displayerSql = displayerSql + "," + propertiesName;
			}
		}
		displayerSql = displayerSql + " from " + Environment.table
				+ " where type_='displayer'";
		displayerSql = displayerSql.replaceFirst(",", "");
	}

	// ~ method
	// Need to override method
	public void load() throws LoadableResourceException {
		// package menu data
		this.getMenuData();
		this.convertMenuDataToObject();
		this.establishMenuRelationship();

		// package displayer data
		this.getDisplayerData();
		this.convertDisplayerDataToObject();

		this.distroy();
	}

	/**
	 * get original data from database
	 */
	private void getMenuData() {
		// TODO Auto-generated method stub
		menuDataMap = connectionTool.getData(menuSql);
	}

	/**
	 * convert the original menu data to {@link MenuComponent}
	 */
	private void convertMenuDataToObject() {
		for (Map<String, Object> dataMap : menuDataMap) {
			MenuComponent menu = new MenuComponent();
			for (Method m : menuSetMap.keySet()) {
				try {
					System.out.println();
					log.debug("set的方法" + m.getName());
					m.invoke(menu, dataMap.get(m.getName().substring(3)));
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("set方法出错");
				}
			}
			menuComponent.add(menu);
			idToMenu.put(menu.getId(), menu);
		}

	}

	/**
	 * establish the father-son relationship of the {@link MenuComponent}
	 */
	private void establishMenuRelationship() {
		for (MenuComponent menu : menuComponent) {
			MenuComponent parentMenu = idToMenu.get(menu.getParent_id());
			if (parentMenu == null) {
				super.menuRepository.addMenu(menu);
				continue;
			}
		//menu.setParent(parentMenu);
			parentMenu.addMenuComponent(menu);
		}
	}

	private void getDisplayerData() {
		this.displayerDataMap = connectionTool.getData(this.displayerSql);
	}

	private void convertDisplayerDataToObject() {
		for (Map<String, Object> dataMap : displayerDataMap) {
			MenuDisplayerMapping displayer = new MenuDisplayerMapping();
			for (Method m : displayerSetMap.keySet()) {
				try {
					log.debug("setting:" + m.getName());
					m.invoke(displayer, dataMap.get(m.getName().substring(3)));
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("get exception from "+m.getDeclaringClass()+":"+m.getName());
				}
			}
			this.menuRepository.addMenuDisplayerMapping(displayer);
		}

	}

	private void distroy() {
		connectionTool.distroy();
	}

	@Override
	public void reload() throws LoadableResourceException {
		menuDataMap.clear();
		menuSetMap.clear();
		displayerSetMap.clear();
		idToMenu.clear();
		menuRepository.clear();
		load();
	}

}
