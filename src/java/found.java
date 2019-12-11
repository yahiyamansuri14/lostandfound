/* create details 
     author :: Yahiya Mansuri
     date ::24/april/19
     time :: 10:54 PM 
*/

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;

/**
 *
 * @author hp
 */
public class found extends HttpServlet {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    PrintWriter out;
    String sql;
    @Override
    public void init(){
        sql="insert into found values(?,?,?,?,?,?,?)";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lostandfound","root","root");
            ps=con.prepareStatement(sql);
        }catch(Exception e){}
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        out=response.getWriter();
        String fname=request.getParameter("fname");
        String fcontact=request.getParameter("fcontact");
        String fremark=request.getParameter("fremark");
        String pname=request.getParameter("pname");
        String lpfname=request.getParameter("lpfname");
        String lpmname=request.getParameter("lpmname");
        //String lpcontact=request.getParameter("lpcontact");
        String lpremark=request.getParameter("lpremark");
        
        try{
            ps.setString(1,fname);
            ps.setString(2,fcontact);
            ps.setString(3,fremark);
            ps.setString(4,pname);
            ps.setString(5,lpfname);
            ps.setString(6,lpmname);
            ps.setString(7,lpremark);
            ps.executeUpdate();
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
 