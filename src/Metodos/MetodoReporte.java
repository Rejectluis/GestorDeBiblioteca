
package Metodos;

import logica_negocio.Usuario;
import logica_negocio.Libro;
import logica_negocio.Prestamo;
import java.util.ArrayList;

public class MetodoReporte {
        private static StringBuilder sb = new StringBuilder();
        private static String separador = "-".repeat(20);
    
    //Método 1- Reporte de libros prestados
    public static String librosPrestados(){
        sb.setLength(0);
        ArrayList<Prestamo> listaPrestamos = Prestamo.getPrestamosLista();
        
        if(listaPrestamos != null && !listaPrestamos.isEmpty()){
            sb.append("LIBROS PRESTADOS:\n");
            int i =1;
            
            for(Prestamo prestamo: listaPrestamos){
               sb.append(i++).append(". ").append(prestamo.getLibroPrestado().getTitulo()).append("\n");
            }
        }else{
            sb.append("No hay libros prestados todavía");
        }
        return sb.toString();
    }
    
    //Método 2- Reporte de libros disponibles
    public static String librosDisponibles(){
        sb.setLength(0);
        ArrayList<Libro> listaLibros = MetodoLibro.getListaLibros();
        int i=1;
        if(listaLibros != null && !listaLibros.isEmpty()){ 
            for(Libro libro: listaLibros){
                if(libro.getEjemplares()>0){
                    sb.append("\n").append(i++).append(". ").append(libro.getTitulo()).append(" - (").append(libro.getEjemplares()).append(")");
                } 
            }
        }else{
            sb.append("No hay libros disponibles todavía");
        }
        return sb.toString();   
    }
    
    //Método 3- Reporte de usuario con más préstamos
    public static String usuriosConMasPrestamos(){
        /*sb.setLength(0);
        ArrayList<Prestamo> lista = ClasesYMain.Prestamo.getPrestamosLista();
            
        if(lista != null && !lista.isEmpty()){
            for(Prestamo prestamo : lista){
                if(prestamo.getUsuarioPrestador().getPrestamosActivos() >= 3){
                    sb.append("\nUSUARIOS CON MÁS PRÉSTAMOS:\n").append(separador).append(prestamo);
                }
            }
        }else{
            sb.append("Todavía no hay usuarios con muchos préstamos");
        }
        */
        sb.setLength(0);
        ArrayList<Prestamo> lista = logica_negocio.Prestamo.getPrestamosLista();
        
        // Verifica si la lista es null o está vacía (ningún préstamo registrado aún)
        if (lista == null || lista.isEmpty()) {
            sb.append("Todavía no hay usuarios registrados con préstamos.\n");
            return sb.toString();
        }
        
        boolean hayUsuarios = false;    // para saber si se encontró al menos un usuario con 3+ préstamos
        sb.append("USUARIOS CON MÁS PRÉSTAMOS (3 o más):\n").append(separador).append("\n");
        
        // Recorre todos los préstamos para verificar qué usuarios tienen ≥3 préstamos activos
        for (Prestamo prestamo : lista) {
            
            Usuario usuario = prestamo.getUsuarioPrestador(); // Obtiene el usuario que hizo el préstamo
            if (usuario.getPrestamosActivos() >= 3) {   // Si tiene 3 o más préstamos activos
                hayUsuarios = true; // Marca true, entonces se agregan los datos en el stringbuilder
                sb.append(usuario).append("\n").append(separador).append("\n"); // Se agrega un dato al reporte
            }
        }
        
        // Si después del recorrido no se encontró ningún usuario con 3+ préstamos
        if (!hayUsuarios) {
            sb.setLength(0); // Se borra encabezado anterior
            sb.append("Ningún usuario tiene 3 o más préstamos activos.\n"); //  Mensaje final 
        }
        return sb.toString();
    }
}
