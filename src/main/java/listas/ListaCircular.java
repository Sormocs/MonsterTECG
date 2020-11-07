package listas;

import com.fasterxml.jackson.databind.JsonNode;

public class ListaCircular {

    private int size;
    private NodoCircular inicio;
    private NodoCircular end;

    /**
     * Inserta al final
     * @param valor Object
     */

    public void InsertEnd(Object valor){

        if (inicio == null){

            NodoCircular nuevoNodo = new NodoCircular(valor, null, null);
            nuevoNodo.setNext(nuevoNodo);
            nuevoNodo.setPrev(nuevoNodo);
            inicio = nuevoNodo;
            end = nuevoNodo;
            this.size = 1;

        } else{

            NodoCircular ultimo = getEnd();
            NodoCircular nuevoNodo = new NodoCircular(valor, inicio, end);

            inicio.setPrev(nuevoNodo);
            end.setNext(nuevoNodo);
            end = nuevoNodo;
            this.size ++;

        }

    }

    /**
     * Cambia el valor
     * @param elemento Object
     * @param pos int
     */

    public void ChangeValue(Object elemento, int pos){

        int contador = 0;
        NodoCircular current = inicio;
        while (contador < size){

            if (contador == pos){
                current.setElemento(elemento);
                break;
            }else{
                contador ++;
                current = current.getNext();
            }

        }

    }

    /**
     * Retorna el final
     * @return NodoCircular
     */

    public NodoCircular getEnd() {
        return end;
    }

    /**
     * Retorna el pirmero
     * @return NodoCircular
     */

    public NodoCircular getInicio(){
        return inicio;
    }

    /**
     * muestra la lista
     */

    public void Show(){

        NodoCircular actual = inicio;
        int contador = 0;
        while(contador < size){

            System.out.println(actual.getElemento());
            actual = actual.getNext();
            contador ++;

        }
        //System.out.println();

    }

    public NodoCircular getNode(int pos){
        if (pos >= size){
            return null;
        }else{
            NodoCircular actual = inicio;
            int contador = 0;
            NodoCircular obtenido = null;
            while (contador < size){
                if (contador == pos){
                    obtenido = actual;
                    break;
                }else{
                    contador ++;
                    actual = actual.getNext();
                }
            }
            return obtenido;
        }
    }

    public int getSize(){
        return this.size;
    }
}
