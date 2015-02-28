/*
 * SliderEditBox.java
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

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class SliderEditBox extends FlowPanel implements HasValueChangeHandlers<Double> {
	
	public SliderEditBox(double value, double min, double max, double step) {
		super();
		
		this.value = value;
		this.min = min;
		this.max = max;
		this.step = step;
		
		label = new Label();
		label.setStyleName("Label");
		label.addDoubleClickHandler(new DoubleClickHandler() {
			@Override
			public void onDoubleClick(DoubleClickEvent event) {
				label.setVisible(false);
				box.setVisible(true);
				box.setFocus(true);
				box.setText(getStringValue());
				box.selectAll();
			}
		});
		label.addMouseDownHandler(new MouseDownHandler() {
			@Override
			public void onMouseDown(MouseDownEvent event) {
				mouseDowned = true;
				mouseX = event.getX();
			}
		});
		label.addMouseUpHandler(new MouseUpHandler() {
			@Override
			public void onMouseUp(MouseUpEvent event) {
				mouseDowned = false;
			}
		});
		
		label.addMouseMoveHandler(new MouseMoveHandler() {
			@Override
			public void onMouseMove(MouseMoveEvent event) {
				if(mouseDowned) {
					int x = event.getX();
					double dx = getStep() * (x - mouseX);
					setValue(getValue() + dx);
					fireEvent(new ValueChangeEvent(getValue()));
					mouseX = x;
				}
			}
		});
		label.addMouseOutHandler(new MouseOutHandler() {
			@Override
			public void onMouseOut(MouseOutEvent event) {
				mouseDowned = false;
			}
		});
		
		box = new TextBox();
		box.setStyleName("TextBox");
		box.setVisible(false);
		box.addKeyDownHandler(new KeyDownHandler() {
			@Override
			public void onKeyDown(KeyDownEvent event) {
				if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					try {
						double newValue = Double.parseDouble(box.getText());
						setValue(newValue);
						fireEvent(new ValueChangeEvent(getValue()));
					}
					catch(Exception ex) {}
					box.setVisible(false);
					label.setVisible(true);
				}
			}
		});
		box.addBlurHandler(new BlurHandler() {
			@Override
			public void onBlur(BlurEvent event) {
				box.setVisible(false);
				label.setVisible(true);
			}
		});
		
		add(label);
		add(box);
		
		setValue(value);
	}
	
	public SliderEditBox() {
		this(50, 0, 100, 1);
	}
	
	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Double> handler) {
		return addHandler(handler, ValueChangeEvent.getType());
	}
	
	public void setSize(String width, String height) {
		label.setSize(width, height);
		box.setSize(width, height);
		super.setSize(width, height);
	}
	
	public void setWidth(String width) {
		label.setWidth(width);
		box.setWidth(width);
		super.setWidth(width);
	}
	
	public void setHeight(String height) {
		label.setHeight(height);
		box.setHeight(height);
		super.setHeight(height);
	}
	
	public void setValue(double value) {
		this.value = value;
		if(this.value < min) this.value = min;
		else if(this.value > max) this.value = max;
		updateValue();
	}
	
	public void updateValue() {
		label.setText(prefix+getStringValue()+postfix);
	}
	
	public double getValue() { return value; }
	public double getStep() { return step; }
	
	public String getStringValue() { return NumberFormat.getFormat(format).format(value); }
	
	public void setFormat(String format) { this.format = format; updateValue(); }
	public void setPrefix(String prefix) { this.prefix = prefix; updateValue(); }
	public void setPostfix(String postfix) { this.postfix = postfix; updateValue(); }
	
	private Label label;
	private TextBox box;
	private double value;
	private double min;
	private double max;
	private double step;
	private boolean mouseDowned = false;
	private int mouseX;
	private String format = "";
	private String prefix = "";
	private String postfix = "";
	
	private class ValueChangeEvent extends com.google.gwt.event.logical.shared.ValueChangeEvent<Double> {
		ValueChangeEvent(Double value) {
			super(value);
		}
	}

}
