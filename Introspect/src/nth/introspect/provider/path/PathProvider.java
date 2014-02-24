package nth.introspect.provider.path;

import java.net.URI;

import nth.introspect.provider.Provider;

public interface PathProvider extends Provider{

	URI getRootPath();

	URI getConfigPath();
	
	URI getConfigPath(String relativePath);

	URI getDocumenPath();
	
	URI getDocumentPath(String relativePath);

	URI getImagePath();
	
	URI getImagePath(CharSequence identifier);
	
	boolean uriExists(URI uri);

	
}