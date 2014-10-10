package se.anviken.temperature.monitor.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface QueryService extends RemoteService {
	String getTable(String input) throws IllegalArgumentException;
}
