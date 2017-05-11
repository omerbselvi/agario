package namnamnam;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import namnamnam.multiplayer.Client;
import namnamnam.multiplayer.Server;

public class Menu implements MouseListener{
	private Rectangle playButton = new Rectangle(DisplayGame.WIDTH/2-50,DisplayGame.HEIGHT/2,100,50);
	private Rectangle quitButton = new Rectangle(DisplayGame.WIDTH/2-50, DisplayGame.HEIGHT/2+100, 100, 50);
	private boolean enabled = true;
	private DisplayGame displayGame;
	private Point pointPlayer1;
	public String[] args;

	public Menu(DisplayGame displayGame) {
		this.displayGame = displayGame;
	}
	
	public void setArgs(String[] A){
		args = A;
	}

	public void render(Graphics2D g2){
		Font font= new Font("calibri", Font.BOLD,50);
		g2.setFont(font);
		g2.setColor(Color.YELLOW);
		g2.fillOval(DisplayGame.WIDTH/2-73, DisplayGame.HEIGHT/2-250, 150, 150);
		g2.setColor(Color.ORANGE);
		g2.drawString("Nam Nam Nam", DisplayGame.WIDTH/2-170, 300);
		g2.setColor(Color.BLACK);
		g2.drawString("Play", playButton.x, playButton.y+40);
		g2.drawString("Quit", quitButton.x, quitButton.y+40);	
	}
	public void setPoint(Point pointPlayer1){
		this.pointPlayer1=pointPlayer1;
	}
	public void setDisplayGame(DisplayGame dg){
		displayGame=dg;
	}
	public void player1Won(Graphics2D g2){
		g2.setColor(Color.GREEN);
		Font font= new Font("calibri", Font.BOLD,50);
		g2.setFont(font);
		g2.drawString("YOU WON", pointPlayer1.x-100, pointPlayer1.y);
	}
	public void player2Won(Graphics2D g2){
		g2.setColor(Color.RED);
		Font font= new Font("calibri", Font.BOLD,50);
		g2.setFont(font);
		g2.drawString("YOU LOST", pointPlayer1.x-100, pointPlayer1.y);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(enabled){
			int mx=e.getX();
			int my=e.getY();
			if(mx>=370&&mx<=470){
				if(my>=340&&my<=390){
					if(args.length == 0){
			            Server server = new Server(displayGame);
			            Thread thread = new Thread(server);
			            thread.start();
					}
					else{
			            Client client = new Client(args[0] ,displayGame);
			            System.out.println(args[0]);
			            Thread thread = new Thread(client);
			            thread.start();
					}	
					DisplayGame.state=DisplayGame.STATE.GAME;
					enabled=false;
				}
			}
			if(mx>=370&&mx<=470){
				if (my>=440&&my<490) {
					System.exit(1);
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

}
