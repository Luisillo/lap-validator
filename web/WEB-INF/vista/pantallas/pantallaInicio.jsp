<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<html>
    <head>
        <title>Titulo del documento</title>
        <meta charset="utf-8" />
        <link rel="stylesheet" type="text/css" href="css/plantilla.css">
    </head>
	
    <body>
       
        <c:import url="/WEB-INF/vista/comun/banner.jsp" />
        <c:import url="/WEB-INF/vista/comun/barraMenu01.jsp" />
  
        <section class="inicio">
            <h2>Inicio</h2>
        </section>
   
        <c:import url="/WEB-INF/vista/comun/barraPie.jsp" />

    </body>
</html>
