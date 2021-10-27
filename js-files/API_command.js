commands = {

};

function registerCommand (command) {
    if (command == null)
        return;
    if (command.name == null || command.exec == null) 
        return;
    commands[command.name] = command;

    if (command.aliases == null)
        return
    for (aliase in command.aliases) {
        commands[aliase] = command;
    }
}

function isRegisteredCommand (name) {
    return name in commands;
}

function onCommand (name, javaSender, args) {
    if (name in commands) {
        commands[name].exec(javaSender, args)
        return true;
    }
    return false;
}