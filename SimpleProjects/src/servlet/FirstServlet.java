package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FirstServlet extends HttpServlet{

    /**
     *
     * 以GET方式访问页面时执行该函数
     * 执行DOGET前会先执行getLastModified,如果浏览器发现getLastModified返回的数值
     * 与上次访问时返回的数值相同，则认为该文档没有更新，浏览器采用的缓存而不执行doGet
     * 如果getLastModified返回-1，则认为是时刻更新的，总是执行该函数
     *
     */
    public void doGet(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException {


        this.log("执行doGet的方法");//调用Servlet自带的日志输出信息到控制台
        this.execute(request,response);//处理doGet

    }

    /**
     * 以Post方式访问页面时执行该函数。执行前不会执行getLastModified
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         this.log("执行doPost的方法");//调用Servlet自带的日志输出信息到控制台
         this.execute(request,response);//处理doPost

    }


    /**
     *返回该Servlet生成的文档的更新时间。对GET方式访问有效
     * 返回的时间为相对于1970年1月1日08:00:00的毫秒数
     * 如果为-1则认为是实时更新。默认为-1
     *
     */
    @Override
    public long getLastModified(HttpServletRequest req) {
        this.log("执行getLastModified方法...");
        return -1;
    }
    //执行方法
    private void execute(HttpServletRequest request,HttpServletResponse response)
        throws ServletException,IOException{
        response.setCharacterEncoding("UTF-8");//设置response编码方式
        request.setCharacterEncoding("UTF-8");//设置request编码方式

        String requestURI = request.getRequestURI();//访问该Servlet的URI
        String method = request.getMethod();//访问Servlet的方式，get或者post
        String param = request.getParameter("param");//客户端提交的参数param的值

        response.setContentType("text/html");//设置文档类型为HTML类型
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<HTML>");
        out.println("<HEAD><TITLE>A Servlet</TITLE></HEAD>");
        out.println("<BODY>");
        out.println("以"+method+"方式访问该界面。取到的param参数为："+param+"<br/>");
        out.println("<form action='"+requestURI+"'method='get'>" +
                "<input type='text' name='param' value='param string'><input" +
                "type='submit' value='已get方式查询页面"+requestURI+"'></form>");
        out.println("<form action='"+requestURI+"'method='post'>" +
                "<input type='text' name='param' value='param string'><input" +
                "type='submit' value='已post方式提交到页面"+requestURI+"'></form>");
        //由客户端浏览器读取该文档的更新时间
        out.println(" <script>document.write('本页面最后更新时间:'+document.lastModified);</script>");

        out.println("</BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();

    }
}
