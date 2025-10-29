
package logica_negocio;

public class Usuario {
    //Atributos
    private String dni;
    private String nombreCompleto;
    private String correoElectronico;
    private int telefono;
    private int prestamosActivos =0;

    //Constructor vacío
    public Usuario() {
    }

    //Getters
    public String getDni() {return dni;}
    public String getNombreCompleto() {return nombreCompleto;}
    public String getCorreoElectronico() { return correoElectronico;}
    public int getTelefono() { return telefono;}
    public int getPrestamosActivos() { return prestamosActivos;}
    
    //Setters
    public void setDni(String dni) { this.dni = dni;}
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto;}
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico;}
    public void setTelefono(int telefono) {this.telefono = telefono;}
    public void setPrestamosActivos(int prestamosActivos) {this.prestamosActivos = prestamosActivos;}
    
    //Métodos
    @Override
    public String toString(){
        return String.format(
            "Nombre: %s\nCorreo: %s\nDNI: %s\nTeléfono: %d\n",
            getNombreCompleto(),
            getCorreoElectronico(),
            getDni(),
            getTelefono());
    }
    //Verificación para el buscador
    public boolean coincideCon(String dato) {
    return this.nombreCompleto.equalsIgnoreCase(dato) || this.dni.equals(dato);
    }
}
