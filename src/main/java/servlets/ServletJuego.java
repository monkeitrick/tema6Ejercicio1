package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import beans.AlmacenPalbras;

public class ServletJuego extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String[] palabras=AlmacenPalbras.getPalabras();
	String palabra="";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		response.setContentType("text/html;charset-UTF-8");
		PrintWriter out = response.getWriter();
		HashMap<Integer, String> letrasMostradas;
		Integer vidas;
		if(request.getParameter("terminado")!=null) {
			String respuesta=request.getParameter("terminado");
			out.println("<!DOCTYPE html>");
			out.println("<html lang=\"es\">");
			out.println("<head>");
			out.println("<title>tema 6 ejercicio 1</title>");
			out.println("</head>");
			out.println("<body>");
			if(respuesta.equals("si")) {
				out.println("<p>Felicidades has ganado, La palabra era"+session.getAttribute("palabra")+"</p>");
				session.invalidate();
				out.println("<a href=\"ServletJuego\">Volver a jugar</a>");
			}
			else {
				out.println("<p>OHHHHHHHHHHHHHHHHH has perdido!!, La palabra era"+session.getAttribute("palabra")+"</p>");
				session.invalidate();
				out.println("<a href=\"ServletJuego\">Volver a jugar</a>");
			}
			out.println("</body>");
			out.println("</html>");
		}
		else {
			try {
				
				if(session.getAttribute("palabra")!=null) {
					palabra=(String) request.getSession().getAttribute("palabra");
				}
				else{
					int numeroAleatorio = (int) (Math.random()*(palabra.length()));
					palabra=palabras[numeroAleatorio];
					request.getSession().setAttribute("palabra",palabra);
				}
				out.println("<!DOCTYPE html>");
				out.println("<html lang=\"es\">");
				out.println("<head>");
				out.println("<title>tema 6 ejercicio 1</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("<h1>Ejercicio 1</h1>");
				out.println("<form action=\"ServletComprobar\" method=\"post\">");
				out.println("<table border='1' style=\'border-collapse: collapse\'><tr>");
				if(session.getAttribute("letrasMostradas")!=null) {
					letrasMostradas=(HashMap<Integer, String>) session.getAttribute("letrasMostradas");
				}
				else {
					letrasMostradas= new HashMap<Integer, String>();
				}
				for(int i=0;i<palabra.length();i++) {
					if(letrasMostradas.containsKey(i)) {
						if(i==palabra.length()) {
							out.println("<td style=\' width:40px; padding: 2px; text-align: center;\'>"+palabra.substring(i)+"</td>");
						}
						else {
							out.println("<td style=\' width:40px; padding: 2px; text-align: center;\'>"+palabra.substring(i,i+1)+"</td>");
						}
					}else {
						Integer aux=i;
						out.println("<td style=\' width:40px; padding: 2px; text-align: center;\'><a href=\"ServletComprobar?letra="+aux+"&palabra="+palabra+"\">ver</a></td>");
					}
					
				}
				out.println("</tr></table>");
				out.println("<ul>");
				if(session.getAttribute("vidas")!=null) {
					vidas=(Integer) session.getAttribute("vidas");
				}
				else {
					vidas=Math.round(palabra.length()/2);
					session.setAttribute("vidas", vidas);
				}
				out.println("<li>"+vidas+" vidas restantes</li>");
				out.println("<li>Tu respuesta <input type=\\\"text\\\" id=\\\"respuesta\\\" name=\\\"respuesta\\\" size=\\\"10\\\"> "
						+ "<input type=\"submit\" value=\"Comprobar\" onclick=\"response.sendRedirect(\"ServletComprobar?\");\"/></li>");
				
				out.println("</ul>");
				out.println("</form>");
				
				
			}
			finally {
				out.println("</body>");
				out.println("</html>");
			}
		}
		
		
	}

}
