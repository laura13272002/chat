
package UN;

/**
 *
 * @author laura
 */
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HiloEscuchagui extends Thread{
    
    DataInputStream entrada;
    boolean x;
    Chat chat;
    public HiloEscuchagui(Socket s) {
        try {
            this.entrada = new DataInputStream(s.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(HiloEscuchagui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run(){
        while(x) {
            try {
                String mensaje = entrada.readUTF();
                
                switch(mensaje.substring(0,3)){
                    case "msj":
                        chat.newmsj(mensaje.substring(3));
                        break;
                    case "upd":
                        chat.updUsers(mensaje.substring(3));
                        break;
                    default:
                      System.out.println(mensaje);  
                } 
            } catch (IOException ex) {
                
                System.out.println("Se ha desconectado");
                
            }
        }
    }
}