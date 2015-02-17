    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="/WEB-INF/vista/etiquetas/struts-html.tld" prefix="html" %>
<style>
  .HipervinculoAdmon{
    color:#000000;
	text-decoration:none;
  }
  
  .HipervinculoAdmon:hover{
    color:#006666;
	text-decoration:underline;
  }
</style>
<script language="javascript" type="text/javascript">
<!--
  function EliminarGente(strGenteName){
    return confirm("¿Desea eliminar la persona '" + strGenteName + "'?")
  }
-->
</script>
    <br>
    <font size='5'><fmt:message key="formaListadoGentes.titulo" /></font>
   
      <div class="table" style="width:100%"  id="tablecontent">
          <div class="tr">
              <div class="td" colspan="4" style="padding-top:25px; padding-bottom:25px;">
                  <a href="solicitarRegistroGente.do" class="HipervinculoAdmon boton">Agregar nueva persona...</a>
              </div>
          </div>
          <div class="tr">
              <div class="td" colspan="4">
                 <html:errors />
              </div>
          </div>
          <div class="tr enlaces" style="background:#CCCCCC">
           <div class="td" style="width:16%"><b id="nombres"><fmt:message key="formaListadoGentes.etiqueta.nombres" /></b></div>
           <div class="td" style="width:16%"><b id="apellidos"><fmt:message key="formaListadoGentes.etiqueta.apellidos" /></b></div>
           <div class="td" style="width:16%"><b id="direccion"><fmt:message key="formaListadoGentes.etiqueta.direccion" /></b></div>
           <div class="td" style="width:16%"><b id="telefono"><fmt:message key="formaListadoGentes.etiqueta.telefono" /></b></div>
           <div class="td" style="width:16%"><b id="ciudad"><fmt:message key="formaListadoGentes.etiqueta.ciudad" /></b></div>
           
           <div class="td"  style="width:16%"><b><fmt:message key="formaListadoGentes.etiqueta.administracion" /></b></div>
          </div>
          <c:forEach var="gente" items="${formaListadoGentes.gentes}">
              <div class="tr">
                  <div class="td" align="left" style="width:16%"><c:out value="${gente.nombres}"/></div>
                  <div class="td" align="left" style="width:16%"><c:out value="${gente.apellidos}"/></div>
                  <div class="td" align="left" style="width:16%"><c:out value="${gente.direccion}"/></div>
                  <div class="td" align="left" style="width:16%"><c:out value="${gente.telefono}"/></div>
                  <div class="td" align="left" style="width:16%">
                    <c:forEach var="ciudad" items="${formaListadoGentes.ciudades}">
                      <c:if test="${ciudad.id == gente.idCiudad}">
                        <c:out value="${ciudad.nombre}"/>
                      </c:if>
                    </c:forEach>
                    
                  </div>
                  <div class="td" align="left" style="width:16%">
                      <a href='solicitarModificarGente.do?id=<c:out value="${gente.id}"/>'
               class="HipervinculoAdmon">
                          <fmt:message key="formaListadoGentes.etiqueta.modificar" />
                      </a>
                      <a href='procesarEliminarGente.do?id=<c:out value="${gente.id}"/>'
               onClick="javascript: return EliminarGente('<c:out value="${gente.nombres}"/>')"
               class="HipervinculoAdmon">
                          <fmt:message key="formaListadoGentes.etiqueta.eliminar" />
                      </a>
                  </div>
              </div>
          </c:forEach>
          <div class="tr">
              <div class="td" colspan="4" align="right" style="padding-top:25px;"><b>Total:</b> ${formaListadoGentes.contador}</div>
          </div>
      </div>
      