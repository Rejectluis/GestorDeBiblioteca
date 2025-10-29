
package Metodos;

import java.util.Scanner;
import logica_negocio.Libro;
import logica_negocio.Usuario;
import logica_negocio.Prestamo;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class UIHelper {
    public static String separador = "-".repeat(20);
    public static Scanner entrada = new Scanner(System.in);
    
    //Lógica de menú
    
    //MAIN DEL MAIN                                        1-. GESTIÓN DE LIBROS
    public static String menuLibros(){
        String numero;
        int opcionGestion =0;
        boolean menuGestion = true; // Esta variable mantiene el menú activo hasta que el usuario desee salir
        
        do {// Se hará todo el proceso hasta que el usuario decida salir            
            System.out.println((String.format(
                "\n--- GESTIÓN DE LIBRO ---\n\n%s\n%s\n%s\n%s\n%s\n%s\n%s",
                "1. Registrar libro",
                "2. Listar libros",
                "3. Buscar libro",
                "4. Editar libro",
                "5. Eliminar libro",
                "6. Regresar al menú principal",
                "Seleccione una opción: "
            )));
            numero = entrada.nextLine();

            try {
                opcionGestion = Integer.parseInt(numero);   //Se recibe el dato como String, y se intenta convertir en un Int
            } catch (Exception e) {
                opcionGestion = -1;    // Si no se puede, entonces el valor de numero es -1
            }
            
            System.out.println("\n".repeat(30));
            switch (opcionGestion) { 
                case 1 -> { // 1. Crear libro y registrarlo
                    System.out.print("Título: ");
                    String titulo = entrada.nextLine();

                    System.out.print("Autor: ");
                    String autor = entrada.nextLine();
                    
                    int anno;
                    int ejemplares;
                    do{
                        System.out.print("Año de publicación: ");
                        String agno = entrada.nextLine();

                        System.out.print("Número de ejemplares: ");
                        String numer = entrada.nextLine();
                        
                        try {
                            anno = Integer.parseInt(agno);
                            ejemplares = Integer.parseInt(numer);
                        } catch (Exception e) {
                            anno = -1;
                            ejemplares = -1;
                        }
                        
                        if(anno <0 || ejemplares <0){
                            JOptionPane.showInputDialog("\nAño o número de ejemplares no váldios. Intente nuevamente.");
                        }
                    }while (anno <0 || ejemplares <0);
                    
                    System.out.print("Código del libro: ");
                    String codigo = entrada.nextLine();

                    boolean libroCreado = MetodoLibro.crearLibro(titulo,autor,anno,ejemplares,codigo);
                    
                    if(libroCreado){
                        System.out.println("\n\n¡Libro registrado correctamente!");
                        ArrayList<Libro> lista = MetodoLibro.getListaLibros();
                        Libro ultimo = lista.get(lista.size() - 1); // Último usuario registrado

                        System.out.println("\nDetalle del último libro agregado:");
                        System.out.println(ultimo); // Se muestra el último usuario agregado
                        System.out.println("\nPresione ENTER para continuar..");
                        entrada.nextLine();
                        
                    }
                }
                case 2 ->{  // 2. Listar libros
                    System.out.println(MetodoLibro.listarLibro());
                }
                case 3 ->{  // 3. Buscar libro
                    System.out.println("Nombre o códgio del libro: ");
                    String dato = entrada.nextLine();
                    Libro libro = MetodoLibro.buscarLibro(dato);
                    
                    if(libro == null){
                        System.out.println("-".repeat(24));
                        System.out.println("Libro no encontrado.");
                    }else{
                        System.out.println("¡Libro encontrado!\n"+"-".repeat(30)+libro.toString()+"\n".repeat(30));
                    }
                }
                case 4 ->{  // 4. Editar libro
                    System.out.println("Título o código del libro: ");  
                    String tituloOCodigo = entrada.nextLine();                           // Se almacena el dato del lbro
                                                                            
                    Libro LibroEncontrado = MetodoLibro.buscarLibro(tituloOCodigo); // Se busca el libro

                    if (LibroEncontrado != null){       // Si el libro existe se muestra se llama al submenú de edición
                        edicionLibro(LibroEncontrado);  //Método de esta clase. Se encuentra en la sección de submenús, casi al final
                        
                    }else{
                        System.out.println("-".repeat(24));
                        System.out.println("El libro no se encuentra\ndisponible");
                    }
                }
                case 5 ->{  //. Eliminar libro
                    System.out.println("Titulo o código del libro: ");
                    String dato = entrada.nextLine();                           //Se solicita el libro a eliminar               
                    boolean libroEliminado = MetodoLibro.eliminarLibro(dato);   //Se elimina el libro con el método
                    
                    
                    if(libroEliminado){                    //Si eliminar es true, se avisa
                        System.out.println("¡Libro borrado con éxito!");
                    }else{
                        System.out.println("-".repeat(24));                 //Si es false, se avisa
                        System.out.println("No se pudo encontrar el\nlibro");
                    }
                }
                case 6 ->{
                    menuGestion = false;
                    System.out.println("\n".repeat(30));
                    //Se vuelve al menú principal
                }
                default -> {
                    System.out.println("\n".repeat(30));
                    System.out.println("Opción fuera de rango.Intente nuevamente.\n");
                    System.out.println("Presione ENTER para continuar...");
                    entrada.nextLine();
                }
            }
        } while (menuGestion); 
        return null;
    }
    
    //MENÚ DEL MAIN                                2-. GESTIÓN DE USUARIOS
    public static String menuUsuarios(){
        String numero;
        int opcionGestion =0;  // Esta variable elije qué dato del usuario desea cambiar. Si nombre, o correo, o telefono, etc
        String datoUser;    // Esta almacena el dato nuevo 
        boolean menuUsuario = true; // Esta variable mantiene el menú activo hasta que el usuario desee salir
        do {            
            System.out.println((String.format("\n--- GESTIÓN DE USUARIOS ---\n%s\n%s\n%s\n%s\n%s\n%s",
            "1. Registrar ususario",
            "2. Listar usuarios",
            "3. Buscar usuarios",
            "4. Editar usuario",
            "5. Eliminar usuario",
            "6. Regresar al menú principal"
        )));
        numero = entrada.nextLine();
        
        try {
            opcionGestion = Integer.parseInt(numero);
        } catch (Exception e) {
            opcionGestion = -1;
        }
        
        switch (opcionGestion) {
            case 1: //Se preparan los datos para crear el objeto usuario 
                System.out.print("Nombre completo: ");   //1.- Nombre del usuario
                String nombre = entrada.nextLine();      //2.- DNI del usuario
                                                         //3.- Correo del usuario
                                                         //4.- Telefono del usuario
                String dni;         //La cantidad de prestamos no, ya que se inicializa en 0
                String correo;
                
                do{ //Se valida que no se ingresen datos erróneos 
                    System.out.print("DNI (8 dígitos): ");
                    dni = entrada.nextLine();
                    
                    System.out.print("Correo (formato: usuario@dominio.com): ");
                    correo = entrada.nextLine();
                    
                    if(!correo.contains("@") || !dni.matches("\\d{8}")){
                        JOptionPane.showInputDialog("\nCorreo o DNI erróneos. Intente nuevamente.");
                    }
                }while(!correo.contains("@") || !dni.matches("\\d{8}"));
                
                System.out.print("Teléfono: ");
                int telefono = entrada.nextInt();
                entrada.nextLine();

                boolean user = MetodoUsuario.registrarUsuario(nombre, dni, correo, telefono);      //Se crea el objeto usuario
                
                if(user){
                    System.out.println("\n\n¡Usuario registrado correctamente!");
                        ArrayList<Usuario> lista = MetodoUsuario.getListaUsuarios();
                        Usuario ultimo = lista.get(lista.size() - 1); // Último usuario registrado

                        System.out.println("\nDetalle del último usuario agregado:");
                        System.out.println(ultimo); // Se muestra el último usuario agregado
                        System.out.println("\nPresione ENTER para continuar..");
                        entrada.nextLine();
                    
                }else{
                    System.out.println("Error de registro, ya existe un usuario con ese DNI.");
                }
                break;

            case 2:// Se muestran los usuarios
                System.out.println(MetodoUsuario.listarUsuarios());
                
                break;
                
            case 3: // Se busca el usuario por nombre o dni
                System.out.println("Nombre o DNI del usuario: ");
                datoUser = entrada.nextLine();
                
                Usuario usuario = MetodoUsuario.buscarUsuario(datoUser);
                if(usuario != null){
                    System.out.println("¡Usuario encontrado!\nDatos:\n"+usuario.toString());
                }else{
                    System.out.println("Usuario no encontrado");
                }
                break;
                
            case 4:// Se busca el usuario y se edita
                System.out.println("Nombre o DNI del usuario: ");
                datoUser = entrada.nextLine();
                Usuario usuarioEncontrado = MetodoUsuario.buscarUsuario(datoUser);  //Se buca al usuario
                
                if(usuarioEncontrado!= null){   // Si el usuario existe, entonces se muestra el menú de opciones
                    edicionUsuario(usuarioEncontrado);
                }else{
                    System.out.println("\nEl usuario no se encuentra disponible.");//Si no existe el usuario se envía un aviso
                }
                break;
                
            case 5://Eliminar libro
                System.out.println("Nombre completo o DNI del usuario:");   //Se solicita un dato del usuario
                datoUser = entrada.nextLine();                     //Se registra: Luis Fernando Lopez Torres Reyes
                boolean eliminado = MetodoUsuario.eliminarUsuario(datoUser);           //Se llama al método eliminar
                
                if(eliminado){       //Si se eliminó el dato, se avisa
                    System.out.println("!Usuario eliminado!");
                }else{                                             //Si no se encontró el usuario, se avisa
                    System.out.println("\n\nUsuario no encontrado");
                }
                break;
            case 6:
                menuUsuario = false;
                break;
            default:
                System.out.println("Opción fuera de rango. Intente nuevamente.\n");
                System.out.println("Presione ENTER para continuar...");
                entrada.nextLine();
            }

        } while (menuUsuario);
        return null;
    }
    //MENÚ DEL MAIN                         3-. MENÚ DE PRÉSTAMOS
    public static String menuPrestamos(){
        String numero;
        int opcionGestion =0;
        String datoUser;
        boolean menuPrestamos = true;
        
        do {            
            System.out.println((String.format(
            "\n--- GESTIÓN DE PRESTAMOS ---\n\n%s\n%s\n%s\n%s\n",
            "1. Registrar préstamo",
            "2. Registrar devolución",
            "3. Listar préstamos activos ",
            "4. Regresar al menú principal"       
        )));
        
        numero = entrada.nextLine();

        try {
            opcionGestion = Integer.parseInt(numero);
        } catch (Exception e) {
            opcionGestion = -1;
        }
        
        System.out.println("\n".repeat(30));
        switch (opcionGestion) {
            case 1:// Registrar préstamo
                System.out.println("Nombre completo o DNI: ");
                datoUser = entrada.nextLine();                       //Se guarda el dato del usuario
                System.out.println("Titulo o código del libro: ");
                String datoLibro = entrada.nextLine();                      //Se guarda el dato del libro
                Prestamo prestamo = logica_negocio.Prestamo.registrarPrestamo(datoUser, datoLibro);//Se llama el método prestar
                /*
                if(prestamo!= null){ 
                    System.out.println("¡Préstamo exitoso!");
                    }else{ // 
                        if (MetodoUsuario.buscarUsuario(datoUser).getPrestamosActivos()>5){ //Si los prestamos son mayor a 5, no puede pedir préstamos, y se avisa
                            System.out.println("Máximo de préstamos alcanzados.\nHaga devoluciones para pedir otro préstamo.");
                        }
                    System.out.println("Préstamo no válido. Usuario no encontrado");    // Si no es por tema de prestamos, entonces es que el usuario no existe, y se avisa
                }
                */

                if (prestamo != null) { // Si el prestamo fue exitoso, se avisa
                    System.out.println("¡Préstamo exitoso!");
                }else{  // Si no se realizó el prestamo, se revisa el número de prestamos del usuario
                    Usuario usuario = MetodoUsuario.buscarUsuario(datoUser);    // Se busca el usuario para poder acceder a prestamosActivos luego
                    if (usuario!=null) {    // Si el usuario existe, se accede a los prestamos que tiene
                        if(usuario.getPrestamosActivos()>=5){   // Si excede el límite de préstamos, se avisa
                            System.out.println("Máximo de préstamos alcanzados.\\nHaga devoluciones para pedir otro préstamo.");
                        }else{ // Si el préstamo no se realizó, y no fue por exceder el límite de prestamosm, entonces es que el libro no existe
                            System.out.println("Libro no encontrado o sin ejemplares disponibles.");
                        }
                    }else{  //Si usuario es null, entonces no se encuentra registrado el usuario.
                        System.out.println("Préstamo no válido. Usuario no encontrado.");
                    }
                }       
                break;
            case 2:// Registrar devolción
                System.out.println("Nombre completo o DNI:");
                datoUser = entrada.nextLine();
                System.out.println("Titulo o código: ");
                datoLibro = entrada.nextLine();
                
                Prestamo prestamoDevuelto = logica_negocio.Prestamo.registrarDevolucion(datoUser, datoLibro);
                
                if(prestamoDevuelto!= null){
                    System.out.println("\n¡Devolución hecha con éxito!\nFecha: "+prestamoDevuelto.getFechaDevolucion());
                    
                }else{
                    System.out.println("\nLa devolución no fue posible.");
                }
                break;
            case 3: // Listar libros
                System.out.println(Prestamo.listarPrestamo());
                break;
            case 4:
                menuPrestamos = false;
                System.out.println("\n".repeat(30));
                break;
            default:
                System.out.println("\n".repeat(30));
                System.out.println("Opción fuera de rango. Intente nuevamente.\n");
                System.out.println("Presione ENTER para continuar...");
                entrada.nextLine();
            }
        } while (menuPrestamos);
        return null;
    }
    
    //MENÚ DEL MAIN                                 4.- MENÚ DE REPORTES
    public static String menuReporte(){
        String numero;
        int opcionGestion =0;
        String datoUser;
        boolean menuReporte=true;
        
        do {            
            System.out.println((String.format("\n--- GESTIÓN DE REPORTE ---\n\n%s\n%s\n%s\n%s\n",
            "1. Reporte de libros prestados",
            "2. Reporte de libros disponibles",
            "3. Reporte de usuarios con más préstamos",
            "4. Regresar al menú principal"       
        )));
        
        numero = entrada.nextLine();
        try {
            opcionGestion = Integer.parseInt(numero);
        } catch (Exception e) {
            opcionGestion = -1;
        }
        
        System.out.println("\n".repeat(30));
        switch (opcionGestion) {
            case 1:
                System.out.println(MetodoReporte.librosPrestados());
                break;
            case 2:
                System.out.println(MetodoReporte.librosDisponibles());
                break;
            case 3:
                System.out.println(MetodoReporte.usuriosConMasPrestamos());
                break;
            case 4:
                menuReporte = false;
                System.out.println("\n".repeat(30));
                break;
            default:
                System.out.println("\n".repeat(30));
                System.out.println("Opción fuera de rango. Intente nuevamente.\n");
                System.out.println("Presione ENTER para continuar...");
                entrada.nextLine();
            }
        } while (menuReporte);
        return null;
    }
    
    //Submenús 
    //Para editar un usuario existente
    public static String edicionUsuario(Usuario usuario) {
        boolean continuar = true;
        int opcion;
        
        while (continuar) {            
            System.out.println((String.format(
                "%s\n %-8s\n %s\n %s\n %s\n",
                "Cambios disponibles:",
                "1-. Nuevo nombre",
                "2-. Nuevo correo",
                "3-. Nuevo teléfono",
                "4-. Salir"
            )));
            
            String input = entrada.nextLine();
            
            try {
                opcion = Integer.parseInt(input);
            } catch (Exception e) {
                opcion = -1;
            }
            if(opcion == 4){
                continuar = false;
                break;
            }
            
            System.out.println("Cambio: ");
            String nuevoValor = entrada.nextLine();
            MetodoUsuario.editarUsuario(usuario, opcion, nuevoValor);
        }
        return null;
    }
    //Submenú para editar libro
    public static String edicionLibro(Libro libro){
        boolean continuar = true;
        int opcion;
        
        while (continuar) {            
            System.out.println((String.format(
                "%s\n %s\n %s\n %s\n %s\n %s\n %s\n",
                "Cambios disponibles",
                "1-. Título",
                "2-. Autor",
                "3-. Año",
                "4-. № de ejemplares",
                "5-. Código",
                 "6-. Volver")
            ));
            String input = entrada.nextLine(); 

            try {
                opcion = Integer.parseInt(input);
            } catch (Exception e) {
                opcion = -1;
            }
            if(opcion == 6){
                continuar = false;
                break;
            }
            
            System.out.println("Cambio: ");
            String nuevoValor = entrada.nextLine();
            MetodoLibro.editarLibro(libro, opcion, nuevoValor);
        }
        return null;
    }
}
