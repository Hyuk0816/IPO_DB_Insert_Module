package com.sideprj.ipoAlarm.listingshares.entity;

import com.sideprj.ipoAlarm.ipodetail.entity.IpoDetail;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "listing_shares")
public class ListingShares {

    @Id
    @Column(name = "ipo_name", nullable = false)
    private String ipoName;

    @Column(name = "listing_date", nullable = false)
    private String listingDate;

    @Column(name = "current_price", nullable = false)
    private String currentPrice;

    @Column(name = "change_rate_previous", nullable = false)
    private String changeRatePrevious;

    @Column(name = "offering_price", nullable = false)
    private String offeringPrice;

    @Column(name = "change_rate_offering_price", nullable = false)
    private String changeRateOfferingPrice;

    @Column(name = "opening_price", nullable = false)
    private String openingPrice;

    @Column(name = "change_rate_opening_to_OfferingPrice", nullable = false)
    private String changeRateOpeningToOfferingPrice;

    @Column(name = "closing_price_first_day", nullable = false)
    private String closingPriceFirstDay;


    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        ListingShares that = (ListingShares) obj;
        return ipoName.equals(that.ipoName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ipoName);
    }
}
