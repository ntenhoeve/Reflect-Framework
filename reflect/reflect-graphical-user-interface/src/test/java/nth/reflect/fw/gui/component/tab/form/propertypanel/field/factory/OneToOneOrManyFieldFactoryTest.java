package nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.layer3domain.DomainObject;

public class OneToOneOrManyFieldFactoryTest extends FieldFactoryTest {

	private PropertyFieldFactory fieldFactory;

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		fieldFactory = createOneToOneOrManyFieldFactory();
	}

	private PropertyFieldFactory createOneToOneOrManyFieldFactory() {
		return new OneToOneOrManyFieldFactory() {

			@Override
			public PropertyField create(PropertyFieldFactoryInfo makeInformation) {
				return null;
			}
		};
	}

	@Test
	public void testCanCreate_givenString_mustReturnFalse() {
		assertCanCreate(fieldFactory, DomainObject.GET_MY_TEXT, false);
	}

	@Test
	public void testCanCreate_givenDomainObject_mustReturnTrue() {
		assertCanCreate(fieldFactory, DomainObject.GET_MY_DOMAIN_OBJECT, true);
	}

}
