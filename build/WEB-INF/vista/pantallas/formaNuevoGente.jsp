    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="/WEB-INF/vista/etiquetas/struts-html.tld" prefix="html" %>


    <br>
    <font size='5'><fmt:message key="formaNuevoGente.titulo" /></font>

    <form id="forma" action="procesarRegistroGente.do" method="post">
        <div class="table">
            <div class="tr">
                <div class="td" colspan="2">
                   <html:errors />
                </div>
            </div>
            <div class="tr">
                <div class="td" align="right">
                    <fmt:message key="formaNuevoGente.etiqueta.nombres" />
                </div>
                <div class="td" align="left">
                    <input type="text" 
                           name="nombres" 
                           size="50" 
                           maxlength="100" 
                           value="${formaNuevoGente.nombres}" />
                </div>
            </div>
            <div class="tr">
                <div class="td" align="right">
                    <fmt:message key="formaNuevoGente.etiqueta.apellidos" />
                </div>
                <div class="td" align="left">
                    <input type="text" 
                           name="apellidos" 
                           size="50" 
                           maxlength="100" 
                           value="${formaNuevoGente.apellidos}" />
                </div>
            </div>
            <div class="tr">
                <div class="td" align="right">
                    <fmt:message key="formaNuevoGente.etiqueta.direccion" />
                </div>
                <div class="td" align="left">
                    <input type="text" 
                           name="direccion" 
                           size="50" 
                           maxlength="100" 
                           value="${formaNuevoGente.direccion}" />
                </div>
            </div>
            <div class="tr">
                <div class="td" align="right">
                    <fmt:message key="formaNuevoGente.etiqueta.telefono" />
                </div>
                <div class="td" align="left">
                    <input type="text" 
                           name="telefono" 
                           size="50" 
                           maxlength="100" 
                           value="${formaNuevoGente.telefono}" />
                </div>
            </div>
            <div class="tr">
                <div class="td" align="right">
                    <fmt:message key="formaNuevoGente.etiqueta.ciudad" />
                </div>
                <div class="td" align="left" style="position:relative">
                    <input type="text" 
                           id ="ciudadBuscar" autocomplete="off" />
                    <div class="buscar">
                      <ul>
                      </ul>
                    </div>
                    <input type="text" 
                           id="idCiudad"
                           name="idCiudad" 
                           size="50" 
                           style="display:none"
                           maxlength="100" />
                  <!--
                    <select name="idCiudad" >
                      <c:forEach var="ciudad" items="${formaNuevoGente.ciudades}">
                        <option value='<c:out value="${ciudad.id}" />'><c:out value="${ciudad.nombre}" /></option>
                      </c:forEach>
                    </select>
                  -->
                </div>
            </div>
            <div class="tr">
                <div class="td" align="right">
                    <fmt:message key="formaNuevoGente.etiqueta.estado" />
                </div>
                <div class="td" align="left" style="position:relative">
                    <div id="estadoNombre"></div>
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
                           onclick="forma.action='procesarRegistroGente.do?volver=si'"/>
                    <input type="button"
                           value="Reset"
                           onclick="location.href='solicitarRegistroGente.do'" />
                    <input type="submit" 
                           name="org.apache.struts.taglib.html.CANCEL" 
                           value="cancelar" 
                           onclick="bCancel=true;">    
                </div>
            </div>
        </div>
    </form>