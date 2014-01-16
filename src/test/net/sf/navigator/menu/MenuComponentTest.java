package net.sf.navigator.menu;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;

/**
 * @author DEPeart
 *
 */
public class MenuComponentTest extends TestCase {

    public MenuComponentTest(String name) {
        super(name);
    }

    static MenuComponent masterMenu;

    public static Test suite() {
        TestSuite suite = new TestSuite(MenuComponentTest.class);

        return new TestSetup(suite) {
            protected void setUp() throws Exception {
                masterMenu = buildMenu();
            }

            protected void tearDown() throws Exception {
            }
        };
    }

    private static MenuComponent buildMenu() {
        MenuComponent complex1 = new MenuComponent();
        complex1.setName("complex1");
        MenuComponent complex2 = new MenuComponent();
        complex2.setName("complex1.1");
        complex2.setParent(complex1);
        MenuComponent complex3 = new MenuComponent();
        complex3.setName("complex1.2");
        complex3.setParent(complex1);
        MenuComponent complex5 = new MenuComponent();
        complex5.setName("complex1.2.1");
        complex5.setParent(complex3);
        MenuComponent complex4 = new MenuComponent();
        complex4.setName("complex1.3");
        complex4.setParent(complex1);
        return complex1;
    }

    /**
     * Ensure we remove the child menus for a parent menu
     */
    public void testRemoveChildren() {
        MenuComponent parent = buildMenu();
        assertEquals(3, parent.getComponents().size());
        parent.removeChildren();
        assertEquals(0, parent.getComponents().size());
    }

    /**
     * Get the depth of a menu
     */
    public void testGetMenuDepth() {
        assertEquals(3,masterMenu.getMenuDepth());
    }

    /**
     * Build the breadcrumb name for a top menu item
     */
    public void testSetBreadCrumbTopMenu() {
        MenuComponent menu = new MenuComponent();
        menu.setName("parent");
        menu.setBreadCrumb(".");
        assertEquals("parent",menu.getBreadCrumb());
    }

    /**
     * Build the breadcrumb name for child menu
     */
    public void testSetBreadCrumbChildMenu() {
        masterMenu.setBreadCrumb(":");
        List menus = masterMenu.getComponents();
        MenuComponent menu = (MenuComponent) menus.get(0);
        menu.setBreadCrumb(":");
        assertEquals("complex1:complex1.1", menu.getBreadCrumb());
    }
}
