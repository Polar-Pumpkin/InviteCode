package org.sct.invitecode;

import lombok.Getter;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.sct.invitecode.data.InviteCodeData;
import org.sct.invitecode.commands.CommandHandler;
import org.sct.invitecode.file.*;
import org.sct.invitecode.listener.Register;
import org.sct.invitecode.util.JudgeDependencies;
import org.sct.invitecode.util.JudgeStorge;
import org.sct.plugincore.util.plugin.CheckUpdate;
import org.sct.plugincore.util.plugin.FileUpdate;

public class InviteCode extends JavaPlugin {
    public static InviteCode instance;
    private String storgetype;

    @Override
    public void onEnable() {
        instance = this;

        InviteCodeData.getPool().submit(() -> {
            FileUpdate.update(instance, "config.yml", getDataFolder().getPath());
            FileUpdate.update(instance, "lang.yml", getDataFolder().getPath());
            CheckUpdate.check(Bukkit.getConsoleSender(), instance);});
        Bukkit.getPluginCommand("InviteCode").setExecutor(new CommandHandler());

        if (Bukkit.getPluginManager().isPluginEnabled("Authme")) {
            Bukkit.getPluginManager().registerEvents(new Register(),this);
        }
        if (!initVault()) {
            getLogger().severe("Vault初始化失败,可能未安装Vault");
            getLogger().severe("或未使用相应的经济插件!");
            getLogger().severe("只能使用普通的物品奖励功能!");
        } else {
            getServer().getConsoleSender().sendMessage("§7[§eInviteCode§7]§2插件已挂钩Vault");
        }
        initialize();
        getServer().getConsoleSender().sendMessage("§7[§eInviteCode§7]§2插件已加载");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("§7[§eInviteCode§7]§c插件已卸载");
    }

    public void initialize() {
        /*初始化文件*/
        Items.loadItems();
        Code.loadCode();
        Lang.loadLang();
        Offline.loadOffline();
        Times.loadTimes();

        /*初始化config*/
        saveDefaultConfig();
        reloadConfig();

        /*判断数据储存方式*/
        JudgeStorge.whichStorge();

        /*判断依赖是否启用*/
        JudgeDependencies.useDenpendencies();

        /*存入离线玩家*/
        for (String list : Offline.getOfflinePlayer()) {
            InviteCodeData.getOfflinelist().add(list);
        }
    }

    private boolean initVault() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        InviteCodeData.setEcon(rsp.getProvider());
        return InviteCodeData.getEcon() != null;
    }

    public static InviteCode getInstance() {
        return instance;
    }
}
