package Server;

import java.net.*;
import java.io.*;


public class Main {

    private ServerSocket serverSocket;

    /**
     * Starts server
     * @param port Port which server will be opened on
     * @throws IOException Thrown from starting and closing port
     */
    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Port open");
        while (true)
                new ClientHandler(serverSocket.accept()).start();
    }

    /**
     * Ends server when stop command is sent
     * @throws IOException
     */
    public void stop() throws IOException {
        serverSocket.close();
    }

    /****************************************************************/

    /**
     * ClientHandler object is called when connections are established on the port
     * and takes care and performs correct actions
     */
    private static class ClientHandler extends Thread{
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket){
            this.clientSocket = socket;
        }//creates client object

        /**
         * Runs the connection between client and server and communicates information between the two
         */
        public void run(){
            try {
                System.out.println("Client Connected");
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                //opens output and input stream to send and receive information

                String inputLine;
                while ((inputLine = in.readLine()) != null){
                    //TODO sends information to handling code
                    if("QUIT".equals(inputLine)){
                        System.out.println("END GAME");
                        out.println("END GAME");
                        break;
                    }//if client sends message "QUIT" server ends communication and tells the client to end the game
                    out.println(inputLine);
                }//reads the input lines

                in.close();
                out.close();
                clientSocket.close();
                //closes client connection after quit messages is sent

            } catch (IOException e) {
                System.out.println("server/client input/outputStream error " + e.getMessage());
            }//catches IOException thrown by any input/output stream errors
        }
    }

    /***************************************************************/
    /**
     * when run, starts the server
     * @param args command line arguments
     * @throws IOException if IO exception is thrown in buffered reader/writer
     */
    public static void main(String[] args) throws IOException {
        Main server = new Main();
        server.start(6666);
        //starts server on the listed port
    }
}
