package top.rainhon.testplugin;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import top.rainhon.testplugin.eventlistener.BlockBreakEventListener;
import top.rainhon.testplugin.eventlistener.PlayerInteractListener;


public enum Trigger {
    BREAK, RIGHT_CLICK, LEFT_CLICK;
    public static void registerTrigger(Trigger trigger, TestPlugin plugin){
        Listener listener;
        switch(trigger){
            case BREAK:
                listener = new BlockBreakEventListener(plugin);
                break;
            case RIGHT_CLICK:
            case LEFT_CLICK:
                listener = new PlayerInteractListener(plugin, trigger);
                break;
                default:return;
        }
        Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
    }
}
