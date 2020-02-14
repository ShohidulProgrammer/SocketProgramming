package chat;

import static chat.chat_server.din;
import static chat.chat_server.s;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import javax.swing.JFileChooser;

/**
 *
 * @author Shoikat
 */
public class chat_client extends javax.swing.JFrame {

    /**
     * Creates new form chat_client
     */
    
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
    File f= null;
    
    
    public chat_client() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jFileChooser = new javax.swing.JFileChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        msg_area = new javax.swing.JTextArea();
        btnFileSend = new javax.swing.JButton();
        txtFile = new javax.swing.JTextField();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemChoosFile = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();

        jMenu4.setText("File");
        jMenuBar2.add(jMenu4);

        jMenu5.setText("Edit");
        jMenuBar2.add(jMenu5);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        msg_area.setEditable(false);
        msg_area.setColumns(20);
        msg_area.setRows(5);
        msg_area.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane1.setViewportView(msg_area);

        btnFileSend.setText("File Send");
        btnFileSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFileSendActionPerformed(evt);
            }
        });

        txtFile.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFileKeyPressed(evt);
            }
        });

        jMenu2.setText("File");

        jMenuItemChoosFile.setText("Choos File");
        jMenuItemChoosFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemChoosFileActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemChoosFile);

        jMenuBar3.add(jMenu2);

        jMenu3.setText("Edit");
        jMenuBar3.add(jMenu3);

        setJMenuBar(jMenuBar3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtFile, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnFileSend, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtFile, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                    .addComponent(btnFileSend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        txtFile.getAccessibleContext().setAccessibleName("txtFile");

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public void fileChooser(){
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        f = chooser.getSelectedFile();
        String fileName = f.getAbsolutePath();
        txtFile.setText(fileName);
    }
    
    private void jMenuItemChoosFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemChoosFileActionPerformed
        // TODO add your handling code here:
        fileChooser();
        
    }//GEN-LAST:event_jMenuItemChoosFileActionPerformed

     private void sendFilePath()
    {
        String filePath;
        try{
            f = new File(txtFile.getText().trim());
            filePath = f.getName();
            // send file path
            dout.writeUTF(filePath);
        }catch(Exception e){}
    }
    
    private void sendFile()
     {
         sendFilePath();
        try{  
        
        // declare file size byte array
        byte [] bytearray = new byte [(int)f.length()]; 
        FileInputStream fin = new FileInputStream(f); 
        BufferedInputStream bin = new BufferedInputStream(fin); 
        
        msg_area.setText("\nReading Files....");
        bin.read(bytearray,0,bytearray.length); // read for send 
        OutputStream os = s.getOutputStream(); 
        
        // send file
        os.write(bytearray,0,bytearray.length); // byte variableName, int startPlaceInFile,int length
        msg_area.setText("\nFile transfer complete");
        txtFile.setText("");
        bin.reset();
        os.flush(); 
        s.close(); 
        } catch (IOException e) {
                System.out.println(e.getMessage());
            }

     }
     
     
    private void btnFileSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFileSendActionPerformed
        sendFile();
    }//GEN-LAST:event_btnFileSendActionPerformed

    private void txtFileKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFileKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            sendFile();
    }//GEN-LAST:event_txtFileKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(chat_client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(chat_client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(chat_client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(chat_client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new chat_client().setVisible(true);
            }
        });
    
        try{
            s =new Socket("127.0.0.1",1201);// local host & port no.
            din=new DataInputStream(s.getInputStream()); // din for input object
            dout=new DataOutputStream(s.getOutputStream()); // dout for output object
            BufferedOutputStream bos = null;
            int bytesRead,current=0;
            
            String msgin="",fileName;
            while(!msgin.equals("exit")){
             msgin=din.readUTF();
             
            //File Recived
            //set only file name
            int index = msgin.lastIndexOf("\\");
            fileName = msgin.substring(index + 1);
            
            txtFile.setText("C:\\Users\\SHOIKAT\\Desktop\\Downloade\\"+fileName);
            fileName = txtFile.getText();
            InputStream is = s.getInputStream();
            FileOutputStream fos = new FileOutputStream(fileName);
            
            
             byte[] buffer = new byte[100];
             int count;
             while((count = din.read(buffer)) != -1){
                  fos.write(buffer, 0, count);
               }
             txtFile.setText("");
             msg_area.setText("\nThe File has been downloaded complete\nIn Location: "+fileName);
               
               // reset
               
               bos.flush();
               fos.flush();
               fos.close();
        }
            
        }catch(Exception e){}
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFileSend;
    private javax.swing.JFileChooser jFileChooser;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuItem jMenuItemChoosFile;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextArea msg_area;
    private static javax.swing.JTextField txtFile;
    // End of variables declaration//GEN-END:variables
}
