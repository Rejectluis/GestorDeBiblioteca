
package Metodos;

import ClasesYMain.Prestamo;
import ClasesYMain.Usuario;
import java.util.ArrayList;

public class MetodoUsuario {
    private static final String separador = "-".repeat(50);
    private static StringBuilder sb = new StringBuilder();
    private static ArrayList<Usuario> listaUsuarios = new ArrayList<>(); 
    
    //Getter del Arraylist
    public static ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }
    
    //Método 1- registrar un usuario dentro del arraylist
    public static void registrarUsuario(Usuario usuario){
        listaUsuarios.add(usuario);
    }
    
    //Método 1-Registrar usuario
    public static boolean registrarUsuario(String nombre, String dni, String correo, int telefono){
        
        // Validación simple: evitar DNI duplicado
         for (Usuario usuario : listaUsuarios) {
            if (usuario.getDni().equals(dni)) {
                return false;
            }
        }
        
        Usuario usuario = new Usuario();
        usuario.setNombreCompleto(nombre);          //Se el nombre del usuario
        usuario.setDni(dni);                        //Se solicita el dni
        usuario.setCorreoElectronico(correo);
        usuario.setTelefono(telefono);
        listaUsuarios.add(usuario);
        return true;
    }
        
    //Métdo 2-Listar usuarios
    public static String listarUsuarios(){
        ArrayList<Usuario> lista = MetodoUsuario.getListaUsuarios();

        if(!lista.isEmpty()){
            sb.append("Usuarios registrados:\n\n");
            int i=1;

            for(Usuario usuario: lista){
                sb.append(String.format("Usuario %d:\n%s\n", i++, separador));
                sb.append(usuario).append("\n");
                sb.append(separador).append("\n\n");
            }
        }else{
            sb.append("-".repeat(27)).append("\n");
            sb.append("Lista de usuarios vacía.");
        }  
        return sb.toString();
    }
    
    //Método 3-.Buscar usuarios por dni o nombre
    public static Usuario buscarUsuario(String dato){
        ArrayList<Usuario> lista = getListaUsuarios();
        
        for(Usuario usuario: lista){
            if(usuario.getNombreCompleto().equalsIgnoreCase(dato) || usuario.getDni().equals(dato)){
                return usuario;
            }
        }
        return null;
    }
    
    //Métodod 4-. Editar usuario
    public static Usuario editarUsuario(Usuario usuario, int opcion, String nuevoValor){
        switch (opcion) {
            case 1 -> usuario.setNombreCompleto(nuevoValor);
            case 2 -> usuario.setCorreoElectronico(nuevoValor);
            case 3 -> {
                try{
                    int nuevoTelefono = Integer.parseInt(nuevoValor);
                    usuario.setTelefono(nuevoTelefono);
                }catch(NumberFormatException e){
                    System.out.println("Número invalido"); 
                }
            }
            default -> System.out.println("Opción no válida"); 
        }
        return usuario;   
    }
    
    //Método 5-. Eliminar usuario
    public static boolean eliminarUsuario(String dato){
        
        Usuario usuario = buscarUsuario(dato);
        if(usuario !=null){
            ArrayList<Usuario> lista = getListaUsuarios();
            return lista.remove(usuario);
        }
        return false;
    }
}
