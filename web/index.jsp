<%@ include file="/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <title>Struts Menu Example Application</title>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <script type="text/javascript" src="scripts/nicetabs.js"></script>
    
    <link rel="stylesheet" type="text/css" media="screen"
        href="styles/global.css" />
    <link rel="stylesheet" type="text/css" media="screen"
      href="styles/nicetabs.css" />

</head>
<body id="index">

<div id="header">
<menu:useMenuDisplayer name="Velocity" config="/templates/indextabs.html"
    bundle="org.apache.struts.action.MESSAGE">
    <ul id="menuList">
    <menu:displayMenu name="projectMenu"/>
    </ul>
</menu:useMenuDisplayer>
</div>

<div id="content" style="width: 100%">
    <h2>Struts Menu Examples</h2>
    <p>
        Click on the menu links below to see the different types of menus
        supported by Struts Menu.
    </p>

    <menu:useMenuDisplayer name="Velocity" config="/templates/index.html">
        <ul class="glassList">
        <menu:displayMenu name="indexMenu"/>
        </ul>
    </menu:useMenuDisplayer>

</div>

<div id="source">
	<a href="<%=request.getRequestURI()%>.src">View JSP Source</a>
</div>

<div id="footer">
  <a href="http://struts-menu.sf.net">Struts Menu Homepage</a>
</div>
</body>
</html>
