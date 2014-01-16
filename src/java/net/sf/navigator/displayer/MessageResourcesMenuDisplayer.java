/*
 * MessageResourcesMenuDisplayer.java
 *
 * Created on February 6, 2001, 3:34 PM
 */
package net.sf.navigator.displayer;

import net.sf.navigator.menu.MenuComponent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.MessageResources;

import javax.servlet.jsp.JspException;
import java.io.IOException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;


/**
 *
 * @author  ssayles
 * @version
 */
public abstract class MessageResourcesMenuDisplayer extends AbstractMenuDisplayer {
    protected final transient Log log = LogFactory.getLog(getClass());

    //~ Instance fields ========================================================

    protected Object messages = null;
    protected Locale locale = null;

    //~ Methods ================================================================

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Object getMessageResources() {
        return messages;
    }

    public void setMessageResources(Object messages) {
        this.messages = messages;
    }

    /**
     * Get the title key from the bundle (if it exists).  This method
     * is public to expose it to Velocity.
     * 
     * @param key the key
     */
    public String getMessage(String key) {
        String message = null;

        if (messages != null && messages instanceof ResourceBundle) {
            if (log.isDebugEnabled()) {
                log.debug("Looking up string '" + key + "' in ResourceBundle");
            }
            ResourceBundle bundle = (ResourceBundle) messages;
            try {
                message = bundle.getString(key);
            } catch (MissingResourceException mre) {
                message = null;
            }
        } else if (messages != null) {
            if (log.isDebugEnabled()) {
                log.debug("Looking up message '" + key + "' in Struts' MessageResources");
            }
            // this is here to prevent a non-struts webapp from throwing a NoClassDefFoundError
            if ("org.apache.struts.util.PropertyMessageResources".equals(messages.getClass().getName())) {
                MessageResources resources = (MessageResources) messages;
                try {
                    if (locale != null) {
                        //Method method = clazz.getMethod("getMessage", new Class[] {Locale.class, String.class});
                        message = resources.getMessage(locale, key);
                    } else {
                        message = resources.getMessage(key);
                    }
                } catch (Throwable t) {
                    message = null;
                }
            }
        } else {
            message = key;
        }

        if (message == null) {
            message = key;
        }

        return message;
    }

    /**
     * Get the menu's target (if it exists).  This method
     * is public to expose it to Velocity.
     *
     * @param menu
     */
    public String getMenuTarget(MenuComponent menu) {
        String menuTarget;

        if (this.target != null) {
            menuTarget = target;
        } else {
            if (menu.getTarget() != null) {
                menuTarget = menu.getTarget();
            } else {
                menuTarget = MenuDisplayer._SELF;
            }
        }

        return menuTarget;
    }

    /**
     * Get the menu's tooltip (if it exists).  This method
     * is public to expose it to Velocity.
     *
     * @param menu
     */
    public String getMenuToolTip(MenuComponent menu) {
        String tooltip;

        if (menu.getToolTip() != null) {
            tooltip = this.getMessage(menu.getToolTip());
        } else {
            tooltip = this.getMessage(menu.getTitle());
        }

        return tooltip;
    }

    public String getMenuOnClick(MenuComponent menu) {
        if (menu.getOnclick() != null) {
            return " onclick=\"" + menu.getOnclick() + "\"";
        }
        return "";
    }

    public abstract void display(MenuComponent menu)
    throws JspException, IOException;
}
