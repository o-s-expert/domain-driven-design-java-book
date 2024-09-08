package expert.os.books.ddd.chapter03;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Tourist {

    private String passportNumber;

    private String name;

    private Set<String> countries = new HashSet<>();

    public Tourist(String passportNumber, String name) {
        Objects.requireNonNull(passportNumber, "Passport number cannot be null");
        Objects.requireNonNull(name, "Name cannot be null");
        this.passportNumber = passportNumber;
        this.name = name;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getName() {
        return name;
    }

    public Set<String> getCountries() {
        return Collections.unmodifiableSet(countries);
    }

    public void addCountry(String country) {
        Objects.requireNonNull(country, "Country cannot be null");
        countries.add(country);
    }
}
