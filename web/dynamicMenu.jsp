<%@ include file="/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ page import="java.util.Map,
                 javax.servlet.jsp.jstl.sql.Result,
                 net.sf.navigator.menu.MenuComponent,
                 net.sf.navigator.menu.MenuRepository"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<title>Dynamic, Database-driven Menu</title>
    <link rel="stylesheet" type="text/css" media="all"
        href="<c:url value="/styles/menuExpandable.css"/>" />
    <script type="text/javascript"
        src="<c:url value="/scripts/menuExpandable.js"/>"></script>
    <link rel="stylesheet" type="text/css" media="all"
        href="<c:url value="/styles/xtree.css"/>" />
    <script type="text/javascript"
        src="<c:url value="/scripts/xtree.js"/>"></script>
    <link rel="stylesheet" type="text/css" media="all"
        href="<c:url value="/styles/global.css"/>" />
    <script type="text/javascript">
        /* Function for showing and hiding elements that use 'display:none' to hide */
        function toggleDisplay(targetId) {
            if (document.getElementById) {
                target = document.getElementById(targetId);
                if (target.style.display == "none"){
                    target.style.display = "";
                } else {
                    target.style.display = "none";
                }
            }
        }
    </script>
</head>

<div id="content" style="width: 500px">
<h1>Database Driven Menu</h1>

<p>This page is designed to show how easy it is create menus from a couple
of database tables.  I'm using JSTL's SQL Tags because they're easy and you
can see all the code that's needed in this one page. This example creates
tables, populates them and then extracts the values to build a menu.  This
logic should probably go into a Servlet, Struts Action or a Servlet Filter to
get the logic of who sees what out of the view.  But this works, so feel free
to copy and/or improve.
</p>

<sql:setDataSource var="db" url="jdbc:hsqldb:db/menu"
    driver="org.hsqldb.jdbcDriver" user="sa" password=""/>

<sql:transaction dataSource="${db}">

    <sql:update>
        DROP TABLE menu_item IF EXISTS
    </sql:update>
    <sql:update>
        CREATE TABLE menu_item (
           id BIGINT not null,
           parent_name VARCHAR(30),
           name VARCHAR(30),
           title VARCHAR(30),
           description VARCHAR(50),
           location VARCHAR(255),
           target VARCHAR(10),
           onclick VARCHAR(100),
           onmouseover VARCHAR(100),
           onmouseout VARCHAR(100),
           image VARCHAR(50),
           altImage VARCHAR(30),
           tooltip VARCHAR(100),
           roles VARCHAR(100),
           page VARCHAR(255),
           width VARCHAR(5),
           height VARCHAR(5),
           forward VARCHAR(50),
           action VARCHAR(50),
           primary key (id)
        )
    </sql:update>

    <sql:update var="updateCount">
        INSERT INTO menu_item
            (id, name, title)
        VALUES
            (1,'DatabaseMenu','Database Menu')
    </sql:update>
    <sql:update var="updateCount">
        INSERT INTO menu_item
            (id, parent_name, name, title, location)
        VALUES
            (2,'DatabaseMenu','Yahoo','Yahoo Mail','http://mail.yahoo.com')
    </sql:update>
    <sql:update var="updateCount">
        INSERT INTO menu_item
            (id, parent_name, name, title, location)
        VALUES
            (3,'DatabaseMenu','JavaBlogs','JavaBlogs','http://javablogs.com')
    </sql:update>
    <sql:update var="updateCount">
        INSERT INTO menu_item
            (id, name, title, location)
        VALUES
            (4,'StandaloneMenu','Standalone Menu','http://raibledesigns.com')
    </sql:update>
    <sql:query var="menus">
        SELECT * FROM menu_item order by id;
    </sql:query>

</sql:transaction>

    <p>
        <strong>DONE:</strong> successfully created menu_items table and
        added the following entries.
    </p>

    <display:table name="${menus.rows}" class="list" style="width: 600px">
        <display:column property="id"/>
        <display:column property="name"/>
        <display:column property="parent_name" title="Parent Name"/>
        <display:column property="title"/>
        <display:column property="location"/>
    </display:table>
    <p>
        If you <a href="#" onclick="toggleDisplay('sqlSource'); return false">
        view the source</a> of the code above - you can see
        that it just creates a table and inserts some data.  This table will
        be dropped, re-created and populated every time this page is loaded.
    </p>
    <div id="sqlSource" style="display:none; margin-left: 10px; margin-top: 0">
