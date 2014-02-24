package nth.introspect.ui.commandline.domain.command;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nth.introspect.Introspect;
import nth.introspect.provider.domain.DomainProvider;
import nth.introspect.provider.domain.info.method.MethodInfo;

public class CommandService {

	
	private static Map<Class<?>, String> types;

	public static Command findCommand(List<Command> commands, String[] arguments) throws IntrospectCommandLineException {
		if (arguments.length < 1) {
			return null;
		}
		String argument = arguments[0].trim().toLowerCase();
		for (Command command : commands) {
			String commandName = command.getName().trim().toLowerCase();
			if (argument.equals(commandName)) {
				return command;
			}
		}
		return null;
	}

	public static List<Command> getCommands() throws IntrospectCommandLineException {
		DomainProvider domainProvider = Introspect.getDomainProvider();

		List<Object> serviceObjects = domainProvider.getServiceObjects();

		if (serviceObjects.size() == 0) {
			throw new IntrospectCommandLineException("No service objects.");
		}

		boolean shortCommandName = serviceObjects.size() == 1;

		List<Command> commands = new ArrayList<Command>();

		for (Object serviceObject : serviceObjects) {
			Class<? extends Object> serviceClass = serviceObject.getClass();
			
			List<MethodInfo> methodInfos = domainProvider.getMethodInfos(serviceClass );

			for (MethodInfo methodInfo : methodInfos) {
				Command command = new Command(serviceObject, methodInfo, shortCommandName);
				commands.add(command);
			}
		}

		if (commands.size() == 0) {
			throw new IntrospectCommandLineException("No service objects with public visible methods.");
		}

		return commands;
	}


	public static Map<Class<?>, String> getSupportedParameterPropertyTypes() {
		if (types == null) {
			types = new HashMap<Class<?>, String>();
			types.put(Boolean.class, Boolean.FALSE + ".." + Boolean.TRUE);
			types.put(Byte.class, Byte.MIN_VALUE + ".." + Byte.MAX_VALUE);
			types.put(Short.class, Short.MIN_VALUE + ".." + Short.MAX_VALUE);
			types.put(Integer.class, Integer.MIN_VALUE + ".." + Integer.MAX_VALUE);
			types.put(Long.class, Long.MIN_VALUE + ".." + Long.MAX_VALUE);
			types.put(Float.class, Float.MIN_VALUE + ".." + Float.MAX_VALUE);
			types.put(Double.class, Double.MIN_VALUE + ".." + Double.MAX_VALUE);
			types.put(Character.class, Character.class.getName());
			types.put(String.class, "Text");
			types.put(File.class, "Path");
			types.put(Date.class, "Date and or Time");
			//TODO enum!
		}
		return types;
	}



}