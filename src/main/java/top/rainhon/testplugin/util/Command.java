package top.rainhon.testplugin.util;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;

public class Command {
    public static void run(CommandSender commandSender, String command){
        try{
            Bukkit.getServer().dispatchCommand(commandSender, command);
        }catch(CommandException e) {
            Bukkit.getLogger().info("执行命令出错:\n"+e.getLocalizedMessage());
        }
    }
}
