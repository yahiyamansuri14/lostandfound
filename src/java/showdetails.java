/* author :: Yahiya Mansuri
   Time:: 11:38 AM
   Date::25/april/2019
*/
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletOutputStream;

/**
 *
 * @author hp
 */
public class showdetails extends HttpServlet {
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;
    PrintWriter out;
    
    @Override
    public void init(){
        sql="select * from lost1";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lostandfound","root","root");
            ps=con.prepareStatement(sql);
            
        }catch(ClassNotFoundException | SQLException e){}
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        out=response.getWriter();
        String adhar=null ,name = null,fname = null,mname = null,contact = null,remark = null;
//        String pname=request.getParameter("name");
        
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.println("<center>");
        //out.println("<h3>"+pname+"'s  Details</h3>");
        
        out.println("-------------------------------------------------------------------------------------------------------------------------");
        
        out.println("<table border=2px solid black>");
            out.println("<tr>");
                out.println("<td>Person Adhar No</td>");
                out.println("<td>Person Name</td>");
                out.println("<td>Father Name</td>");
                out.println("<td>Mother Name</td>");
                out.println("<td>Contact</td>");
                out.println("<td>Remark</td>");
                out.println("<td>image</td>");
            out.println("</tr>");
        try{
            //ps.setString(1,pname);
            rs=ps.executeQuery();
                
                while(rs.next())
                {
                    adhar=rs.getString(1);
                    name=rs.getString(2);
                    fname=rs.getString(3);
                    mname=rs.getString(4);
                    contact=rs.getString(5);
                    remark=rs.getString(6);
                    
                    out.println("<tr>");
                    out.println("<td>"+adhar+"</td>");
                    out.println("<td>"+name+"</td>");
                    out.println("<td>"+fname+"</td>");
                    out.println("<td>"+mname+"</td>");
                    out.println("<td>"+contact+"</td>");
                    out.println("<td>"+remark+"</td>");
                    out.println("<td><img width=100 height=100 src=imgloaderlost?name="+adhar+" /></td>");
                    
                    out.println("</tr>");
                }
        }catch(Exception e){out.println(e);}
        
        out.println("</table>");
        out.println("</center>");
        out.println("</body>");
        out.println("</html>");
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(showdetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(showdetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    public void destory(){
        try{
            con.close();
        }catch(Exception e){}
    }
}
 
            
            