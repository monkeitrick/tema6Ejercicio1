package servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ServletComprobar extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		
//		System.out.println("hola6");
//		System.out.println(request.getParameter("letra"));
		if(request.getParameter("letra")!=null) {
			String palabra=(String) request.getParameter("palabra");
			String[] palabraLetras= new String[palabra.length()];
//			System.out.println("hola7");
			Integer letra=Integer.valueOf(request.getParameter("letra"));
			HashMap<Integer, String> letrasMostradas;
//			System.out.println("hola8");
			if(session.getAttribute("letrasMostradas")!=null) {
				letrasMostradas=(HashMap<Integer, String>) session.getAttribute("letrasMostradas");
//				System.out.println("hola");
			}
			else {
				letrasMostradas= new HashMap<Integer, String>();
//				System.out.println("hola2");
			}
			for(int i=0;i<palabra.length();i++) {
				if(i==palabra.length()) {
					palabraLetras[i]=palabra.substring(i);
//					System.out.println("hola3");
				}
				else {
					palabraLetras[i]=palabra.substring(i, i+1);
//					System.out.println("hola4");
				}
			}
			letrasMostradas.put(letra, palabraLetras[letra]);
			session.setAttribute("letrasMostradas", letrasMostradas);
			int vidas=(int) session.getAttribute("vidas");
			vidas--;
			if(vidas==0) {
//				System.out.println("hola2");
				response.sendRedirect("ServletJuego?terminado=no");
			}
			else {
				session.setAttribute("vidas", vidas);
//				System.out.println("hola5");
				response.sendRedirect("ServletJuego");
			}
			
		}
		else{
			System.out.println("hola5t");
			System.out.println(request.getParameter("respuesta"));
			if(request.getParameter("respuesta")!=null) {
				String respuesta=(String) request.getParameter("respuesta");
				String palabra=(String) session.getAttribute("palabra");
				System.out.println("hola");
				if(respuesta.toLowerCase().equals(palabra.toLowerCase())) {
					System.out.println("hola1");
					response.sendRedirect("ServletJuego?terminado=si");
					
				}
				else {
					int vidas=(int) session.getAttribute("vidas");
					if(vidas==1) {
						System.out.println("hola2");
						response.sendRedirect("ServletJuego?terminado=no");
						
					}
					else {
						vidas--;
						session.setAttribute("vidas",vidas);
						System.out.println("hola3");
						response.sendRedirect("ServletJuego");
					}
				}
			}
		}
	}

}
