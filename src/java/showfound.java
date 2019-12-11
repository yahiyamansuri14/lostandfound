 /*  author :: Yahiya Mansuri
     Time ::1:02 AM 
     Date::25/april/2019
*/
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hp
 */
public class showfound extends HttpServlet {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;
    PrintWriter out;
    
    @Override
    public void init(){
        sql="select * from found";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lostandfound","root","root");
            ps=con.prepareStatement(sql);
            
        }catch(ClassNotFoundException | SQLException e){}
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        out=response.getWriter();
        String fname = null,fcontact = null,fremark = null,contact = null,pname=null,lpfname=null,lpmname=null,lpremark=null;
        
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.println("<center>");
        out.println("<h3>Found Person Details</h3>");
        
        out.println("-------------------------------------------------------------------------------------------------------------------------");
        
        out.println("<table border=2px solid black>");
            out.println("<tr>");
                out.println("<td>Founder Name</td>");
                out.println("<td>Founder Contact</td>");
                out.println("<td>Founder Remark</td>");
                out.println("<td>Found Person Name</td>");
                out.println("<td>Person Father Name</td>");
                out.println("<td>Person Mother Name</td>");
                out.println("<td>Found Person Remark</td>");
            out.println("</tr>");
        try{
            //ps.setString(1,pname);
            rs=ps.executeQuery();
                
                while(rs.next())
                {
                    fname=rs.getString(1);
                    fcontact=rs.getString(2);
                    fremark=rs.getString(3);
                    pname=rs.getString(4);
                    lpfname=rs.getString(5);
                    lpmname=rs.getString(6);
                    lpremark=rs.getString(7);
                    

                    out.println("<tr>");
                    out.println("<td>"+fname+"</td>");
                    out.println("<td>"+fcontact+"</td>");
                    out.println("<td>"+fremark+"</td>");
                    out.println("<td>"+pname+"</td>");
                    out.println("<td>"+lpfname+"</td>");
                    out.println("<td>"+lpmname+"</td>");
                    out.println("<td>"+lpremark+"</td>");
                    out.println("</tr>");
                }
        }catch(Exception e){out.println(e);}
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
        processRequest(request, response);
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
        processRequest(request, response);
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

}
