/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UN;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author laura
 */
public class Cliente extends Thread{
    Socket socket;
    DataInputStream entrada;
    DataOutputStream salida;
    boolean run=false;
    String nombre;
    long id;
    static long contid=1;

    public Cliente(Socket s) throws IOException {
        id=contid++;
        this.socket = s;
        this.entrada = new DataInputStream(socket.getInputStream());
        this.salida = new DataOutputStream(socket.getOutputStream());
    }
    
    @Override
    public void run() {
        String mensaje = "";
        do {
            try {
                mensaje = entrada.readUTF();
                run=true;
                nombre=mensaje;
                System.out.println("Ingresó: "+nombre);
                SocketsServer.addCliente(this);
                SocketsServer.sendGrupo("Se ha unido: "+ nombre, id);
                break;
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while(true);
            
    
        do {
            try {
                mensaje = entrada.readUTF();//Se lee en el servidor usando su flujo de datos (servidor escucha mensaje enviado por el cliente)
                System.out.println("El cliente dijo:");
                System.out.println(mensaje);//Se muestra mensaje
                SocketsServer.sendGrupo(this.nombre+": "+mensaje,id);
                salida.writeUTF("Dijiste: " + mensaje);
            } catch (IOException ex) {
                System.out.println("se fue xd");
                run=false;
                SocketsServer.removeCliente(this);
                SocketsServer.sendGrupo(nombre+" se ha desconectado ☺", id);
            }
        } while (run);
        
        try {
            socket.close();//Fin de la conexión cliente
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
            

    }    

    void updUsers(LinkedList<Cliente> clientList) {
        try {
            String s="upd";
            for (Cliente cliente : clientList) {
                if(cliente.id!=id){
                    s=s+cliente.nombre+"\n";
                }
            }
            salida.writeUTF(s);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void send(String mensaje) {
        try {
            salida.writeUTF("msj"+mensaje);
            
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

