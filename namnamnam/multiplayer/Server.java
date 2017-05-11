package namnamnam.multiplayer;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import namnamnam.DisplayGame;
import namnamnam.Players;

public class Server implements Runnable {
    private DisplayGame panel;
    private boolean mapSent = false;

    public Server(DisplayGame panel) {
        this.panel = panel;
    }

    private void hostGame() throws IOException {
        ServerSocket listener = new ServerSocket(4444);
        Socket socket = listener.accept();

        if (!mapSent) {
            sendFoods(socket);
            sendPoisons(socket);
            mapSent = true;
        }

        while (true) {
            get(socket);
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            post(socket);
        }
    }

    private void sendFoods(Socket socket) {
        try {
            OutputStream oStream = socket.getOutputStream();
            ObjectOutputStream ooStream = new ObjectOutputStream(oStream);
            ooStream.writeObject(panel.getFood());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void post(Socket socket) throws IOException {
        OutputStream oStream = socket.getOutputStream();
        ObjectOutputStream ooStream = new ObjectOutputStream(oStream);
        ooStream.writeObject(panel.getPlayer1());
    }


    private void sendPoisons(Socket socket) {
        try {
            OutputStream oStream = socket.getOutputStream();
            ObjectOutputStream ooStream = new ObjectOutputStream(oStream);
            ooStream.writeObject(panel.getPoison());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void get(Socket socket) {
        try {
            InputStream iStream = socket.getInputStream();
            ObjectInputStream oiStream = new ObjectInputStream(iStream);
            Players player2 = (Players) oiStream.readObject();
            panel.setPlayer2(player2);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void run() {
        try {
            hostGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
