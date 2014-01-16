/*
 * RolesPermissionsAdapter.java
 *
 * Created on December 7, 2002 2:25 PM
 */

package net.sf.navigator.menu;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

/**
 * This class used container-managed security to check access
 * to menus.  The roles are set in menu-config.xml.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class RolesPermissionsAdapter implements PermissionsAdapter {
    private Pattern delimiters = Pattern.compile("(?<!\\\\),");
    private HttpServletRequest request;

    public RolesPermissionsAdapter(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * If the menu is allowed, this should return true.
     *
     * @return whether or not the menu is allowed.
     */
    public boolean isAllowed(MenuComponent menu) {
        if (menu.getRoles() == null) {
            return true; // no roles define, allow everyone
        } else {
            // Get the list of roles this menu allows
            String[] allowedRoles = delimiters.split(menu.getRoles());
            for (int i=0; i < allowedRoles.length; i++) {
                if (request.isUserInRole(allowedRoles[i])) {
                    return true;
                }
            }
        }
        return false;
    }


}
