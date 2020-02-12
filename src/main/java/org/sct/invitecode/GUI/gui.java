package org.sct.invitecode.GUI;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.sct.invitecode.InviteCode;
import org.sct.invitecode.enumeration.ConfigType;
import org.sct.invitecode.file.Config;
import org.sct.invitecode.file.Items;
import java.util.HashMap;
import java.util.Map;

public class gui {
    private static int slot;
    private Map<Integer,Integer> slot_item = new HashMap<>();

    public Inventory setGUI() {
        //todo 修改GUI
        slot = 0;
        Inventory inv = Bukkit.createInventory(null,1 * 9,"§bRewardList");
        for (int reward : Config.getIntList(ConfigType.REWARD)) {
            if (slot<=8) {
                /*获取config内设置的奖励物品*/
                ItemStack item = Items.getItemStack(reward);

                /*存入栏位-物品*/
                slot_item.put(slot,reward);
                inv.setItem(slot,item);
            }
            slot++;
        }
        return inv;
    }

    public Map gethashmap() {
        return slot_item;
    }


}
