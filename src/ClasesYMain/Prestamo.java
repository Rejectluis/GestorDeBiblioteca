
package ClasesYMain;

import java.time.LocalDate;
import Metodos.MetodoLibro;
import Metodos.MetodoUsuario;
import ClasesYMain.Usuario;
import ClasesYMain.Libro;
import java.util.ArrayList;


public class Prestamo {
    static StringBuilder sb = new StringBuilder();
    private static ArrayList<Prestamo> prestamosLista = new ArrayList<>(); //Composición:(codigoPrestamo, libro, usuario)
    private static String separador = "-".repeat(20);
    
    //Atributos
    private String codigoPrestamo;
    private Libro libroPrestado;
    private Usuario usuarioPrestador;    //Usuario que tomó el prestamo
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    
    //Constructor
    public Prestamo(String codigoPrestamo, Libro libroPrestado, Usuario usuarioPrestador) {
        this.codigoPrestamo = codigoPrestamo;
        this.libroPrestado = libroPrestado;
        this.usuarioPrestador = usuarioPrestador;
        this.fechaPrestamo = LocalDate.now();
        this.fechaDevolucion = null;
    }
    
    //Getters
    public String getCodigoPrestamo() {return codigoPrestamo;}
    public Libro getLibroPrestado() {return libroPrestado;}
    public Usuario getUsuarioPrestador() {return usuarioPrestador;}
    public LocalDate getFechaPrestamo() {return fechaPrestamo;}
    public LocalDate getFechaDevolucion() {return fechaDevolucion;}
    public static ArrayList<Prestamo> getPrestamosLista(){ return prestamosLista;}
    
    //Setters
    public void setCodigoPrestamo(String codigoPrestamo) {this.codigoPrestamo = codigoPrestamo;}
    public void setLibroPrestado(Libro libroPrestado) {this.libroPrestado = libroPrestado;}
    public void setUsuarioPrestador(Usuario usuarioPrestador) {this.usuarioPrestador = usuarioPrestador;}
    public void setFechaPrestamo(LocalDate fechaPrestamo) {this.fechaPrestamo = fechaPrestamo;}
    public void setFechaDevolucion(LocalDate fechaDevolucion) {this.fechaDevolucion = fechaDevolucion;}
    public void devolver(){this.fechaDevolucion = LocalDate.now();}
    public void prestar() {this.fechaPrestamo = LocalDate.now();}
    
    //Métodos//
    //Prestar ejemplar (Si se presta un ejemplar, al usurio se le aumenta en 1 pestramosActivos y dsiminuye el nro de ejemplares)
    public static boolean prestarEjemplares(Usuario usuario, String tituloOCodigo){
        Libro libro = MetodoLibro.buscarLibro(tituloOCodigo);
        
        if(libro.prestarEjemplar()){  //Si fue posible realizar el prestamo, entonces se arroja true
            usuario.setPrestamosActivos(usuario.getPrestamosActivos()+1);
            return true;    //Préstamo válido
        }else{
            return false;   //Préstamo no válido
        }
    }
    //Devolver ejemplar (Si se devuelve un ejemplar, se diminuye a pestramosActivos y aumenta el nro de ejemplares)
    public static boolean devolverEjemplares(Usuario usuario, String tituloOCodigo){
        Libro libro = MetodoLibro.buscarLibro(tituloOCodigo);
        
        if(libro.devolverEjemplar()){
            usuario.setPrestamosActivos(usuario.getPrestamosActivos()-1);
            return true;
        }
        return false;
    }
    
    @Override
    public String toString(){
        return String.format(
            "Usuario: %s (DNI: %s)\n" +
            "Libro: %s (Código: %s)\n" +
            "Código: %s\n" +
            "Fecha de prestamo: %s\n" +
            "Fecha de devolución: %s",
            getUsuarioPrestador().getNombreCompleto(),
            getUsuarioPrestador().getDni(),
            getLibroPrestado().getTitulo(),
            getLibroPrestado().getCodigo(),
            getCodigoPrestamo(),
            getFechaPrestamo(),
            getFechaDevolucion() != null ? getFechaDevolucion() : "No devuelto"
        );
    }
    
