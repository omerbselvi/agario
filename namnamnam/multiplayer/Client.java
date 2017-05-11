package namnamnam.multiplayer;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import namnamnam.DisplayGame;
import namnamnam.Foods;
import namnamnam.Players;
import namnamnam.Poisons;

public class Client implements Runnable {
    private String address;
    private DisplayGame panel;

    public Client(String address, DisplayGame panel) {
        this.address = address;
        this.panel = panel;
    }

    private void connectToPeer() {
        Socket socket = null;

        try {
            socket = new Socket(this.address, 4444);
            receiveFoods(socket);
            receivePoisons(socket);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                post(socket);
                TimeUnit.MILLISECONDS.sleep(1);
                get(socket);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
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
    
    private void receiveFoods(Socket socket) throws IOException, ClassNotFoundException {
        InputStream iStream = socket.getInputStream();
        ObjectInputStream oiStream = new ObjectInputStream(iStream);
        Foods foods = (Foods) oiStream.readObject();
        panel.setFood(foods);;
    }

    private void post(Socket socket) throws IOException {
        OutputStream oStream = socket.getOutputStream();
        ObjectOutputStream ooStream = new ObjectOutputStream(oStream);
        ooStream.writeObject(panel.getPlayer1());
    }


    private void receivePoisons(Socket socket) throws IOException, ClassNotFoundException {
        InputStream iStream = socket.getInputStream();
        ObjectInputStream oiStream = new ObjectInputStream(iStream);
        Poisons poisons = (Poisons) oiStream.readObject();
        panel.setPoison(poisons);
    }


    @Override
    public void run() {
        connectToPeer();
        }
}
