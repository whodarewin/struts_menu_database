/*
 * VelocityMenuDisplayer.java
 *
 * Created on December 7, 2002, 12:35 AM
 */
package net.sf.navigator.displayer;

import net.sf.navigator.menu.MenuComponent;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.app.tools.VelocityFormatter;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.tools.view.servlet.ServletLogger;
import org.apache.velocity.tools.view.servlet.WebappLoader;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

/**
 * @author  <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 * @version 1.0
 */
public class VelocityMenuDisplayer extends MessageResourcesMenuDisplayer {
    protected static final Log log = LogFactory.getLog(VelocityMenuDisplayer.class);
    private static VelocityEngine velocityEngine = new VelocityEngine();
    private PageContext pageContext = null;

    public static void initialize(ServletContext context) {
        velocityEngine.setApplicationAttribute(ServletContext.class.getName(), context);

        // default to servletlogger, which logs to the servlet engines log
        velocityEngine.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS,
                             ServletLogger.class.getName());

        // by default, load resources with webapp resource loader
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "webapp");
        velocityEngine.setProperty("webapp.resource.loader.class", WebappLoader.class.getName());

        // now all is ready - init Velocity
        try {
            Properties props = new Properties();
            ResourceBundle defaults = ResourceBundle.getBundle("net.sf.navigator.displayer.velocity");
            for (Enumeration keys = defaults.getKeys(); keys.hasMoreElements();) {
                String key = (String) keys.nextElement();
                props.put(key, defaults.getString(key));
            }

            // look to see if the user has overridden velocity.properties by
            // placing velocity.properties in WEB-INF/classes
            ResourceBundle custom = null;

            try {
                custom = ResourceBundle.getBundle("velocity");
                for (Enumeration keys = custom.getKeys(); keys.hasMoreElements();) {
                    String key = (String) keys.nextElement();
                    props.put(key, custom.getString(key));
                }
            } catch (MissingResourceException mre) {
                log.debug("No velocity.properties found in classpath, using default settings");
            }

            velocityEngine.init(props);
        } catch (Exception e) {
            log.error("Error initializing Velocity: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //~ Methods ================================================================

    public void init(PageContext pageContext, MenuDisplayerMapping mapping) {
        super.init(pageContext, mapping);
        this.pageContext = pageContext;
    }

    public void display(MenuComponent menu) throws JspException, IOException {
        if (isAllowed(menu)) {
            displayComponents(menu);
        }
    }

    protected void displayComponents(MenuComponent menu)
    throws JspException, IOException {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        Template t;

        try {
            String template = getConfig();

            if (template == null) {
                throw new JspException("You must specify a template using the 'config' attribute.");
            } else {
                log.debug("using template: " + template);
            }

            t = velocityEngine.getTemplate(template);
        } catch (Exception e) {
            String msg = "Error initializing Velocity: " + e.toString();
            log.error(msg, e);
            throw new JspException(msg, e);
        }

        StringWriter sw = new StringWriter();
        VelocityContext context = new VelocityContext();

        context.put("formatter", new VelocityFormatter(context));
        context.put("now", Calendar.getInstance().getTime());
        context.put("ctxPath", request.getContextPath());
        // add a helper class for string manipulation
        context.put("stringUtils", new StringUtils());

        // add a Map for use by the Expandable List Menu
        context.put("map", new HashMap());

        // see if a name and property were passed in
        if (!StringUtils.isEmpty(name)) {
            Object val1 = pageContext.findAttribute(name);

            if (val1 != null) {
                context.put(name, val1);
            }
        }

        // request-scope attributes
        Enumeration e = request.getAttributeNames();

        while (e.hasMoreElements()) {
            String name = (String) e.nextElement();
            Object value = request.getAttribute(name);
            context.put(name, value);
        }

        context.put("request", request);
        context.put("session", request.getSession(false));

        if (pageContext.getAttribute("menuId") != null) {
            context.put("menuId", pageContext.getAttribute("menuId"));
        }
        context.put("menu", menu);
        context.put("displayer", this);

        try {
            t.merge(context, sw);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new JspException(ex);
        }

        String result = sw.getBuffer().toString();

        // Print this element to our output writer
        pageContext.getOut().print(result);
    }

    public void end(PageContext context) {
        this.pageContext = null;
    }
}
