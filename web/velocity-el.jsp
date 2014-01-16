<%@ include file="/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <title>Expression Language Example</title>
        <link rel="stylesheet" type="text/css" media="screen"
            href="styles/global.css" />
    </head>
<body>

<div id="content">
<strong>Expression Language Menu:</strong>
<br /> &nbsp;- defaults to using JSTL's ResourceBundle<br />
<c:set var="displayer" value="Velocity"/>
<c:set var="menuName" value="ToDoListMenuFile"/>

<!-- For the EL tag, you can specify a global ResourceBundle in web.xml using
     the following code:
     
     <context-param>
        <param-name>javax.servlet.jsp.jstl.fmt.localizationContext</param-name>
        <param-value>TrackerRes</param-value>
     </context-param>
    
     You can also set it manually using: <fmt:setBundle basename="TrackerRes"/>
     
     Both the EL and regular tag will default to Struts Messages.
-->

<menu-el:useMenuDisplayer name="${displayer}" config="/templates/table.html">
  <menu-el:displayMenu name="${menuName}"/>
</menu-el:useMenuDisplayer>

<hr />

<strong>Normal Menu:</strong>
<br /> &nbsp;- defaults to using Struts's ResourceBundle<br />

<menu:useMenuDisplayer name="Velocity" config="/templates/table.html">
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

