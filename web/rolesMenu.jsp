<%@ include file="/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">


<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <title>Expandable List Menu Example, using DHTML</title>
    
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <link rel="stylesheet" type="text/css" media="screen" 
        href="styles/global.css" />  
        
    <link rel="stylesheet" type="text/css" media="screen" 
        href="styles/menuExpandable.css" />    

    <script type="text/javascript" src="scripts/menuExpandable.js"></script>
</head>
<body>

<menu:useMenuDisplayer name="ListMenu" 
    bundle="org.apache.struts.action.MESSAGE"
    permissions="rolesAdapter">
    <menu:displayMenu name="ToDoListMenuFile"/>
    <menu:displayMenu name="ToDoListMenuEdit"/>
    <menu:displayMenu name="Permissions"/>
</menu:useMenuDisplayer>
    
<div id="content">
This menu will hide menu items if you're not in the "roles" attribute
for the menu or item.  These roles are set in menu-config.xml.  You will
need to <a href="secure.jsp">login</a> to as tomcat/tomcat to see the 
third (permissions) menu.
</div>

<div id="source">
	<a href="<%=request.getRequestURI()%>.src">View JSP Source</a>
  <br />
  <a href="<%=request.getContextPath()%>/index.jsp">Back to Index</a>
</div>

</body>
</html>
