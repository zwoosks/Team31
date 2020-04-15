package me.zwoosks.team31;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Jugadors {
	
	public static String getJugadors() {
		String retornar = null;
		
		
		try {
            @SuppressWarnings("resource")
			Socket socket = new Socket();
            socket.connect(new InetSocketAddress("149.202.221.187", 25588), 1 * 1000);
           
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
           
            out.write(0xFE);
           
            StringBuilder str = new StringBuilder();
           
            int b;
            while ((b = in.read()) != -1) {
                    if (b != 0 && b > 16 && b != 255 && b != 23 && b != 24) {
                            str.append((char) b);
                    }
            }
           
            String[] data = str.toString().split("§");
            int onlinePlayers = Integer.valueOf(data[1]);
            int maxPlayers = Integer.valueOf(data[2]);
            
            retornar = onlinePlayers + "/" + maxPlayers + " connectats!";
            
            
		} catch (Exception evt) {
            evt.printStackTrace();
            retornar = "ERROR";
		}
		
		
		return retornar;
	}
	
}