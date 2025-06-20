package com.yixu.Model;

import java.util.List;

public class MaterialMatchResult {
    private final List<ItemSlotAndAmount> slots;
    private final int totalFound;
    private final boolean enough;

    public MaterialMatchResult(List<ItemSlotAndAmount> slots, int totalFound, int requiredAmount) {
        this.slots = slots;
        this.totalFound = totalFound;
        this.enough = totalFound >= requiredAmount;
    }

    public List<ItemSlotAndAmount> getSlots() {
        return slots;
    }

    public int getTotalFound() {
        return totalFound;
    }

    public boolean isEnough() {
        return enough;
    }

    public static class ItemSlotAndAmount {
        private final int slot;
        private final int amount;

        public ItemSlotAndAmount(int slot, int amount) {
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
}
