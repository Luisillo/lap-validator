package edu.uag.iidis.scec.modelo;

import java.util.ArrayList;
import java.util.List;


/**
 * Esta clase es usada para representar a un usuario de la
 * aplicación. 
 *
 * <p>
 * <a href="Usuario.java.html"><i>Ver código fuente</i></a>
 * </p>
 *
 * @author <a href="mailto:vramos@uag.mx">Victor Ramos</a>
 * @version 1.0
 */
public class Usuario extends Persona {
    
    protected Credencial credencial;
    protected List roles = new ArrayList();
    protected List estados = new ArrayList();

    public Usuario(){
    }

    public Usuario(NombrePersona nombre, 
                   Credencial credencial) {
        super(nombre);
        this.credencial=credencial;
    }


    /**
     * Regresa el identificador del usuario.
     * @return Long
     */
    public Credencial getCredencial() {
        return credencial;
    }

    /**
     * Establece el identificador del usuario.
     * @param id Identificador del usuario
     */
    public void setCredencial(Credencial credencial) {
        this.credencial = credencial;
    }


    /**
     * Regresa los roles a los que pertenece el usuario.
     * @return List
     */
    public List getRoles() {
        return roles;
    }


    /**
     * Establece los roles del usuario
     * @param roles
     */
    public void setRoles(List roles) {
        this.roles = roles;
    }


    /**
     * Agrega un rol para el usuario
     * @param rol
     */
    public void addRol(Rol rol) {
        if (roles.indexOf(rol) < 0) {
            roles.add(rol);
            rol.getUsuarios().add(this);
        }
    }

    
    public void removeRol(Rol rol) {
        roles.remove(rol);
        rol.getUsuarios().remove(this);
    }

    /* Estados aquí */
    /**
     * Regresa los estados a los que pertenece el usuario.
     * @return List
     */
    public List getEstados() {
        return estados;
    }


    /**
     * Establece los estados del usuario
     * @param estados
     */
    public void setEstados(List estados) {
        this.estados = estados;
    }


    /**
     * Agrega un estado para el usuario
     * @param estado
     */
    public void addEstado(Estado estado) {
        if (estados.indexOf(estado) < 0) {
            estados.add(estado);
            estado.getUsuarios().add(this);
        }
    }

    
    public void removeEstado(Estado estado) {
        estados.remove(estado);
        estado.getUsuarios().remove(this);
    }
}