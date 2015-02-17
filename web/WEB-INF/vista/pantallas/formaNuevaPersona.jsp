    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="/tags/struts-html" prefix="html" %>


    <br>
    <font size='5'><fmt:message key="formaNuevaPersona.titulo" /></font>

    <form id="forma" action="procesarRegistro.do" method="post">
        <div class="table">
            <div class="tr">
                <div class="td" colspan="2">
                   <html:errors />
                </div>
            </div>
            <div class="tr">
                <div class="td" align="right">
                    <fmt:message key="formaNuevaPersona.etiqueta.prefijo" />
                </div>
                <div class="td" align="left">
                    <input type="text" 
                           name="prefijo" 
                           size="12" 
                           maxlength="12" 
                           value="${formaNuevaPersona.prefijo}" />
                </div>
            </div>
            <div class="tr">
                <div class="td" align="right">
                    <fmt:message key="formaNuevaPersona.etiqueta.nombre" />
                </div>
                <div class="td" align="left">
                    <input type="text" 
                           name="nombre" 
                           size="50" 
                           maxlength="100" 
                           value="${formaNuevaPersona.nombre}" />
                </div>
            </div>
            <div class="tr">
                <div class="td" align="right">
                    <fmt:message key="formaNuevaPersona.etiqueta.apellidoPaterno" />
                </div>
                <div class="td" align="left">
                    <input type="text" 
                           name="apellidoPaterno" 
                           size="50" 
                           maxlength="100" 
                           value="${formaNuevaPersona.apellidoPaterno}" />
                </div>
            </div>
            <div class="tr">
                <div class="td" align="right">
                    <fmt:message key="formaNuevaPersona.etiqueta.apellidoMaterno" />
                </div>
                <div class="td" align="left">
                    <input type="text" 
                           name="apellidoMaterno" 
                           size="50" 
                           maxlength="100" 
                           value="${formaNuevaPersona.apellidoMaterno}" />
                </div>
            </div>
      <!---    /cidades --->      
            <div class="tr">
                <div class="td" align="right">
                    <fmt:message key="formaNuevaPersona.etiqueta.ciudades" />
                </div>
                <div class="td" align="left">
       				<select name="ciudad">
                        <c:forEach var="rol" items="${formaNuevaPersona.ciudades}">
    		            <option value="${rol.nombre}">"${rol.descripcion}"</option>
            			</c:forEach>
                     </select>
                </div>
            </div>
            
            
            
            
            <div class="tr">
                <div class="td" align="right">
                    <fmt:message key="formaNuevaPersona.etiqueta.nombreUsuario" />
                </div>
                <div class="td" align="left">
                    <input type="text" 
                           name="nombreUsuario" 
                           size="50" 
                           maxlength="100" 
                           value="${formaNuevaPersona.nombreUsuario}" />
                </div>
            </div>
            <div class="tr">
                <div class="td" align="right">
                    <fmt:message key="formaNuevaPersona.etiqueta.claveAcceso" />
                </div>
                <div class="td" align="left">
                    <input type="text" 
                           name="claveAcceso" 
                           size="50" 
                           maxlength="100" 
                           value="${formaNuevaPersona.claveAcceso}" />
                </div>
            </div>
            <div class="tr">
                <div class="td" align="right">
                    <fmt:message key="formaNuevaPersona.etiqueta.claveAccesoConfirmacion" />
                </div>
                <div class="td" align="left">
                    <input type="text" 
                           name="claveAccesoConfirmacion" 
                           size="50" 
                           maxlength="100" 
                           value="${formaNuevaPersona.claveAccesoConfirmacion}" />
                </div>
            </div>
            <div class="tr">
                <div class="td" colspan="2" align="center">
                    <input type="submit" 
                           name="submit"
                           value="Agregar y terminar"/>
                    <input type="submit" 
                           name="submit"
                           value="Agregar y volver"
                           onclick="forma.action='procesarRegistro.do?volver=si'"/>
                    <input type="button"
                           value="Reset"
                           onclick="location.href='solicitarRegistro.do'" />
                    <input type="submit" 
                           name="org.apache.struts.taglib.html.CANCEL" 
                           value="Cancelar" 
                           onclick="bCancel=true;">    
                </div>
            </div>
        </div>
    </form>



