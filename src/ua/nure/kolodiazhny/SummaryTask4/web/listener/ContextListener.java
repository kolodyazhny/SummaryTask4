package ua.nure.kolodiazhny.SummaryTask4.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Context Listener that initializes Log4j and CommandContainer.
 *
 * @author B.kolodiazhny
 */
@WebListener
public class ContextListener implements ServletContextListener {

	public static final Logger LOG = Logger.getLogger(ContextListener.class);

	public void contextDestroyed(ServletContextEvent sce) {
		// no operations
	}

	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		initLogging(context);
		initCommandContainer();
	}

	/**
	 * Initializes Apache Log4j.
	 */
	private void initLogging(ServletContext context) {
		try {
			PropertyConfigurator.configure(context.getRealPath("WEB-INF/log4j.properties"));
			LOG.debug("Log4j has been initialized");
		} catch (Exception e) {
			System.err.println("Can't configurate Log4j!");
			e.printStackTrace();
		}
	}

	/**
	 * Initializes CommandContainer class.
	 */
	private void initCommandContainer() {
		try {
			Class.forName("ua.nure.kolodiazhny.SummaryTask4.web.command.CommandContainer");
			LOG.debug("CommandContainer has been initialized");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("Cannot initialize Command Container");
		}
	}

}
