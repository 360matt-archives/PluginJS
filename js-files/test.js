registerCommand({
	name: "hey",

	exec: (sender, args) => {
		sender.sendMessage("§eOn va envoyer un message à: §6" + args[0]);

		player = getPlayer(args[0]);
		if (player != null) {
			getPlayer(args[0]).sendMessage("§eBonsoir !!");
		}
	}	
})

