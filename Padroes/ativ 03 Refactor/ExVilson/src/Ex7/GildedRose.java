package Ex7;



public class GildedRose {
	Item[] items;

	
	public GildedRose(Item[] items) {
		this.items = items;
	}

	// ---------------------------------------------------------------------------------------
	public void updateQuality() {
		for (int i = 0; i < items.length; i++) {

			UpdateQualityForItemsThatAgeWell(items[i]);

			if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
				items[i].sellIn = items[i].sellIn - 1;
			}
			
			UpdateQualityForExpiredItems(items[i]);
			
		}
	}

	private void decrementQuality(Item item) {
		item.quality -= 1;
	}

	private void incrementQuality(Item item) {
		item.quality += 1;
	}

	private void UpdateQualityForItemsThatAgeWell(Item item) {
		if (!item.name.equals("Aged Brie") && !item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
			if (item.quality > 0) {
				if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
					decrementQuality(item);
				}
			}
		} else {
			if (item.quality < 50) {
				incrementQuality(item);

				if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
					if (item.sellIn < 11) {
						if (item.quality < 50) {
							incrementQuality(item);
						}
					}

					if (item.sellIn < 6) {
						if (item.quality < 50) {
							incrementQuality(item);
						}
					}
				}
			}
		}

	}
	
	private void UpdateQualityForExpiredItems(Item item) {
		if (item.sellIn < 0) {
			if (!item.name.equals("Aged Brie")) {
				if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
					if (item.quality > 0) {
						if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
							decrementQuality(item);
						}
					}
				} else {
					item.quality = item.quality - item.quality;
				}
			} else {
				if (item.quality < 50) {
					incrementQuality(item);
				}
			}
		}
	}
}