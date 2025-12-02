package com.rocketnine;

import org.approvaltests.combinations.CombinationApprovals;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

//Uncomment the following line to use DiffMerge when Received/Approved differ
//@UseReporter(DiffMergeReporter.class)
class GildedRoseApprovalTests {

    @Test
    @Disabled
    void test_UpdateQuality_SingleItem() {
        CombinationApprovals.verifyAllCombinations(
                this::doUpdateQuality,
                new String[] {"foo", "Aged Brie", "Backstage passes to a TAFKAL80ETC concert", "Sulfuras, Hand of Ragnaros"},
                new Integer[] { 0 },  // SellIn
                new Integer[] { 10 }  // Quality
        );
    }

    private String doUpdateQuality(String name, int sellIn, int quality) {
        Item[] items = new Item[]{new Item(name, sellIn, quality)};
        GildedRose sut = new GildedRose(items);
        sut.updateQuality();
        return sut.items[0].toString();
    }
}
