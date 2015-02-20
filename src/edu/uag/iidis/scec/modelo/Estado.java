package edu.uag.iidis.scec.modelo;

import java.io.Serializable;
import java.util.*;


/**
 * Esta clase es usada para representar un rol de un
 * usuario. 
 *
 * <p><a href="Estado.java.html"><i>Ver código fuente</i></a></p>
 *
 * @author <a href="mailto:vramos@uag.mx">Victor Ramos</a>
 * @version 1.0
 */
public class Estado extends ClaseBase 
        implements Serializable {

    private Long id;
    private String estado;
    private String pais;
    private List usuarios = new ArrayList();

    public Estado() {
    }

    public Estado(Long id){
        this.id = id;
    }

    public Estado(String estado, String pais){
        this.estado=estado;
        this.pais=pais;
    }

    /**
     * Regresa el id del rol.
     * @return Long
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Establece el id del rol.
     * @return void
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * Regresa el estado del rol.
     * @return String
     */
    public String getEstado() {
        return this.estado;
    }

    /**
     * Establece el estado del rol.
     * @return void
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    /**
     * Regresa la descripción del rol.
     * @return String
     */
    public String getPais() {
        return this.pais;
    }

    /**
     * Establece la descripción del rol.
     * @return void
     */
    public void setPais(String pais) {
        this.pais = pais;
    }
    /**
     * Regresa los usuarios del rol.
     * @return List
     */
    public List getUsuarios() {
        return this.usuarios;
    }

    /**
     * Establece los usuarios del rol.
     * @return void
     */
    public void setUsuarios(List usuarios) {
        this.usuarios = usuarios;
    }

    public void addUsuario(Usuario usuario) {
        usuarios.add(usuario);
        usuario.getEstados().add(this);
    }
}
