package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import dao.StudentDAO;
import entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

    StudentDAO dao = new StudentDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action.equals("add")) {

            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String course = req.getParameter("course");
            double fee = Double.parseDouble(req.getParameter("fee"));

            Student student = new Student();
            student.setId(id);
            student.setName(name);
            student.setCourse(course);
            student.setFee(fee);

            dao.saveStudent(student);

            resp.getWriter().println("Student Added Successfully");
        }

        else if (action.equals("update")) {

            int id = Integer.parseInt(req.getParameter("id"));

            Student student = dao.searchStudent(id);

            if (student != null) {

                student.setName(req.getParameter("name"));
                student.setCourse(req.getParameter("course"));
                student.setFee(Double.parseDouble(req.getParameter("fee")));

                dao.updateStudent(student);

                resp.getWriter().println("Student Updated Successfully");
            }

            else {
                resp.getWriter().println("Student Not Found");
            }
        }

        else if (action.equals("delete")) {

            int id = Integer.parseInt(req.getParameter("id"));

            dao.deleteStudent(id);

            resp.getWriter().println("Student Deleted Successfully");
        }

        else if (action.equals("search")) {

            int id = Integer.parseInt(req.getParameter("id"));

            Student student = dao.searchStudent(id);

            PrintWriter out = resp.getWriter();

            if (student != null) {

                out.println("Student Details");
                out.println("ID : " + student.getId());
                out.println("Name : " + student.getName());
                out.println("Course : " + student.getCourse() );
                out.println("Fee : " + student.getFee());
            }

            else {

                out.println("Student Not Found");
            }
        }

        else if (action.equals("view")) {

            List<Student> list = dao.getAllStudents();

            PrintWriter out = resp.getWriter();

            out.println("Student List");
            out.println("-------------------------------------------------------");
            for (Student s : list) {

                out.println("ID : " + s.getId() );
                out.println("Name : " + s.getName() );
                out.println("Course : " + s.getCourse() );
                out.println("Fee : " + s.getFee() );
                out.println("---------------------------------------------------");
            }
        }
    }
}