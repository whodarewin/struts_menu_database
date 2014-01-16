package net.sf.navigator.menu.parser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import net.sf.navigator.menu.MenuRepository;
import net.sf.navigator.util.LoadableResourceException;
/**
 * the base class of parser
 * 
 * @author roger han
 * @date : 2014-1-6 下午02:43:28
 */
public abstract class Parser {
	private static Log log = LogFactory.getLog(Parser.class);

    protected  MenuRepository menuRepository;
    
    public void init(MenuRepository menuRepository){
    	this.menuRepository = menuRepository;
    }
	
    /**
     * this load function fill config into menuRepository
     * 
     * @throws LoadableResourceException
     */
	public abstract void load() throws LoadableResourceException;
	
	public abstract void reload() throws LoadableResourceException;
	
}
