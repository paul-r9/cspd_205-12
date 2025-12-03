package com.rocketnine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GildedRose_100PerCentCoverageTest {

    public static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";

    private Item[] createItemArray(String itemName, int sellIn, int quality) {
        return new Item[] { new Item(itemName, sellIn, quality) };
    }

    // This one test brings the updateQualty method to 46% coverage
    @Test
    void AgedBrie_QualityIncreases_TwiceAsFastAfterSellIn() {
        GildedRose sut = new GildedRose(createItemArray("Aged Brie", 0, 30));
        sut.updateQuality();
        assertEquals(32, sut.items[0].quality, "Aged Brie increases quality with age");
    }

    // This additional test increase coverage to 75%
    @Test
    void AnythingBesidesBrieOrPass_QualityDecreasesTwiceAsFastAfterSellIn() {
        GildedRose sut = new GildedRose(
                createItemArray("not brie or backstage pass or legnadary", 0, 10));
        sut.updateQuality();
        assertEquals(8, sut.items[0].quality, "generic items decrease quality twice as fast after sell in");
    }

    // This third test yields 100% coverage
    @Test
    void BackstagePass_PastTheConcertDate_QualityReducesToZero() {
        GildedRose sut = new GildedRose(createItemArray(BACKSTAGE_PASS, 0, 10));
        sut.updateQuality();
        assertEquals(0, sut.items[0].quality, "backstage passes are worthless after the concert date");
    }

    @Test
    void BackstagePass_NearConcertDate_QualityIncreasesFaster() {
        GildedRose sut = new GildedRose(createItemArray(BACKSTAGE_PASS, 5, 20));
        sut.updateQuality();
        assertEquals(23, sut.items[0].quality);
    }

}
