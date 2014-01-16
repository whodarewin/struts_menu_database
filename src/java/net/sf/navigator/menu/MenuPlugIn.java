/*
 * MenuPlugin.java
 *
 * Created on March 6, 2002, 10:04 PM
 */
package net.sf.navigator.menu;


import java.io.IOException;
import java.sql.SQLException;

import net.sf.navigator.db.Config;
import net.sf.navigator.menu.parser.Parser;
import net.sf.navigator.util.LoadableResourceException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.apache.velocity.runtime.directive.Parse;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


/**
 * Struts plug-in adapter for the menuing module.
 * @author  ssayles
 */
public class MenuPlugIn implements PlugIn {
    //~ Instance fields ========================================================

    /**
     * The <code>Log</code> instance for this class.
     */
    private static Log log = LogFactory.getLog(MenuPlugIn.class);
    private MenuRepository repository;
    private String menuConfig = "/WEB-INF/menu-config.xml";
    private HttpServlet servlet;

    //~ Methods ================================================================

    public String getMenuConfig() {
        return menuConfig;
    }

    public void setMenuConfig(String menuConfig) {
        this.menuConfig = menuConfig;
    }

    public void init(ActionServlet servlet, ModuleConfig config)
    throws ServletException {
        if (log.isDebugEnabled()) {
            log.debug("Starting struts-menu initialization");
        }

        this.servlet = servlet;
		Config configForInit = new Config();
		configForInit.setConfigParam(servlet.getServletContext().getRealPath(menuConfig));
		try {
			configForInit.init();
		} catch (LoadableResourceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Parser parse = null;
		try {
			parse = configForInit.getParser();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        repository = new MenuRepository();
        repository.setLoadParam(menuConfig);
        repository.setServletContext(servlet.getServletContext());
        parse.init(repository);
        try {
            //repository.load();
        	parse.load();
            servlet.getServletContext().setAttribute(MenuRepository.MENU_REPOSITORY_KEY, repository);

            if (log.isDebugEnabled()) {
                log.debug("struts-menu initialization successful");
            }
        } catch (LoadableResourceException lre) {
            throw new ServletException("Failure initializing struts-menu: " +
                lre.getMessage());
        }
    }

    public void destroy() {
        repository = null;
        servlet.getServletContext().removeAttribute(MenuRepository.MENU_REPOSITORY_KEY);
        menuConfig = null;
        servlet = null;
    }
}
