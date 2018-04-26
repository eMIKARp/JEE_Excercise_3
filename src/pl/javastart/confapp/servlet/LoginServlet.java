package pl.javastart.confapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.javastart.confapp.beans.User;


@WebServlet(urlPatterns = "/LoginServlet", initParams = {@WebInitParam(name ="defaultUsername", value ="Neznajomy")})
public class LoginServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
  
    public LoginServlet() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(60);
		User user = createNewUser(request, session);
		sendResponse(user, response);
	}
	
	public User createNewUser(HttpServletRequest request, HttpSession session)
	{
		User user = new User();
		
			if (request.getParameter("username") == null||"".equals(request.getParameter("username")))
				user.setUsername(getServletConfig().getInitParameter("defaultUsername"));
			else 
				{
					user.setUsername(request.getParameter("username"));
					session.setAttribute("username", user.getUsername());
				}
		return user;
	}
	
	public void sendResponse(User user, HttpServletResponse response) throws IOException
	{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("<html>");
		writer.println("<head><title>Witaj " + user.getUsername() + " !</title></head>");
		writer.println("<body><h1>Witaj " + user.getUsername() + " !</h1></body>");
		writer.println("</html>");
		
	}

}
