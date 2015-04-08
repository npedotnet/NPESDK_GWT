# NPESDK_GWT
NPESDK GWT utility library :shipit:

![alt text](http://3dtech.jp/wiki/index.php?plugin=attach&refer=NPESDK_GWT&openfile=NPESDK_GWT_DEMO.png "NPESDK_GWT")

Online demo page is here!

http://npe-net.appspot.com/npesdk/gwt/demo/index.html

## License

Released under the MIT license.

https://github.com/npedotnet/NPESDK_GWT/blob/master/LICENSE

## Getting Started

Download npesdk-gwt-1.0.jar and edit yourproject.gwt.xml.

http://npedotnet.github.io/archives/npesdk-gwt-1.0.jar

```xml
<inherits name='net.npe.gwt'/>
```

## CanvasCreator

CanvasCreator is able to create a TGA or DDS HTML5 Canvas.

```java
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.typedarrays.shared.ArrayBuffer;

import net.npe.gwt.canvas.CanvasCreator;
import net.npe.gwt.canvas.CanvasCreator.ImageType;
import net.npe.gwt.xhr.client.ArrayBufferRequest;

String url = "images/test.tga";
ArrayBufferRequest abr = new ArrayBufferRequest(url, new ArrayBufferRequest.Handler() {
	@Override
	public void onSuccess(ArrayBuffer arrayBuffer) {
		Canvas canvas = CanvasCreator.createImageCanvas(arrayBuffer, ImageType.TGA);
		panel.add(canvas);
	}
	@Override
	public void onFailure() {
	}
});
abr.send();
```

## Client UI

### Range

Range is &lt;input type="range" /&gt; class for GWT.

![alt text](http://3dtech.jp/wiki/index.php?plugin=attach&refer=NPESDK_GWT&openfile=NPESDK_GWT_Range.png "Range image")

**Source code**
```java
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import net.npe.gwt.event.dom.client.InputEvent;
import net.npe.gwt.event.dom.client.InputHandler;
import net.npe.gwt.user.client.ui.Range;

// Constructor(value, min, max, step)
final Range range = new Range(50.0, 0.0, 100.0, 1.0);

// onchange event handling
range.addChangeHandler(new ChangeHandler() {
    @Override
    public void onChange(ChangeEvent event) {
        System.out.println("onChange:"+range.getValue());
    }
});

// oninput event handling
range.addInputHandler(new InputHandler() {
    @Override
    public void onInput(InputEvent event) {
        System.out.println("onInput:"+range.getValue());
    }
});
```
### SliderEditBox

SliderEditBox is a Slider and a EditBox docking class for GWT.

![alt text](http://3dtech.jp/wiki/index.php?plugin=attach&refer=NPESDK_GWT&openfile=NPESDK_GWT_SliderEditBox.png "SliderEditBox label")
![alt text](http://3dtech.jp/wiki/index.php?plugin=attach&refer=NPESDK_GWT&openfile=NPESDK_GWT_SliderEditBox_edit.png "SliderEditBox editbox")

**Source code**
```java
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import net.npe.gwt.user.client.ui.SliderEditBox;

// Constructor(value, min, max, step)
SliderEditBox slider = new SliderEditBox(50.0, 0.0, 100.0, 1.0);

// Set css style name
slider.setStyleName("SliderEditBox");

// Set NumberFormat if needed
slider.setFormat("##0.0");

// Set prefix and postfix if needed
slider.setPrefix(" ");
slider.setPostfix(" %");

// Value change event handling
slider.addValueChangeHandler(new ValueChangeHandler<Double>() {
    @Override
    public void onValueChange(ValueChangeEvent<Double> event) {
        System.out.println("onValueChange:"+event.getValue());
    }
});
```

**CSS**
```css
.SliderEditBox {
    width: 120px;
    height: 18px;
}

.SliderEditBox .Label {
    width: 120px;
    height: 18px;
    background: #333333;
    color: orange;
    cursor: col-resize;
    border-bottom: 1px dotted orange;
    font-size: 14px;
    font-weight: normal;
    text-align: center;
    display: table-cell;
    vertical-align: middle;
    user-select: none;
    -moz-user-select: none;
    -webkit-user-select: none;
    -ms-user-select: none;
}

.SliderEditBox .TextBox {
    width: 118px;
    height: 16px;
    margin: 0;
    padding: 0;
    font-size: 12px;
    outline: none;
}
```

### Knob

Knob is a value modular class for GWT, and it can change a value as turning a pointer (counter)clockwise.

![alt text](http://3dtech.jp/wiki/index.php?plugin=attach&refer=NPESDK_GWT&openfile=NPESDK_GWT_Knob.png "Knob image")

**Source code**
```java
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import net.npe.gwt.user.client.ui.Knob;

// Constructor(value, min, max, radius)
Knob knob = new Knob(50.0, 0.0, 100.0, 32.0);

// Set css style name
knob.setStyleName("Knob");

// Value change event handling
knob.addValueChangeHandler(new ValueChangeHandler<Double>() {
    @Override
    public void onValueChange(ValueChangeEvent<Double> event) {
        System.out.println("onValueChange:"+event.getValue());
    }
});
```

**CSS**
```css
.Knob {
    width: 64px;
    height: 64px;
    background-color: #151515;
    border: 2px outset silver;
    border-radius: 64px;
    -moz-border-radius: 64px;
    outline: none;
    cursor: crosshair;
    user-select: none;
    -moz-user-select: none;
    -webkit-user-select: none;
    -ms-user-select: none;
}

.Knob .Panel {
    width: inherit;
    height: inherit;
    background-color: inherit;
    border-radius: inherit;
    position: absolute;
}

.Knob .Panel .Pointer {
    width: 2px;
    height: 30px;
    background-color: darkorange;
    border: 1px solid orange;
    border-radius: 4px;
    -moz-border-radius: 4px;
    user-select: none;
    -moz-user-select: none;
    -webkit-user-select: none;
    -ms-user-select: none;
    position: absolute;
    left: 30px;
    transform-origin: 50% 100%;
}

.Knob .Panel .LowLine {
    width: 1px;
    height: 40px;
    background: silver;
    position: absolute;
    left: 32px;
    top: -8px;
    transform-origin: 50% 100%;
}

.Knob .Panel .HighLine {
    width: 1px;
    height: 40px;
    background: silver;
    position: absolute;
    left: 32px;
    top: -8px;
    transform-origin: 50% 100%;
}

.Knob .Panel .Label {
    width: 64px;
    height: 16px;
    position: absolute;
    top: 48px;
    color: orange;
    font-size: 8px;
    font-weight: normal;
    text-align: center;
    vertical-align: middle;
    user-select: none;
    -moz-user-select: none;
    -webkit-user-select: none;
    -ms-user-select: none;
}

.Knob .Panel .Button {
    position: absolute;
    left: 22px;
    top: 22px;
    width: 20px;
    height: 20px;
    margin: 0;
    padding: 0;
    text-decoration: none;
    cursor: pointer;
    cursor: hand;
    font-size:small;
    color: silver;
    background-color: #353535;
    border: 2px outset silver;
    border-radius: 18px;
    -moz-border-radius: 18px;
    user-select: none;
    -moz-user-select: none;
    -webkit-user-select: none;
    -ms-user-select: none;
    outline: none;
}
.Knob .Panel .Button:active {
    border: 2px inset silver;
    color: darkorange;
}
.Knob .Panel .Button:hover {
    color: orange;
    background-color: #353535;
    border-color: orange;
}
```
