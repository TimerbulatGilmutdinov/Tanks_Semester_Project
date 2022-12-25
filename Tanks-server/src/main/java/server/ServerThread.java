package server;

import java.io.ObjectInputStream;

public class ServerThread extends Thread {
    private final int id;

    public ServerThread(int id) {
        this.id = id;
    }

//    @Override
//    public void run() {
//        Object data = null;
//        try {
//            ObjectInputStream input = new ObjectInputStream(client.getInputStream());
//            while ((data = input.readObject()) != null) {
//                for (int i : oosList.keySet()) {
//                    if (i != id && oosList.containsKey(i)) {
//                        oosList.get(i).writeObject(data);
//                        oosList.get(i).flush();
//                    }
//                }
//            }
//            input.reset();
//            input.close();
//        } catch (Exception ex) {
//            console.append("The client: " + id + " has disconected.\n");
//            disconect();
//        }
//    }
}
