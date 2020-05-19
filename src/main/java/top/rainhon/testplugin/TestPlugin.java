package top.rainhon.testplugin;

import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import top.rainhon.testplugin.exception.InvalidConfigException;

import java.util.HashMap;
import java.util.Map;

public class TestPlugin extends JavaPlugin{

    public Map<String, Object> configMap = new HashMap<String, Object>();

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.validateConfig();
        //注册触发方式
        Trigger.registerTrigger((Trigger) this.configMap.get("trigger"), this);

    }

    private void validateConfig(){
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

        //判断配置触发方式是否合法
        String triggerString = this.getConfig().getString("trigger");
        if(triggerString == null){
            throw new InvalidConfigException("请设置触发方式");
        }

        Trigger trigger;
        try{
            trigger = Trigger.valueOf(triggerString.toUpperCase());
        }catch(Exception e){
            throw new InvalidConfigException("无效的触发方式");
        }

        this.configMap.put("trigger", trigger);
        this.configMap.put("targetMaterial", targetMaterial);
        this.configMap.put("command", this.getConfig().isString("command"));
    }
}
