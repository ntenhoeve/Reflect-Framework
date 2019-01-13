package nth.reflect.fw.javafx.control.table;

import java.util.Collection;

import nth.reflect.fw.gui.component.tab.table.TableTab;
import nth.reflect.fw.gui.item.method.menu.TableRowMenuItems;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class TableInfoForTableTab extends TableInfo {

	private final TableTab tableTab;
	private final Class<?> itemType;
	private final ReflectionProvider reflectionProvider;
	private final LanguageProvider languageProvider;

	public TableInfoForTableTab(TableTab tableTab) {
		this.tableTab = tableTab;
		UserInterfaceContainer userInterfaceContainer = tableTab.getUserInterfaceContainer();
		this.reflectionProvider = userInterfaceContainer.get(ReflectionProvider.class);
		this.languageProvider = userInterfaceContainer.get(LanguageProvider.class);
		ActionMethodInfo actionMethodInfo = tableTab.getMethodInfo();
		this.itemType = actionMethodInfo.getReturnTypeInfo().getGenericType();

	}

	@Override
	public Object getValues() {
		try {
			Object methodOwner = tableTab.getMethodOwner();
			ActionMethodInfo actionMethodInfo = tableTab.getMethodInfo();
			Object methodParameterValue = tableTab.getMethodParameter();
			Object methodResult = actionMethodInfo.invoke(methodOwner, methodParameterValue);
			return methodResult;
		} catch (Exception e) {
			StringBuilder message = new StringBuilder(tableTab.getDisplayName());
			message.append(": ");
			message.append(languageProvider.getText("Error getting table values."));
			throw new RuntimeException(message.toString(), e);
			// UserInterfaceController userInterfaceController =
			// tableTab.getUserInterfaceContainer()
			// .get(UserInterfaceController.class);
			// userInterfaceController.showErrorDialog(tableTab.getViewTitle(),
			// "Error getting table values.", e);
		}
	}

	@Override
	public Class<?> getValuesType() {
		return itemType;
	}

	@Override
	public ReflectionProvider getReflectionProvider() {
		return reflectionProvider;
	}

	@Override
	public LanguageProvider getLanguageProvider() {
		return languageProvider;
	}

	@Override
	public Collection<Item> getRowMenuItems(Object selectedObject) {
		Collection<Item> items = new TableRowMenuItems(tableTab, selectedObject);
		return items;
	}
}
