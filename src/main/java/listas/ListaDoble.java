package listas;

/**
 * clase para el manejo de una lista doblemente enlazada
 */

public class ListaDoble {

    private NodoDoble inicio;
    private NodoDoble fin;

    public ListaDoble(){
        inicio = null;
        fin = null;
    }

    /**
     * Metodo para saber si esta vacia
     * @return
     */
    public boolean Vacia(){
        return inicio == null;
    }

    /**
     * Método para insertar al inicio
     * @param dato
     */
    public void  InsertarInicio(Object dato){
        if (!Vacia()){
            inicio = new NodoDoble(dato, null, null);
            fin = inicio;
        } else {
            NodoDoble nuevo = new NodoDoble(dato, null, inicio);
            inicio.setAnterior(nuevo);
            inicio = nuevo;
        }
    }

    /**
     * Método para insertar al final
     * @param dato
     */
    public void  InsertarFinal(Object dato){
        if (inicio == null) {
            fin = new NodoDoble(dato, null, null);
            inicio = fin;
        } else {
            NodoDoble nuevo = new NodoDoble(dato, null, fin);
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
    }

    /**
     * Metodo para eliminar al inicio
     * @return Object
     */

    public Object EliminarInicio(){

        Object dato = inicio.getDato();
        inicio = inicio.getSiguiente();

        if(!Vacia()){
            inicio.setAnterior(null);
        } else{
            fin = null;
        }
        return dato;
    }

    /**
     * metodo para ELiminar al final
     * @return objecto
     */

    public Object EliminarFinal(){

        Object dato = fin.getDato();
        fin = fin.getSiguiente();

        if(fin != null){
            fin.setSiguiente(null);
        } else{
            inicio = null;
        }
        return dato;
    }

    /**
     * recorre e imprime la lista en consola
     */
    public void MostrarAdelante(){
        NodoDoble temp = inicio;

        while (temp != null){
            System.out.println(temp.getDato());
            Object das = temp.getDato();
            temp = temp.getSiguiente();
        }
    }

    /**
     * Retorna el nodo siguiente
     * @param temp NodoDoble
     * @return NodoDoble
     */

    public NodoDoble MoverDerecha(NodoDoble temp){
        if (temp.getSiguiente() == null){
            return temp;
        } else {
            return temp.getSiguiente();
        }
    }

    /**
     * Retorna el nodo anterior
     * @param temp NodoDoble
     * @return NodoDoble
     */

    public NodoDoble MoverIzquierda(NodoDoble temp){
        if (temp.getAnterior() == null){
            return temp;
        } else {
            return temp.getAnterior();
        }
    }
}
