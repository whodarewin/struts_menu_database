<%@ taglib uri="http://struts-menu.sf.net/tag" prefix="menu" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <title>Basic Example</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <link rel="stylesheet" type="text/css" media="screen" href="styles/global.css" />
    <script type="text/javascript" src="scripts/cookies.js"></script>
</head>
<body>

<html>
<head>

</head>
<body>
<table cellpadding=0 cellspacing=0>
<tr valign=top>
 <td>
    <menu:useMenuDisplayer name="DropDown" bundle="org.apache.struts.action.MESSAGE">
    <table cellpadding=0 cellspacing=0>
      <tr>
        <td>
          <menu:displayMenu name="ToDoListMenuFile" target="filewindow"/>
        </td>
      </tr>
      <tr>
        <td>
          <menu:displayMenu name="ToDoListMenuEdit"/>
        </td>
      </tr>
      <tr>
        <td>
          <menu:displayMenu name="CaseDetailMenuCase"/>
        </td>
      </tr>
    </table>
    </menu:useMenuDisplayer>
 </td>
 <td>
    <menu:useMenuDisplayer name="Simple" bundle="org.apache.struts.action.MESSAGE">
    <table cellpadding=0 cellspacing=0>
      <tr>
        <td>
          <menu:displayMenu name="ToDoListMenuFile"/>
        </td>
      </tr>
      <tr>
        <td>
          <menu:displayMenu name="ToDoListMenuEdit"/>
        </td>
      </tr>
    </table>
    </menu:useMenuDisplayer>
 </td>
</tr>
</table>





</body>
</html>
