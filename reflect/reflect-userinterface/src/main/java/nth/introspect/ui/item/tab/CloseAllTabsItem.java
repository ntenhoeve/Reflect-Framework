package nth.introspect.ui.item.tab;

import java.net.URISyntaxException;

import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.layer1userinterface.view.View;
import nth.introspect.layer1userinterface.view.ViewContainer;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.reflection.behavior.icon.IconUriClassResource;
import nth.introspect.ui.images.IntrospectImage;

public class CloseAllTabsItem extends Item {

	private static final String CLOSE_ALL_TABS = "Close all tabs";
	private final ViewContainer<View> viewContainer;

	@SuppressWarnings("unchecked")
	public CloseAllTabsItem(LanguageProvider languageProvider,
			final ViewContainer<View> viewContainer) {
		super(languageProvider);
		this.viewContainer = viewContainer;
		setText(CLOSE_ALL_TABS);
		setDescription(CLOSE_ALL_TABS);
		try {
			setIconURI(new IconUriClassResource(IntrospectImage.TABS_CLOSE_ALL)
					.getAbsoluteURI());
		} catch (URISyntaxException e) {
		}
		setAction(new Action() {
			@Override
			public void run() {
				for (int i = viewContainer.getViewCount() - 1; i >= 0; i--) {
					View view = viewContainer.getView(i);
					viewContainer.removeView(view);
				}
			}
		});

	}

	@Override
	public boolean isEnabled() {
		return viewContainer.getViewCount() > 1;
	}

}