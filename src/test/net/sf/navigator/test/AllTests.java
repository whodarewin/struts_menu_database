package net.sf.navigator.test;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AllTests extends TestCase {

	public AllTests(String arg0) {
		super(arg0);
	}

	public static Test suite() {

		TestSuite suite = new TestSuite("AllTests");
		suite.addTest(net.sf.navigator.menu.PackageTests.suite());
		return suite;
	}
}
