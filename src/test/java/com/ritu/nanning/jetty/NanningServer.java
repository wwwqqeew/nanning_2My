package com.ritu.nanning.jetty;
import org.eclipse.jetty.server.Server;

/**
 * logout
 * @author Joe
 * 2015-11-26 要更新 TrackClient
 */
public class NanningServer {
	public static final int PORT = 8099;
	public static final String CONTEXT = "/";
	public static final String[] TLD_JAR_NAMES = new String[] { "sitemesh", "spring-webmvc", "shiro-web" };

	public static void main(String[] args) throws Exception {
		// 设定Spring的profile com.ritu.trafficlight.entity.Linkcross TRFF_ROLE 
		System.setProperty("spring.profiles.active", "development");
		System.setProperty("orpiler.disablejsr199", "true");
		// 启动Jetty 当前个数
		Server server = JettyFactory.createServerInSource(PORT, CONTEXT);
		JettyFactory.setTldJarNames(server, TLD_JAR_NAMES);

		try {
			server.start();
			System.out.println("Server running at http://localhost:" + PORT + CONTEXT);
			System.out.println("Hit Enter to reload the application quickly");
			// 等待用户输入回车重载应用.
			while (true) {
				char c = (char) System.in.read();
				if (c == '\n') {
					JettyFactory.reloadContext(server);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

}
