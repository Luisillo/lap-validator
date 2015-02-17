    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="/WEB-INF/vista/etiquetas/struts-html.tld" prefix="html" %>


    <br>
    <font size='5'><fmt:message key="formaNuevoCiudad.titulo" /></font>

    <form id="forma" action="procesarRegistroCiudad.do" method="post">
        <div class="table">
            <div class="tr">
                <div class="td" colspan="2">
                   <html:errors />
                </div>
            </div>
            <div class="tr">
                <div class="td" align="right">
                    <fmt:message key="formaNuevoCiudad.etiqueta.nombre" />
                </div>
                <div class="td" align="left">
                    <input type="text" 
                           name="nombre" 
                           size="50" 
                           maxlength="100" 
                           value="${formaNuevoCiudad.nombre}" />
                </div>
            </div>
            <div class="tr">
                <div class="td" align="right">
                    <fmt:message key="formaNuevoCiudad.etiqueta.descripcion" />
                </div>
                <div class="td" align="left">
                    <input type="text" 
                           name="descripcion" 
                           size="50" 
                           maxlength="100" 
                           value="${formaNuevoCiudad.descripcion}" />
                </div>
            </div>
            <div class="tr">
                <div class="td" align="right">
                    <fmt:message key="formaNuevoCiudad.etiqueta.estado" />
                </div>
                <div class="td" align="left">
                    <select name="idEstado" >
                      <c:forEach var="estado" items="${formaNuevoCiudad.estados}">
                        <option value='<c:out value="${estado.id}" />'><c:out value="${estado.nombre}" /></option>
                      </c:forEach>
                    </select>
                </div>
            </div>
                  
            <div class="tr">
                <div class="td" style="width:400px;" align="center">
                    <input type="submit" 
                           name="submit"
                           value="Agregar y terminar"/>
                    <input type="submit" 
                           name="submit"
                           value="Agregar y volver"
                           onclick="forma.action='procesarRegistroCiudad.do?volver=si'"/>
                    <input type="button"
                           value="Reset"
                           onclick="location.href='solicitarRegistroCiudad.do'" />
                    <input type="submit" 
                           name="org.apache.struts.taglib.html.CANCEL" 
                           value="cancelar" 
                           onclick="bCancel=true;">    
                </div>
            </div>
        </div>
    </form>