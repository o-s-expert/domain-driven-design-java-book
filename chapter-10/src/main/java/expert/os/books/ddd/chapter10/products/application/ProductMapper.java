package expert.os.books.ddd.chapter10.products.application;

import expert.os.books.ddd.chapter10.products.domain.Category;
import expert.os.books.ddd.chapter10.products.domain.Manufacturer;
import expert.os.books.ddd.chapter10.products.domain.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI)
public interface ProductMapper {
    ProductDTO toDTO(Product product);

    Product toEntity(ProductDTO productDTO);

    ManufacturerDTO toDTO(Manufacturer manufacturer);

    Manufacturer toEntity(ManufacturerDTO manufacturerDTO);

    CategoryDTO toDTO(Category category);

    Category toEntity(CategoryDTO categoryDTO);

}
