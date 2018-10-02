package nth.reflect.fw.infrastructure.random.generator.name;

import nth.reflect.fw.infrastructure.random.csv.repository.Repositories;
import nth.reflect.fw.infrastructure.random.generator.collection.FromStringListGenerator;

/**
 * Generates random list of typical English first names for males. Source:
 * https://names.mongabay.com/data/1000.html
 * 
 * @author nilsth
 *
 */
public class LastNameGenerator extends FromStringListGenerator {
	public LastNameGenerator() {
		super(Repositories.lastNameRepository().getLastNames());
	}
}
