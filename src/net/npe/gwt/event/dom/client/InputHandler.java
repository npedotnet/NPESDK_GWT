/*
 * InputHandler.java
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

import com.google.gwt.event.shared.EventHandler;

/**
 * Handler for {@link InputEvent} events.
 */
public interface InputHandler extends EventHandler {

	/**
	 * Called when a input event is fired.
	 * 
	 * @param event the {@link InputEvent} that was fired
	 */
	void onInput(InputEvent event);
	
}
