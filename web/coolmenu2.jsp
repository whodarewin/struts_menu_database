<%-- netscape 4.x does not render the characters correctly after the
  layer has been hidden. --%>
<%-- <%@ page contentType="text/html; charset=UTF-8" %> --%>

<%@ include file="/common/header.jsp"%>

<script type="text/javascript" src="./scripts/coolmenus3.js"></script>
<!-- Custom config for this example -->
<script type="text/javascript" src="./scripts/coolmenu2-config.js"></script>

<menu:useMenuDisplayer name="CoolMenu" 
    bundle="org.apache.struts.action.MESSAGE">
    <menu:displayMenu name="ToDoListMenuFile"/>
    <menu:displayMenu name="ToDoListMenuEdit"/>
    <menu:displayMenu name="CaseDetailMenuCase"/>
</menu:useMenuDisplayer>

<br>

<!-- put the form on a layer for coolmenus to handle Netscape4
     this is indicated by setting oCMenu.hideForm("document.formlayer")
     see the javscript coolmenu configuration file.
-->
<ilayer id="formLayer">
<form action="someaction.do">
  sample field1:<input type="text" name="sample"/> <br>
  sample field2:<input type="text" name="sample2"/> <br>
  options: 
      <select name="select1">
          <option>option value</option>
      </select>
</form>
</ilayer>

<b> the following is taken from a coolmenus3 example: </b>
<hr>
As you can see in this page there are form elements. In Netscape 4 there's a bug that makes ALL form elements
get the highest z-index. That means that the form elements "shines" trough the elements. In explorer and netscape 6 this only goes 
for select boxes. So I have made a check that you can turn on (oCMenu.checkselect) that checks for select boxes and
hides them if they come in the way of the menu. Unfortunatly this can not be done in Netscape so I have added another workaround to 
that problem on this page. Surround your entire form with a ILAYER tag (ilayers are positioned relative by default). Add a id to 
the layer and place that id inside the variable oCMenu.hideForm, like this: oCMenu.hideForm="document.LAYER_NAME"
<hr>
<br>

I have also found that Netscape 4 does not render the characters correctly after the form has been hidden if the content-type is set to UTF-8.  This may mean that you can't use this technique for netscape 4 and internationalized sites that are not ascii.  I haven't tested this thouroughly, but I think there definately some issues.  I've commented out the content-type directive in the jsp page.

<%@ include file="/common/footer.jsp"%>