    //Método 1-. Registrar préstamo
    public static Prestamo registrarPrestamo(String datoUsuario,String titutloOCodigoLibro){
        /*
        ArrayList<Usuario> usuarioslista = MetodoUsuario.getListaUsuarios();                                                                  //1. Verificar que el usuario esté registrado. ¿Cómo? por medio del DNI          
        Usuario usuario = MetodoUsuario.buscarUsuario(datoUsuario);         //2. Si verificado =(True), continúa.
                                                                            //3. Mostrar estado del usuario  (Main)
        if(usuario == null){
            return null;
        }                                  //3. Usuario existe y préstamos activos
        int prestamos = usuario.getPrestamosActivos();//estado del usuario  //4. Buscar el libro (titulo o código)
                                                                            //5. Mostar dato del libro (Main)
        if(usuario !=null && prestamos >=0 && prestamos <=5){               //6. Prestar o no                       
            //El resultado (el libro o null) se almacena en el objeto libro
            ArrayList<Libro> librolista = MetodoLibro.getListaLibros();
            
            if(librolista != null){                                 // Verifica que la lista no esté vacía
                prestarEjemplares(usuario, titutloOCodigoLibro);    // Se llama al método prestar    
                
                if(prestarEjemplares(usuario, titutloOCodigoLibro)){  // Si se prestó el libro, entonces contúa

                    Libro libro = MetodoLibro.buscarLibro(titutloOCodigoLibro); //Se almacena el objeto libro en libro para guardarlo en prestamos
                    String codigoPrestamo = "P-" + System.currentTimeMillis();  //Se genera un código de prestamo
                    Prestamo prestamoNuevo = new Prestamo(codigoPrestamo, libro, usuario); // se crea el prestamo
                    prestamoNuevo.prestar();    // Se asigna la fecha de préstamo por medio del setter        
                    prestamosLista.add(prestamoNuevo);
                    return prestamoNuevo;
                }   
            }    
        }
        */
        ArrayList<Usuario> usuarioslista = MetodoUsuario.getListaUsuarios();                                                                  //1. Verificar que el usuario esté registrado. ¿Cómo? por medio del DNI          
        Usuario usuario = MetodoUsuario.buscarUsuario(datoUsuario);         //2. Si verificado =(True), continúa.
                                                                            //3. Mostrar estado del usuario  (Main)
        //  Paso 1: validar existencia de usuario
        if(usuario == null){
            return null;
        }                                  //3. Usuario existe y préstamos activos
        int prestamos = usuario.getPrestamosActivos();//estado del usuario  //4. Buscar el libro (titulo o código)
                                                                            //5. Mostar dato del libro (Main)
        //  Paso 2: verificar si está dentro del límit                                                                 
        if (prestamos < 0 || prestamos > 5) {
            return null; // No permite préstamo si ya llegó al límite
        }                                                                    
         
        // Paso 3: validar lista de libros
        ArrayList<Libro> librolista = MetodoLibro.getListaLibros();
            if (librolista == null || librolista.isEmpty()) {
        return null;
        }                                                                    
        
        //Paso 4: intentar prestar ejemplar
        if (prestarEjemplares(usuario, titutloOCodigoLibro)) {
            Libro libro = MetodoLibro.buscarLibro(titutloOCodigoLibro);
            if (libro != null) {
                String codigoPrestamo = "P-" + System.currentTimeMillis();
                Prestamo prestamoNuevo = new Prestamo(codigoPrestamo, libro, usuario);
                prestamoNuevo.prestar();
                prestamosLista.add(prestamoNuevo);
                return prestamoNuevo;
            }
        }    
        return null;
    }
    
    //(+) Método buscar un prestamo de la lista                           Nota: (+) Significa método adicional
    public static Prestamo buscarPrestamo(String tituloOCodigo){// Recibe un dato del libro (titulo o código del libro)
        ArrayList<Prestamo> lista = getPrestamosLista();
        
        for(Prestamo prestamo: lista){// 1-. Recorrer la lista de prestamos
            Libro libro = prestamo.getLibroPrestado();// Se guarda en libro el objeto Libro que pertece a la lista de prestados.
            
            // 2: Si el titulo o dato coincide con el dato, se retorna el préstamo.
            if(libro.getTitulo().equalsIgnoreCase(tituloOCodigo) || libro.getCodigo().equalsIgnoreCase(tituloOCodigo)){                                            
                return prestamo;
            }
        }
        return null;
    }
    
    // Método 2-. Registrar devolución
    public static Prestamo registrarDevolucion(String datoUsuario, String tituloOCodigoLibro){
        /*
        Si se prestó un libro es porque ya se verficó que el usuario existe, y cuyos préstamos están en el rango.
        Además, se verificó que también existe,ergo, si se prestó, entonces el número de
        ejemplares disminyó. Al devolver, habrá que aumentar el número de ejemplares y disminuir los prestamosActivos
        del usuario.
        Objetivo:
        1-. Si existenciaUsuario y prestamos >0 = True, continúa.
        2-. Si el dato del libro prestado está en listaprestado & pertenece al usuario del paso 1 = true
        3-. Se llama al método devolver(aumenta 1 los ejemplares y disminuye los prestadosActivos)
        4-. Se elimina el objeto en el arraylist
        5-. Se retorna el arraylist actualizado
        */
        ArrayList<Usuario> lista = MetodoUsuario.getListaUsuarios();    // En lista se almacena el arraylist de usuarios
        Usuario usuario = MetodoUsuario.buscarUsuario(datoUsuario);     // En usuario, más de lo mismo, pero con el arraylist de usuario
        
        if(usuario !=null && usuario.getPrestamosActivos() >0){//   Paso 1: Buscar usuario
            Prestamo prestamo = buscarPrestamo(tituloOCodigoLibro); // Paso 2: Buscar el préstamo por titulo o codigo del libro
            
            // Paso 2.1: Verificar que el préstamo encontrado pertenece al usuario
            if(prestamo != null && prestamo.getUsuarioPrestador().equals(usuario)){ 
                Libro libro = prestamo.getLibroPrestado();  //  Se extrae el libro de préstamo
                
                if(devolverEjemplares(usuario,tituloOCodigoLibro)){     // Paso 3: Devolver libro (aumentar ejemplares, reducir préstamo)
                    prestamosLista.remove(prestamo);        //  Paso 4: Eliminar el préstamo de la lista
                    prestamo.devolver();                    //  (+) Se registra la fecha de devolución por medio del setter
                    return prestamo;                        //  Paso 5: Retornar el préstamo actualizado
                }
            }
        }
        return null;
    }
    
    //Método 3-. Listar préstamos activos
    public static String listarPrestamo(){
        sb.setLength(0);
        ArrayList<Prestamo> lista = getPrestamosLista();
        
        if(!lista.isEmpty()){   //1: Verfica que la lista no esté vacía
            int i=1;            //Para estética
            for(Prestamo prestamo: lista){
            sb.append(String.format("Préstamo %d:\n%s\n",i++, separador));  //Préstamo 1
            sb.append(prestamo).append("\n");                               //-----------------
            sb.append(separador).append("\n\n");                            //secuencia de datos..
            }                                                               //------------------
        }else{
            sb.append("Lista de préstamos vacía.");
        }
        return sb.toString();                                               //Se muestra el resultado
    } 
}