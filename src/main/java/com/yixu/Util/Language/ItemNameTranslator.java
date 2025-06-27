package com.yixu.Util.Language;

import com.yixu.Config.ConfigManager;
import org.bukkit.inventory.ItemStack;

public class ItemNameTranslator {

    public static String getItemName(ItemStack itemStack) {
        String translationKey = itemStack.translationKey();
        return ConfigManager.getItemJson().getJsonValue(translationKey);
    }

}

