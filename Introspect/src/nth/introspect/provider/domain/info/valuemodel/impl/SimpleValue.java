package nth.introspect.provider.domain.info.valuemodel.impl;

import nth.introspect.valuemodel.ReadOnlyValueModel;

public class SimpleValue implements ReadOnlyValueModel {
	private final Object value;

	public SimpleValue(Object value) {
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value.toString();
	}

	@Override
	public Class<?> getValueType() {
		return value.getClass();
	}

	@Override
	public boolean canGetValue() {
		return true;
	}
}