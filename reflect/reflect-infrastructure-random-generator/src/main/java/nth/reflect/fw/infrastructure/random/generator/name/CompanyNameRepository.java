package nth.reflect.fw.infrastructure.random.generator.name;

import java.util.List;
import java.util.stream.Collectors;

import nth.reflect.fw.infrastructure.random.generator.resource.Repository;
import nth.reflect.fw.infrastructure.random.generator.resource.Resources;

public class CompanyNameRepository implements Repository<String> {

	@Override
	public List<String> getAll() {
		List<Product> allProducts = Resources.productRepository().getAll();
		List<String> allCompanyNames = allProducts.stream().map(Product::getCompany).collect(Collectors.toList());
		return allCompanyNames;
	}

}