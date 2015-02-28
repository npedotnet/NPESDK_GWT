/*
 * Range.java
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

import net.npe.gwt.event.dom.client.HasInputHandlers;
import net.npe.gwt.event.dom.client.InputEvent;
import net.npe.gwt.event.dom.client.InputHandler;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

public class Range extends Widget implements HasChangeHandlers, HasInputHandlers {
	
	public Range(double value, double min, double max, double step) {
		super();
		Element element = Document.get().createElement("input").<Element>cast();
		element.setPropertyString("type", "range");
		setElement(element);
		setMin(min);
		setMax(max);
		setStep(step);
		setValue(value);
	}
	
	public Range() {
		this(50, 0, 100, 1);
	}

	@Override
	public HandlerRegistration addChangeHandler(ChangeHandler handler) {
		return addDomHandler(handler, ChangeEvent.getType());
	}
	
	@Override
	public HandlerRegistration addInputHandler(InputHandler handler) {
		return addDomHandler(handler, InputEvent.getType());
	}
	
	public double getValue() {
		return getElement().getPropertyDouble("value");
	}
	
	public double getMin() {
		return getElement().getPropertyDouble("min");
	}
	
	public double getMax() {
		return getElement().getPropertyDouble("max");
	}
	
	public double getStep() {
		return getElement().getPropertyDouble("step");
	}
	
	public void setValue(double value) {
		getElement().setPropertyDouble("value", value);
	}
	
	public void setMin(double min) {
		getElement().setPropertyDouble("min", min);
	}
	
	public void setMax(double max) {
		getElement().setPropertyDouble("max", max);
	}
	
	public void setStep(double step) {
		getElement().setPropertyDouble("step", step);
	}
	
}
