package net.sf.navigator.menu;

import java.io.IOException;
import java.sql.SQLException;

import net.sf.navigator.db.Config;
import net.sf.navigator.menu.parser.Parser;
import net.sf.navigator.util.LoadableResourceException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContextException;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.xml.sax.SAXException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * This loader is available for those that use the Spring Framework.  To 
 * use it, simply configure it as follows in your applicationContext.xml file.
 * </p>
 * <pre>
 * &lt;bean id="menu" class="net.sf.navigator.menu.MenuLoader"&gt;
 *  &lt;property name="menuConfig"&gt;
 *      &lt;value&gt;/WEB-INF/menu-config.xml&lt;/value&gt;
 *   &lt;/property&gt;
 * &lt;/bean&gt;
 * </pre>
 * <p>The menuConfig property is an optional attribute.  It is set to 
 * /WEB-INF/menu-config.xml by default.</p>
 * 
 * @author Matt Raible
 */
public class MenuLoader extends WebApplicationObjectSupport {
    private static Log log = LogFactory.getLog(MenuLoader.class);

    /** Configuration file for menus */
    private String menuConfig = "/WEB-INF/menu-config.xml";

    /**
     * Set the Menu configuration file
     * @param menuConfig the file containing the Menus/Items
     */
    public void setMenuConfig(String menuConfig) {
        this.menuConfig = menuConfig;
    }

    /**
     * Initialization of the Menu Repository.
     * @throws org.springframework.context.ApplicationContextException if an error occurs
     */
    protected void initApplicationContext() throws ApplicationContextException {
        try {
            if (log.isDebugEnabled()) {
                log.debug("Starting struts-menu initialization");
            }
            ServletContext ctx = this.getServletContext();
            Config configForInit = new Config();
    		configForInit.setConfigParam(ctx.getRealPath(menuConfig));
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

    		MenuRepository repository = new MenuRepository();
    		repository.setLoadParam(menuConfig);
    		repository.setServletContext(ctx);
    		parse.init(repository);

    		try {
    			parse.load();
                ctx.setAttribute(MenuRepository.MENU_REPOSITORY_KEY, repository);

                if (log.isDebugEnabled()) {
                    log.debug("struts-menu initialization successful");
                }
            } catch (LoadableResourceException lre) {
                throw new ServletException("Failure initializing struts-menu: " +
                                           lre.getMessage());
            }
        } catch (Exception ex) {
            throw new ApplicationContextException("Failed to initialize Struts Menu repository",
                                                  ex);
        }
    }
}
