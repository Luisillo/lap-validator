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
  function EliminarEstado(strEstadoName){
    return confirm("¿Desea eliminar el estado '" + strEstadoName + "'?")
  }
-->
</script>
    <br>
    <font size='5'><fmt:message key="formaListadoEstados.titulo" /></font>
    <div class="table" style="width:100%" >
        <div class="tr">
            <div class="td" colspan="4" style="padding-top:25px; padding-bottom:25px;">
                <a href="solicitarRegistroEstado.do" class="HipervinculoAdmon boton">Agregar nuevo estado...</a>
            </div>
        </div>
        <div class="tr">
            <div class="td" colspan="4">
               <html:errors />
            </div>
        </div>
        <div class="tr" style="background:#CCCCCC;">
         <div class="td" style="width:20%"><b><fmt:message key="formaListadoEstados.etiqueta.nombre" /></b></div>
         <div class="td" style="width:58%; border-right-style:solid; border-left-style:solid; border-width:1px; border-color:#000000;"><b><fmt:message key="formaListadoEstados.etiqueta.descripcion" /></b></div>
         <div class="td" style="width:20%"><b><fmt:message key="formaListadoEstados.etiqueta.administracion" /></b></div>
        </div>
        <c:forEach var="estado" items="${formaListadoEstados.estados}">
            <div class="tr">
                <div class="td" align="left" style="width:20%"><c:out value="${estado.nombre}"/></div>
                <div class="td" align="left" style="width:58%"><c:out value="${estado.descripcion}"/></div>
                <div class="td" align="left" style="width:20%">
                    <a href='solicitarModificarEstado.do?id=<c:out value="${estado.id}"/>'
					   class="HipervinculoAdmon">
                        <fmt:message key="formaListadoEstados.etiqueta.modificar" />
                    </a>
                    <a href='procesarEliminarEstado.do?id=<c:out value="${estado.id}"/>'
					   onClick="javascript: return EliminarEstado('<c:out value="${estado.nombre}"/>')"
					   class="HipervinculoAdmon">
                        <fmt:message key="formaListadoEstados.etiqueta.eliminar" />
                    </a>
                </div>
            </div>
        </c:forEach>
        <div class="tr">
            <div class="td" colspan="4" align="right" style="padding-top:25px;"><b>Total:</b> ${formaListadoEstados.contador}</div>
        </div>
    </div>