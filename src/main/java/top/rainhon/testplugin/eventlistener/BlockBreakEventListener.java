package top.rainhon.testplugin.eventlistener;

import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.command.CommandException;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockBreakEventListener implements Listener{

    private Material targetMaterial;
    private String command;
    private JavaPlugin plugin;
    private Server server;

    public BlockBreakEventListener(JavaPlugin plugin){
        this.plugin = plugin;
        this.targetMaterial = Material.getMaterial(plugin.getConfig().getString("material"));
        this.command = plugin.getConfig().getString("command");
        this.server = plugin.getServer();
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if(event.getBlock().getType() == this.targetMaterial){
            try{
                if(! (event.getPlayer() instanceof Player)){
                    return;
                }
                this.server.dispatchCommand(event.getPlayer(), this.command);

            }catch(CommandException e) {
                this.plugin.getLogger().info("执行命令出错:\n"+e.getLocalizedMessage());
            }
        }
    }
}
