package nth.reflect.javafx.control.tabpane;

import com.jfoenix.controls.JFXDrawer;

import javafx.animation.TranslateTransition;
import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer2service.MainMenu;
import nth.introspect.ui.style.MaterialColors;
import nth.reflect.javafx.control.itemtreelist.RfxItemTreeView;
import nth.reflect.javafx.control.mainmenu.RfxMainMenuView;
import nth.reflect.javafx.control.style.RfxColorFactory;

public class RfxMenuAndContentPane extends StackPane {

	private static final int MENU_ANIMATION_DURATION = 500;
	private final BorderPane menuPane;
	private final BorderPane contentPane;
	private final BooleanBinding windowExtraWideBinding;

	
	/**
	 * TODO move content window to the right when wide screen and menu opened. see {@link JFXDrawer}
	 * @param userInterfaceContainer
	 * @param windowExtraWideBinding
	 */
	public RfxMenuAndContentPane(UserInterfaceContainer userInterfaceContainer, BooleanBinding windowExtraWideBinding) {
		this.windowExtraWideBinding = windowExtraWideBinding;
		contentPane = createContent();
		getChildren().add(contentPane);

		menuPane = new RfxMainMenuView(userInterfaceContainer, windowExtraWideBinding);
		getChildren().add(menuPane);
		setAlignment(menuPane, Pos.TOP_LEFT);
	}

//	private BorderPane createMenuPane(UserInterfaceContainer userInterfaceContainer) {
//		BorderPane menuPane = new BorderPane();
////		menuPane.setBackground(RfxColorFactory
////				.createBackGround(MaterialColors.getContentColorSet().getBackground()));
//		menuPane.setMinWidth(RfxTabBarPane.MENU_WIDTH);
//		menuPane.setMaxWidth(RfxTabBarPane.MENU_WIDTH);
//		
//		javafx.scene.paint.Color lineColor = RfxColorFactory
//				.create(MaterialColors.getContentColorSet().getForeground3());
//		BorderStroke borderStroke = new BorderStroke(null, lineColor, null, null, null,
//				BorderStrokeStyle.SOLID, null, null, null, null, Insets.EMPTY);
//		Border border = new Border(borderStroke);
//		menuPane.setBorder(border);
//
//		RfxItemTreeView mainMenuList = new RfxItemTreeView(userInterfaceContainer);
//		menuPane.setCenter(mainMenuList);
//		return menuPane;
//	}

	private BorderPane createContent() {
		BorderPane content = new BorderPane();
		content.setBackground(RfxColorFactory
				.createBackGround(MaterialColors.getContentColorSet().getBackground()));
		Label label = new Label("Content");
		content.setCenter(label);
		return content;
	}

	/**
	 * See
	 * https://stackoverflow.com/questions/31601900/javafx-how-to-create-slide-in-animation-effect-for-a-pane-inside-a-transparent
	 */
	public void toggleMenuVisibility() {
		if (menuPane.getTranslateX() == 0) {
			hideMenu();
		} else {
			showMenu();
		}
	}

	public void hideMenu() {
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(menuPane);
		translate.setDuration(Duration.millis(MENU_ANIMATION_DURATION));
		double width = menuPane.getMinWidth();
		translate.setToX(width * -1);
		translate.play();
	}

	public void showMenu() {
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(menuPane);
		translate.setDuration(Duration.millis(MENU_ANIMATION_DURATION));
		translate.setToX(0);
		translate.play();
	}

	public void clearContentPane() {
		contentPane.setCenter(null);
	}

	public void setContentPane(Node content) {
		contentPane.setCenter(content);
	}

}