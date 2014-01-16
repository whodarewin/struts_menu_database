/*
 * PermissionsFormMenuDisplayer.java
 *
 * Created on April 30, 2002, 12:25 AM
 */
package net.sf.navigator.example;

import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.jsp.JspException;

import net.sf.navigator.displayer.SimpleMenuDisplayer;
import net.sf.navigator.menu.MenuComponent;


/**
 *
 * @author  ssayles
 */
public class PermissionsFormMenuDisplayer extends SimpleMenuDisplayer {
    //~ Static fields/initializers =============================================

    private static final MessageFormat inputMessage =
        new MessageFormat(
            "<input type=\"checkbox\" name=\"menus\" value=\"{0}\"/>");
    private static final MessageFormat itemMessage =
        new MessageFormat(
            "<tr><td class=\"smd-menu-item\">{0} {1} {2} {3}</td></tr>");

    //~ Methods ================================================================

    protected void displayComponents(MenuComponent menu, int level)
    throws JspException, IOException {
        String title = getMessage(menu.getTitle());
        MenuComponent[] components = menu.getMenuComponents();

        if (components.length > 0) {
            out.println(displayStrings.getMessage("smd.menu.item.top",
                    getSpace(level) +
                    displayStrings.getMessage("smd.menu.item.image.bullet") +
                    getMenuInput(menu) + getMessage(menu.getTitle())));

            for (int i = 0; i < components.length; i++) {
                if (components[i].getMenuComponents().length > 0) {
                    displayComponents(components[i], level + 1);
                } else {
                    out.println(getMenuItem(components[i], getSpace(level + 1)));
                }
            }
        } else {
            out.println(getMenuItem(menu, ""));
        }
    }

    private String getMenuItem(MenuComponent menu, String space) {
        return itemMessage.format(new String[] {
                space, getMenuInput(menu), getImage(menu),
                getMessage(menu.getTitle())
            });
    }

    private String getMenuInput(MenuComponent menu) {
        return inputMessage.format(new String[] { menu.getName() });
    }
}
