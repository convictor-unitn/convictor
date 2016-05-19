package me.peserico.javaweb.templateapp.utils;

import java.util.Objects;

/**
 * This class represents a Route: a route is a relative path referenced to the app
 * excluding the context.
 * A route is mapped to an action of a controller with a format.
 * If the format is not specified, it's set to html as default
 * @author umberto
 */
public class Route {

	private final String method;
	private final String url;
	private final String controllerName;
	private final String actionName;
	private final String format;

	/**
	 * This constructs a route with default format as html
	 * @param method is the http method to match
	 * @param url is the url to map
	 * @param controller is the controller to map to
	 * @param action is the action to map
	 */
	public Route(String method, String url, String controller, String action) {
		this.method = method;
		this.url = url;
		this.controllerName = controller;
		this.actionName = action;
		this.format = "html";
	}
	
	/**
	 * This constructs a route with custom format
	 * @param method is the http method to match
	 * @param url is the url to map
	 * @param controllerName is the controller to map to
	 * @param actionName is the action to map
	 * @param format is the format of this route
	 */
	public Route(String method, String url, String controllerName, String actionName, String format) {
		this.method = method;
		this.url = url;
		this.controllerName = controllerName;
		this.actionName = actionName;
		this.format = format;
	}

	/**
	 * Url getter
	 * @return url the url of this route
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * This method returns the http method of this route
	 * @return
	 */
	public String getMethod() {
		return method;
	}
	
	/**
	 * ControllerName getter
	 * @return controllerName the name of the controller of this route
	 */
	public String getControllerName() {
		return controllerName;
	}

	/**
	 * ActionName getter
	 * @return actionName the name of the action of this route
	 */
	public String getActionName() {
		return actionName;
	}

	/**
	 * format getter
	 * @return format the name of the format of this route
	 */
	public String getFormat() {
		return format;
	}
	
	@Override
	public String toString() {
		return "Route{" + "url=" + url + ", controller=" + controllerName + ", action=" + actionName + '}';
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 53 * hash + Objects.hashCode(this.url);
		hash = 53 * hash + Objects.hashCode(this.controllerName);
		hash = 53 * hash + Objects.hashCode(this.actionName);
		hash = 53 * hash + Objects.hashCode(this.format);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Route other = (Route) obj;
		if (!Objects.equals(this.url, other.url)) {
			return false;
		}
		if (!Objects.equals(this.controllerName, other.controllerName)) {
			return false;
		}
		if (!Objects.equals(this.actionName, other.actionName)) {
			return false;
		}
		if (!Objects.equals(this.format, other.format)) {
			return false;
		}
		return true;
	}
	
	
}
