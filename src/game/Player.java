package game;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Player {
											// control speed and movement
	public static final int MAX_V = 100;
	public static final int MAX_TOP = 30;
	public static final int MAX_BOTTOM = 450;
	
	
	
	Image img_c = new ImageIcon("D:\\Java\\Java Eclipse 2019\\Formula1\\src\\res\\playercar.png").getImage();
	Image img_l = new ImageIcon("D:\\Java\\Java Eclipse 2019\\Formula1\\src\\res\\playercar_left.png").getImage();
	Image img_r = new ImageIcon("D:\\Java\\Java Eclipse 2019\\Formula1\\src\\res\\playercar_right.png").getImage();
	Image img = img_c;
	
	



	public Player(FileInputStream fileInputStream) {
		// TODO Auto-generated constructor stub
	}

	public Rectangle getRect() {
		return new Rectangle(x, y, 50, 50);
	}
	
	
	int v = 0;
	int dv = 0;
	int s = 0;
	
	int x = 30;
	int y = 100;
	int dy = 0;
	
	int layer1 = 0;
	int layer2 = 1200;
	
	public void move() {
		s += v;
		v += dv;
		if(v <= 0) v = 0;
		if(v >= MAX_V) v = MAX_V;
		y -= dy;
		if(y <= MAX_TOP) y = MAX_TOP;
		if(y >= MAX_BOTTOM) y = MAX_BOTTOM;
		
		if(layer2 - v <= 0) {
			layer1 = 0;
			layer2 = 1200;
		} else {
			layer1 -= v;
			layer2 -= v;
		}
		
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT) {
			dv = 1;
		}
		if(key == KeyEvent.VK_LEFT) {
			dv = -3;
		}
		if(key == KeyEvent.VK_UP) {
			dy = 5;
			img = img_l;
		}
		if(key == KeyEvent.VK_DOWN) {
			dy = -5;
			img = img_r;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT) {
			dv = 0;
		}
		if(key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
			dy = 0;
			img = img_c;
		}
	}

	public void play() {
		
		
	}
	
	
}
