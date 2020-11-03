package game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.player.Player;
import javazoom.jl.decoder.JavaLayerException;

public class AudioThread implements Runnable {

	@Override
	public void run() {
		
		
		try {
			Player p = new Player(new FileInputStream("D:\\Hush - Fired up (OST NFS Most Wanted).mp3"));
			p.play();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		

	

	}
}
