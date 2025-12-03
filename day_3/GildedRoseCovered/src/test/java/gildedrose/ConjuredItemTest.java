package gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConjuredItemTest {

    private Item[] createItemArray(String itemName, int sellIn, int quality) {
        return new Item[] { new Item(itemName, sellIn, quality) };
    }

    // This one test brings the updateQualty method to 46% coverage
    @Test
    void ConjuredItem_Before_SellIn() {
        GildedRose sut = new GildedRose(createItemArray("Conjured Mana Bun", 10, 5));
        sut.updateQuality();
        assertEquals(3, sut.items[0].quality);
    }

    @Test
    void ConjuredItem_AfterSellIn_QualiotyDecreasesTwiceAsFast() {
        GildedRose sut = new GildedRose(createItemArray("Conjured Mana Bun", 0, 10));
        sut.updateQuality();
        assertEquals(6, sut.items[0].quality);
    }

}
