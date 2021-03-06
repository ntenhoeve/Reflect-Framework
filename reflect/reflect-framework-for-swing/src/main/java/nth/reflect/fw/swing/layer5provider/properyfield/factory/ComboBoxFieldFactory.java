package nth.reflect.fw.swing.layer5provider.properyfield.factory;

import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;

public class ComboBoxFieldFactory extends nth.reflect.fw.gui.layer5provider.properyfield.factory.ComboBoxFieldFactory {

	@Override
	public PropertyField create(FormTab formTab, PropertyValueModel propertyValueModel) {
		ComboBoxField comboBoxField = new ComboBoxField(formTab, propertyValueModel);
		return comboBoxField;
	}

}
