package nth.introspect.junit;

import nth.introspect.junit.layer5provider.authorization.AcmeAuthorizationProviderTest;
import nth.introspect.junit.layer5provider.reflection.behavior.disabled.DisabledModelForActionMethodTest;
import nth.introspect.junit.layer5provider.reflection.behavior.disabled.DisabledModelForPropertiesTest;
import nth.introspect.junit.layer5provider.reflection.behavior.hidden.HiddenModelForActionMethodTest;
import nth.introspect.junit.layer5provider.reflection.behavior.hidden.HiddenModelForPropertiesTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AcmeAuthorizationProviderTest.class,
		DisabledModelForPropertiesTest.class,
		DisabledModelForActionMethodTest.class,
		HiddenModelForPropertiesTest.class,
		HiddenModelForActionMethodTest.class })
public class AllTests {

}