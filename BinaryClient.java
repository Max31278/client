/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.*;
import java.net.Socket;

/**
 * @author MaxK
 */
public class BinaryClient {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try {
            Socket socket = new Socket("localhost", 4444);
            String str1 = " ";
            boolean flag = true;
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream deserializer = new ObjectInputStream(socket.getInputStream());
            while (flag == true) {
                System.out.println("Введите команду и необходимые параметры: ");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                str1 = reader.readLine();
                if (str1.equals("exit")) {
                    flag = false;
                    oos.writeUTF(str1);
                    oos.flush();
                    oos.close();
                    deserializer.close();
                    socket.close();
                } else {
                    oos.writeUTF(str1);
                    oos.flush();
                    String c = deserializer.readUTF();
                    System.out.println(c);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
