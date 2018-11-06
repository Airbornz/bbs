package com.airbornz.bbs.order;

public enum MenuItem {

	//Define items
	STEAK("Steak",16.99), 
	CHICKEN("Chicken",10.99), 
	SALMON("Salmon",11.99), 
	SCRAMBLED_EGGS("Scrambled Eggs",3.99), 
	HASH_BROWNS("Hash Brown", 1.99),
	ENGLISH_MUFFIN("English Muffin", 1.2), 
	FRENCH_TOAST("French Toast", 2.99),
	COFFEE("Coffee", 1.25);
	
	private double price;
	private String displayName;
	
	/**
	 * Construct an item 
	 * @param displayName The name that will be printed on the receipt.
	 * @param price The price of the item.
	 */
	private MenuItem(String displayName, double price) {
		this.displayName = displayName;
		this.price = price;
	}

	/**
	 * Get the price of the item.
	 * @return double price of the item.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Get the display name of the item.
	 * @return String the display name.
	 */
	public String getDisplayName() {
		return displayName;
	}
	
	public static MenuItem fromNumber(int number) {
		switch(number) {
		case 1: return MenuItem.STEAK;
		case 2: return MenuItem.CHICKEN;
		case 3: return MenuItem.SALMON;
		case 4: return MenuItem.SCRAMBLED_EGGS;
		case 5: return MenuItem.HASH_BROWNS;
		case 6: return MenuItem.ENGLISH_MUFFIN;
		case 7: return MenuItem.FRENCH_TOAST;
		case 8: return MenuItem.COFFEE;
		default: return null;
		}
	}
	
}
