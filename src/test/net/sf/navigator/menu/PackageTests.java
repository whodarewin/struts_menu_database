package net.sf.navigator.menu;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class PackageTests extends TestCase {

    public PackageTests(String arg0) {
        super(arg0);
    }

    public static Test suite()
    {
        TestSuite suite = new TestSuite("net.sf.navigator.menu");
        suite.addTest(MenuRepositoryTest.suite());
        suite.addTest(MenuComponentTest.suite());
        return suite;
    }
}
