package backend;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

@WebServlet("/quiz")
public class QuizServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path == null || "/".equals(path)) {
            path = "/index.html";
        }
        
        InputStream is = getServletContext().getResourceAsStream("/frontend" + path);
        if (is == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        
        response.setContentType(getServletContext().getMimeType(path));
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = is.read(buffer)) != -1) {
            response.getOutputStream().write(buffer, 0, bytesRead);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String action = request.getParameter("action");
        JSONObject jsonResponse = new JSONObject();

        try {
            if ("addQuestion".equals(action)) {
                String question = request.getParameter("question");
                String[] options = request.getParameterValues("options");
                String answer = request.getParameter("answer");

                AddQues addQues = new AddQues();
                addQues.addQuestion(new QuizQuestion(question, options, answer));
                jsonResponse.put("status", "success");
            } else if ("takeQuiz".equals(action)) {
                Student student = new Student();
                JSONObject quizResults = student.startQuiz();
                jsonResponse.put("results", quizResults);
            }
        } catch (Exception e) {
            jsonResponse.put("status", "error");
            jsonResponse.put("message", e.getMessage());
        }

        response.getWriter().write(jsonResponse.toString());
    }
}

