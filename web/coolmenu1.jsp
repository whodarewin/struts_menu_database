<%@ include file="/common/header.jsp"%>

<script type="text/javascript" src="./scripts/coolmenus3.js"></script>
<script type="text/javascript" src="./scripts/coolmenu-config.js"></script>

<menu:useMenuDisplayer name="CoolMenu" bundle="org.apache.struts.action.MESSAGE">
    <menu:displayMenu name="ToDoListMenuFile"/>
    <menu:displayMenu name="ToDoListMenuEdit"/>
    <menu:displayMenu name="CaseDetailMenuCase"/>
</menu:useMenuDisplayer>

<%@ include file="/common/footer.jsp"%>
