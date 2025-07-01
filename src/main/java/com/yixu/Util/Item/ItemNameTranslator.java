package com.yixu.Util.Item;

import com.yixu.Config.ConfigManager;
import org.bukkit.inventory.ItemStack;

public class ItemNameTranslator {

    public static String getItemName(ItemStack itemStack) {
        return ConfigManager.getItemJson().getJsonValue(itemStack.getType().toString());
    }

}

