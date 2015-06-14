package nth.introspect.documentation;

import nth.introspect.application.IntrospectApplication;
import nth.introspect.container.impl.DomainContainer;
import nth.introspect.container.impl.ServiceContainer;
import nth.introspect.controller.userinterface.UserInterfaceController;
import nth.introspect.provider.Provider;

/**
 * <p>
 * {@link ServiceObject}s contain {@link ServiceObjectActionMethod}s that are
 * displayed and invoked by the {@link UserInterfaceController}.
 * {@link ServiceObjectActionMethod}s create or retrieve {@link DomainObject}s
 * where the user does not have an existing {@link DomainObject} to navigate
 * from.
 * </p>
 * <p>
 * The {@link ServiceLayer} needs to be kept thin (see Martin Fowlers article on
 * the <a href="http://martinfowler.com/bliki/AnemicDomainModel.html">Anemic
 * Domain Model</a> <a
 * href="http://en.wikipedia.org/wiki/Anti-pattern">anti-pattern</a>). Therefore
 * {@link ServiceObject}s should not contain business rules/logic or validation
 * rules/logic, but delegate work to collaborations of domain objects in the
 * next layer down. Business logic and validation logic should be placed in
 * {@link DomainObject}s as much as possible. If you put them in other places,
 * such as {@link ServiceObject}s, changes are that the business logic and
 * validation logic will be scattered over different locations, making extending
 * or de-buging code difficult and expensive.
 * </p>
 * <p>
 * A web shop example:
 * <ul>
 * <li>The {@link UserInterfaceController} class calls the
 * {@link ServiceObjectActionMethod} findProduct(searchCriteria) method on
 * {@link ServiceObject}: ProductService</li>
 * <li>This method will call the findProduct on {@link InfrastructureObject}:
 * ProductRepository</li>
 * <li>This method will return a list of {@link DomainObject}s that meet the
 * search criteria</li>
 * </ul>
 * </p>
 * <p>
 * {@link ServiceObject}s do not have state and therefore do not have properties
 * (no getter and setter methods). To me a {@link ServiceObject} with state is a
 * good indicator of a <a
 * href="http://en.wikipedia.org/wiki/Code_smell">code-smell</a>, which you can
 * solve by moving the {@link ServiceObjectActionMethod}s that share state
 * (fields) to an new or existing {@link DomainObject}.
 * </p>
 * <p>
 * Service objects contain:
 * <ul>
 * <li>{@link ServiceObjectActionMethod}s: that define user actions</li>
 * <li>Methods: that define {@link ObjectBehavior}</li>
 * <li>Annotations: that define {@link ObjectBehavior}</li>
 * </ul>
 * </p>
 * 
 * 
 * <h3>Naming</h3> {@link ServiceObject}s are normally named to the
 * {@link DomainObject}s that they service (e.g. CustomerService, OrderService,
 * etc).
 * 
 * <h3>Construction</h3>
 * <p>
 * The principle of �naked objects� is that any <a
 * href="http://en.wikipedia.org/wiki/Plain_Old_Java_Object">'Plain Old Java
 * Object' (POJO)</a> can function as a {@link ServiceObject}. In other words: a
 * service class does not have to inherit from any special class, nor implement
 * any particular interface, nor have any specific attributes.
 * </p>
 *
 * TODO: Service objects need to be registred to the
 * {@link IntrospectApplication#getServiceClasses()} method
 * 
 * <br>
 * {@link ServiceObject}s are created by the {@link ServiceContainer}. TODO how
 * objects are created with Dependency Injection by the
 * {@link IntrospectFramework}:<br>
 * 
 * {@link ServiceObject}s can have references to other objects (being other
 * {@link ServiceObject}s, {@link DomainObject}s, {@link InfrastructureObject}s
 * or {@link Provider} objects). In example: TODO: A Customer object needs a
 * references to a ShoppingCartFactory object. The Customer object can therefore
 * be created by the {@link DomainContainer} with Customer customer=(Customer)
 * domainContainer.getObject(Customer.class). The ShopingCart object will
 * automatically be injected as a constructor parameter of the Customer class.
 * In order to create {@link DomainObject}s using dependency injection you need
 * to:</li>
 * <ul>
 * <li>Add the reference object as a parameter in the constructor and link it to
 * a private field, so that it can be used throughout the class. TODO what
 * happens see above</li>
 * <li>Override the {@link IntrospectApplication#getDomainClasses()} method and
 * return a list of {@link DomainObject}s that need to be created using
 * Dependency Injection</li>
 * <li>The object that creates the Customer objects needs to have a reference to
 * the {@link DomainContainer}. A CustomerService object can get a reference to
 * the {@link DomainContainer} when it is created by the
 * {@link ServiceContainer} (which is done by the {@link IntrospectFramework})</li>
 * 
 * TODO Registering the service serves two purposes: � It makes the service's
 * actions available to the user � It instructs the Naked Objects framework to
 * inject that service into any domain object that needs access to it. See
 * Dependency Injection. A service may provide multiple methods any of which may
 * appear as user actions - following the same rules as for actions on a domain
 * object.
 * </ul>
 * </ul>
 * 
 * <h3>Presentation</h3> TODO menu items <br>
 * <h2>Action Methods</h2> {@insert ServiceObjectActionMethod}
 * 
 * @author Nils ten Hoeve
 */
public interface ServiceObject extends Documentation {

}
