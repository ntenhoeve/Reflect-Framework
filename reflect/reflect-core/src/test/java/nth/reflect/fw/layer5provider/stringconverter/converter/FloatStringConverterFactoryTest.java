package nth.reflect.fw.layer5provider.stringconverter.converter;

import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverterFactory;

public class FloatStringConverterFactoryTest extends StringConverterFactoryTest {

	@Override
	protected StringConverterFactory getStringConverterFactory() {
		return new FloatStringConverterFactory();
	}

}