package telefonos;  

import java.util.Scanner;

/**
 * @author J.Manuel Domínguez
  *
 */

public class Movil {
	 //describimos los atributos 

 
    private int numTelefono;
    private long IMEI;
    private String marca;
    private String sisOperativo;
    private String operador;
    private int permanencia;
    private String usuario;

    //declarar el constructor por defecto
    public Movil() {
        
        this(0,0,"","","",0,""); // LLamada al otro constructor sobrecargado
    }

    //declarar el constructor con los parametros
    /**
     * @param numTelefono
     * @param IMEI
     * @param marca
     * @param sisOperativo
     * @param operador
     * @param permanencia
     * @param usuario
     */
    public Movil(int numTelefono, long IMEI, String marca, String sisOperativo, String operador, int permanencia, String usuario) {
        this.numTelefono = numTelefono;
        this.IMEI = IMEI;
        this.marca = marca;
        this.sisOperativo = sisOperativo;
        this.operador = operador;
        this.permanencia = permanencia;
        this.usuario = usuario;
    }
    
    //Métodos get y set de los siete atributos dentro que tiene la clase movil generados automaticamente

    public int getNumTelefono() {
        return numTelefono;
    }

    /**
     * @param numTelefono
     */
    public void setNumTelefono(int numTelefono) {
        this.numTelefono = numTelefono;
    }

    /**
     * @return
     */
    public long getIMEI() {
        return IMEI;
    }

    /**
     * @param IMEI
     */
    public void setIMEI(long IMEI) {
        this.IMEI = IMEI;
    }

    /**
     * @return
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return
     */
    public String getSisOperativo() {
        return sisOperativo;
    }

    /**
     * @param sisOperativo
     */
    public void setSisOperativo(String sisOperativo) {
        this.sisOperativo = sisOperativo;
    }

    /**
     * @return
     */
    public String getOperador() {
        return operador;
    }

    /**
     * @param operador
     */
    public void setOperador(String operador) {
        this.operador = operador;
    }

    /**
     * @return
     */
    public int getPermanencia() {
        return permanencia;
    }

    /**
     * @param permanencia
     */
    public void setPermanencia(int permanencia) {
        this.permanencia = permanencia;
    }

    /**
     * @return
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    
    /**
     * Si permanencia es 0 pide por consola un nuevo valor para operador y 
     * actualiza con el introducido.
     *   
     * @return true si la portabilidad se ha realzado; false en caso contrario
     */
    public boolean portabilidad() {
        if (permanencia == 0) {
            
            System.out.println("Su permanencia ha vencido, ¿cuál es su nuevo operador?:");
            Scanner sc = new Scanner(System.in);
            
            operador = sc.next();
            
            System.out.println("Su nuevo operador es " + operador); 
            sc.close();
            return true;
        } else {
            System.out.println("Su permanencia no ha vencido, no puede cambiar de operador");
            return false;

        }
        
       
    }
    
    // pasaunMes: Este método resta 1 al valor del atributo permanencia, siempre que sea mayor que 0.
   
    /**
     *  Si la permanencia es mayor que cero la decrementa en una unidad
     */
    public void pasaunMes() {
    	
        if (permanencia > 0) {
        	permanencia--;
        	System.out.println("Quedan " + permanencia + " meses de permanencia");
        } else {
            System.out.println("Ya esta exento de permanencia");
        }
    }

    // Creamos el método con la que se genera la frase que saldrá por pantalla con el método toString
    // Lo haceomos sobrescribiendo el método de la clase padre Object

    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        
        
    	return "Número Teléfono =" + numTelefono + "\nIMEI =" + IMEI + "\nMarca =" + marca + "\nSistema Operativo =" 
    		+ sisOperativo + "\nOperador=" + operador + "\nPermanencia=" + permanencia + "\nUsuario=" + usuario;
    }
}
