package net.sf.navigator.db;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import net.sf.navigator.menu.parser.DBParse;
import net.sf.navigator.menu.parser.Parser;
import net.sf.navigator.menu.parser.XMLParser;
import net.sf.navigator.migrate.XmlToDatabase;
import net.sf.navigator.util.LoadableResourceException;

import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

/**
 * this class is used to get config from struts-menu.xml file;
 * 
 * @author roger han
 * @date : 2014-1-6 下午02:39:07
 */
public class Config {
	//~ Static fields===================================================
	private static final String DATABASE = "DATABASE";
	private static final String XML = "XML";
	private static final String CLASS = "CLASS";
	//~ Instance fields=================================================
	/**
	 * the location of strurs-menu.xml
	 */
	private String configParam;
	private Environment configProperties;
	
	//~ init =======================================================================
	public void init() throws LoadableResourceException, IOException, SAXException, SQLException{
		Digester digester = new Digester();
		digester.setClassLoader(Thread.currentThread().getContextClassLoader());
		digester.addObjectCreate("MenuConfig/config", "net.sf.navigator.db.Environment", "type");
		digester.addSetProperties("MenuConfig/config");
		digester.addCallMethod("MenuConfig/config/jdbcClassName", "setJdbcClassName",0);
		digester.addCallMethod("MenuConfig/config/url", "setUrl",0);
		digester.addCallMethod("MenuConfig/config/username", "setUsername",0);
		digester.addCallMethod("MenuConfig/config/password", "setPassword",0);
		digester.addCallMethod("MenuConfig/config/location", "setLocation",0);
		digester.addCallMethod("MenuConfig/config/className", "setClassName",0);
		digester.addCallMethod("MenuConfig/config/table", "setTable",0);
		
		try {
			configProperties = (Environment)digester.parse(new File(configParam));
		} catch (Exception e) {
			e.printStackTrace();
		}
		configProperties.init();
		if(configProperties.getMoveToDatabase()){
			XmlToDatabase xml = new XmlToDatabase();
			xml.move(this);
		}
	}
	
	//~Methods ==================================================================
	
	/**
	 * return the Parser that used
	 * @return
	 * @throws Exception
	 */
	public Parser getParser() throws Exception{
		String configLocation = configProperties.getConfigLocation().toUpperCase();
		if(configLocation.equals(DATABASE)){
			return new DBParse();
		}else if(configLocation.equals(XML)){
			return new XMLParser();
		}else if(configLocation.equals(CLASS)){
			
			Class<Parser> clazz = (Class<Parser>) Class.forName(configProperties.getClassName());
			return clazz.newInstance();
		}else{
			//if none of the parser is defined ,return XMLParser;
			return new XMLParser();
		}
		//throw new LoadableResourceException("Error parsing resource file: " + configParam + "unrecognized param"+configLocation);
	}

	public String getConfigParam() {
		return configParam;
	}

	public void setConfigParam(String configParam) {
		this.configParam = configParam;
	}

	public Environment getConfigProperties() {
		return configProperties;
	}

	public void setConfigProperties(Environment configProperties) {
		this.configProperties = configProperties;
	}
	
	
}
