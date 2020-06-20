package vConomy.Surface.Commands;

import vConomy.Main;
import vConomy.Data.Configuration.Config;
import vConomy.Data.Configuration.Writers.Writer;

public class CommandHandler {

	private Main main;
	private Config cfg;
	private Writer writer;
	
	public CommandHandler(Main main) {
		this.main = main;
		cfg = new Config(main);
		writer = cfg.getWriter();
	}
	
}
