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

<jsp:include page="listMenu.jsp" flush="true"/>
 
<div id="content">
This menu is based on <a href="http://www.gazingus.org/dhtml/?id=109">this example</a>
from Dave Lindquist.
</div>

<div id="pageSource">
<strong>View source of files used in this page:</strong><br />
- <a href="styles/menuExpandable.css.src">styles/menuExpandable.css</a>
    <a href="styles/menuExpandable.css" 
        title="Right click and 'Save Target As...' to download">
        <img src="images/download.gif" 
        class="download" alt="download"/></a><br />
- <a href="scripts/menuExpandable.js.src">scripts/menuExpandable.js</a>
    <a href="scripts/menuExpandable.js" 
        title="Right click and 'Save Target As...' to download">
        <img src="images/download.gif" 
        class="download" alt="download"/></a><br />
- <a href="images/minus.gif">images/minus.gif</a><br />
- <a href="images/plus.gif">images/plus.gif</a><br />
</div>

<div id="source">
	<a href="<%=request.getRequestURI()%>.src">View JSP Source</a>
  <br />
  <a href="<%=request.getContextPath()%>/index.jsp">Back to Index</a>
</div>

</body>
</html>
