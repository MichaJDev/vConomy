package vConomy.Surface.Commands;

import vConomy.Main;
import vConomy.Data.Configuration.Config;
import vConomy.Data.Configuration.Writers.Writer;
import vConomy.Data.Models.Enums.LogType;
import vConomy.Surface.Commands.Wallet.WalletCmd;

@SuppressWarnings("unused")
public class CommandHandler {

	private Main main;
	private Config cfg;
	private Writer writer;
	private WalletCmd wCmd;

	public CommandHandler(Main main) {
		this.main = main;
		cfg = new Config(main);
		writer = cfg.getWriter();
		wCmd = new WalletCmd(main);
		writer.writeErrorLog("CommandHandler Setup");
		main.log(LogType.INFO, "Setup CommandHandler Succesfully!");
	}

	public WalletCmd getWalletCommand() {
		return wCmd;
	}

}
