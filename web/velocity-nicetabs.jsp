<%@ include file="/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <title>Nice Tabs! Menu (with Velocity) Example</title>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <link rel="stylesheet" type="text/css" media="screen" href="styles/global.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="styles/nicetabs.css" />

    <script type="text/javascript" src="scripts/nicetabs.js"></script>
</head>
<body id="nicetabs">

<div id="header">
<menu:useMenuDisplayer name="Velocity" config="/templates/nicetabs.html"
    bundle="org.apache.struts.action.MESSAGE">
    <ul class="menuList">
    <menu:displayMenu name="TabbedHome"/>
    <menu:displayMenu name="TabbedAbout"/>
    <menu:displayMenu name="TabbedContact"/>
    <menu:displayMenu name="TabbedExit"/>
    </ul>
</menu:useMenuDisplayer>
</div>

<div id="content">
    <h2>Nice Tabs (a.k.a. Sliding Doors of CSS)</h2>
    <p>
        Nice tabs adapted from A List Apart's 
        <a href="http://www.alistapart.com/articles/slidingdoors2/">Sliding Doors of CSS, Part II</a>.
    </p>
</div>

<div id="pageSource">
<strong>View source of files used in this page:</strong><br />
- <a href="templates/nicetabs.html.src">templates/nicetabs.html</a> 
    <a href="templates/nicetabs.html" 
        title="Right click and 'Save Target As...' to download">
        <img src="images/download.gif" 
        class="download" alt="download"/></a><br />
- <a href="styles/nicetabs.css.src">styles/nicetabs.css</a>
    <a href="styles/nicetabs.css" 
        title="Right click and 'Save Target As...' to download">
        <img src="images/download.gif" 
        class="download" alt="download"/></a><br />
- <a href="scripts/nicetabs.js.src">scripts/nicetabs.js</a>
    <a href="scripts/nicetabs.js" 
        title="Right click and 'Save Target As...' to download">
        <img src="images/download.gif" 
        class="download" alt="download"/></a><br />
- <a href="images/bg.gif">images/bg.gif</a><br />
- <a href="images/left_both.gif">images/left_both.gif</a><br />
- <a href="images/right_both.gif">images/right_both.gif</a><br />
</div>

<div id="source">
    <a href="<%=request.getRequestURI()%>.src">View JSP Source</a><br />
    <a href="<%=request.getContextPath()%>/index.jsp">Back to Index</a>
</div>

</body>
</html>
