package top.rainhon.testplugin.exception;
import org.bukkit.command.CommandException;

public class InvalidConfigException extends CommandException{
    public InvalidConfigException(java.lang.String msg){
        super(msg);
    }
}
