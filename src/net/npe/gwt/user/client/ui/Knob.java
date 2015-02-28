/*
 * Knob.java
 * 
 * Copyright (c) 2015 Kenji Sasaki
 * Released under the MIT license.
 * https://github.com/npedotnet/NPESDK_GWT/blob/master/LICENSE
 * 
 * English document
 * https://github.com/npedotnet/NPESDK_GWT/blob/master/README.md
 * 
 * Japanese document
 * http://3dtech.jp/wiki/index.php?NPESDK_GWT
 * 
 */
package net.npe.gwt.user.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Label;

public class Knob extends FocusPanel implements HasValueChangeHandlers<Double> {
	
	public Knob(double value, double min, double max, double radius) {
		super();
		
		this.min = min;
		this.max = max;
		this.radius = radius;
		this.initialValue = value;
		
		this.addMouseDownHandler(new MouseDownHandler() {
			@Override
			public void onMouseDown(MouseDownEvent event) {
				double r = getRadius();
				double x = event.getX() - r;
				double y = r - event.getY();
				oldAngle = Math.atan2(y, x);
				mouseDowned = true;
			}
		});
		
		this.addMouseMoveHandler(new MouseMoveHandler() {
			@Override
			public void onMouseMove(MouseMoveEvent event) {
				if(mouseDowned) {
					double r = getRadius();
					double x = event.getX() - r;
					double y = r - event.getY();
					double newAngle = Math.atan2(y, x);
					double diff = newAngle - oldAngle;
					angle -= Math.toDegrees(diff);
					oldAngle = newAngle;
					if(angle < -180.0) angle += 360.0;
					else if(angle > 180.0) angle -= 360.0;
					
					setAngle(angle);
					fireEvent(new ValueChangeEvent(getValue()));

				}
			}
		});
		
		this.addMouseUpHandler(new MouseUpHandler() {
			@Override
			public void onMouseUp(MouseUpEvent event) {
				mouseDowned = false;
			}
		});
		
		this.addMouseOutHandler(new MouseOutHandler() {
			@Override
			public void onMouseOut(MouseOutEvent event) {
				mouseDowned = false;
			}
		});
		
		this.addMouseOverHandler(new MouseOverHandler() {
			@Override
			public void onMouseOver(MouseOverEvent event) {
				mouseDowned = false;
			}
		});
		
		FlowPanel panel = new FlowPanel();
		panel.setStyleName("Panel");
		
		pointer = new FlowPanel();
		pointer.setStyleName("Pointer");
		panel.add(pointer);
		
		lowLine = new FlowPanel();
		lowLine.setStyleName("LowLine");
		panel.add(lowLine);

		highLine = new FlowPanel();
		highLine.setStyleName("HighLine");
		panel.add(highLine);

		Button button = new Button();
		button.setStyleName("Button");
		button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				initializeValue();
			}
		});
		panel.add(button);
		
		label = new Label();
		label.setStyleName("Label");
		panel.add(label);

		setLimitAngle(130);
		setValue(initialValue);
		
		add(panel);
		
	}
	
	public Knob() {
		this(50, 0, 100, 32);
	}
	
	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Double> handler) {
		return addHandler(handler, ValueChangeEvent.getType());
	}
	
	public void initializeValue() {
		setValue(initialValue);
		fireEvent(new ValueChangeEvent(getValue()));
	}
	
	public void updateMeter() {
		label.setText(getFormattedValue()+postfix);
	}
	
	public void updatePointer() {
		String rotate = "rotate("+angle+"deg)";
		pointer.getElement().getStyle().setProperty("transform", rotate);
	}
	
	public void setAngle(double angle) {
		this.angle = angle;
		if(this.angle > limitAngle) this.angle = limitAngle;
		else if(this.angle < -limitAngle) this.angle = -limitAngle;
		double zeroone = (this.angle + limitAngle) / (2 * limitAngle);
		this.value = min + zeroone * (max - min);
		updateMeter();
		updatePointer();
	}
	
	public void setLimitAngle(double limitAngle) {
		this.limitAngle = limitAngle;
		lowLine.getElement().getStyle().setProperty("transform", "rotate(-"+limitAngle+"deg)");
		highLine.getElement().getStyle().setProperty("transform", "rotate("+limitAngle+"deg)");
	}
	
	public double getValue() { return value; }
	
	public void setValue(double value) {
		this.value = value;
		if(this.value > max) this.value = max;
		else if(this.value < min) this.value = min;
		double zeroone = (this.value - min) / (max - min);
		this.angle = (zeroone - 0.5) * 2 * limitAngle;
		updateMeter();
		updatePointer();
	}
	
	public double getRadius() { return radius; }
	
	public void setRadius(double radius) { this.radius = radius; }
	
	public void setFormat(String format) {
		this.format = format;
		updateMeter();
	}
	
	public void setPostfix(String postfix) {
		this.postfix = postfix;
		updateMeter();
	}
	
	public String getFormattedValue() { return NumberFormat.getFormat(format).format(value); }

	private FlowPanel pointer;
	private FlowPanel lowLine;
	private FlowPanel highLine;
	private Label label;
	
	private boolean mouseDowned;
	private double radius = 32;
	private double angle = 0;
	private double limitAngle;
	private double oldAngle;
	private String format = "0.00";
	private double value;
	private double min;
	private double max;
	private double initialValue;
	private String postfix = "";
	
	private class ValueChangeEvent extends com.google.gwt.event.logical.shared.ValueChangeEvent<Double> {
		ValueChangeEvent(Double value) {
			super(value);
		}
	}

}
