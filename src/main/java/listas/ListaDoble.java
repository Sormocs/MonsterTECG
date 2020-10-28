package listas;

public class ListaDoble {

    private NodoDoble inicio;
    private NodoDoble fin;

    public ListaDoble(){
        inicio = null;
        fin = null;
    }

    //método para saber is la lista está vacia
    public boolean Vacia(){
        return inicio == null;
    }

    //Agregar nodo al inicio
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

    //Agregar nodo al final
    public void  InsertarFinal(Object dato){
        if (!Vacia()) {
            fin = new NodoDoble(dato, null, null);
            inicio = fin;
        } else {
            NodoDoble nuevo = new NodoDoble(dato, fin, null);
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
    }

    //Eliminar al inicio

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

    //Eliminar al final

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

    //recorre la lista hacia delante y muestra el dato
    public void MostrarAdelante(){
        NodoDoble temp = inicio;

        while (temp != null){
            System.out.println(temp.getDato());
            temp = temp.getSiguiente()
        }
    }
}
