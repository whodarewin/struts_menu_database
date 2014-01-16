/*
 * PermissionsAdapter.java
 *
 * Created on April 29, 2002, 10:15 PM
 */

package net.sf.navigator.menu;

/**
 * Defines a pluggable adapter into the menu framework that is used for 
 * checking permissions on menus. 
 *
 * @author  ssayles
 */
public interface PermissionsAdapter {
    
    /**
     * If the menu is allowed, this should return true.
     *
     * @return whether or not the menu is allowed.
     */
    public boolean isAllowed(MenuComponent menu);
    
}
