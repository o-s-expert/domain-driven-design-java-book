package expert.os.books.ddd.chapter04.domain.soccer;

import java.math.BigDecimal;
import java.util.Currency;

public record Money(Currency currency, BigDecimal amount) {
}
