package com.admin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet. annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delete")
public class BooksDeleteServlet extends HttpServlet{

    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        try {
            int id=Integer.parseInt(req.getParameter("id"));

            BookDAOImpl dao=new BookDAOImpl(DBConnect.getConn());
            boolean f=dao.deleteBooks(b);

            HttpSession session=req.getSession();

            if(f)
            {
                session.setAttribute("succMsg", "Book Deleted Successfully..");
                resp.sendRedirect ("admin/all_books.jsp");
            }else {
                session.setAttribute("failedMsg", "Something wrong on server");
                resp.sendRedirect ("admin/all_books.jsp");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}