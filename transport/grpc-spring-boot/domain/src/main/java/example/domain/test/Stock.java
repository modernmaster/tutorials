package example.domain.test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import domain.HistoricalPrice;
import lombok.*;
import net.badata.protobuf.converter.annotation.ProtoClass;
import net.badata.protobuf.converter.annotation.ProtoField;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;

//import uk.co.jamesmcguigan.forecaster.stock.pattern.Pattern;
//import uk.co.jamesmcguigan.forecaster.stock.price.HistoricalPrice;
//import uk.co.jamesmcguigan.forecaster.stock.trend.Trend;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Map;
//
@ProtoClass(domain.Stock.class)
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Stock {
    @Id
    @ProtoField
    private String id;
    @ProtoField
    private String admissionDate;
    @ProtoField
    private String companyName;
    @ProtoField
    private String symbol;
    @ProtoField
    private Boolean active;
    @ProtoField
    private String icbIndustry;
    @ProtoField
    private String icbSuperSector;
    @ProtoField
    private String countryOfIncorporation;
    @ProtoField
    private String market;
    @ProtoField
    private String companyMarketCap;
    @JsonDeserialize(using = DoubleConvertor.class)
    @ProtoField
    private Double price;
    @JsonDeserialize(using = DoubleConvertor.class)
    private Double percentageChange;
    @JsonDeserialize(using = DoubleConvertor.class)
    private Double openPrice;
    private Integer volume;
    private Integer avgVolume;
    @JsonDeserialize(using = DoubleConvertor.class)
    private Double pe;
    @JsonDeserialize(using = DoubleConvertor.class)
    private Double high;
    @JsonDeserialize(using = DoubleConvertor.class)
    private Double eps;
    @JsonDeserialize(using = DoubleConvertor.class)
    private Double change;
    @JsonDeserialize(using = DoubleConvertor.class)
    private Double closeYesterday;
    private Integer sharesOutstanding;
    private String currency;
    @JsonDeserialize(using = DoubleConvertor.class)
    private Double low;
    private String lastTradeTime;
    @JsonDeserialize(using = DoubleConvertor.class)
    private Double high52;
    @JsonDeserialize(using = DoubleConvertor.class)
    private Double low52;
    private String delay;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<HistoricalPrice> historicalPrices;
//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    private Map<String, Trend> trends;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Pattern> patterns;
    private LocalDateTime dateTimeUpdated;

    @Override
    public int hashCode() {
        return String.join("", getCompanyName(), getPrice().toString()).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass().equals(Stock.class)) {
            Stock stock = (Stock) obj;
            return getCompanyName().equals(stock.getCompanyName()) &&
                    getPrice().equals(stock.getPrice());
        }
        return false;
    }
}
