<%@ include file="/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <title>CoolMenus4 (with Velocity) Example</title>
    
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    
    <link rel="stylesheet" type="text/css" media="screen" href="styles/global.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="styles/coolmenu.css" />
    
    <script type="text/javascript" src="scripts/coolmenus4.js"></script>
    <script type="text/javascript" src="scripts/cm_addins.js"></script>

</head>
<body>

<!-- Custom Configuration for this example, must come after body to work in IE -->    
<script type="text/javascript" src="scripts/coolmenu4-config.js"></script>

<menu:useMenuDisplayer name="Velocity" config="/templates/coolmenus.html"
  bundle="org.apache.struts.action.MESSAGE">
<script type="text/javascript">
<!--
    <menu:displayMenu name="ToDoListMenuFile"/>
    <menu:displayMenu name="ToDoListMenuEdit"/>
    <menu:displayMenu name="CaseDetailMenuCase"/>
oCMenu.construct();
// -->
</script>
</menu:useMenuDisplayer>

<div id="content" style="margin-top: 50px">
The homepage for this menu is <a href="http://dhtmlcentral.com/projects/coolmenus">http://dhtmlcentral.com/projects/coolmenus</a>.
Please refer to this site and it's support system for any menu-specific questions. 
</div>

<div id="pageSource">
<strong>View source of files used in this page:</strong><br />
- <a href="templates/coolmenus.html.src">templates/coolmenus.html</a> 
    <a href="templates/coolmenus.html" 
        title="Right click and 'Save Target As...' to download">
        <img src="images/download.gif" 
        class="download" alt="download"/></a><br />
- <a href="styles/coolmenu.css.src">styles/coolmenu.css</a>
    <a href="styles/coolmenu.css" 
        title="Right click and 'Save Target As...' to download">
        <img src="images/download.gif" 
        class="download" alt="download"/></a><br />
- <a href="scripts/coolmenus4.js.src">scripts/coolmenus4.js</a>
    <a href="scripts/coolmenus4.js" 
        title="Right click and 'Save Target As...' to download">
        <img src="images/download.gif" 
        class="download" alt="download"/></a><br />
- <a href="scripts/cm_addins.js.src">scripts/cm_addins.js</a>
    <a href="scripts/cm_addins.js" 
        title="Right click and 'Save Target As...' to download">
        <img src="images/download.gif" 
        class="download" alt="download"/></a><br />
- <a href="scripts/coolmenu4-config.js.src">scripts/coolmenu4-config.js</a>
    <a href="scripts/coolmenu4-config.js" 
        title="Right click and 'Save Target As...' to download">
        <img src="images/download.gif" 
        class="download" alt="download"/></a><br />
- <a href="images/cm_fill.gif">images/cm_fill.gif</a><br />
- <a href="images/Rarrow.gif">images/Rarrow.gif</a><br />
</div>

<div id="source">
    <a href="<%=request.getRequestURI()%>.src">View JSP Source</a><br />
    <a href="<%=request.getContextPath()%>/index.jsp">Back to Index</a>
</div>

</body>
</html>
