package nth.reflect.fw.ui.item.method;

import java.net.URL;
import java.util.List;
import java.util.function.Predicate;

import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.filter.LinkedToPropertyFilter;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.filter.ParameterTypeFilter;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;
import nth.reflect.fw.ui.item.HierarchicalItem;
import nth.reflect.fw.ui.view.FormView;

public class PropertyMethodOwnerItem extends HierarchicalItem {

	private FormView formView;

	public PropertyMethodOwnerItem (FormView formView, ReadOnlyValueModel parameterValueModel) {
		this(formView, parameterValueModel,null);
	}

	public PropertyMethodOwnerItem(FormView formView,
			ReadOnlyValueModel parameterValueModel,
			PropertyInfo propertyToExclude) {
		super(formView.getUserInterfaceContainer().get(LanguageProvider.class));
		this.formView = formView;
		pupulateChildren(formView, parameterValueModel, propertyToExclude);
	}

	public void pupulateChildren(FormView formView,
			ReadOnlyValueModel parameterValueModel,
			PropertyInfo propertyInfo) {
		ReadOnlyValueModel domainValueModel = formView.getDomainValueModel();
		Class<?> domainClass = domainValueModel.getValueType();
		Class<?> parameterClass = parameterValueModel.getValueType();

		ReflectionProvider reflectionProvider=formView.getUserInterfaceContainer().get(ReflectionProvider.class);
		ClassInfo classInfo = reflectionProvider.getClassInfo(domainClass);
		List<PropertyInfo> propertyInfos = classInfo.getPropertyInfosSorted();
		for (PropertyInfo otherPropertyInfo : propertyInfos) {

			if (otherPropertyInfo != propertyInfo) {
				Predicate<ActionMethodInfo> filter=new LinkedToPropertyFilter(otherPropertyInfo).and(new ParameterTypeFilter(parameterClass));
				List<ActionMethodInfo> propertyMethods = classInfo.getActionMethodInfos(filter);
				for (ActionMethodInfo propertyMethodInfo : propertyMethods) {
					PropertyMethodItem propertyMethodItem = new PropertyMethodItem(
							formView, otherPropertyInfo, propertyMethodInfo,
							parameterValueModel, true);
					getChildren().add(propertyMethodItem);
				}
			}
		}
	}

	@Override
	public URL getIconURL() {
		return formView.getViewIconURL();
	}

	@Override
	public String getText() {
		// TODO return TitleUtil.createTitle(methodInfo, methodParameter,
		// false);
		return formView.getViewTitle();// using description instead of MaterialAppBarTitle
										// because the MaterialAppBarTitle could be truncated
	}

	@Override
	public boolean isVisible() {
		return hasChildren();
	}

	@Override
	public String getDescription() {
		return formView.getViewDescription();
	}

}
