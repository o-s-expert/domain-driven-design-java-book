package expert.os.books.ddd.chapter10.products.application;

import expert.os.books.ddd.chapter10.products.infra.FieldVisibilityStrategy;
import jakarta.json.bind.annotation.JsonbVisibility;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.Objects;


@Schema(description = "Represents the manufacturer of a product.")
@JsonbVisibility(FieldVisibilityStrategy.class)
public class ManufacturerDTO {

    @Schema(description = "Name of the manufacturer.")
    private String name;

    @Schema(description = "Address of the manufacturer.")
    private String address;

    @Schema(description = "Contact number of the manufacturer.")
    private String contactNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

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
