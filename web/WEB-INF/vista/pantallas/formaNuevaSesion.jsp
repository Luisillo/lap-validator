<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="/WEB-INF/vista/etiquetas/struts-html.tld" prefix="html" %>

    <section>
      <div class="col-md-8 card_style top-space center-block no-float">
        <h1><fmt:message key="formaNuevoSesion.etiqueta.titulo" /></h1>
        <form id="forma" action="procesarLogin.do" method="post">
          <div>
            <html:errors />
          </div>
          <div class="form-login">
            <input type="text" 
                   placeholder="Nombre"
                   name= "nombrePrefijo"
                   maxlength="100"
                   autocomplete="off"
                   value="${formaNuevaSesion.nombre}" />          
            <input type="password" 
                   placeholder="Password"
                   name= "claveAcceso"
                   maxlength="100"
                   autocomplete="off"
                   value="${formaNuevaSesion.claveAcceso}" />
          </div>
          <div>
            <input type="submit" 
              name="submit"
              class="btn btn-info" 
              value="Auntentificar"/>
          </div>
      </form>
      </div>
    </section>
