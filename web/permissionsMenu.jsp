<%@ include file="/taglibs.jsp"%>
<html>
    <head><title>Permissions Menu</title>

<% 
String displayer = (String)session.getAttribute("displayer"); 
if (displayer == null) {
  displayer="CoolMenu";
}
if (displayer.equalsIgnoreCase("CoolMenu")) { 
%>
    <script language="JavaScript1.2" src="./scripts/coolmenus3.js"></script>
    </head>
<body>
<script src="./scripts/permissionsMenu-config.js"></script>
<% } else if (displayer.equalsIgnoreCase("CoolMenu4")) { %>
    <link rel="stylesheet" type="text/css" media="screen" 
        href="styles/coolmenu.css" />    
    
    <script type="text/javascript" src="scripts/coolmenus4.js"></script>
    <script type="text/javascript" src="scripts/cm_addins.js"></script>
    </head>
<body>
<script type="text/javascript" src="scripts/coolmenu4-config.js"></script>
<% } else { %>
    <link rel="stylesheet" type="text/css" media="screen" 
        href="styles/menuExpandable.css" />    

    <script type="text/javascript" src="scripts/menuExpandable.js"></script>
</head>
<body>
<% } %>

<menu:useMenuDisplayer name='<%=displayer%>' 
        bundle="org.apache.struts.action.MESSAGE"
        permissions="exampleAdapter">
    <menu:displayMenu name="ToDoListMenuFile"/>
    <menu:displayMenu name="ToDoListMenuEdit"/>
    <menu:displayMenu name="Permissions"/>
</menu:useMenuDisplayer>


</body>
</html>
