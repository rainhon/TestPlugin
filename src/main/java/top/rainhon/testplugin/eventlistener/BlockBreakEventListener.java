package top.rainhon.testplugin.eventlistener;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandException;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import top.rainhon.testplugin.TestPlugin;
import top.rainhon.testplugin.util.Command;

public class BlockBreakEventListener implements Listener{

    private TestPlugin plugin;

    public BlockBreakEventListener(TestPlugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if(event.getBlock().getType() == this.plugin.configMap.get("targetMaterial")){
            Player player = event.getPlayer();
            try{
                if(! (player instanceof Player)){
                    return;
                }
                Command.run(player, (String)this.plugin.configMap.get("command"));
            }catch(CommandException e) {
                Bukkit.getLogger().info("执行命令出错:\n"+e.getLocalizedMessage());
            }
        }
    }
}
