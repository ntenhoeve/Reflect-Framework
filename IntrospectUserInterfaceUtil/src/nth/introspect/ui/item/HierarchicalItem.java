package nth.introspect.ui.item;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import nth.introspect.Introspect;
import nth.introspect.provider.language.LanguageProvider;
import nth.introspect.provider.userinterface.item.Item;

public class HierarchicalItem extends Item {

	private List<Item> children = new ArrayList<Item>();
	private HierarchicalItem parent;

	public HierarchicalItem() {
	}

	/**
	 * Constructs a new menu item that can optionally have an icon and a action
	 * associated with it. Icon and action can be null, but a caption must be
	 * given.
	 * 
	 * @param text
	 *            The text associated with the action
	 * @param action
	 *            The action to be fired
	 */
	public HierarchicalItem(String text, URI iconURI, Action action) {

		if (text == null) {
			throw new IllegalArgumentException("caption cannot be null");
		}
		setText(text);
		setDescription(text);
		setIconURI(iconURI);
		setAction(action);
	}

	public HierarchicalItem(String text, Action action) {
		if (text == null) {
			throw new IllegalArgumentException("caption cannot be null");
		}
		setText(text);
		setDescription(text);
		setAction(action);
		LanguageProvider languageProvider = Introspect.getLanguageProvider();
		String iconName = languageProvider.getDefaultValue(text);
		setIconURI(Introspect.getPathProvider().getImagePath(iconName));
	}

	/**
	 * Checks if the item has children (if it is a sub-menu).
	 * 
	 * @return True if this item has children
	 */
	public boolean hasChildren() {
		return !isSeparator() && children.size() > 0;
	}

	/**
	 * For the containing item. This will return null if the item is in the
	 * top-level menu bar.
	 * 
	 * @return The containing {@link nth.introspect.ui.item.HierarchicalItem} ,
	 *         or null if there is none
	 */
	public HierarchicalItem getParent() {
		return parent;
	}

	/**
	 * This will return the children of this item or null if there are none.
	 * 
	 * @return List of children items, or null if there are none
	 */
	public List<Item> getChildren() {
		return children;
	}

	/**
	 * Returns the number of children.
	 * 
	 * @return The number of child items
	 */
	public int getSize() {
		if (children != null) {
			return children.size();
		}
		return -1;
	}

	/**
	 * Set the parent of this item. This is called by the addItem method.
	 * 
	 * @param parent
	 *            The parent item
	 */
	public void setParent(HierarchicalItem parent) {
		this.parent = parent;
	}

	public void addItem(int index, Item menuItem) {
		children.add(index, menuItem);
	}

	public void addItem(Item menuItem) {
		children.add(menuItem);
	}

	/**
	 * Adds a separator to this menu. A separator is a way to visually group
	 * items in a menu, to make it easier for users to find what they are
	 * looking for in the menu.
	 * 
	 */
	public HierarchicalItem addSeparator() {
		HierarchicalItem item = createSeperator();
		addItem(createSeperator());
		return item;
	}

	public HierarchicalItem addSeparator(int index) {
		HierarchicalItem item = createSeperator();
		addItem(index, item);
		return item;
	}

	private static HierarchicalItem createSeperator() {
		HierarchicalItem item = new HierarchicalItem();
		item.setSeparator(true);
		item.setText("");
		return item;
	}

	public String getTextPath(String seperator) {
		String textPath = "";
		if (parent != null) {
			textPath = parent.getTextPath(seperator);
			textPath += seperator;
		}
		textPath += getText();
		return textPath;
	}

}
