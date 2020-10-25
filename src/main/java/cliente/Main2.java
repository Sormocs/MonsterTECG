package cliente;

import servidor.HiloServidor;

public class Main2 {
    public static void main(String[] args) {

        Runnable iniciar_conexion = new Cliente();
        Thread hilo = new Thread(iniciar_conexion);
        hilo.start();

    }
}
