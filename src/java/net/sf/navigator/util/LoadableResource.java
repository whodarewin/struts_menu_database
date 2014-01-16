/*
 * LoadableResource.java
 *
 * Created on January 28, 2000, 11:38 AM
 */
package net.sf.navigator.util;

import javax.servlet.ServletContext;


/**
 * Defines the interface for a loadable web application resource.
 *
 * @author  Scott Sayles
 * @version
 */
public interface LoadableResource {
    //~ Methods ================================================================

    public void setLoadParam(String loadParam);

    public String getLoadParam();

    public void setName(String name);

    public String getName();

//    public void load() throws LoadableResourceException;

    public void clear() throws LoadableResourceException;

    public ServletContext getServletContext();

    public void setServletContext(ServletContext servletContext);
}
