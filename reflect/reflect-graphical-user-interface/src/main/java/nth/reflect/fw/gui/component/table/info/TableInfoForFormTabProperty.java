package nth.reflect.fw.gui.component.table.info;

import java.lang.reflect.Method;
import java.util.Collection;

import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.menu.PropertyPanelMenuItems;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

public class TableInfoForFormTabProperty extends TableInfo {

	private final PropertyValueModel propertyValueModel;
	private final FormTab formTab;

	public TableInfoForFormTabProperty(FormTab formTab, PropertyValueModel propertyValueModel) {
		super(formTab.getUserInterfaceContainer());
		this.formTab = formTab;
		this.propertyValueModel = propertyValueModel;
	}

	@Override
	public Object getValues() {
		return propertyValueModel.getValue();
	}

	@Override
	public TypeInfo getTypeInfo() {
		return propertyValueModel.getPropertyInfo().getTypeInfo();
	}

	@Override
	public Collection<Item> getRowMenuItems(ReadOnlyValueModel actionMethodParameterModel) {
		PropertyPanelMenuItems items = new PropertyPanelMenuItems(formTab, actionMethodParameterModel,
				propertyValueModel.getPropertyInfo());
		return items;
	}

	@Override
	public Method getValuesMethod() {
		Method getterMethod = propertyValueModel.getPropertyInfo().getGetterMethod();
		return getterMethod;
	}

}
