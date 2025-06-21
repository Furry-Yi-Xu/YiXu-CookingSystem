package com.yixu.Model;

public class SlotAndAmountModel {

    private final int slot;
    private final int amount;

    public SlotAndAmountModel(int slot, int amount) {
        this.slot = slot;
        this.amount = amount;
    }

    public int getSlot() {
        return slot;
    }

    public int getAmount() {
        return amount;
    }
}
