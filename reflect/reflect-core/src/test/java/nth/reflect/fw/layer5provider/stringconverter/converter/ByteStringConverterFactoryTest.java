package nth.reflect.fw.layer5provider.stringconverter.converter;

import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverterFactory;

public class ByteStringConverterFactoryTest extends StringConverterFactoryTest {

	@Override
	protected StringConverterFactory getStringConverterFactory() {
		return new ByteStringConverterFactory();
	}

}