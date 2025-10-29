
package logica_negocio;

import Metodos.MetodoLibro;
import java.util.Scanner;
import Metodos.UIHelper;
/*
 hola
*/
public class Main {
    public static void main(String[] args){
        Scanner entrada = new Scanner(System.in);
        
        String numero;
        int opcionGestion;
        int respuesta;
        String dato;
        boolean menuValido = true;
        do {            
            System.out.println((String.format(
                "--- MENÚ PRINCIPAL---\n%s\n%s\n%s\n%s\n%s\n%s\n",
                "1. Gestión de libros",
                "2. Gestión de usuarios",
                "3. Gestión de préstamos",
                "4. Reportes",
                "5. Salir",
                "Seleccione una opción: "
            ))); 
            numero = entrada.nextLine();

            try {
                respuesta = Integer.parseInt(numero);
            } catch (NumberFormatException e) {
                respuesta = -1;
            }
        
            switch (respuesta) {
                case 1 -> UIHelper.menuLibros();
                case 2 -> UIHelper.menuUsuarios();
                case 3 -> UIHelper.menuPrestamos();
                case 4 -> UIHelper.menuReporte();
                case 5 -> menuValido = false;
                default -> {
                    System.out.println("\n".repeat(30));
                    System.out.println("Opción fuera de rango. Intente nuevamente.\n");
                    System.out.println("Presione ENTER para continuar...");
                    entrada.nextLine();
                    }
                } 
        }while (menuValido);
    }
}
