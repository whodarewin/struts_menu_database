/*
 * SetPermissionsActions.java
 *
 * Created on April 30, 2002, 1:22 AM
 */
package net.sf.navigator.example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.navigator.menu.PermissionsAdapter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 *
 * @author  ssayles
 */
public final class SetPermissionsAction extends Action {
    //~ Instance fields ========================================================

    /**
     * The <code>Log</code> instance for this application.
     */
    private Log log = LogFactory.getLog(SetPermissionsAction.class);

    //~ Methods ================================================================

    /**
     * Process the specified HTTP request, and create the corresponding HTTP
     * response (or forward to another web component that will create it).
     * Return an <code>ActionForward</code> instance describing where and how
     * control should be forwarded, or <code>null</code> if the response has
     * already been completed.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param actionForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     *
     * @exception Exception if business logic throws an exception
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        //ActionErrors errors = new ActionErrors();
        String[] menus = request.getParameterValues("menus");

        PermissionsAdapter permissions = new SimplePermissionsAdapter(menus);
        request.getSession().setAttribute("exampleAdapter", permissions);

        // added by Matt Raible to dynamically switch menus
        request.getSession().setAttribute("displayer",
            request.getParameter("displayer"));

        return (mapping.findForward("success"));
    }
}
