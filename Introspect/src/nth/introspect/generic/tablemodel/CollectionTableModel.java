package nth.introspect.generic.tablemodel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;

public class CollectionTableModel extends AbstractTableModel implements DomainTableModel {

	private static final long serialVersionUID = 1903326845833688508L;
	private List<PropertyInfo> propertyInfos;
	private List<Object> list;

	public CollectionTableModel(ReflectionProvider reflectionProvider, Collection<?> collection, Class<?> domainClass) {
		if (collection == null) {
			list = new ArrayList<Object>();
		} else {
			list = new ArrayList<Object>(collection);// convert collection to an array list to safegard the sequance of objects
		}
		propertyInfos = reflectionProvider.getOrderedAndVisiblePropertyInfos(domainClass);
	}

	@Override
	public int getColumnCount() {
		return propertyInfos.size();
	}

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object domainObject = list.get(rowIndex);
		PropertyInfo propertyInfo = propertyInfos.get(columnIndex);
		return propertyInfo.getFormat().equals(domainObject);
	}

	@Override
	public String getColumnName(int column) {
		PropertyInfo propertyInfo = propertyInfos.get(column);
		return propertyInfo.getDisplayName();
	}

	@Override
	public Object getDomainValue(int rowIndex) {
		return list.get(rowIndex);
	}


}