package net.sf.navigator.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;


/**
 * <p>Servlet used to display source code for example pages</p>
 * @author fgiust (from displaytag project)
 */
public class DisplaySourceServlet extends HttpServlet {
    //~ Static fields/initializers =============================================

    //~ Methods ================================================================

    /**
     * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest, HttpServletResponse)
     */
    protected final void doGet(HttpServletRequest request,
                               HttpServletResponse response)
    throws ServletException, IOException {

        String file = request.getRequestURI();
        file = file.substring(0, file.lastIndexOf("."));
        file = StringUtils.replace(file, request.getContextPath(), "");
        //if (file.indexOf("/") != -1) {
            //file = file.substring(file.indexOf("/") + 1);
        //}
        
        InputStream is =
            getServletContext().getResourceAsStream(file);

        if (is == null) {
            throw new ServletException("Unable to find file: " + file);
        }

        response.setContentType("text/html");

        PrintWriter lOut = response.getWriter();

        lOut.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" " +
                     "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
        lOut.println("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">");
        lOut.println("<head>");
        lOut.println("<title>");
        lOut.println("source for " + file);
        lOut.println("</title>");
        lOut.println("<meta http-equiv=\"content-type\" content=\"text/html; charset=ISO-8859-1\" />");
        lOut.println("</head>");
        lOut.println("<body>");
        lOut.println("<pre>");

        for (int lChar = is.read(); lChar != -1;
                 lChar = is.read()) {
            if (lChar == '<') {
                lOut.print("&lt;");
            } else {
                lOut.print((char) lChar);
            }
        }

        lOut.println("</pre>");
        lOut.println("</body>");
        lOut.println("</html>");
    }
}
