<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<html>
    <head>
        <title>Titulo del documento</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <link rel="stylesheet" type="text/css" href="css/plantilla.css">
    </head>
	
    <body>

        <c:import url="/WEB-INF/vista/comun/banner.jsp" />
        <c:import url="/WEB-INF/vista/comun/barraMenu01.jsp" />
        <section class="inicio">
            <h3>Pantalla de Bienvenida</h3>
            <a href="solicitarListarRoles.do">Administración de roles</a>
        </section>
        <c:import url="/WEB-INF/vista/comun/barraPie.jsp" />
    </body>
</html>
