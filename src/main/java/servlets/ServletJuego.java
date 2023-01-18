package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.AlmacenPalbras;

public class ServletJuego extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String[] palabras=AlmacenPalbras.getPalabras();
	String palabra="";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset-UTF-8");
		PrintWriter out = response.getWriter();
		
		try {
			if(palabra.equals("")) {
				int numeroAleatorio = (int) (Math.random()*(palabra.length()));
				palabra=palabras[numeroAleatorio];
			}
			out.println("<!DOCTYPE html>");
			out.println("<html lang=\"es\">");
			out.println("<head>");
			out.println("<title>tema 6 ejercicio 1</title>");
			out.println("<link rel=\"stylesheet\" href=\"../css/style.css\">");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Ejercicio 1</h1>");
			out.println("<table border=\"solid 1px\"><tr>");
			for(int i=0;i<palabra.length();i++) {
				out.println("<td margin=\"2px\" padding=\"2px\" text-align=\"centre\"><a href=\"ServletComprobar?palabra=\""+palabra+">ver</a></td>");
			}
			out.println("</tr></table>");
		}
		finally {
			out.println("</body>");
			out.println("</html>");
		}
		
	}

}
