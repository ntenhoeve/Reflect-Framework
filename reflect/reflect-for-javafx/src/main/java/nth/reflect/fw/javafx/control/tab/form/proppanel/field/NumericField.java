package nth.reflect.fw.javafx.control.tab.form.proppanel.field;

import java.text.Format;
import java.text.ParseException;
import java.util.Optional;

import javafx.beans.value.ObservableValue;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyFieldWidth;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;

public class NumericField extends TextField {

	public NumericField(PropertyValueModel propertyValueModel) {
		super(propertyValueModel);
	}

	@Override
	public void onChange(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		if (!newValue.matches("\\d*")) {
			newValue = newValue.replaceAll("[^\\d]", "");
			// TODO minus, point, length
		}
		setText(newValue);
		Optional<Format> format = getPropertyValueModel().getPropertyInfo().getFormat();
		if (format.isPresent()) {
			try {
				Object value = format.get().parseObject(newValue);
				getPropertyValueModel().setValue(value);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public PropertyFieldWidth getPropertyFieldWidth() {
		return PropertyFieldWidth.SMALL;
	}

}
