package namnamnam;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;
import java.util.Random;

public class Players implements Serializable {
	private Ellipse2D.Double Player;
	private Color playerColor;
	private double velocity=5;
	Random random;
	Players(){
		random= new Random();
		Player=new Ellipse2D.Double(random.nextInt(500)+2000, random.nextInt(500)+1000, 25, 25);
		playerColor= new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
	}
	public void drawPlayers(Graphics2D g2){
		g2.setColor(playerColor);
		g2.fill(Player);
	}
	public void increaseSize(){
		Player.width += 0.9;
		Player.height +=0.9;
		velocity -= 0.03;
	}
	public void decreaseSize(){
		Player.width -= 0.9;
		Player.height -=0.9;
		velocity += 0.03;
	}
	public void moveRight(){
		Player.x+=1;
	}
	public Ellipse2D.Double getPlayer() {
		return Player;
	}
	public double getX(){
		return Player.x;
	}
	public double getY(){
		return Player.y;
	}
	public void setPlayer(Ellipse2D.Double player) {
		Player = player;
	}
	public double getVelocity() {
		return velocity;
	}
	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}
}
