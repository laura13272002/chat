package UN;

import java.net.*;// paquete de Java que Contiene clases para soportar aplicaciones que acceden a redes TCP/IP. Permite el uso de las clases Socket, ServerSocket
import java.io.*;//Contiene clases para manejar la entrada/salida. Permite el uso de las clases DataOutputStream, BufferedReader
import java.util.LinkedList;

public class SocketsServer {
    static LinkedList<Cliente> clientList;
    private static void createServer() {
        clientList= new LinkedList();
        InetAddress ipAddress = null; //Esta clase proporciona objetos que se pueden utilizar para manipular tanto direcciones IP como nombres de dominio.
        int portNumber = 3000;//Puerto para la conexión
        int backlog = 0;//longitud máxima solicitada de la cola de conexiones entrantes
        String mensaje;
        

        try {
            ipAddress = InetAddress.getByName("localhost");//El método getByName() devuelve objeto InetAdress a partir del nombre
        } catch (UnknownHostException u) {
            System.out.println("No se puede obtener la dirección IP");
        }

        try {
            ServerSocket servidor = new ServerSocket(portNumber, backlog, ipAddress);// Se crea un servidor con los parametros especificados para enlazar
            System.out.println("Inicia el servidor: " + servidor);//Muestra conexión del servidor
            
            while(true) {
                Socket cliente = servidor.accept();//Servidor acepta conexión del cliente
                System.out.println("Se conecta el cliente: " + cliente);//muestra datos de conexión del cliente
                Cliente c = new Cliente(cliente);
                c.start();
                
            }

        } catch (IOException e) {
            System.out.println("ERROR: " + e.toString());
        }
    }

    public static void main(String[] args) {
        createServer();
    }

    static void addCliente(Cliente aThis) {
        clientList.add(aThis);
        for (Cliente cliente : clientList) {
            cliente.updUsers(clientList);
        }
    }

    static void removeCliente(Cliente aThis) {
        boolean s=clientList.remove(aThis);
        if(s){
            for (Cliente cliente : clientList) {
                cliente.updUsers(clientList);
            }
        }
    }

    static void sendGrupo(String mensaje, long id) {
        for (Cliente cliente : clientList) {
            if(cliente.id!=id){
                cliente.send(mensaje);
            }
        }
    }

}