<!-- Inicio de código para determinar el tiempo de procesamiento -->
	<%@ page import="java.util.*" %>
	<% long t1=System.currentTimeMillis(); %>
<!-- Fin de código para determinar el tiempo de procesamiento -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

	<h2>Personas</h2>
	<a href="directorio.do?accion=nuevaPersona">Agregar Persona</a>

  	<c:choose>
  		<c:when test='${empty param.ordenarPor}'>
			<sql:query var="personas" dataSource="jdbc/sic">
				SELECT * FROM persona
			</sql:query>
  		</c:when>

  		<c:when test='${not empty param.ordenarPor}'>
			<sql:query var="personas">
				SELECT * FROM persona ORDER BY ?
      			<sql:param value='${param.ordenarPor}'/>
			</sql:query>
  		</c:when>
  	</c:choose>	
	
	<table align="center">
		<!-- Inicio de Encabezado de la Lista -->
		
		<!-- Inicio de Encabezado de Columnas -->
		<tr>
			<th>
				<a href='verPersonas.jsp?ordenarPor=id'>Id</a>
			</th>
			<th>
				<a href='verPersonas.jsp?ordenarPor=nombre'>Nombre</a>
			</th>
			<th>X</th>			
		</tr>
		<!-- Fin de Encabezado de Columnas -->
		<!-- Fin de Encabezado de la Lista -->
		
		<!-- Inicio del Detalle de la Lista -->
		<c:forEach var="persona" items="${personas.rows}">
		<tr>
			<td align="center" valign="top" width="50px">
				<a href='editarPersona.jsp?id=<c:out value="${persona.id}"/>'>
					<c:out value="${persona.id}"/>
				</a>
			</td>
			<td align="left" valign="top" width="350px">
				<c:out value="${persona.apellidos}"/>
			</td>
			<td align="center" valign="top" width="20px">
				<a href='eliminarPersona.jsp?id=<c:out value="${persona.id}"/>'>X</a>
			</td>
		</tr>
		</c:forEach>
		<!-- Fin del Detalle de la Lista -->
		
		<!-- Inicio del Pie de la Lista -->
		
		<!-- Fin del Pie de la Lista -->
				
	</table>

<!-- Inicio de código para calcular y visualiza el tiempo de procesamiento -->
<p><%= System.currentTimeMillis()-t1 %>ms</p>
<!-- Fin de código para calcular y visualiza el tiempo de procesamiento -->