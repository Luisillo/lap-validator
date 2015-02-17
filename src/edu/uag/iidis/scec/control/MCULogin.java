package edu.uag.iidis.scec.control;


import java.io.IOException;
import java.util.Date;
 
import edu.uag.iidis.scec.modelo.Usuario;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class MCULogin implements Filter
{


 public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException{
        HttpServletRequest miRequest=(HttpServletRequest)request; 
        String url = (String) miRequest.getRequestURL().toString();
        System.out.println(url);
        if (matchExcludePatterns(url)) {
            System.out.println("No entra a filtro");
            chain.doFilter(request, response);
            return;
        }
        System.out.println("Entra a filtro");

         
        HttpServletResponse miResponse = (HttpServletResponse) response;

        String miUsuario = (String) miRequest.getSession().getAttribute("user");

        System.out.println("Comenzando Filter ");
        System.out.println(miUsuario);
        
        if (miUsuario == null){
            miRequest.getRequestDispatcher("/registroUsuario.do").forward(request,response);   

        } else{
            chain.doFilter(request, response);
        }
        
    }
    public void init(FilterConfig cfg) throws ServletException {
        System.out.println("Demo Filter ");

    }
    
    public void destroy() {}

    public boolean matchExcludePatterns(String url) {
        if(url==null) {
            return true;
        }
        String str1 = "tutorials point", str2 = "http://";
        CharSequence array[] = new CharSequence[3];
        array[0] = ".js";
        array[1] = ".css";
        array[2] = "procesarLogin";
        for(int i=0; i<3; i++) {
            boolean retval = url.contains(array[i]);
            if (retval) {
                return true;
            }
        }
        return false;
    }
}