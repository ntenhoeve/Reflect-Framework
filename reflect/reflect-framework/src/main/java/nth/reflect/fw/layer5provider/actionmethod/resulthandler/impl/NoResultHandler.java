package nth.reflect.fw.layer5provider.actionmethod.resulthandler.impl;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.actionmethod.resulthandler.ActionMethodResultHandler;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

/**
 * Shows a message when a {@link ActionMethod} without a return value (Void) is
 * executed
 * 
 * @author nilsth
 *
 */
public class NoResultHandler implements ActionMethodResultHandler {

	public static final TranslatableString MESSAGE = new TranslatableString(
			NoResultHandler.class.getCanonicalName() + ".message", "%s was successfully executed.");

	@Override
	public boolean canProcess(ProviderContainer container, ActionMethodInfo actionMethodInfo) {
		return !actionMethodInfo.hasReturnValue();
	}

	@Override
	public void process(UserInterfaceContainer container, ActionMethodInfo methodInfo, Object methodOwner,
			Object methodParameter, Object methodResult) {
		TranslatableString actionMethodTitle = methodInfo.getTitle(methodParameter);
		TranslatableString message = MESSAGE.withParameters(actionMethodTitle);
		UserInterfaceController userInterface = container.get(UserInterfaceController.class);
		userInterface.showMessage(message);
	}

}
