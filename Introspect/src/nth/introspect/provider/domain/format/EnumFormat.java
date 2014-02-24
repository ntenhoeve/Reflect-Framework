package nth.introspect.provider.domain.format;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

import nth.introspect.Introspect;
import nth.introspect.provider.language.LanguageProvider;

public class EnumFormat extends Format {

	private static final long serialVersionUID = 165765643765L;

	@Override
	public StringBuffer format(Object enumValue, StringBuffer toAppendTo, FieldPosition pos) {
		LanguageProvider languageProvider = Introspect.getLanguageProvider();
		String key = languageProvider.getKey(enumValue);
		String defaultValue = languageProvider.getDefaultValue(key);
		String value = languageProvider.getText(key, defaultValue);
		return toAppendTo.append(value);
	}

	@Override
	public Object parseObject(String source, ParsePosition pos) {
		throw new MethodNotSupportedException();
	}

}