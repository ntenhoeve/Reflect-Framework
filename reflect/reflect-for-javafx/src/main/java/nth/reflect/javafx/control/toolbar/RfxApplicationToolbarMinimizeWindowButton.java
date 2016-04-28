package nth.reflect.javafx.control.toolbar;

import com.jfoenix.svg.SVGGlyph;

import javafx.scene.paint.Color;
import nth.introspect.ui.style.control.ToolbarIconStyle;
import nth.reflect.javafx.control.style.RfxColorFactory;
import nth.reflect.javafx.control.window.RfxWindow;

/**
 * Reflect Application Toolbar Minimize button for JavaFX with Google Material Design style
 * 
 * @author nilsth
 *
 */
public class RfxApplicationToolbarMinimizeWindowButton extends RfxApplicationToolbarButton {
	public RfxApplicationToolbarMinimizeWindowButton(RfxWindow window, ToolbarIconStyle iconStyle) {
		super();
		setIcon(iconStyle);
		setMenuAction(window);
	}

	private void setIcon(ToolbarIconStyle iconStyle) {
		Color color = RfxColorFactory.create(iconStyle.getColor());
		SVGGlyph minus = new SVGGlyph(0, "MINIMIZE",
				"M390.242,202.892h-84.075V13.455C306.167,6.024,294.346,0,279.761,0h-46.213c-14.583,0-26.408,6.024-26.408,13.455     v189.437h-84.071c-8.433,0-15.269,5.339-15.269,11.927c0,0,125.952,184.874,137.404,193.819c11.449,8.947,22.899,0,22.899,0     l137.404-193.819C405.507,208.231,398.672,202.892,390.242,202.892z M408.651,497.323c0,10.565-7.475,19.128-16.7,19.128H124.727c-9.221,0-16.7-8.563-16.7-19.128v-19.128     c0-10.565,7.479-19.128,16.7-19.128H391.95c9.225,0,16.7,8.563,16.7,19.128L408.651,497.323L408.651,497.323z",
				color);
		int size = iconStyle.getSize();
		minus.setSize(size,size);
		setGraphic(minus);
	}

	private void setMenuAction(RfxWindow window) {
		setOnAction((action)->window.minimize());
	}

}