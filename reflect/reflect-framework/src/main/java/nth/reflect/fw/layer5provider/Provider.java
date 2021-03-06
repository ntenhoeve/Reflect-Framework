package nth.reflect.fw.layer5provider;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.container.ConstructionInjection;
import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer2service.ServiceObject;
import nth.reflect.fw.layer5provider.authorization.AuthorizationProvider;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.notification.NotificationProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.validation.ValidationProvider;
import nth.reflect.fw.layer5provider.url.UrlProvider;
import nth.reflect.fw.layer5provider.version.VersionProvider;

/**
 * {@link Provider}s are like {@link ServiceObject}s, but they provide access to
 * objects that provide information for the {@link ReflectFramework}, such as:
 * <ul>
 * <li>Authorization (see {@link AuthorizationProvider})</li>
 * <li>Validation (see {@link ValidationProvider})</li>
 * <li>Multiple languages (see {@link LanguageProvider})</li>
 * <li>Notifications (see {@link NotificationProvider})</li>
 * <li>URL information (see {@link UrlProvider})</li>
 * <li>Object meta information (see {@link ReflectionProvider})</li>
 * <li>Version information (see {@link VersionProvider})</li>
 * </ul>
 * <p>
 * {@link Provider}s may be used by any class within an application.
 * </p>
 * <p>
 * {@link Provider}s have a default implementation and each
 * {@link ReflectApplication} can use different implementations of these
 * {@link Provider}s where needed. For more information see the
 * {@link ReflectApplication}.get...Service()} methods
 * </p>
 * <p>
 * You can easily change or add to the {@link ReflectFramework} functionality by
 * implementing or overriding the existing {@link Provider}s.
 * </p>
 * 
 * <h3>Reflect Service Object Construction</h3>
 * <p>
 * {@link Provider}'s are instantiated by the {@link ProviderContainer} (see
 * {@link DependencyInjectionContainer}) {@link Provider}s can have references
 * to other {@link Provider}s. These objects are injected into the
 * {@link Provider}s (see the {@link ConstructionInjection} section.
 * </p>
 * <h3>Provider Presentation</h3>
 * <p>
 * The methods of {@link Provider}s are not displayed by the
 * {@link UserInterfaceController}.
 * </p>
 * 
 * <h2>Authorization Provider</h2> {@insert AuthorizationProvider}
 * <p>
 * <h2>Validation Provider</h2> {@insert ValidationProvider}
 * <p>
 * <h2>Language Provider</h2> {@insert LanguageProvider}
 * <p>
 * <h2>Notification Provider</h2> {@insert NotificationProvider}
 * <p>
 * <h2>Reflection Provider</h2> {@insert ReflectionProvider}
 * <p>
 * <h2>Version Provider</h2> {@insert VersionProvider}
 * <p>
 * <h2>URL Provider</h2> {@insert UrlProvider}
 * <p>
 * <h2>String Converter Provider</h2> {@insert StringConverterProvider}
 * <p>
 * <h2>Property Field Provider</h2> {@insert PropertyFieldProvider}
 * <p>
 * <h2>Property Action Method Execution Provider</h2>
 * {@insert ActionMethodExecutionProvider}
 * <p>
 * <h2>Property Action Method Pre-Handler Provider</h2>
 * {@insert ActionMethodPreHandlerProvider}
 * <p>
 * <h2>Property Action Method Result Handler Provider</h2>
 * {@insert ActionMethodResultHandlerProvider}
 * <p>
 * 
 * @author Nils ten Hoeve
 */
public interface Provider {

}
