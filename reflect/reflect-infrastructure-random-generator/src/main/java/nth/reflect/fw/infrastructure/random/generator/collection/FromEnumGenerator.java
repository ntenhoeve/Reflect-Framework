package nth.reflect.fw.infrastructure.random.generator.collection;

import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.RandomGenerator;

@SuppressWarnings("rawtypes")
public class FromEnumGenerator extends RandomGenerator<Enum> {

	private Enum<?>[] enumerationValues;

	public FromEnumGenerator(Class<? extends Enum> enumClass) {
		enumerationValues = enumClass.getEnumConstants();
	}

	@Override
	public Enum generate() {
		int randomIndex = Random.intGenerator().forMax(enumerationValues.length).generate();
		return enumerationValues[randomIndex];
	}

}