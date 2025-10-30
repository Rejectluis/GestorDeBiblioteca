
package ClasesYMain;

import java.util.Scanner;

public class Libro { 
    //Atributos
    private String codigo;
    private String titulo;
    private String autor;
    private int anno;
    private int ejemplares=0;
    
    //Constructor vacío
    public Libro() {
    }
    
    //Getters
    public String getCodigo() {return codigo;}
    public String getTitulo() {return titulo;}
    public String getAutor() {return autor;}
    public int getAnno() {return anno;}
    public int getEjemplares() {return ejemplares;}
    
    //Setters
    public void setCodigo(String codigo) {this.codigo = codigo;}
    public void setTitulo(String titulo) {this.titulo = titulo;}
    public void setAutor(String autor) {this.autor = autor;}
    public void setAnno(int anno) { 
        if (anno >0){
            this.anno = anno;    
        }else{
            System.out.println("Año no válido. Intente nuevamente.");
        }
    }
    public void setEjemplares(int ejemplares) {
        if (ejemplares>0){
            this.ejemplares = ejemplares;
        }else{
            System.out.println("No se pueden asignar ejemplares negativos.");
        }
    }
    
    /*
    Luis del futuro, creo que estos deben ir en otra clase, en Prestamo tal vez
    Luis del futuro reportandose, respuesta: No, aquí es donde deben estar los métodos prestar y devolver
    */
    //Métodos 
    public boolean prestarEjemplar(){
        if(ejemplares >0){  //Se verifica que no puedan haber ejemplares negativos
            ejemplares--;
            return true;    //Préstamo válido
        }else{
            return false;   //Préstamo no válido
        }
    }
    public boolean devolverEjemplar(){
        ejemplares++;
        return true;
    }

    @Override
    public String toString(){
        return String.format(
            "\nTítulo: %s\nAutor: %s\nAño: %d\nDisponibles: %d\nCódigo: %s\n",
            getTitulo(),
            getAutor(),
            getAnno(),
            getEjemplares(),
            getCodigo()
        );
    } 
}
