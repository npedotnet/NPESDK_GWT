/*
 * InputEvent.java
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
package net.npe.gwt.event.dom.client;

import com.google.gwt.event.dom.client.DomEvent;

/**
 * Represents a native input event.
 */
public class InputEvent extends DomEvent<InputHandler> {
	
	/**
	 * Event type for input events. Represents the meta-data associated with this event.
	 */
	private static final Type<InputHandler> TYPE = new Type<InputHandler>("input", new InputEvent());
	
	/**
	 * Gets the event type associated with input events.
	 * 
	 * @return the handler type
	 */
	public static Type<InputHandler> getType() {
		return TYPE;
	}
	
	/**
	 * Protected constructor, use
	 * {@link DomEvent#fireNativeEvent(com.google.gwt.dom.client.NativeEvent, com.google.gwt.event.shared.HasHandlers)}
	 * to fire input events.
	 */
	protected InputEvent() {
	}

	@Override
	public com.google.gwt.event.dom.client.DomEvent.Type<InputHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(InputHandler handler) {
		handler.onInput(this);
	}

}
