package nth.introspect.provider.userinterface;

import java.net.URI;

import nth.introspect.provider.Provider;
import nth.introspect.provider.domain.info.method.MethodInfo;
import nth.introspect.provider.userinterface.view.ViewContainer;

public interface UserInterfaceProvider<T> extends Provider{

	public void showErrorDialog(String title, String message, Throwable throwable);

	public void showInfoDialog(String title, String message);

	public void showProgressDialog(String taskDescription, int currentValue, int maxValue);//TODO refactor parameters to: taskName, int percentageCompleted

	public void closeProgressDialog();//TODO remove. progress dialog should close automatically when percentageCompleted=100

	public void openURI(URI uri);
	
	public void downloadFile(DownloadStream downloadStream);

	@SuppressWarnings("rawtypes")
	public ViewContainer getViewContainer();

	/**
	 * This method is called from a {@link MethodItem} and starts the process of invoking a method
	 * 
	 * @param serviceObject
	 * @param methodInfo
	 * @param methodParameterValue
	 */

	public void startExecution(Object serviceObject, MethodInfo methodInfo, Object methodParameterValue);

	/**
	 * This method is called from {@link #startExecution(Object, MethodInfo, Object)} or from the {@link FormOkItem} linked to the OK button <br>
	 * It needs the check if the method is enabled before the method is executed<br>
	 * It needs to validate the method parameter value before the method is executed
	 * 
	 * @param serviceObject
	 * @param methodInfo
	 * @param methodParameterValue
	 */

	void excuteMethod(Object serviceObject, MethodInfo methodInfo, Object methodParameterValue);

	/**
	 * This method is called from {@link #startMethodExecutionThread(Object, MethodInfo, Object)} when the thread is completed.<br>
	 * It will open a new view or InfoDialog to show the method return value
	 * 
	 * @param serviceObject
	 * @param methodInfo
	 * @param methodParameterValue
	 * @param methodReturnValue
	 */

	void showMethodReturnValue(Object serviceObject, MethodInfo methodInfo, Object methodParameterValue, Object methodReturnValue);

}