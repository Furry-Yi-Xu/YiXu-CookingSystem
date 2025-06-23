package com.yixu.Config.CookingConfig;

import com.yixu.Config.ConfigManager;

public class CookingGUIConfig {
    private static final String Button_PATH = "cooking_gui_button.";

    public static String getButtonType(String buttonName) {
        return ConfigManager.getGuiConfig().getConfig().getString(Button_PATH + buttonName + ".type");
    }

    public static String getButtonMaterial(String buttonName) {
        return ConfigManager.getGuiConfig().getConfig().getString(Button_PATH + buttonName + ".material");
    }

    public static int getButtonSlot(String buttonName) {
        return ConfigManager.getGuiConfig().getConfig().getInt(Button_PATH + buttonName + ".slot");
    }

    public static int getBookSlot() {
        return ConfigManager.getGuiConfig().getConfig().getInt("cooking_book_slot");
    }

}
