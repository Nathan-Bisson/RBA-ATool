package com.algonquincollege.biss0180.rgbatool;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import java.util.Observable;
import java.util.Observer;

import model.RGBAModel;

import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

/**
 * View and controller (i.e. a UI delegate) of a RGBA model.
 *
 * As the controller:
 *   a) event handler for the view
 *   b) observer of the model, which is an RGBAModel.
 *
 * Features the Update / React Strategy.
 *
 * @author Your Nathan Bisson (biss0180)
 * @version 1.0
 *
 */

public class MainActivity extends Activity implements Observer, OnValueChangeListener {
	private static final String TAG = "RGB-A TOOL";
	 
	private NumberPicker blue;
	private NumberPicker green;
	private ViewGroup layout;
	private RGBAModel model;
	private NumberPicker red;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences settings = getSharedPreferences( getResources().getString(R.string.app_name), Context.MODE_PRIVATE );
        
     // instantiate a new RGB-A model
     // and observer it
        model = new RGBAModel( settings.getInt("red",   RGBAModel.MAX_RGB),
                settings.getInt("green", RGBAModel.MAX_RGB),
                settings.getInt("blue",  RGBAModel.MAX_RGB),
                settings.getInt("alpha", RGBAModel.MAX_ALPHA) );
     model.addObserver(this);
     
     blue = (NumberPicker) findViewById( R.id.pickerBlue );
     green = (NumberPicker) findViewById( R.id.pickerGreen );
     layout = (ViewGroup) findViewById( R.id.mainLayout );
     red = (NumberPicker) findViewById( R.id.pickerRed );
     
     SeekBar alpha = (SeekBar) findViewById( R.id.seekBarAlpha );
     
     alpha.setMax(RGBAModel.MAX_ALPHA);
     blue.setMaxValue( RGBAModel.MAX_RGB );
     blue.setMinValue( RGBAModel.MIN_RGB );
     green.setMaxValue( RGBAModel.MAX_RGB );
     green.setMinValue( RGBAModel.MIN_RGB );
     red.setMaxValue( RGBAModel.MAX_RGB );
     red.setMinValue( RGBAModel.MIN_RGB );
     
     blue.setOnValueChangedListener(this);
     green.setOnValueChangedListener(this);
     red.setOnValueChangedListener(this);
     
     
     alpha.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			// TODO Auto-generated method stub
			Log.i( TAG, "Setting the model's alpha value to: " + seekBar.getProgress() );
			model.setAlpha( seekBar.getProgress() );
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
			
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}
    	 
     }); 
     
     alpha.setProgress( model.getAlpha() );
     this.updateView( );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        
    	//int id = item.getItemId();
        //if (id == R.id.action_about) {
          //  return true;
        //}
        
        switch ( item.getItemId() ) {
        case R.id.action_black:
        	model.asBlack();
        	return true;
        case R.id.action_blue:
        	model.asBlue();
        	return true;
        case R.id.action_cyan:
        	model.asCyan();
        	return true;
        case R.id.action_green:
        	model.asGreen();
        	return true;
        case R.id.action_magenta:
        	model.asMagenta();
        	return true;
        case R.id.action_red:
        	model.asRed();
        	return true;
        case R.id.action_white:
        	model.asWhite();
        	return true;
        case R.id.action_yellow:
        	model.asYellow();
        	return true;
        default: 
        	return super.onOptionsItemSelected(item);
        }
    }
    
    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
    	switch(picker.getId() ) {
    	case R.id.pickerBlue:
    		Log.i( TAG, "Setting the model's blue value to: " + newVal );
    		model.setBlue( newVal );
    	    break;
    	case R.id.pickerGreen:
    		Log.i( TAG, "Setting the model's green value to: " + newVal );
    		model.setGreen( newVal );
    	    break;
    	case R.id.pickerRed:
    		Log.i( TAG, "Setting the model's red value to: " + newVal );
    		model.setRed( newVal );
    	    break;    
    	}
    }
    
    @Override
    public void update(Observable obs, Object data) {
        this.updateView();
    }
    
    private void updateAlpha() {
        // display the alpha value as a percentage (%)
        layout.setAlpha( ((float) model.getAlpha()) / 100 );
    }
    
    private void updateBlue() {
        blue.setValue( model.getBlue() );
    }
    
    private void updateGreen() {
        green.setValue( model.getGreen() );
    }
    
    private void updateRed() {
        red.setValue( model.getRed() );
    }
    
    // GET the color value from the model, and
    // SET the activity's background color to the model's color
    private void updateColor() {
    	this.getWindow().getDecorView().setBackgroundColor( model.getColor() );
    }
    
 // Synchronize each view component with the current state of the model.
    public void updateView() {
        this.updateAlpha();
        this.updateBlue();
        this.updateGreen();
        this.updateRed();
        this.updateColor();
    }
    
 // Remember the user's settings for RGB-A
    @Override
    protected void onStop() {
      SharedPreferences settings = getSharedPreferences( getResources().getString(R.string.app_name), Context.MODE_PRIVATE );
      SharedPreferences.Editor editor = settings.edit();

      editor.putInt( "red",   model.getRed() );
      editor.putInt( "green", model.getGreen() );
      editor.putInt( "blue",  model.getBlue() );
      editor.putInt( "alpha", model.getAlpha() );

      editor.commit();
      super.onStop();
    }
    
}
