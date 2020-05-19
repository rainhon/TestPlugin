package top.rainhon.testplugin.eventlistener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
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

    /**
     * 玩家点击触发
     * 右击会主手副手各触发一次，所有右击时要判断触发的hand
     * @param event
     */
    @EventHandler
    public void onPlyaerInteract(PlayerInteractEvent event) {
        if(event.getAction() != this.targetAction) return;
        if(this.targetAction == Action.RIGHT_CLICK_BLOCK && event.getHand() == EquipmentSlot.HAND)return;
        if(event.getClickedBlock().getType() != this.plugin.configMap.get("targetMaterial")) return;

        Command.run(event.getPlayer(), (String)this.plugin.configMap.get("command"));
    }
}
