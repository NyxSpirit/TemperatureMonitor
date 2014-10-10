package se.anviken.temperature.monitor.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
	/**
	 * The client side stub for the RPC service.
	 */
	@RemoteServiceRelativePath("graph")
public interface GetGraphDataService extends RemoteService {
		String getData(String input) throws IllegalArgumentException;
}
