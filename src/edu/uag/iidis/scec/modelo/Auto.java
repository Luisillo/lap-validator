package edu.uag.iidis.scec.modelo;

import java.io.Serializable;
import java.util.*;


/**
 * Esta clase es usada para representar un rol de un
 * usuario. 
 *
 * <p><a href="Auto.java.html"><i>Ver código fuente</i></a></p>
 *
 * @author <a href="mailto:vramos@uag.mx">Victor Ramos</a>
 * @version 1.0
 */
public class Auto extends ClaseBase 
        implements Serializable {

    private Long id;
    private String marca;
    private String color;
	private String placas;
	private String propietario;
    private List usuarios = new ArrayList();

    public Auto() {
    }

    public Auto(Long id){
        this.id = id;
    }

    public Auto(String marca, String color, String placas, String propietario){
        this.marca=marca;
		this.color=color;
		this.placas=placas;
		this.propietario=propietario;
        
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
     * Regresa el marca del rol.
     * @return String
     */
    public String getMarca() {
        return this.marca;
    }

    /**
     * Establece el marca del rol.
     * @return void
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }
	
	public String getColor(){
	
		return color;
	}
	
	public void setColor(String color){
	
		this.color = color;
	}

    public String getPlacas(){
	
		return placas;
	}
	
	public void setPlacas(String placas){
	
		this.placas = placas;
	}
	
	public String getPropietario(){
	
		return propietario;
	}
	
	public void setPropietario(String propietario){
	
		this.propietario = propietario;
	}
  
}
