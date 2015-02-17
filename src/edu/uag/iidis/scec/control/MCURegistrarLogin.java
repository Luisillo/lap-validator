package edu.uag.iidis.scec.control;

import edu.uag.iidis.scec.vista.*;
import edu.uag.iidis.scec.modelo.*;
import edu.uag.iidis.scec.servicios.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;



public final class MCURegistrarLogin

        extends MappingDispatchAction {

    private Log log = LogFactory.getLog(MCURegistrarLogin.class);


    public ActionForward solicitarRegistro(
                ActionMapping mapping,
                ActionForm form,
                HttpServletRequest request,
                HttpServletResponse response)
            throws Exception {

        if (log.isDebugEnabled()) {
            log.debug(">solicitarRegistro");
        }
        return (mapping.findForward("exito"));
    }



    public ActionForward procesarRegistro(
                ActionMapping mapping,
                ActionForm form,
                HttpServletRequest request,
                HttpServletResponse response)
            throws Exception {

        if (log.isDebugEnabled()) {
            log.debug(">procesarRegistro");
        }

        // Verifica si la acción fue cancelada por el usuario
        if (isCancelled(request)) {
            if (log.isDebugEnabled()) {
                log.debug("<La acción fue cancelada");
            }
            return (mapping.findForward("cancelar"));
        }

        
        // Se obtienen los datos para procesar el registro
        FormaNuevoLogin forma = (FormaNuevoLogin)form;

        Login rol = new Login(forma.getnombrePrefijo(),
                          forma.getclaveAcceso());
        System.out.println("Imprimo "+forma.getnombrePrefijo()+" y "+forma.getclaveAcceso());
        ManejadorLogin mr = new ManejadorLogin();
        int resultado = mr.crearLogin(rol);

        HttpServletRequest miRequest=(HttpServletRequest)request;  
        

        ActionMessages errores = new ActionMessages();
        switch (resultado) {
            case 0:   
                miRequest.getSession().setAttribute("user",forma.getnombrePrefijo());
                if (log.isDebugEnabled()) {
                    log.debug(">Sesión creada: "+forma.getnombrePrefijo());
                }
                return (mapping.findForward("exito"));

            case 1:
                errores.add(ActionMessages.GLOBAL_MESSAGE,
                            new ActionMessage("errors.nombreGenteYaExiste",
                                               forma.getnombrePrefijo()));   
                if (log.isDebugEnabled()) {
                    log.debug(">Incorrecto");
                }             
                saveErrors(request, errores);
                return (mapping.getInputForward());

            case 3:
                log.error("Ocurrió un error de infraestructura");
                errores.add(ActionMessages.GLOBAL_MESSAGE,
                            new ActionMessage("errors.infraestructura"));                
                saveErrors(request, errores);
                return (mapping.getInputForward());

            default:
                log.warn("ManejadorUsuario.crearUsuario regresó reultado inesperado");
                errores.add(ActionMessages.GLOBAL_MESSAGE,
                            new ActionMessage("errors.infraestructura"));                
                saveErrors(request, errores);
                return (mapping.getInputForward());
        }
    }
}

