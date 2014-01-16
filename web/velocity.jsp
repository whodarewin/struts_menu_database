<%@ include file="/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <title>Velocity Menu Example</title>
        <link rel="stylesheet" type="text/css" media="screen"
            href="styles/global.css" />
    </head>
<body>

<div id="content">
Table Menu with Velocity:<br />
<menu:useMenuDisplayer name="Velocity" config="/templates/table.html"
  bundle="org.apache.struts.action.MESSAGE">
  <menu:displayMenu name="indexMenu"/>
</menu:useMenuDisplayer>

<hr />

Simple menu with Velocity:<br />
<menu:useMenuDisplayer name="Velocity" config="simple.html"
  bundle="org.apache.struts.action.MESSAGE">
  <menu:displayMenu name="ToDoListMenuFile"/>
</menu:useMenuDisplayer>

</div>

<div id="pageSource">
<strong>Files used in this page:</strong><br />
- <a href="templates/table.html.src">templates/table.html</a>
</div>

<div id="source">
    <a href="<%=request.getRequestURI()%>.src">View JSP Source</a><br />
    <a href="<%=request.getContextPath()%>/index.jsp">Back to Index</a>
</div>

</body>
</html>

