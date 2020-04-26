package nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.layer3domain.AllFeatureDomainObject;

public class CheckBoxFieldFactoryTest extends FieldFactoryTest {

	private CheckBoxFieldFactory checkBoxFieldFactory;

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		checkBoxFieldFactory = createFieldFactory();
	}

	protected CheckBoxFieldFactory createFieldFactory() {
		return new CheckBoxFieldFactory() {
			@Override
			public PropertyField create(PropertyFieldFactoryInfo makeInformation) {
				return null;
			}
		};
	}

	@Test
	public void testCanCreate_forSimpleBooleanProperty_mustReturnTrue()
			throws NoSuchMethodException, SecurityException {
		assertCanCreate(checkBoxFieldFactory, AllFeatureDomainObject.IS_MY_PRIMITIVE_BOOLEAN, true);
	}

	@Test
	public void testCanCreate_forBooleanProperty_mustReturnTrue() throws NoSuchMethodException, SecurityException {
		assertCanCreate(checkBoxFieldFactory, AllFeatureDomainObject.IS_MY_BOOLEAN, true);
	}

	@Test
	public void testCanCreate_forStringProperty_mustReturnFalse() throws NoSuchMethodException, SecurityException {
		assertCanCreate(checkBoxFieldFactory, AllFeatureDomainObject.GET_MY_TEXT, false);
	}

}
