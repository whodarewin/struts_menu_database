<%@ page language="java" contentType="javascript/x-javascript" %>
<%@ taglib uri="http://struts-menu.sf.net/tag" prefix="menu" %>

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