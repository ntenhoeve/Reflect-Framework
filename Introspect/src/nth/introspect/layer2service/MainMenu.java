package nth.introspect.layer2service;

import nth.introspect.documentation.Documentation;
import nth.introspect.layer3domain.DomainObject;
import nth.introspect.layer3domain.DomainObjectMenu;
import nth.introspect.layer5provider.reflection.behavior.parameterfactory.ParameterFactory;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * TODO PICTURE MAIN MENU
 * <p>
 * The {@link MainMenu} is displayed on a panel on the left hand side of the user
 * interface (sometimes this panel is hidden but it can always be made visible
 * again using the main toolbar. The main menu provides the user access to the
 * {@link DomainObject}s so that the user can work on them. The {@link MainMenu} contains all
 * {@link ActionMethod}s of {@link ServiceObject}s that either have no method parameter or have
 * an {@link ParameterFactory}.
 * </p>
 * <p>
 * {@link ActionMethod}s of {@link ServiceObject}s that take a {@link DomainObject} as a parameter and
 * have no {@link ParameterFactory} are displayed in {@link DomainObjectMenu}s and
 * propertyMenus.
 * </p>
 * Each {@link ServiceObject} is always displayed as a sub menu
 * 
 * @author nilsth
 *
 */
public interface MainMenu extends Documentation {

}