// Student : RASULMETOV AZIZBEK
// Subject : System Implementation
// ID : 201953082052
// Game Project
// Formula 1

package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Road extends JPanel implements ActionListener, Runnable{
	
	
	Timer mainTimer = new Timer(25, this);
	
	Image img = new ImageIcon("D:\\Java\\Java Eclipse 2019\\Formula1\\src\\res\\road.png").getImage();
	
	Player p = new Player(null);
	
	Thread enemiesFactory = new Thread(this);
	
	Thread audioThread = new Thread(new AudioThread());
	
	private boolean gameOver = false;
	private boolean running = true;
	
	List<Enemy> enemies = new ArrayList<Enemy>();
	
	public Road() {
		mainTimer.start();
		enemiesFactory.start();
		audioThread.start();
		addKeyListener(new MyKeyAdapter());
		setFocusable(true);
	}
	
	private class MyKeyAdapter extends KeyAdapter{
		
		public MyKeyAdapter() {
			
		}
		public void keyPressed(KeyEvent e) {
			p.keyPressed(e);
		}
		public void keyReleased(KeyEvent e) {
			p.keyReleased(e);
		}
	}
	
	public void paint(Graphics g) {
		g = (Graphics2D) g;
		g.drawImage(img, p.layer1, 0, null);
		g.drawImage(img, p.layer2, 0, null); // background
		g.drawImage(p.img, p.x, p.y, null); //player car
		if(gameOver) {
			g.setColor(Color.BLACK);
			g.setFont(new Font("Sanserif", Font.BOLD, 80));
			g.drawString("Game Over", 350, 300);
		}
		
		double v = (200 / Player.MAX_V) * p.v; // speed in Km/ph
		g.setColor(Color.WHITE);
		Font font = new Font("Arial", Font.ITALIC, 20);
		g.setFont(font);
		g.drawString("Speed: " + v + " km/h", 100, 60);
		
		
		
		Iterator<Enemy> itr = enemies.iterator();
		while(itr.hasNext()) {
			Enemy e = itr.next();
			if(e.x >= 2400 || e.x <= -2000) {
				itr.remove();
			}else {
				e.move();
				g.drawImage(e.img, e.x, e.y, null);
			}
		
		}
		
	}
	
	public void actionPerformed(ActionEvent e) {
		p.move();
		repaint();
		testCollisionWithEnemies();
		testWin();
		
	}

	private void testWin() {
	
		if(p.s > 1290000) {
			JOptionPane.showMessageDialog(null, "You win!");
			System.exit(0);
		}
		
	}

	private void testCollisionWithEnemies() {
		
		Iterator<Enemy> itr = enemies.iterator();
		while(itr.hasNext()) {
			Enemy e = itr.next();
			if(p.getRect().intersects(e.getRect())) {
				gameOver = true;
				stop();
			}
		}
	}

	@Override
	public void run() {
		
		while(running) {
			Random r = new Random();
			try{ 
				Thread.sleep(r.nextInt(2000));
				enemies.add(new Enemy(1200, 
						r.nextInt(600), r.nextInt(60), this));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void stop() {
		running = false;
		try {
			enemiesFactory.join();
		}catch(Exception e) {}
	}
}