<pre>&lt;sql:setDataSource var="db" url="jdbc:hsqldb:db/menu"
    driver="org.hsqldb.jdbcDriver" user="sa" password=""/&gt;

&lt;sql:transaction dataSource="${db}"&gt;

    &lt;sql:update&gt;
        DROP TABLE menu_item IF EXISTS
    &lt;/sql:update&gt;
    &lt;sql:update&gt;
        CREATE TABLE menu_item (
           id BIGINT not null,
           parent_name VARCHAR(30),
           name VARCHAR(30),
           title VARCHAR(30),
           description VARCHAR(50),
           location VARCHAR(255),
           target VARCHAR(10),
           onclick VARCHAR(100),
           onmouseover VARCHAR(100),
           onmouseout VARCHAR(100),
           image VARCHAR(50),
           altImage VARCHAR(30),
           tooltip VARCHAR(100),
           roles VARCHAR(100),
           page VARCHAR(255),
           width VARCHAR(5),
           height VARCHAR(5),
           forward VARCHAR(50),
           action VARCHAR(50),
           primary key (id)
        )
    &lt;/sql:update&gt;

    &lt;sql:update var="updateCount"&gt;
        INSERT INTO menu_item
            (id, name, title)
        VALUES
            (1,'DatabaseMenu','Database Menu')
    &lt;/sql:update&gt;
    &lt;sql:update var="updateCount"&gt;
        INSERT INTO menu_item
            (id, parent_name, name, title, location)
        VALUES
            (2,'DatabaseMenu','Yahoo','Yahoo Mail','http://mail.yahoo.com')
    &lt;/sql:update&gt;
    &lt;sql:update var="updateCount"&gt;
        INSERT INTO menu_item
            (id, parent_name, name, title, location)
        VALUES
            (3,'DatabaseMenu','JavaBlogs','JavaBlogs','http://javablogs.com')
    &lt;/sql:update&gt;
    &lt;sql:update var="updateCount"&gt;
        INSERT INTO menu_item
            (id, name, title, location)
        VALUES
            (4,'StandaloneMenu','Standalone Menu','http://raibledesigns.com')
    &lt;/sql:update&gt;
    &lt;sql:query var="menus"&gt;
        SELECT * FROM menu_item order by id;
    &lt;/sql:query&gt;

