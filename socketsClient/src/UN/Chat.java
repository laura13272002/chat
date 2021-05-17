
package UN;

//import java.io.BufferedReader;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.*; // paquete de Java que Contiene clases para soportar aplicaciones que acceden a redes TCP/IP. Permite el uso de las clases Socket, ServerSocket
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author laura
 */
public class Chat extends javax.swing.JFrame {
    HiloEscuchagui hiloe;
    String nombre;
    Socket cliente;
    /**
     * Creates new form chat
     */
    public Chat(HiloEscuchagui he, Socket cliente, String nombre) {
        initComponents();
        
        try {
            out = new DataOutputStream(cliente.getOutputStream());
            this.hiloe = he;
            hiloe.chat = this;
            this.nombre = nombre;
            this.cliente = cliente;
            hist.setText("Se ha conectado "+ nombre+"\n");
            noms.setText(nombre +"\n");
            out.writeUTF(nombre);
        } catch (IOException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        hist = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        noms = new javax.swing.JTextArea();
        text = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        hist.setEditable(false);
        hist.setColumns(20);
        hist.setRows(5);
        jScrollPane1.setViewportView(hist);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 410, 280));

        jScrollPane2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane2.setHorizontalScrollBar(null);

        noms.setEditable(false);
        noms.setColumns(20);
        noms.setRows(5);
        jScrollPane2.setViewportView(noms);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, 120, 280));
        jPanel1.add(text, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 310, 30));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UN/send.png"))); // NOI18N
        jButton1.setText("Enviar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 380, -1, 30));

        jButton2.setText("Finalizar chat");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 380, 110, 30));

        jLabel3.setFont(new java.awt.Font("Arial Narrow", 3, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Bienvenido al chat grupal!");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 410, -1));
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 480));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UN/dos.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 490));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //Socket cliente;
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    
    
    DataOutputStream out;
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            String mnsj = text.getText();
            
            hist.append(nombre+": "+ mnsj+"\n");
            out.writeUTF(mnsj);
            text.setText("");
            
        } catch (IOException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            hiloe.x=false;
            cliente.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea hist;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea noms;
    private javax.swing.JTextField text;
    // End of variables declaration//GEN-END:variables

    void newmsj(String mensaje) {
        hist.append(mensaje+"\n" );
    }

    void updUsers(String upd) {
        noms.setText(upd);
    }
}
