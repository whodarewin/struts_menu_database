<%@ include file="/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <title>XTree (with Velocity) Example</title>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <link rel="stylesheet" type="text/css" media="screen"
        href="styles/global.css" />
    <link rel="stylesheet" type="text/css" media="screen"
        href="styles/xtree.css" />
    <style type="text/css">
    div.container {
        position: absolute;
        width: 200px;
        top: 0px;
        left: 0px;
        height: 100%;
        padding: 5px;
        overflow: auto;
    }
    </style>
    <script type="text/javascript" src="scripts/xtree.js"></script>

</head>
<body>

<div class="container">
<script type="text/javascript">
<menu:useMenuDisplayer name="Velocity" config="/templates/xtree.html"
  bundle="org.apache.struts.action.MESSAGE">
if (document.getElementById) {
    <menu:displayMenu name="ToDoListMenuFile"/>
    <menu:displayMenu name="ToDoListMenuEdit"/>
    <menu:displayMenu name="CaseDetailMenuCase"/>
} else {
  var msg = "Your browser does not support document.getElementById().\n";
    msg += "You must use a modern browser for this menu.";
  alert(msg);
}
</menu:useMenuDisplayer>
</script>
</div>

<div id="content" style="margin-left: 150px">
The homepage for this menu is <a href="http://webfx.eae.net/dhtml/xtree/index.html">http://webfx.eae.net/dhtml/xtree/index.html</a>.
Please refer to this site and it's support system for any menu-specific questions. 
</div>

<div id="pageSource">
<strong>Files used in this page:</strong><br />
- <a href="templates/xtree.html.src">templates/xtree.html</a>
    <a href="templates/xtree.html" 
        title="Right click and 'Save Target As...' to download">
        <img src="images/download.gif" 
        class="download" alt="download"/></a><br />
- <a href="styles/xtree.css.src">styles/xtree.css</a>
    <a href="styles/xtree.css" 
        title="Right click and 'Save Target As...' to download">
        <img src="images/download.gif" 
        class="download" alt="download"/></a><br />
- <a href="scripts/xtree.js.src">scripts/xtree.js</a>
    <a href="scripts/xtree.js" 
        title="Right click and 'Save Target As...' to download">
        <img src="images/download.gif" 
        class="download" alt="download"/></a><br />
- <a href="images/foldericon.png">images/foldericon.png</a><br />
- <a href="images/openfoldericon.png">images/openfoldericon.png</a><br />
- <a href="images/foldericon.png">images/foldericon.png</a><br />
- <a href="images/file.png">images/file.png</a><br />
- <a href="images/I.png">images/I.png</a><br />
- <a href="images/L.png">images/L.png</a><br />
- <a href="images/Lminus.png">images/Lminus.png</a><br />
- <a href="images/Lplus.png">images/Lplus.png</a><br />
- <a href="images/T.png">images/T.png</a><br />
- <a href="images/Tminus.png">images/Tminus.png</a><br />
- <a href="images/Tplus.png">images/Tplus.png</a><br />
- <a href="images/blank.png">images/blank.png</a><br />
</div>

<div id="source">
    <a href="<%=request.getRequestURI()%>.src">View JSP Source</a><br />
    <a href="<%=request.getContextPath()%>/index.jsp">Back to Index</a>
</div>


</body>
</html>

