package namnamnam;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;
import java.util.Random;

public class Foods implements Serializable {
	private Ellipse2D.Double foods[];
	private Color foodColors[];
	
	Foods(int numoffoods){
		foods= new Ellipse2D.Double[numoffoods];
		foodColors= new Color[numoffoods];
		callOnce();
	}
	public void callOnce(){
		randomFoodColorInitializer();
		initializeFoods();
	}
	public void randomFoodColorInitializer(){
		Random a=new Random();
		
		for (int i = 0; i < foodColors.length; i++) {
			foodColors[i]=new Color(a.nextInt(255),a.nextInt(255),a.nextInt(255));
		}

	}
	public void drawFood(Graphics2D g2){

		for (int i = 0; i < foods.length; i++) {
			if(foods[i]!=null){
			g2.setColor(foodColors[i]);
			g2.fill(foods[i]);
			}
		}
	}
	public void initializeFoods(){
		Random a=new Random();
		for (int i = 0; i < foods.length; i++) {
			foods[i]=new Ellipse2D.Double(a.nextInt(4000), a.nextInt(3000), 9.3, 9.3);
			}
	}
	public Ellipse2D.Double[] getFoods() {
		return foods;
	}
	public void setFoods(Ellipse2D.Double[] foods) {
		this.foods = foods;
	}
	public Color[] getFoodColors() {
		return foodColors;
	}
	public void setFoodColors(Color[] foodColors) {
		this.foodColors = foodColors;
	}
}
