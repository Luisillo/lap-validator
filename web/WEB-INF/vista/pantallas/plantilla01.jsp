<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<html>
    <head>
        <title>Titulo del documento</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
        <link rel="stylesheet" type="text/css" href="css/plantilla.css">
        <link rel="stylesheet" type="text/css" href="css/mensajes.css">
    </head>
    
    <body>
       
        <c:import url="/WEB-INF/vista/comun/banner.jsp" />
        <c:import url="/WEB-INF/vista/comun/barraMenu01.jsp" />
  
        <section class="inicio">
            <c:import url="/WEB-INF/vista/pantallas/${param.c}" />
        </section>
   
        <c:import url="/WEB-INF/vista/comun/barraPie.jsp" />

    </body>
</html>