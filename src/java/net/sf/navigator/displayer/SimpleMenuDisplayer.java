/*
 * SimpleMenuDisplayer.java
 *
 * Created on February 15, 2001, 11:14 AM
 */
package net.sf.navigator.displayer;

import net.sf.navigator.menu.MenuComponent;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import java.io.IOException;


/**
 *
 * @author  ssayles
 * @version
 */
public class SimpleMenuDisplayer extends MessageResourcesMenuDisplayer {
    //~ Static fields/initializers =============================================

    protected static final String nbsp = NBSP;

    //~ Methods ================================================================

    public void init(PageContext pageContext, MenuDisplayerMapping mapping) {
        super.init(pageContext, mapping);

        try {
            out.println(displayStrings.getMessage("smd.style"));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void display(MenuComponent menu) throws JspException, IOException {
        if (isAllowed(menu)) {
            out.println(displayStrings.getMessage("smd.menu.top"));
            displayComponents(menu, 0);
            out.println(displayStrings.getMessage("smd.menu.bottom"));
        }
    }

    protected void displayComponents(MenuComponent menu, int level)
    throws JspException, IOException {
        MenuComponent[] components = menu.getMenuComponents();

        if (components.length > 0) {
            out.println(displayStrings.getMessage("smd.menu.item.top",
                    getSpace(level) +
                    displayStrings.getMessage("smd.menu.item.image.bullet") +
                    getMessage(menu.getTitle())));

            for (int i = 0; i < components.length; i++) {
                if (components[i].getMenuComponents().length > 0) {
                    if (isAllowed(components[i])) {
                        displayComponents(components[i], level + 1);
                    }
                } else {
                    if (isAllowed(components[i])) {
                        out.println(displayStrings.getMessage("smd.menu.item",
                                    components[i].getUrl(),
                                    super.getMenuTarget(components[i]),
                                    super.getMenuToolTip(components[i]),
                                    this.getSpace(level + 1) + getImage(components[i]) +
                                    this.getMessage(components[i].getTitle())));
                    }
                }
            }
        } else {
            out.println(displayStrings.getMessage("smd.menu.item",
                    menu.getUrl(), super.getMenuTarget(menu),
                    super.getMenuToolTip(menu),
                    this.getSpace(level) + getImage(menu) +
                    getMessage(menu.getTitle())));
        }
    }

    protected String getSpace(int length) {
        String space = EMPTY;

        for (int i = 0; i < length; i++) {
            space = space + nbsp + nbsp;
        }

        return space;
    }

    protected String getImage(MenuComponent menu) {
        String imageTag;

        if ((menu.getImage() == null) || (menu.getImage().equals(EMPTY))) {
            imageTag = EMPTY;
        } else {
            imageTag =
                displayStrings.getMessage("smd.menu.item.image",
                    menu.getImage(), super.getMenuToolTip(menu));
        }

        return imageTag;
    }
}
