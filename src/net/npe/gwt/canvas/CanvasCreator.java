/*
 * CanvasCreator.java
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

package net.npe.gwt.canvas;

import java.io.IOException;

import net.npe.dds.DDSReader;
import net.npe.tga.TGAReader;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.CanvasPixelArray;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.ImageData;
import com.google.gwt.typedarrays.client.Uint8ArrayNative;
import com.google.gwt.typedarrays.shared.ArrayBuffer;

public class CanvasCreator {
	
	public enum ImageType {
		DDS,
		TGA,
	}
	
	public static Canvas createImageCanvas(ArrayBuffer arrayBuffer, ImageType type) {
		Uint8ArrayNative u8array = Uint8ArrayNative.create(arrayBuffer);
		byte [] buffer = new byte[u8array.length()];
		for(int i=0; i<buffer.length; i++) {
			buffer[i] = (byte)u8array.get(i);
		}
		return createImageCanvas(buffer, type);
	}
	
	public static Canvas createImageCanvas(byte [] buffer, ImageType type) {
		if(type == ImageType.DDS) {
			int [] pixels = DDSReader.read(buffer, DDSReader.ABGR, 0);
			int width = DDSReader.getWidth(buffer);
			int height = DDSReader.getHeight(buffer);
			return createImageCanvas(pixels, width, height);
		}
		else if(type == ImageType.TGA) {
			try {
				int [] pixels = TGAReader.read(buffer, TGAReader.ABGR);
				int width = TGAReader.getWidth(buffer);
				int height = TGAReader.getHeight(buffer);
				return createImageCanvas(pixels, width, height);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static Canvas createImageCanvas(int [] pixels, int width, int height) {
		
		Canvas canvas = Canvas.createIfSupported();
		
		if(canvas == null) return null;
		
		canvas.setCoordinateSpaceWidth(width);
		canvas.setCoordinateSpaceHeight(height);
		
		Context2d context = canvas.getContext2d();
		ImageData data = context.createImageData(width, height);

		CanvasPixelArray array = data.getData();
		for(int i=0; i<width*height; i++) { // ABGR
			array.set(4*i+0, pixels[i] & 0xFF);
			array.set(4*i+1, (pixels[i] >> 8) & 0xFF);
			array.set(4*i+2, (pixels[i] >> 16) & 0xFF);
			array.set(4*i+3, (pixels[i] >> 24) & 0xFF);
		}
		context.putImageData(data, 0, 0);
		
		return canvas;
		
	}

}
