/*<!-- create details -->
<!-- author :: Yahiya Mansuri
     date ::25/april/19
     time :: 10:02 AM -->
*/
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hp
 */
public class lostentry extends HttpServlet {
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    PrintWriter out;
    String sql;
    @Override
    public void init(){
        sql="insert into lost values(?,?,?,?,?)";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("mysql:jdbc://localhost:3306/lostandfound","root","root");
            ps=con.prepareStatement(sql);
        }catch(Exception e){}
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        out=response.getWriter();
        String pname=request.getParameter("pname");
        String fname=request.getParameter("fname");
        String mname=request.getParameter("mname");
        String contact=request.getParameter("contact");
        String remark=request.getParameter("remark");
        
        try{
            ps.setString(1,pname);
            ps.setString(2,fname);
            ps.setString(3,mname);
            ps.setString(4,contact);
            ps.setString(5,remark);
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
