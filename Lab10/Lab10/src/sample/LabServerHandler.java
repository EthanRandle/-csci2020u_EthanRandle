package sample;

import java.io.*;
import java.net.*;
import java.util.StringTokenizer;
import java.util.NoSuchElementException;
import java.util.Date;
public class LabServerHandler implements Runnable{
    private Socket socket = null;
    private BufferedReader requestInput = null;
    private DataOutputStream responseOutput = null;

    public LabServerHandler(Socket socket){     //establishes streams between client to send to server
        this.socket = socket;
        try {
            requestInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            responseOutput = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e){e.printStackTrace();}
    }

    public void run(){
        //this is where we read requests and respond to them
        try {
            String line = requestInput.readLine();                          //readline stops at \n \r
            handleRequest(line);
        }catch(IOException e){e.printStackTrace();}
        finally {
            try {
                requestInput.close();
                responseOutput.close();
                socket.close();
            } catch (IOException p) {
                p.printStackTrace();
            }
        }

    }
    public void handleRequest(String message)throws IOException{
        try{
            //expected format: Username: message Text
            StringTokenizer tokenizer = new StringTokenizer(message);
            String username = tokenizer.nextToken(":");
            String msg = tokenizer.nextToken(":");
        }catch(NoSuchElementException e){e.printStackTrace();}

        if(message.equalsIgnoreCase("help")){
            System.out.println("Help is on the way!");
        }
    }

    public void sendResponse(String response) throws IOException {
        //servers response is updating the list
        responseOutput.writeBytes(response);
    }
}
