/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rastroserversockettcp;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eduardo elias
 */
public class RastroServerSocketTCP1 {

    private ServerSocket serverSocket;
    
    private void criarSeverSocket(int port) throws IOException
    {
        serverSocket = new ServerSocket(port);
    }
    private Socket esperaConexao() throws IOException
    {
        Socket socket = serverSocket.accept();
        return socket;
    }
    private void fechaSocket(Socket socket) throws IOException
    {
        socket.close();
    }
    private void tratarConexao(Socket socket) throws IOException, ClassNotFoundException
    {
        try {            
            System.out.println("Client connected.");
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());                    
            //ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                
            //String msg = (String) input.readLine();
            //System.out.println(" message: " + msg); 
            
            //output.writeUTF("ok");
            //output.flush(); 
                
            Scanner s = new Scanner(socket.getInputStream()); 

            while (s.hasNextLine()) { 
                String dados = s.nextLine(); 
                System.out.println(dados);
            }  
            
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            String inputLine, outputLine;
            
            output.writeUTF("ok");
            output.flush(); 
            
            // Initiate conversation with client
            /*RastroServerSocketTCP kkp = new RastroServerSocketTCP();
            outputLine = kkp.processInput(null);
            out.println(outputLine);

            while ((inputLine = in.readLine()) != null) {
                outputLine = kkp.processInput(inputLine);
                out.println(outputLine);
                if (outputLine.equals("Bye."))
                    break;
            }
                
            input.close();
            //output.close(); 
              
            /*s.close();
            socket.close();             */
            
            output.close();
            socket.close();
            
            //fechaSocket(socket);       
        } catch (IOException ex) {
            Logger.getLogger(RastroServerSocketTCP1.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            fechaSocket(socket);
        }           
    }
    
    /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws ClassNotFoundException {
        // TODO code application logic here        
        try {
            RastroServerSocketTCP1 server = new RastroServerSocketTCP1();
            System.out.println("Server started...");                    
            server.criarSeverSocket(54320);
                                        
            while (true) {                            
                System.out.println("Waiting conection...");                    
                Socket socket = server.esperaConexao();        
                server.tratarConexao(socket);          
                System.out.println("Client finished.");
            }
        } catch (IOException ex) {
            Logger.getLogger(RastroServerSocketTCP1.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
}
