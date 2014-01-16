To build this project, perform the following steps:

1. Download and install Maven (http://maven.apache.org).
2. Create a MAVEN_HOME environment variable that points to where 
   you installed Maven. Then add $MAVEN_HOME/bin to your PATH.
3. Navigate to the directory you've expanded the source to and
   execute "maven jar" to create target/struts-menu-2.x.jar.

To deploy the struts-menu example application, do the following:

1. Download and install Tomcat (http://jakarta.apache.org/tomcat).
2. Create a CATALINA_HOME environment variable that points to
   where you installed Tomcat.
3. Execute "maven deploy" to deploy the app to Tomcat.
4. Open http://localhost:8080/struts-menu in your favorite browser.

To integrate struts-menu into your webapp, do the following:

1. Place struts-menu-2.x.jar into your app's WEB-INF/lib directory.
2. Add the plug-in settings to your struts-config.xml file.
   <plug-in className="net.sf.navigator.menu.MenuPlugIn"/>
3. Create a menu-config.xml file in your app's WEB-INF directory.
   Look in web/WEB-INF for the sample app's menu-config file
   and trim it down to fit your needs.  For a complete list of 
   possible attributes, see the MenuBase class's javadocs at
   http://tinyurl.com/x5zt.
4. Add a taglib declaration to the top of your JSP:
   <%@ taglib uri="http://struts-menu.sf.net/tag" prefix="menu" %>
5. Add code to render your menu in your JSP:
   <menu:useMenuDisplayer name="TabbedMenu" 
     bundle="org.apache.struts.action.MESSAGE">
     <menu:displayMenu name="Home"/>
     <menu:displayMenu name="About"/>
   </menu:useMenuDisplayer>
