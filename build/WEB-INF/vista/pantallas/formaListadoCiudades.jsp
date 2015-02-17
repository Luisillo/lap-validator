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
  function EliminarCiudad(strCiudadName){
    return confirm("¿Desea eliminar el ciudad '" + strCiudadName + "'?")
  }
-->
</script>
    <br>
    <font size='5'><fmt:message key="formaListadoCiudades.titulo" /></font>
    <div class="table" style="width:100%" >
        <div class="tr">
            <div class="td" colspan="4" style="padding-top:25px; padding-bottom:25px;">
                <a href="solicitarRegistroCiudad.do" class="HipervinculoAdmon boton">Agregar nuevo ciudad...</a>
            </div>
        </div>
        <div class="tr">
            <div class="td" colspan="4">
               <html:errors />
            </div>
        </div>
        <div class="tr" style="background:#CCCCCC">
         <div class="td" style="width:20%"><b><fmt:message key="formaListadoCiudades.etiqueta.nombre" /></b></div>
         <div class="td" style="width:20%"><b><fmt:message key="formaListadoCiudades.etiqueta.estado" /></b></div>
         <div class="td" style="width:38%; border-right-style:solid; border-left-style:solid; border-width:1px; border-color:#000000;"><b><fmt:message key="formaListadoCiudades.etiqueta.descripcion" /></b></div>
         <div class="td" style="width:20%"><b><fmt:message key="formaListadoCiudades.etiqueta.administracion" /></b></div>
        </div>
        <c:forEach var="ciudad" items="${formaListadoCiudades.ciudades}">
            <div class="tr">
                <div class="td" align="left" style="width:20%"><c:out value="${ciudad.nombre}"/></div>
                <div class="td" align="left" style="width:20%">
                  <c:forEach var="estado" items="${formaListadoCiudades.estados}">
                    <c:if test="${estado.id == ciudad.idEstado}">
                      <c:out value="${estado.nombre}"/>
                    </c:if>
                  </c:forEach>
                  
                </div>
                <div class="td" align="left" style="width:38%"><c:out value="${ciudad.descripcion}"/></div>
                <div class="td" align="left" style="width:20%">
                    <a href='solicitarModificarCiudad.do?id=<c:out value="${ciudad.id}"/>'
					   class="HipervinculoAdmon">
                        <fmt:message key="formaListadoCiudades.etiqueta.modificar" />
                    </a>
                    <a href='procesarEliminarCiudad.do?id=<c:out value="${ciudad.id}"/>'
					   onClick="javascript: return EliminarCiudad('<c:out value="${ciudad.nombre}"/>')"
					   class="HipervinculoAdmon">
                        <fmt:message key="formaListadoCiudades.etiqueta.eliminar" />
                    </a>
                </div>
            </div>
        </c:forEach>
        <div class="tr">
            <div class="td" colspan="4" align="right" style="padding-top:25px;"><b>Total:</b> ${formaListadoCiudades.contador}</div>
        </div>
    </div>