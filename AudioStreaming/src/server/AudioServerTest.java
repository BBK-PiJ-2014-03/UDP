package server;

import java.io.File;
import java.io.IOException;
import java.net.*;

import javax.sound.sampled.*;

public class AudioServerTest {

	public static void main(String[] args) {
		
		try {
			
			DatagramSocket socket = new DatagramSocket(10012);
			
			File file = new File("");
			
			AudioInputStream ais = AudioSystem.getAudioInputStream(file);
			
			AudioFormat af = ais.getFormat();
			
			SourceDataLine line;
			DataLine.Info info = new DataLine.Info(TargetDataLine.class, af);
			line = (SourceDataLine) AudioSystem.getLine(info);
			line.open(af);
			line.start();
			
			socket.close();
		
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
