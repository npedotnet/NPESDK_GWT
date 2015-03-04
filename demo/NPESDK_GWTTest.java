package net.npe.gwt.test.client;

import net.npe.gwt.event.dom.client.InputEvent;
import net.npe.gwt.event.dom.client.InputHandler;
import net.npe.gwt.user.client.ui.Knob;
import net.npe.gwt.user.client.ui.Range;
import net.npe.gwt.user.client.ui.SliderEditBox;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class NPESDK_GWTTest implements EntryPoint {
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		FlowPanel panel = new FlowPanel();
		panel.setStyleName("Panel");
		
		// Title
		HTML title = new HTML("<h1>NPESDK GWT DEMO PAGE</h1>");
		panel.add(title);
		
		Grid grid = new Grid(5, 5);
		grid.setStyleName("Grid");
		
		int row = 0;
		
		// Header
		setHeader(grid, row++);
		/*
		grid.setWidget(0, 0, new Label("ClassName"));
		grid.setWidget(0, 1, new Label("Widget"));
		grid.setWidget(0, 2, new Label("Value"));
		grid.setWidget(0, 3, new Label("StyleName"));
		*/
		
		// Range with ChangeEvent
		final Range range = new Range();
		final Label rangeValue = new Label(range.getValue()+"");
		range.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				rangeValue.setText(range.getValue()+"");
			}
		});
		setWidget(grid, row++, "Range", range, rangeValue, "(none)", "Value handling with ChangeHandler");
		
		// Range with InputEvent
		final Range inputRange = new Range();
		final Label inputRangeValue = new Label(inputRange.getValue()+"");
		inputRange.addInputHandler(new InputHandler() {
			@Override
			public void onInput(InputEvent event) {
				inputRangeValue.setText(inputRange.getValue()+"");
			}
		});
		setWidget(grid, row++, "Range", inputRange, inputRangeValue, "(none)", "Value handling with InputHandler");
		
		// SliderEditBox
		SliderEditBox box = new SliderEditBox(50, 0, 100, 1);
		box.setStyleName("SliderEditBox");
		box.setPostfix("%");
		final Label boxValue = new Label(box.getValue()+"");
		box.addValueChangeHandler(new ValueChangeHandler<Double>() {
			@Override
			public void onValueChange(ValueChangeEvent<Double> event) {
				boxValue.setText(event.getValue()+"");
			}
		});
//		panel.add(createPanel("SliderEditBox", box, boxValue, "SliderEditBox"));
		grid.setWidget(row, 0, new Label("SliderEditBox"));
		grid.setWidget(row, 1, box);
		grid.setWidget(row, 2, boxValue);
		grid.setWidget(row, 3, new Label("SliderEditBox"));
		row++;
		
		// Knob
		final Knob knob = new Knob(50, 0, 100, 32);
		knob.setStyleName("Knob");
		final Label knobValue = new Label(knob.getFormattedValue());
		knob.addValueChangeHandler(new ValueChangeHandler<Double>() {
			@Override
			public void onValueChange(ValueChangeEvent<Double> event) {
				knobValue.setText(knob.getFormattedValue());
			}
		});
//		panel.add(createPanel("Knob", knob, knobValue, "Knob"));
		grid.setWidget(row, 0, new Label("Knob"));
		grid.setWidget(row, 1, knob);
		grid.setWidget(row, 2, knobValue);
		grid.setWidget(row, 3, new Label("Knob"));
		row++;
		
		panel.add(grid);
		
		// CSS Label
		HTML css = new HTML("<em>css is <a href=\"npesdk_gwt_sample.css\">here</a></em>");
		panel.add(css);
		
		RootLayoutPanel.get().add(panel);
		
	}
	
	private void setHeader(Grid grid, int row) {
		// Class Label
		Label classLabel = new Label("ClassName");
		classLabel.setStyleName("ClassHeaderLabel");
		grid.setWidget(row, 0, classLabel);
		// Widget Label
		Label widgetLabel = new Label("Widget");
		widgetLabel.setStyleName("WidgetHeaderLabel");
		grid.setWidget(row, 1, widgetLabel);
		// Value Label
		Label valueLabel = new Label("Value");
		valueLabel.setStyleName("ValueHeaderLabel");
		grid.setWidget(row, 2, valueLabel);
		// Style Label
		Label styleLabel = new Label("StyleName");
		styleLabel.setStyleName("StyleHeaderLabel");
		grid.setWidget(row, 3, styleLabel);
		// Comment Label
		Label commentLabel = new Label("Comment");
		commentLabel.setStyleName("CommentHeaderLabel");
		grid.setWidget(row, 4, commentLabel);
	}
	
	private void setWidget(Grid grid, int row, String name, Widget widget, Widget value, String css, String comment) {
		grid.setWidget(row, 0, new Label(name));
		grid.setWidget(row, 1, widget);
		grid.setWidget(row, 2, value);
		grid.setWidget(row, 3, new Label(css));
		grid.setWidget(row, 4, new Label(comment));
	}
	
}
