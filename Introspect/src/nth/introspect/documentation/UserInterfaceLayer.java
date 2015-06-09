package nth.introspect.documentation;

import nth.introspect.container.impl.UserInterfaceContainer;
import nth.introspect.controller.userinterface.UserInterfaceController;
/**
 * The {@link UserInterfaceLayer} is part if the {@link IntrospectArchitecture}. <br>
 * The {@link UserInterfaceLayer} is also know as presentation layer (but that wouldn't really do it justice)
 * The {@link UserInterfaceLayer} contains the {@link UserInterfaceController}, which is responsible for displaying and controlling the user interface<br>
 * The {@link UserInterfaceLayer} is implemented by the {@link UserInterfaceContainer}<br>
 *
 * Note that this layer is the top layer, which means it may know all the objects in the lower layers but not visa versa! See {@link IntrospectArchitecture}
 * 
 * <h2>UserInterfaceController</h2>
 * {@insert UserInterfaceController}
 * 
 * 
 * @author Nils ten Hoeve
 * 
 */
public interface UserInterfaceLayer extends Documentation{

}
