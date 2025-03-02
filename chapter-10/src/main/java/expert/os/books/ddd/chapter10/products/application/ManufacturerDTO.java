package expert.os.books.ddd.chapter10.products.application;

import expert.os.books.ddd.chapter10.products.infra.FieldVisibilityStrategy;
import jakarta.json.bind.annotation.JsonbVisibility;
import jakarta.nosql.Column;

@JsonbVisibility(FieldVisibilityStrategy.class)
public class ManufacturerDTO {

    private String name;

    private String address;

    private String contactNumber;



}
