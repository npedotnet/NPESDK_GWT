/*
 * ArrayBufferRequest.java
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

package net.npe.gwt.xhr.client;

import com.google.gwt.typedarrays.shared.ArrayBuffer;
import com.google.gwt.xhr.client.ReadyStateChangeHandler;
import com.google.gwt.xhr.client.XMLHttpRequest;
import com.google.gwt.xhr.client.XMLHttpRequest.ResponseType;

public class ArrayBufferRequest {
	
	public interface Handler {
		void onSuccess(ArrayBuffer arrayBuffer);
		void onFailure();
	}
	
	public ArrayBufferRequest(String url, final Handler handler) {
		xhr = XMLHttpRequest.create();
		xhr.setResponseType(ResponseType.ArrayBuffer);
		xhr.setOnReadyStateChange(new ReadyStateChangeHandler() {
			@Override
			public void onReadyStateChange(XMLHttpRequest xhr) {
				if(xhr.getReadyState() == XMLHttpRequest.DONE) {
					if(xhr.getStatus() >= 400) {
						if(handler != null) handler.onFailure();
					}
					else {
						if(handler != null) handler.onSuccess(xhr.getResponseArrayBuffer());
					}
				}
			}
		});
		xhr.open("GET", url);
	}
	
	public void send() {
		xhr.send();
	}
	
	private XMLHttpRequest xhr;
	
}
