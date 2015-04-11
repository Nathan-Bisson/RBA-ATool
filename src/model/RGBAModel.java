package model;

import java.util.Observable;

import android.graphics.Color;

/**
 * The model holds the data.
 *
 * Model color.
 * Based on red, green, blue and alpha (transparency).
 *
 * RGB: integer values in the domain range of 0 to 255 (inclusive).
 * Alpha: integer value in the domain range of 0 to 255 (inclusive).
 *
 * @author Nathan Bisson (biss0180@algonquinlive.com)
 * @version 1.0
 */

public class RGBAModel extends Observable {
	
	public static final Integer MAX_ALPHA = 100;
	public static final Integer MAX_RGB = 255;
	public static final Integer MIN_ALPHA = 0;
	public static final Integer MIN_RGB = 0;
	
	private Integer alpha;
	private Integer blue;
	private Integer green;
	private Integer red;

	public RGBAModel() {
		// TODO Auto-generated constructor stub
		this( MAX_RGB, MAX_RGB, MAX_RGB, MAX_ALPHA );
	}
	
	public RGBAModel(Integer red, Integer green, Integer blue, Integer alpha) {
		super();

	    this.red = red;
	    this.green = green;
	    this.blue = blue;
	    this.alpha = alpha;
	}
	
	public void asBlack() {
	    this.setRGB(MIN_RGB, MIN_RGB, MIN_RGB);
	}
	
	public void asBlue() {
	    this.setRGB(MIN_RGB, MIN_RGB, MAX_RGB);
	}
	
	public void asCyan() {
	    this.setRGB(MIN_RGB, MAX_RGB, MAX_RGB);
	}
	
	public void asGreen() {
	    this.setRGB(MIN_RGB, MAX_RGB, MIN_RGB);
	}
	
	public void asMagenta() {
	    this.setRGB(MAX_RGB, MIN_RGB, MAX_RGB);
	}
	
	public void asRed() {
	    this.setRGB(MAX_RGB, MIN_RGB, MIN_RGB);
	}
	
	public void asWhite() {
	    this.setRGB(MAX_RGB, MAX_RGB, MAX_RGB);
	}
	
	public void asYellow() {
	    this.setRGB(MAX_RGB, MAX_RGB, MIN_RGB);
	}
	
	public int getColor() {
		return Color.rgb(red, green, blue);
	}
	
	public int getRed() {
		return this.red;
	}
	
	public int getGreen() {
		return this.green;
	}
	
	public int getBlue() {
		return this.blue;
	}
	
	public int getAlpha() {
		return this.alpha;
	}
	
	public void setRed(Integer red) {
		this.red = red;
		this.updateObservers();
	}
	
	public void setGreen(Integer green) {
		this.green = green;
		this.updateObservers();
	}
	
	public void setBlue(Integer blue) {
		this.blue = blue;
		this.updateObservers();
	}
	
	public void setAlpha(Integer alpha) {
		this.alpha = alpha;
		this.updateObservers();
	}
	
	public void setRGB(Integer red, Integer green, Integer blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		
		this.updateObservers();
	}
	
	@Override
	public String toString() {
		return "RGB-A [r=" + red + " g=" + green + " b=" + blue + " alpha=" + alpha + "]";
	}
	
	/**
	 * The model's state has changed!
	 *
	 * Notify all registered observers that this model has changed
	 * state, and the registered observers should change too.
	 */
	private void updateObservers() {		
	    this.setChanged();
	    this.notifyObservers();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RGBAModel model = new RGBAModel(127, 127, 127, 255);
		
		System.out.println(model);

	}

}
