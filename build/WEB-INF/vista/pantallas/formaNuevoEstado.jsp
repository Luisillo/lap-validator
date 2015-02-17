    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="/WEB-INF/vista/etiquetas/struts-html.tld" prefix="html" %>


    <br>
    <font size='5'><fmt:message key="formaNuevoEstado.titulo" /></font>

    <form id="forma" action="procesarRegistroEstado.do" method="post">
        <div class="table">
            <div class="tr">
                <div class="td" colspan="2">
                   <html:errors />
                </div>
            </div>
            <div class="tr">
                <div class="td" align="right">
                    <fmt:message key="formaNuevoEstado.etiqueta.nombre" />
                </div>
                <div class="td" align="left">
                    <input type="text" 
                           name="nombre" 
                           size="50" 
                           maxlength="100" 
                           value="${formaNuevoEstado.nombre}" />
                </div>
            </div>
            <div class="tr">
                <div class="td" align="right">
                    <fmt:message key="formaNuevoEstado.etiqueta.descripcion" />
                </div>
                <div class="td" align="left">
                    <input type="text" 
                           name="descripcion" 
                           size="50" 
                           maxlength="100" 
                           value="${formaNuevoEstado.descripcion}" />
                </div>
            </div>
            <div class="tr">
                <div class="td"  style="width:400px;" align="center">
                    <input type="submit" 
                           name="submit"
                           value="Agregar y terminar"/>
                    <input type="submit" 
                           name="submit"
                           value="Agregar y volver"
                           onclick="forma.action='procesarRegistroEstado.do?volver=si'"/>
                    <input type="button"
                           value="Reset"
                           onclick="location.href='solicitarRegistroEstado.do'" />
                    <input type="submit" 
                           name="org.apache.struts.taglib.html.CANCEL" 
                           value="cancelar" 
                           onclick="bCancel=true;">    
                </div>
            </div>
        </div>
    </form>