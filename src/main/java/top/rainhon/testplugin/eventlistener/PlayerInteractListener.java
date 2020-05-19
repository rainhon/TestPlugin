package top.rainhon.testplugin.eventlistener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import top.rainhon.testplugin.TestPlugin;
import top.rainhon.testplugin.Trigger;
import top.rainhon.testplugin.util.Command;

public class PlayerInteractListener implements Listener{

    private TestPlugin plugin;
    private Action targetAction;

    public PlayerInteractListener(TestPlugin plugin, Trigger trigger){
        this.plugin = plugin;
        if(trigger == Trigger.LEFT_CLICK) this.targetAction = Action.LEFT_CLICK_BLOCK;
        if(trigger == Trigger.RIGHT_CLICK) this.targetAction = Action.RIGHT_CLICK_BLOCK;
    }

    @EventHandler
    public void onPlyaerInteract(PlayerInteractEvent event) {
        
        if(event.getAction() != this.targetAction) return;

        if(event.getClickedBlock().getType() != this.plugin.configMap.get("Material")) return;

        Command.run(event.getPlayer(), (String)this.plugin.configMap.get("command"));
    }
}
