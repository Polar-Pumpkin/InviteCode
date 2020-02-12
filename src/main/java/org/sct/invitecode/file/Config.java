package org.sct.invitecode.file;

import org.bukkit.configuration.file.FileConfiguration;
import org.sct.invitecode.InviteCode;
import org.sct.invitecode.enumeration.ConfigType;

import java.util.List;

public class Config {

    private static FileConfiguration config = InviteCode.getInstance().getConfig();

    public static void loadConfig() {
        config = InviteCode.getInstance().getConfig();
    }

    public static String getString(ConfigType configType) {
        return config.getString(configType.getPath());
    }

    public static int getInt(ConfigType configType) {
        return config.getInt((configType.getPath()));
    }

    public static List<Integer> getIntList(ConfigType configType) {
        return config.getIntegerList(configType.getPath());
    }

    public static boolean getBoolean(ConfigType configType) {
        return config.getBoolean(configType.getPath());
    }

    public static List<String> getStringList(ConfigType configType) {
        return config.getStringList(configType.getPath());
    }


    public static List<Integer> getRewardList(int times) {
        return config.getIntegerList(ConfigType.TIMES.TimesPath(times) + ".Reward");
    }

    public static int getRewardMoney(int times) {
        return config.getInt(ConfigType.TIMES.TimesPath(times) + ".Money");
    }

    public static int getRewardPoints(int times) {
        return config.getInt(ConfigType.TIMES.TimesPath(times) + ".Points");
    }
}