&lt;/sql:transaction&gt;

    &lt;p&gt;
        &lt;strong&gt;DONE:&lt;/strong&gt; successfully created menu_items table and
        added the following entries.
    &lt;/p&gt;

    &lt;display:table name="${menus.rows}" class="list" style="width: 600px"&gt;
        &lt;display:column property="id"/&gt;
        &lt;display:column property="name"/&gt;
        &lt;display:column property="parent_name" title="Parent Name"/&gt;
        &lt;display:column property="title"/&gt;
        &lt;display:column property="location"/&gt;
    &lt;/display:table&gt;</pre>
    </div>

    <p>
        Now let's build a Menu definition with this data.  Below is the
        Java scriplet code that is used to build this menu.
    </p>
    <p style="margin-left: 30px; font-style: italic">
        In a <b>well-architected</b> application, you might pull the data
        from the database using Hibernate, iBATIS, or JDBC.  You could then
        use a Business Delegate for your who-sees-what logic and call the Delegate
        from a ServletFilter, a ServletContextListener or a Login Servlet.
    </p>

    <%
        // I had issues using the existing repository - creating a new one
        // seems to solve the problem.  If you figure out how to use the default
        // Repository and keep your menus from duplicating themselves - please
        // let me know!

        MenuRepository repository = new MenuRepository();
        // Get the repository from the application scope - and copy the
        // DisplayerMappings from it.
        MenuRepository defaultRepository = (MenuRepository)
                application.getAttribute(MenuRepository.MENU_REPOSITORY_KEY);
        repository.setDisplayers(defaultRepository.getDisplayers());

        Result result = (Result) pageContext.getAttribute("menus");
        Map[] rows = result.getRows();
        for (int i=0; i < rows.length; i++) {
            MenuComponent mc = new MenuComponent();
            Map row = rows[i];
            String name = (String) row.get("name");
            mc.setName(name);
            String parent = (String) row.get("parent_name");
            System.out.println(name + ", parent is: " + parent);
            if (parent != null) {
                MenuComponent parentMenu = repository.getMenu(parent);
                if (parentMenu == null) {
                    System.out.println("parentMenu '" + parent + "' doesn't exist!");
                    // create a temporary parentMenu
                    parentMenu = new MenuComponent();
                    parentMenu.setName(parent);
                    repository.addMenu(parentMenu);
                }

                mc.setParent(parentMenu);
            }
            String title = (String) row.get("title");
            mc.setTitle(title);
            String location = (String) row.get("location");
            mc.setLocation(location);
            repository.addMenu(mc);
        }
        pageContext.setAttribute("repository", repository);
    %>

    <p style="margin-top: 10px; color: red"><code>---- begin scriplet code ----</code></p>
    <table style="margin-top: 0; margin-bottom: 10px" cellpadding="3" cellspacing="0" bgcolor="#ffffff">
       <tr>

      <!-- start source code -->
       <td nowrap valign="top" align="left">
        <code>
    <font color="#3f7f5f">//&nbsp;I&nbsp;had&nbsp;issues&nbsp;using&nbsp;the&nbsp;existing&nbsp;repository&nbsp;-&nbsp;creating&nbsp;a&nbsp;new&nbsp;one</font><br>

    <font color="#3f7f5f">//&nbsp;seems&nbsp;to&nbsp;solve&nbsp;the&nbsp;problem.&nbsp;&nbsp;If&nbsp;you&nbsp;figure&nbsp;out&nbsp;how&nbsp;to&nbsp;use&nbsp;the&nbsp;default</font><br>
    <font color="#3f7f5f">//&nbsp;Repository&nbsp;and&nbsp;keep&nbsp;your&nbsp;menus&nbsp;from&nbsp;duplicating&nbsp;themselves&nbsp;-&nbsp;please</font><br>

    <font color="#3f7f5f">//&nbsp;let&nbsp;me&nbsp;know!</font><br>
    <font color="#ffffff"></font><br>
    <font color="#000000">MenuRepository&nbsp;repository&nbsp;=&nbsp;</font><font color="#7f0055"><b>new&nbsp;</b></font><font color="#000000">MenuRepository</font><font color="#000000">()</font><font color="#000000">;</font><br>
    <font color="#3f7f5f">//&nbsp;Get&nbsp;the&nbsp;repository&nbsp;from&nbsp;the&nbsp;application&nbsp;scope&nbsp;-&nbsp;and&nbsp;copy&nbsp;the</font><br>

    <font color="#3f7f5f">//&nbsp;DisplayerMappings&nbsp;from&nbsp;it.</font><br>
    <font color="#000000">MenuRepository&nbsp;defaultRepository&nbsp;=&nbsp;</font><font color="#000000">(</font><font color="#000000">MenuRepository</font><font color="#000000">)</font><br>
    <font color="#ffffff">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><font color="#000000">application.getAttribute</font><font color="#000000">(</font><font color="#000000">MenuRepository.MENU_REPOSITORY_KEY</font><font color="#000000">)</font><font color="#000000">;</font><br>

    <font color="#000000">repository.setDisplayers</font><font color="#000000">(</font><font color="#000000">defaultRepository.getDisplayers</font><font color="#000000">())</font><font color="#000000">;</font><br>
    <font color="#ffffff"></font><br>
    <font color="#000000">Result&nbsp;result&nbsp;=&nbsp;</font><font color="#000000">(</font><font color="#000000">Result</font><font color="#000000">)&nbsp;</font><font color="#000000">pageContext.getAttribute</font><font color="#000000">(</font><font color="#2a00ff">&#34;menus&#34;</font><font color="#000000">)</font><font color="#000000">;</font><br>

    <font color="#000000">Map</font><font color="#000000">[]&nbsp;</font><font color="#000000">rows&nbsp;=&nbsp;result.getRows</font><font color="#000000">()</font><font color="#000000">;</font><br>
    <font color="#7f0055"><b>for&nbsp;</b></font><font color="#000000">(</font><font color="#7f0055"><b>int&nbsp;</b></font><font color="#000000">i=</font><font color="#990000">0</font><font color="#000000">;&nbsp;i&nbsp;&lt;&nbsp;rows.length;&nbsp;i++</font><font color="#000000">)&nbsp;{</font><br>

    <font color="#ffffff">&nbsp;&nbsp;&nbsp;&nbsp;</font><font color="#000000">MenuComponent&nbsp;mc&nbsp;=&nbsp;</font><font color="#7f0055"><b>new&nbsp;</b></font><font color="#000000">MenuComponent</font><font color="#000000">()</font><font color="#000000">;</font><br>
    <font color="#ffffff">&nbsp;&nbsp;&nbsp;&nbsp;</font><font color="#000000">Map&nbsp;row&nbsp;=&nbsp;rows</font><font color="#000000">[</font><font color="#000000">i</font><font color="#000000">]</font><font color="#000000">;</font><br>

    <font color="#ffffff">&nbsp;&nbsp;&nbsp;&nbsp;</font><font color="#000000">String&nbsp;name&nbsp;=&nbsp;</font><font color="#000000">(</font><font color="#000000">String</font><font color="#000000">)&nbsp;</font><font color="#000000">row.get</font><font color="#000000">(</font><font color="#2a00ff">&#34;name&#34;</font><font color="#000000">)</font><font color="#000000">;</font><br>
    <font color="#ffffff">&nbsp;&nbsp;&nbsp;&nbsp;</font><font color="#000000">mc.setName</font><font color="#000000">(</font><font color="#000000">name</font><font color="#000000">)</font><font color="#000000">;</font><br>

    <font color="#ffffff">&nbsp;&nbsp;&nbsp;&nbsp;</font><font color="#000000">String&nbsp;parent&nbsp;=&nbsp;</font><font color="#000000">(</font><font color="#000000">String</font><font color="#000000">)&nbsp;</font><font color="#000000">row.get</font><font color="#000000">(</font><font color="#2a00ff">&#34;parent_name&#34;</font><font color="#000000">)</font><font color="#000000">;</font><br>
    <font color="#ffffff">&nbsp;&nbsp;&nbsp;&nbsp;</font><font color="#000000">System.out.println</font><font color="#000000">(</font><font color="#000000">name&nbsp;+&nbsp;</font><font color="#2a00ff">&#34;,&nbsp;parent&nbsp;is:&nbsp;&#34;&nbsp;</font><font color="#000000">+&nbsp;parent</font><font color="#000000">)</font><font color="#000000">;</font><br>

    <font color="#ffffff">&nbsp;&nbsp;&nbsp;&nbsp;</font><font color="#7f0055"><b>if&nbsp;</b></font><font color="#000000">(</font><font color="#000000">parent&nbsp;!=&nbsp;</font><font color="#7f0055"><b>null</b></font><font color="#000000">)&nbsp;{</font><br>
    <font color="#ffffff">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><font color="#000000">MenuComponent&nbsp;parentMenu&nbsp;=&nbsp;repository.getMenu</font><font color="#000000">(</font><font color="#000000">parent</font><font color="#000000">)</font><font color="#000000">;</font><br>

    <font color="#ffffff">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><font color="#7f0055"><b>if&nbsp;</b></font><font color="#000000">(</font><font color="#000000">parentMenu&nbsp;==&nbsp;</font><font color="#7f0055"><b>null</b></font><font color="#000000">)&nbsp;{</font><br>
    <font color="#ffffff">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><font color="#000000">System.out.println</font><font color="#000000">(</font><font color="#2a00ff">&#34;parentMenu&nbsp;'&#34;&nbsp;</font><font color="#000000">+&nbsp;parent&nbsp;+&nbsp;</font><font color="#2a00ff">&#34;'&nbsp;doesn't&nbsp;exist!&#34;</font><font color="#000000">)</font><font color="#000000">;</font><br>

    <font color="#ffffff">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><font color="#3f7f5f">//&nbsp;create&nbsp;a&nbsp;temporary&nbsp;parentMenu</font><br>
    <font color="#ffffff">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><font color="#000000">parentMenu&nbsp;=&nbsp;</font><font color="#7f0055"><b>new&nbsp;</b></font><font color="#000000">MenuComponent</font><font color="#000000">()</font><font color="#000000">;</font><br>
    <font color="#ffffff">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><font color="#000000">parentMenu.setName</font><font color="#000000">(</font><font color="#000000">parent</font><font color="#000000">)</font><font color="#000000">;</font><br>

    <font color="#ffffff">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><font color="#000000">repository.addMenu</font><font color="#000000">(</font><font color="#000000">parentMenu</font><font color="#000000">)</font><font color="#000000">;</font><br>
    <font color="#ffffff">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><font color="#000000">}</font><br>
    <font color="#ffffff"></font><br>
    <font color="#ffffff">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><font color="#000000">mc.setParent</font><font color="#000000">(</font><font color="#000000">parentMenu</font><font color="#000000">)</font><font color="#000000">;</font><br>
    <font color="#ffffff">&nbsp;&nbsp;&nbsp;&nbsp;</font><font color="#000000">}</font><br>

    <font color="#ffffff">&nbsp;&nbsp;&nbsp;&nbsp;</font><font color="#000000">String&nbsp;title&nbsp;=&nbsp;</font><font color="#000000">(</font><font color="#000000">String</font><font color="#000000">)&nbsp;</font><font color="#000000">row.get</font><font color="#000000">(</font><font color="#2a00ff">&#34;title&#34;</font><font color="#000000">)</font><font color="#000000">;</font><br>
    <font color="#ffffff">&nbsp;&nbsp;&nbsp;&nbsp;</font><font color="#000000">mc.setTitle</font><font color="#000000">(</font><font color="#000000">title</font><font color="#000000">)</font><font color="#000000">;</font><br>

    <font color="#ffffff">&nbsp;&nbsp;&nbsp;&nbsp;</font><font color="#000000">String&nbsp;location&nbsp;=&nbsp;</font><font color="#000000">(</font><font color="#000000">String</font><font color="#000000">)&nbsp;</font><font color="#000000">row.get</font><font color="#000000">(</font><font color="#2a00ff">&#34;location&#34;</font><font color="#000000">)</font><font color="#000000">;</font><br>
    <font color="#ffffff">&nbsp;&nbsp;&nbsp;&nbsp;</font><font color="#000000">mc.setLocation</font><font color="#000000">(</font><font color="#000000">location</font><font color="#000000">)</font><font color="#000000">;</font><br>

    <font color="#ffffff">&nbsp;&nbsp;&nbsp;&nbsp;</font><font color="#000000">repository.addMenu</font><font color="#000000">(</font><font color="#000000">mc</font><font color="#000000">)</font><font color="#000000">;</font><br>
    <font color="#000000">}</font><br>
    <font color="#000000">pageContext.setAttribute</font><font color="#000000">(</font><font color="#2a00ff">&#34;repository&#34;</font><font color="#000000">,&nbsp;repository</font><font color="#000000">)</font><font color="#000000">;</font></code>

       </td>

      <!-- end source code -->
       </tr>
     </table>

    <p><code style="color: red">---- end scriplet code ----</code></p>

    <p style="padding: 10px 0">Now that we've built our menu repository, we can easily display it with the following code:</p>
    <pre>&lt;menu:useMenuDisplayer name="ListMenu" repository="repository"&gt;
    &lt;menu:displayMenu name="DatabaseMenu"/&gt;
    &lt;menu:displayMenu name="StandaloneMenu"/&gt;
&lt;/menu:useMenuDisplayer&gt;</pre>

    <p style="padding: 10px 0">Which results in:</p>
    <div class="dynamicMenu">
        <menu:useMenuDisplayer name="ListMenu" repository="repository">
            <menu:displayMenu name="DatabaseMenu"/>
            <menu:displayMenu name="StandaloneMenu"/>
        </menu:useMenuDisplayer>
    </div>

    <p style="padding: 10px 0">
        You could also grab the menu names dynamically from the repository and loop
        through them using JSTL's &lt;c:forEach&gt; tag:
    </p>
    <pre>&lt;menu:useMenuDisplayer name="Velocity" config="/templates/xtree.html"
    repository="repository"&gt;
    &lt;c:forEach var="menu" items="${repository.topMenus}"&gt;
        &lt;menu-el:displayMenu name="${menu.name}"/&gt;
    &lt;/c:forEach&gt;
&lt;/menu:useMenuDisplayer&gt;</pre>

    <p style="padding: 10px 0">Which results in:</p>

    <div class="dynamicMenu tree">
    <script type="text/javascript">
        <menu:useMenuDisplayer name="Velocity" config="/templates/xtree.html"
            repository="repository">
          <c:forEach var="menu" items="${repository.topMenus}">
            <menu-el:displayMenu name="${menu.name}"/>
          </c:forEach>
        </menu:useMenuDisplayer>
    </script>
    </div>
</div>


<div id="source">
  <a href="<%=request.getRequestURI()%>.src">View JSP Source</a>
  <br />
  <a href="<%=request.getContextPath()%>/index.jsp">Back to Index</a>
</div>
</body>
</html>

