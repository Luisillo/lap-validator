    <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
    <%@ taglib uri='http://java.sun.com/jstl/fmt' prefix='fmt' %>
    <%@ taglib uri='/WEB-INF/vista/etiquetas/struts-html.tld' prefix='html' %>

      <c:forEach var='ciudad' items='${formaNuevoGenteCiudades.ciudades}'>
        <li onclick="seleccionarCiudad(<c:out value='${ciudad.id}' />, &quot;<c:out value='${ciudad.nombre}' />&quot;, &quot;<c:forEach var='estado' items='${formaNuevoGenteCiudades.estados}'><c:if test='${estado.id == ciudad.idEstado}'><c:out value='${estado.nombre}'/></c:if></c:forEach>&quot;)"><c:out value='${ciudad.nombre}' /></li>
      </c:forEach>