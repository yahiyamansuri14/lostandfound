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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

/**
 *
 * @author hp
 */
public class lostperson extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String s1="",s2="",s3="",s4="",s5="",s6="";
        
        byte b[]=null;
        try{
            
            DiskFileItemFactory factory=new DiskFileItemFactory();
            ServletFileUpload upload=new ServletFileUpload(factory);
            List<FileItem> items=upload.parseRequest(new ServletRequestContext(request));
            
            for(FileItem item:items){
                String name=item.getFieldName();
                if(name.equals("adhar")){
                    s1=item.getString();
                }else if(name.equals("pname")){
                    s2=item.getString();
                }else if(name.equals("fname")){
                    s3=item.getString();
                }else if(name.equals("mname")){
                    s4=item.getString();
                }else if(name.equals("contact")){
                    s5=item.getString();
                }else if(name.equals("remark")){
                    s6=item.getString();
                }else if(name.equals("image")){
                    b=item.get();
                }
            }
            String sql="insert into lost1 values(?,?,?,?,?,?,?)";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lostandfound", "root", "root");
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, s1);
            ps.setString(2, s2);
            ps.setString(3, s3);
            ps.setString(4, s4);
            ps.setString(5, s5);
            ps.setString(6, s6);
            ps.setBytes(7, b);
            ps.executeUpdate();
            con.close();
            
        }catch(Exception e){
            out.println(e);
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
