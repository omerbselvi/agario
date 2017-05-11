import java.awt.geom.Ellipse2D;

public class Camera {
	
	double x,y;
	
	public Camera(float x,float y) {
		this.x=x;
		this.y=y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public void renderCamera(Ellipse2D.Double ball){
		x=-ball.getX()+320;
		y=-ball.getY()+240;


		
	}
	
	
}
