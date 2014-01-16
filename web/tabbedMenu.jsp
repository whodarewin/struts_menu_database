<%@ include file="/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <title>Expandable List Menu Example, using DHTML</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" media="screen" href="styles/global.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="styles/tabs.css" />    
    <script type="text/javascript" src="scripts/tabs.js"></script>
</head>
<body>

<menu:useMenuDisplayer name="TabbedMenu" bundle="org.apache.struts.action.MESSAGE">
    <menu:displayMenu name="TabbedHome"/>
    <menu:displayMenu name="TabbedAbout"/>
    <menu:displayMenu name="TabbedContact"/>
    <menu:displayMenu name="TabbedExit"/>
</menu:useMenuDisplayer>


<div id="content" style="width: 60%">
    <h2>CSS Tabs with Submenus</h2>
    <p>
        Adapted from <a href="http://www.kalsey.com/tools/csstabs/">
        Adam Kalsey's demo</a>.  Changes include:
    </p>
    <ul>
        <li>Removed redundant CSS classes - now uses JavaScript, CSS and 
            Cookies to detect which menu should be active.</li>
        <li>Added XHTML Strict DOCTYPE and adjusted CSS accordingly.</li>
        <li>Integrated into <a href="http://sf.net/projects/struts-menu">
            Struts-Menu</a> as a new displayer (TabbedMenuDisplayer).</li>
    </ul>
    <p><strong>How it works</strong><br /><br />
        JavaScript is used to set the active menu when the page first loads. 
        It does this by comparing the current location with the href's
        in the menu's links.  Then it uses current.indexOf(eachLink) and
        matches the first one.  If more than one link matches the current link,
        then the cookie from the last clicked menu is used.
        <br /><br />
        As always, send any comments via e-mail to the 
        <a href="mailto:struts-menu-user@lists.sourceforge.net?subject=Tabbed Menu">
        struts-menu user mailing list</a>.
    </p>
    <p><strong>NOTE:</strong> If you do not
        have a location/page on your menu, the first item in the submenu is used.
        This menu does not currently allow for dynamically showing the submenu 
        when you mouseover the tabs, but this could probably be added easily.
     </p>
</div>

<div id="pageSource">
<strong>View source of files used in this page:</strong><br />
- <a href="templates/tabs.html.src">templates/tabs.html</a> 
    <a href="templates/tabs.html" 
        title="Right click and 'Save Target As...' to download">
        <img src="images/download.gif" 
        class="download" alt="download"/></a><br />
- <a href="styles/tabs.css.src">styles/tabs.css</a>
    <a href="styles/tabs.css" 
        title="Right click and 'Save Target As...' to download">
        <img src="images/download.gif" 
        class="download" alt="download"/></a><br />
- <a href="scripts/tabs.js.src">scripts/tabs.js</a>
    <a href="scripts/tabs.js" 
        title="Right click and 'Save Target As...' to download">
        <img src="images/download.gif" 
        class="download" alt="download"/></a><br />
</div>

<div id="source">
    <a href="<%=request.getRequestURI()%>.src">View JSP Source</a><br />
    <a href="<%=request.getContextPath()%>/index.jsp">Back to Index</a>
</div>

</body>
</html>
