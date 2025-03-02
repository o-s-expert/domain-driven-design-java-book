package expert.os.books.ddd.chapter10.products.application;

import expert.os.books.ddd.chapter10.products.infra.FieldVisibilityStrategy;
import jakarta.json.bind.annotation.JsonbVisibility;

import java.util.Objects;

@JsonbVisibility(FieldVisibilityStrategy.class)
public class ManufacturerDTO {

    private String name;

    private String address;

    private String contactNumber;


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ManufacturerDTO that = (ManufacturerDTO) o;
        return Objects.equals(name, that.name)
                && Objects.equals(address, that.address)
                && Objects.equals(contactNumber, that.contactNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, contactNumber);
    }

    @Override
    public String toString() {
        return "ManufacturerDTO{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }
}
