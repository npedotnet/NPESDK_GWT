/*
 * HasInputHandlers.java
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

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

/**
 * A widget that implements this interface provides registration for
 * {@link InputHandler} instances.
 */
public interface HasInputHandlers extends HasHandlers {
	
	/**
	 * Adds a {@link InputEvent} handler.
	 *
	 * @param handler the input handler
	 * @return {@link HandlerRegistration} used to remove this handler
	 */
	HandlerRegistration addInputHandler(InputHandler handler);
	
}
