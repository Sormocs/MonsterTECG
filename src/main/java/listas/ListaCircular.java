package listas;

public class ListaCircular {

    private int size;
    private NodoCircular inicio;
    private NodoCircular end;

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

    public void Delete(Object elemento){

        int contador = 0;
        NodoCircular current = inicio;
        while (contador < size){

            if (current.getElemento() == elemento){
                NodoCircular prev = current.getPrev();
                NodoCircular sig = current.getNext();

                prev.setNext(sig);
                sig.setPrev(prev);
                size--;
                break;
            }else{
                contador ++;
                current = current.getNext();
            }

        }

    }

    public boolean Contains(Object valor){

        NodoCircular actual = inicio;
        while(actual != end){
            if (actual.getElemento() == valor){
                System.out.println("Contiene");
                return true;
            } else{
                actual = actual.getNext();
            }
        }if (end.getElemento() == valor){
            return true;
        }else{
            System.out.println("No contiene");
            return false;
        }

    }

    public NodoCircular getEnd() {
        return end;
    }

    public NodoCircular getInicio(){
        return inicio;
    }

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
        if (pos > size){
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
