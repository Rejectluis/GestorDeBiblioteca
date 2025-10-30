
package Metodos;

import ClasesYMain.Libro;
import ClasesYMain.Prestamo;
import java.util.ArrayList;

public class MetodoLibro {
    static StringBuilder sb = new StringBuilder();
    private static String separador = "-".repeat(20);
    private static ArrayList<Libro> listaLibros = new ArrayList<>();
    
    //Getter del arraylist (Para acceder a él)
    public static ArrayList<Libro> getListaLibros() {
        return listaLibros;
    }
    
    //  MÉTODOS //
    //Método 1- Registrar un libro en el arraylist
    
    //Se crea un objeto "Libro" con parámetros, y luego se agrega al arraylist
    public static boolean crearLibro(String titulo,String autor,int anno,int ejemplares,String codigo){ 
        
        // Validación simple: evitar codigo duplicado
        for (Libro libro : listaLibros) {
            if (libro.getCodigo().equals(codigo)) {
                return false;
            }
        }
        
        Libro libro = new Libro();
        libro.setTitulo(titulo);
        libro.setAutor(autor);
        libro.setAnno(anno);
        libro.setEjemplares(ejemplares);
        libro.setCodigo(codigo);
        listaLibros.add(libro);
        return true;  
    }
    
    //Método 2-. Listar libros 
    public static String listarLibro(){
        sb.setLength(0);
        ArrayList<Libro> lista= MetodoLibro.getListaLibros();
        
        if(!lista.isEmpty()){//Si el arraylist no está vacío entonces se imprime    
            int i=1;         //propósito: para que los libros salgan con orden. Ej: Libro 1.. Libro 2...
            
            for(Libro libro: lista){
                sb.append(String.format("Libro %d:\n%s",i++,separador)).append("\n");//Impresion: Libro 1
                sb.append(libro).append("\n");                          //-------------------
                sb.append(separador).append("\n\n");                    // secuencia de datos..
            }                                                           //--------------------
        }else{
            sb.append("Librería vacía por ahora");
        }
        return sb.toString();                                            //Se retornan los datos
    }

    //Métdodo 3-.Buscar por título o código 
    public static Libro buscarLibro(String dato){
        ArrayList<Libro> lista= MetodoLibro.getListaLibros();

        for(Libro libro: lista){
            if(libro.getTitulo().equalsIgnoreCase(dato) || libro.getCodigo().equalsIgnoreCase(dato)){
                return libro;
            }
        }
        return null;
    }
     //Método 4-Editar libro
    public static Libro editarLibro(Libro libro, int opcion, String nuevoValor){
        
            switch (opcion) {
                case 1 -> libro.setTitulo(nuevoValor);              
                case 2 -> libro.setAutor(nuevoValor);
                case 3 -> {                                         //El tipo de dato esperado es String, pero en la clase es int, por ello se prueba convertir en Integer
                    try{                                            //si nuevoValor es un número se convierte en int
                        libro.setAnno(Integer.parseInt(nuevoValor));//si no se puede convertir en int se envía un error
                    }catch(NumberFormatException e){                //Error: "Año no válido"
                        System.out.println("Año no válido");
                       }
                }
                case 4 -> {                                         //Se repite la verificación de arriba (case 3)
                    try{
                        libro.setEjemplares(Integer.parseInt(nuevoValor));
                    }catch(NumberFormatException e){
                        System.out.println("Número de ejemplares no válido.");
                     }
                } 
                case 5 ->{
                    libro.setCodigo(nuevoValor);
                } 
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        return libro;
    } 

    //Método 5-Eliminar libro           
    public static boolean eliminarLibro(String tituloOCodigo) {
        Libro libro = buscarLibro(tituloOCodigo);               // 1. Se busca el libro

        if (libro != null ) {                                   // 2. Si el libro no es null, continúa
           ArrayList<Libro> listaLibros = getListaLibros();     // 3. Se accede al arraylist de libros
                                                                // 4. Se remueve el libro
           return listaLibros.remove(libro);                    // 5. Se devuelve true con el libro eliminado                                                 
        }
        return false; // Libro no encontrado en la lista
    }
}
