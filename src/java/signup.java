
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hp
 */
public class signup extends HttpServlet {
    PrintWriter out;
    PreparedStatement ps;
    Connection con;
    String sql;
    @Override
    public void init(){
        sql="insert into users values(?,?,?,?,?,?)";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("mysql:jdbc://localhost:3306/lostandfound","root","root");
            ps=con.prepareStatement(sql);
        }catch(Exception e){}
    }
    @Override
    public void destroy(){
        try{
            con.close();
        }catch(Exception e){}
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        out=response.getWriter();
        String fname=request.getParameter("fname");
        String lname=request.getParameter("lname");
        String mno=request.getParameter("mno");
        String gender=request.getParameter("gender");
        String email=request.getParameter("email");
        String pwd=request.getParameter("pwd");
        String rpwd=request.getParameter("rpwd");
        if(rpwd.equals(pwd))
        {
        try{
            ps.setString(1,fname);
            ps.setString(2,lname);
            ps.setString(3,mno);
            ps.setString(4,gender);
            ps.setString(5,email);
            ps.setString(6,pwd);
            ps.executeUpdate();
        }catch(Exception e){out.println(e);}
        }
        else
        {
            
            out.println("Password Not Match");
            
        }
        
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
