<%@ include file="/common/header.jsp"%>

Select menus to <b>disable</b>.
<br />
<html:form action="/setPermissions">
<table border="1">
 <tr>
    <td>
    <table cellpadding=0 cellspacing=0>
    <tr valign=top>
     <td>
        <menu:useMenuDisplayer name="MenuForm" bundle="org.apache.struts.action.MESSAGE">
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
    </td>
 </tr>
 <tr>
      <td>
       <input type="radio" name="displayer" id="displayer" 
         value="CoolMenu" checked="checked"/> Use CoolMenu3<br />
       <input type="radio" name="displayer" id="displayer" 
         value="CoolMenu4" /> Use CoolMenu4<br />
       <input type="radio" name="displayer" id="displayer" 
         value="ListMenu" /> Use ListMenu       
      </td>
 </tr>
 <tr>
      <td>
       <html:submit value="Set Permissions"/>
      </td>
 </tr>
</table>


</html:form>

<%@ include file="/common/footer.jsp"%>
