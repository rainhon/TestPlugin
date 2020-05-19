package top.rainhon.testplugin;

import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import top.rainhon.testplugin.eventlistener.BlockBreakEventListener;
import top.rainhon.testplugin.exception.InvalidConfigException;

public class TestPlugin extends JavaPlugin{

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        //判断设置目标方块类型是否合法
        if(!this.getConfig().isString("material")){
            throw new InvalidConfigException("无效方块id，请检查是否配置"+this.getConfig().getString("material"));
        }

        Material targetMaterial = Material.getMaterial(this.getConfig().getString("material"));

        if(targetMaterial == null || !targetMaterial.isBlock()){
            throw new InvalidConfigException("无效方块id，请检查配置是否正确");
        }

        //判断配置命令是否合法
        if(!this.getConfig().isString("command")){
            throw new InvalidConfigException("无效命令");
        }

        getServer().getPluginManager().registerEvents(new BlockBreakEventListener(this), this);
    }

}
