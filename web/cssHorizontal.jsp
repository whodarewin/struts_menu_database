<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ include file="/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <title>Drop-Down Menus, Horizontal Style</title>
    
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="text/javascript">
    /*<![CDATA[*/
    function IEHoverPseudo() {

        var navItems = document.getElementById("primary-nav").getElementsByTagName("li");

        for (var i=0; i<navItems.length; i++) {
            if(navItems[i].className == "menubar") {
                navItems[i].onmouseover=function() { this.className += " over"; }
                navItems[i].onmouseout=function() { this.className = "menubar"; }
            }
        }

    }
    window.onload = IEHoverPseudo;
    /*]]>*/
    </script>

    <style type="text/css">

        body { font: normal 80% verdana; }

        ul#primary-nav,
        ul#primary-nav ul {
            margin: 0;
            padding: 0;
            width: 150px; /* Width of Menu Items */
            border-bottom: 1px solid #ccc;
            background: #fff; /* IE6 Bug */
            font-size: 100%;
        }

        ul#primary-nav li {
            position: relative;
            list-style: none;
        }

        ul#primary-nav li a {
            display: block;
            text-decoration: none;
            color: #777;
            padding: 5px;
            border: 1px solid #ccc;
            border-bottom: 0;
        }

        /* Fix IE. Hide from IE Mac \*/
        * html ul#primary-nav li { float: left; height: 1%; }
        * html ul#primary-nav li a { height: 1%; }
        /* End */

        ul#primary-nav ul {
            position: absolute;
            display: none;
            left: 149px; /* Set 1px less than menu width */
            top: 0;
        }

        ul#primary-nav li ul li a { padding: 2px 5px; } /* Sub Menu Styles */

        ul#primary-nav li:hover ul ul ul,
        ul#primary-nav li.over ul ul,
        ul#primary-nav li.over ul ul ul { display: none; } /* Hide sub-menus initially */

        ul#primary-nav li:hover ul,
        ul#primary-nav li li:hover ul,
        ul#primary-nav li li li:hover ul,
        ul#primary-nav li.over ul,
        ul#primary-nav li li.over ul,
        ul#primary-nav li li li.over ul { display: block; } /* The magic */

        ul#primary-nav li.menubar { background: transparent url(images/arrow.gif) right center no-repeat; }

        ul#primary-nav li:hover,
        ul#primary-nav li.over { background-color: #f9f9f9; }

        ul#primary-nav li a:hover { color: #E2144A; }

    </style>
</head>
<body>

<div id="topMenu">
<% if (request.getParameter("velocity") != null) { %>
<menu:useMenuDisplayer name="Velocity" config="/templates/cssMenu.html">
<ul id="primary-nav" class="menuList">
    <menu:displayMenu name="ToDoListMenuFile"/>
    <menu:displayMenu name="ToDoListMenuEdit"/>
    <menu:displayMenu name="CaseDetailMenuCase"/>
    <menu:displayMenu name="Standalone"/>
</ul>
</menu:useMenuDisplayer>
<% } %>

<% if (request.getParameter("velocity") == null) { %>
<menu:useMenuDisplayer name="CSSListMenu" id="primary-nav">
    <menu:displayMenu name="ToDoListMenuFile"/>
    <menu:displayMenu name="ToDoListMenuEdit"/>
    <menu:displayMenu name="CaseDetailMenuCase"/>
    <menu:displayMenu name="Standalone"/>
</menu:useMenuDisplayer>
<% } %>
</div>

<div class="note" style="margin: 20px; margin-right: 100px">
    This menu is based on <a href="http://www.alistapart.com/articles/horizdropdowns">this article</a>
    and <a href="http://www.nickrigby.com/examples/dropdown3/index.htm">demo</a> from Nick Rigby.
    <br/><br/>
    To use this menu in your application, you'll need to copy the CSS and IE-specific JavaScript from
    this page (view-source to get it).  Your &lt;menu:useMenuDisplayer&gt; tag should look as follows:
    <pre style="font-size: 12px; background: #ffd; padding: 5px; border: 1px solid silver">
&lt;menu:useMenuDisplayer name="CSSListMenu" id="primary-nav"&gt;</pre>
    If you change the "id" attribute, make sure you change it in your CSS/JavaScript as well. In
    menu-config.xml, you'll need to define the "CSSListMenu" displayer.
    <pre style="font-size: 12px; background: #ffd; padding: 5px; border: 1px solid silver">
&lt;Displayer name="CSSListMenu" type="net.sf.navigator.displayer.CSSListMenuDisplayer"/&gt;</pre>
    
    The CSSListMenuDisplayer was added to Struts Menu in version 2.4. 
    <br/><br/>
    <a href="<%=request.getRequestURI()%>.src">View Source</a> to see how the Velocity version of this menu
    or <a href="?velocity=true">click here to view it</a>.
    <br/><br/>
    <a href="<c:url value="/cssVertical.jsp"/>">&raquo; View vertical version of this menu</a>
    <br/><br/>
    <a href="<c:url value="/"/>">&laquo; Return to Index of Menus</a>
</div>

</body>
</html>
