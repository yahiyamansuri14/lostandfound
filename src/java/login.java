
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
/**
 *
 * @author hp
 */
public class login extends HttpServlet {
    
        Connection con;
        PreparedStatement ps;
        String sql;
        ResultSet rs;
        @Override
        public void init(){
            sql="select pwd from users where email=?";
            try{
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lostandfound","root","root");
                ps=con.prepareStatement(sql);
            }catch(Exception e){}
        }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
            PrintWriter out=response.getWriter();
            String email=request.getParameter("email");
            String pwd=request.getParameter("pwd");
            String s="";
            boolean b;
            try{
                ps.setString(1,email);
                rs=ps.executeQuery();
                
                
                if(rs.next())
                {
                    s=rs.getString(1);
                    if(s.equals(pwd))    
                    {
                        response.sendRedirect("navigation.html");
                    }
                    else
                    {
                        out.println("Incorrect Password");
                    }
                }
                else
                {
                    out.println("Invalid Account");
                    response.sendRedirect("signup.html");
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
