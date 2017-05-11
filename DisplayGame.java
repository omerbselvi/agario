import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import javax.swing.*;

public class DisplayGame extends JPanel implements ActionListener{

	Ellipse2D.Double ball;
	Ellipse2D.Double foods[];
	Color foodColors[];
	Camera cam;
	boolean isGameInitialized=false;
	boolean isFoodColorInitialized=false;
	
	double v=6;
	public DisplayGame() {
		Timer timer=new Timer(30,this);
		timer.start();
		cam= new Camera(0,0);
		foods= new Ellipse2D.Double[50];
		foodColors=new Color[50];
		ball=new Ellipse2D.Double(500, 400, 25, 25);

		
		}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;

		if(isGameInitialized==false)
		initializeGame();
		if(isFoodColorInitialized==false)
		randomFoodColorInitializer();
		g2.translate(cam.getX(), cam.getY());
		drawFood(g2);
		didBallIntersect();
		cam.renderCamera(ball);
		setBackground(Color.GRAY);
		g2.setColor(Color.ORANGE);
		g2.fill(ball);
		g2.setColor(Color.RED);	
		g2.translate(-cam.getX(), -cam.getY());
	}
	//CHECK IF BALL INTERSECTS WITH FOOD IF IT DOES MAKE BALL BIGGER
	public void didBallIntersect(){
		for (int i = 0; i < foods.length; i++) {
			if(foods[i]!=null && ball.getBounds().intersects(foods[i].getBounds())){
				foods[i]=null;
				ball.width+=0.8;
				ball.height+=0.8;
				v-=0.1;
			}
		}
	}
	//FOOD COLOR INITIALIZER
	public void randomFoodColorInitializer(){
		Random a=new Random();
		
		for (int i = 0; i < foodColors.length; i++) {
			foodColors[i]=new Color(a.nextInt(255),a.nextInt(255),a.nextInt(255));
		}
		isFoodColorInitialized=true;
	}
	//DRAWING FOOD TO THE SCREEN
	public void drawFood(Graphics2D g2){

		for (int i = 0; i < foods.length; i++) {
			if(foods[i]!=null){
			g2.setColor(foodColors[i]);
			g2.fill(foods[i]);
			}
		}
	}
	//FOOD INITIALIZER
	public void initializeGame(){
		Random a=new Random();

		for (int i = 0; i < foods.length; i++) {
			foods[i]=new Ellipse2D.Double(a.nextInt(1000), a.nextInt(800), 8, 8);
			
			}
		isGameInitialized=true;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Point p=getMousePosition();
		System.out.println("BALL X:"+ball.x+" BALL Y:"+ball.y);

		if(p==null)return;
		System.out.println("MOUSE X:"+p.x+" MOUSE Y:"+p.y);
		double dx = p.x - ball.x - ball.width/2;
		double dy = p.y - ball.y - ball.height/2;
		if(dx*dx+dy*dy >12){
		double a=Math.atan2(dy, dx);
		ball.x += v * Math.cos(a);
		ball.y += v * Math.sin(a);}
//		OLD MOVE METHOD		
//		double a=(p.x-ball.x-10);
//		double b=(p.y-ball.y-10);
//		ball.x+=a*0.05;
//		ball.y+=b*0.05;
		repaint();	
	}
}