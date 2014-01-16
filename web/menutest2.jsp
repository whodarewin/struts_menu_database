<%@ include file="/common/header.jsp"%>

<table cellpadding=0 cellspacing=0>
<tr valign=top>
 <td>
    <menu:useMenuDisplayer name="DropDown" config="AppDisplayerStrings" bundle="org.apache.struts.action.MESSAGE">
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
 <td width=50px></td>
 <td>
    <menu:useMenuDisplayer name="Simple" config="AppDisplayerStrings" bundle="ISOCodeRes">
    <table cellpadding=0 cellspacing=0>
      <tr height=4 bgcolor="darkblue"><td></tr></td>
      <tr>
        <td>
          <menu:displayMenu name="Countries"/>
        </td>
      </tr>
    </table>
    </menu:useMenuDisplayer>
 </td>
 <td width=50px></td>
 <td>
    <menu:useMenuDisplayer name="Simple" config="AppDisplayerStrings" bundle="org.apache.struts.action.MESSAGE">
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

<%@ include file="/common/footer.jsp"%>