package listas;

public class Stack {
    int max = 16;
    Object nodo;
    int top;
    Object[] stack = new Object[max];

    public Stack(){
        this.top = -1;
    }

    public boolean isEmpty(){
        return (top<0);
    }

    public boolean push(Object nodo){
        //Ingresar dato al inicio
        if (top>=(max-1)){
            System.out.println("la lista esta llena");
            return false;
        } else{
            this.stack[++this.top] = nodo;
            return true;
        }
    }

    public boolean pop(){
        //Sacar dato al inicio
        if(this.isEmpty()){
            System.out.println("la lista esta vacia");
            return false;
        } else{
            this.stack[this.top--] = null;
            return true;
        }
    }

    public void print(){
        //Imprimir la clase
        for (Object i : stack){
            System.out.println(i);
        }
    }
}

