package net.sf.navigator.menu;

import java.util.ArrayList;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author DEPeart
 *
 */
public class MenuRepositoryTest extends TestCase {

	public MenuRepositoryTest(String name) {
		super(name);
	}

	static MenuRepository repository;

	public static Test suite() {
		TestSuite suite = new TestSuite(MenuRepositoryTest.class);

		return new TestSetup(suite) {
			protected void setUp() throws Exception {
				repository = buildRepository();
			}

			protected void tearDown() throws Exception {
			}
		};
	}

	public void setUp() throws Exception {
	}

	protected void tearDown() throws Exception {
	}

	private static MenuRepository buildRepository() {
		MenuRepository rep = new MenuRepository();
		MenuComponent parent = new MenuComponent();
		parent.setDescription("parentMenuName");
		parent.setName("parent");
		MenuComponent child = new MenuComponent();
		child.setDescription("childMenuName");
		child.setName("child");
		child.setParent(parent);
		rep.addMenu(parent);
		MenuComponent menu = new MenuComponent();
		menu.setDescription("menu");
		menu.setName("menu");
		rep.addMenu(menu);
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
		rep.addMenu(complex1);
		return rep;
	}

	/**
	 * Ensure we return a child of a parent menu.
	 * @return Success
	 */
	public void testGetMenu() {

		MenuComponent menu = repository.getMenu("parent.child", ".");
		assertEquals("childMenuName", menu.getDescription());
	}

	/**
	 * Ensure we return a parent menu only
	 * @return Success
	 */
	public void testGetMenuParent() {

		MenuComponent menu = repository.getMenu("parent", ".");
		assertEquals("parentMenuName", menu.getDescription());
	}

	/**
	 * Test no menu found.  
	 * @return null value. Failure
	 */
	public void testGetMenuInvalidMenuName() {

		MenuComponent menu = repository.getMenu("failure", ".");
		assertNull(menu);
	}

	/**
	 * Test invalid delimiter, but first menu level requested and so will be found
	 * @return Sucess
	 */
	public void testGetMenuInvalidDelimiterParentMenu() {

		MenuComponent menu = repository.getMenu("parent", ",");
		assertEquals("parentMenuName", menu.getDescription());
	}

	/**
	 * Test no menu found, because of invalid delimiter, when a child menu level
	 * @return null value. Failure
	 */
	public void testGetMenuInvalidDelimiter() {

		MenuComponent menu = repository.getMenu("parent.child", ",");
		assertNull(menu);
	}

	/**
	 * Test for a null repository
	 * @return null value. Failure
	 */
	public void testGetMenuNewRepository() {

		MenuComponent menu = repository.getMenu("parent.child", ",");
		assertNull(menu);
	}

	/**
	 * Test for a null delimiter
	 * @exception NullPointerException. Failure
	 */
	public void testGetMenuNoDelimiter() {

		try {
			MenuComponent menu = repository.getMenu("parent.child", null);
			fail("Should have thrown a NullPointerException");
		} catch (NullPointerException e) {
		}
	}

	/**
	 * Test for a null menuName
	 * @exception NullPointerException. Failure
	 */
	public void testGetMenuNoMenuName() {

		try {
			MenuComponent menu = repository.getMenu(null, ".");
			fail("Should have thrown a NullPointerException");
		} catch (NullPointerException e) {
		}
	}

	/**
	 * Test for an empty menuName
	 * @return null value. Failure
	 */
	public void testGetMenuEmptyMenuName() {

		MenuComponent menu = repository.getMenu("", ".");
		assertNull(menu);
	}

	/**
	 * Test for an empty delimiter
	 * @return null value. Failure
	 */
	public void testGetMenuEmptyDelimiter() {

		MenuComponent menu = repository.getMenu("parent.child", "");
		assertNull(menu);
	}

	/**
	 * Test for a null menuName
	 * @return -1
	 */
	public void testGetMenuDepthNullMenuName() {

		assertEquals(-1, repository.getMenuDepth(null));
	}

	/**
	 * Test for an invalid menuName
	 * @return -1
	 */
	public void testGetMenuDepthNoMenuName() {

		assertEquals(-1, repository.getMenuDepth("noMenu"));
	}

	/**
	 * Test for a depth of 1 menu level
	 * @return 1
	 */
	public void testGetMenuDepthOf1() {

		assertEquals(1, repository.getMenuDepth("menu"));
	}

	/**
	 * Test for a depth of 2 menu levels
	 * @return 2
	 */
	public void testGetMenuDepthOf2() {

		assertEquals(2, repository.getMenuDepth("parent"));
	}

	/**
	 * Test for a depth of 3 menu levels
	 * @return 3
	 */
	public void testGetMenuDepthOf3() {

		assertEquals(3, repository.getMenuDepth("complex1"));
	}

	/**
	 * Test for a depth of 3 menu levels
	 * @return 3
	 */
	public void testGetMenuDepthAll() {

		assertEquals(3, repository.getMenuDepth());
	}

	/**
	 * Test returning the top menus within an array
	 * @return 3
	 */
	public void testGetTopMenusAsArray() {

		MenuComponent[] menus = repository.getTopMenusAsArray();
		assertEquals(3, menus.length);
	}

	/**
	 * Test removing all menus from the repository
	 * @return 0
	 */
	public void testRemoveAllMenus() {

		MenuRepository rep = buildRepository();
		assertEquals(3, rep.getTopMenus().size());
		rep.removeAllMenus();
		assertEquals(0, rep.getTopMenus().size());
	}

	/**
	 * Test get a list of the top menus' names
	 * @return Success
	 */
	public void testGetTopMenusNames() {
		MenuRepository rep = buildRepository();
		ArrayList names = (ArrayList) rep.getTopMenusNames();
		assertEquals(3, names.size());
		assertEquals("parent", ((String) names.get(0)));
	}

	/**
	 * Test building the breadcrumbs but no delimiter
	 * @return Success
	 */
	public void testBuildBreadCrumbNullPointerException() {
		MenuRepository rep = buildRepository();
		try {
			rep.buildBreadCrumbs();
			fail("NullPointerException expected");
		} catch (NullPointerException e) {
		}
	}

	/**
	 * Test building the breadcrumbs passing the delimiter
	 * @return Success
	 */
	public void testBuildBreadCrumbPassingDelimiter() {
		MenuRepository rep = buildRepository();
		rep.buildBreadCrumbs(":");
		MenuComponent menu = rep.getMenu("parent");
		assertEquals("parent",menu.getBreadCrumb());
		MenuComponent[] menus = menu.getMenuComponents();
		assertEquals("parent:child",menus[0].getBreadCrumb());		
	}

}
