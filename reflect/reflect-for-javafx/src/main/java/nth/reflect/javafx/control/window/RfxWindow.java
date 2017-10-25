package nth.reflect.javafx.control.window;

import java.net.MalformedURLException;
import java.util.List;

import javafx.animation.TranslateTransition;
import javafx.beans.Observable;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.view.View;
import nth.reflect.javafx.control.RfxControl;
import nth.reflect.javafx.control.window.appbar.RfxAppBar;
import nth.reflect.javafx.control.window.mainmenu.RfxMainMenuPane;

public class RfxWindow extends BorderPane implements RfxControl {


	private final BooleanBinding extraHighBinding;
	private final BooleanBinding extraWideBinding;
	private final BooleanProperty mainMenuVisibleProperty;
	private final ObservableList<View> tabsProperty;
	private final ObjectPropertyBase<View> selectedTabProperty;
	
	private RfxMenuAndContentPane menuAndContentPane;
	private RfxTabButtonBar tabButtonBar;
	private RfxMainMenuPane menuPane;

	
	public static final int MENU_WIDTH = 300;
	private static final int WINDOW_FAIRLY_HIGH_BINDING = 700;
	public static final double WINDOW_FAIRLY_WIDE_BINDING = MENU_WIDTH * 3;
	private static final int MENU_SLIDE_ANIMATION_DURATION = 500;


	
	public RfxWindow(UserInterfaceContainer userInterfaceContainer) throws MalformedURLException {
		super();

		extraHighBinding = heightProperty().greaterThan(WINDOW_FAIRLY_HIGH_BINDING);
		extraWideBinding = widthProperty().greaterThan(WINDOW_FAIRLY_WIDE_BINDING);
		mainMenuVisibleProperty=createMainMenuVisibleProperty();
		tabsProperty = createTabsPropery();
		selectedTabProperty = createSelectedTabProperty();
		
		userInterfaceContainer.add(this);

		setMinWidth(300);
		setMinHeight(500);

		menuPane = new RfxMainMenuPane(userInterfaceContainer);
		tabButtonBar = new RfxTabButtonBar(this);
		BorderPane appBar = new RfxAppBar( userInterfaceContainer, tabButtonBar);
		setTop(appBar);
		
		menuAndContentPane = new RfxMenuAndContentPane(userInterfaceContainer, menuPane);
		setCenter(menuAndContentPane);
	}


	private SimpleObjectProperty<View> createSelectedTabProperty() {
		SimpleObjectProperty<View> selectedTabProperty = new SimpleObjectProperty<>();
		selectedTabProperty.addListener(this::onSelectedTabChanged);
		return selectedTabProperty;
	}


	private ObservableList<View> createTabsPropery() {
		ObservableList<View> tabsProperty = FXCollections.<View>observableArrayList();
		tabsProperty.addListener(this::onTabsChanged);
		return tabsProperty;
	}


	public void onTabsChanged(Change change) {
		if (tabsProperty.size() == 0) {
			selectedTabProperty.set(null);
			updateTabs();
		} else if (!change.getList().contains(selectedTabProperty.get())) {
			selectNewTab(change);
		} else {
			updateTabs();
		}
	}

	private void updateTabs() {
		tabButtonBar.update(tabsProperty);
		updateContent();
	}

	private void updateContent() {
		View selectedTab = selectedTabProperty.get();
		if (selectedTab==null) {
			menuAndContentPane.clearContentPane();
		} else {
			menuAndContentPane.setContentPane((Node)selectedTab);
		}
	}

	private void selectNewTab(Change<View> change) {
		while (change.next()) {

			if (change.wasAdded()) {
				List<View> added = change.getAddedSubList();
				View lastAdded = added.get(added.size() - 1);
				selectedTabProperty.set(lastAdded);// this also calls
													// updateTabBar();
			} else if (change.wasRemoved()) {
				int newIndex = tabsProperty.indexOf(selectedTabProperty.get()) - 1;
				if (newIndex < 0) {
					newIndex = 0;
				}
				View selectedTab = change.getList().get(newIndex);
				selectedTabProperty.set(selectedTab);// this also calls
														// updateTabBar();
			} else {
				// mutated or pre-mutated (changed order)
				updateTabs();
			}
		}
	}

	public void onSelectedTabChanged(Observable observable) {
		updateTabs();
	}
	
	public ObjectProperty<View> getSelectedTabProperty() {
		return selectedTabProperty;
	}

	public ObservableList<View> getTabsProperty() {
		return tabsProperty;
	}


	private BooleanProperty createMainMenuVisibleProperty() {
		BooleanProperty mainMenuVisibleProperty= new BooleanPropertyBase(true) {
			
			@Override
			public String getName() {
				return "mainMenuVisibleProperty";
			}
			
			@Override
			public Object getBean() {
				return this;
			}
			
		};
		mainMenuVisibleProperty.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				if (newValue) {
					onMainMenuShow();
				} else {
					onMainMenuHide();
				}
			}
		});
		return mainMenuVisibleProperty;
	}

	public BooleanBinding getExtraHighBinding() {
		return extraHighBinding;
	}


	public BooleanBinding getExtraWideBinding() {
		return extraWideBinding;
	}

	public BooleanProperty getMainMenuVisibleProperty() {
		return mainMenuVisibleProperty;
	}

	private void onMainMenuHide() {
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(menuPane);
		translate.setDuration(Duration.millis(MENU_SLIDE_ANIMATION_DURATION));
		double width = menuPane.getMinWidth();
		translate.setToX(width * -1);
		translate.play();
	}

	private void onMainMenuShow() {
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(menuPane);
		translate.setDuration(Duration.millis(MENU_SLIDE_ANIMATION_DURATION));
		translate.setToX(0);
		translate.play();
	}



}