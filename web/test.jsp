<%@ include file="/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <title>Velocity Menu Example</title>
        <link rel="stylesheet" type="text/css" media="screen" 
            href="styles/global.css" />  
            
        <link rel="stylesheet" type="text/css" media="screen"
            href="styles/coolmenu.css" />
        
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

Simple menu with Velocity:<br />
<menu:useMenuDisplayer name="Velocity" config="/templates/table.html"
  bundle="org.apache.struts.action.MESSAGE">
  <menu:displayMenu name="ToDoListMenuFile"/>
</menu:useMenuDisplayer>


</body>
</html>
