package com.ayasaidi.deals.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Deal {

    @Id
    @Column(name = "deal_unique_id")
    private String dealUniqueId;
    @Column(name = "from_currency")

    private String fromCurrency;
    @Column(name = "to_currency")

    private String toCurrency;
    @Column(name = "deal_amount")

    private BigDecimal  dealAmount;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "deal_timestamp")

    private LocalDateTime dealTimestamp;

    public Deal( ) {
    }


    public Deal(String dealUniqueId, String fromCurrency, String toCurrency, BigDecimal dealAmount, LocalDateTime dealTimestamp) {
        this.dealUniqueId = dealUniqueId;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.dealAmount = dealAmount;
        this.dealTimestamp = dealTimestamp;
    }

    public String getDealUniqueId() {
        return dealUniqueId;
    }

    public void setDealUniqueId(String dealUniqueId) {
        this.dealUniqueId = dealUniqueId;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public LocalDateTime getDealTimestamp() {
        return dealTimestamp;
    }

    public void setDealTimestamp(LocalDateTime dealTimestamp) {
        this.dealTimestamp = dealTimestamp;
    }

    public BigDecimal getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(BigDecimal dealAmount) {
        this.dealAmount = dealAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deal deal = (Deal) o;
        return Objects.equals(dealUniqueId, deal.dealUniqueId) && Objects.equals(fromCurrency, deal.fromCurrency) && Objects.equals(toCurrency, deal.toCurrency) && Objects.equals(dealAmount, deal.dealAmount) && Objects.equals(dealTimestamp, deal.dealTimestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dealUniqueId, fromCurrency, toCurrency, dealAmount, dealTimestamp);
    }

    @Override
    public String toString() {
        return "Deal{" +
                "dealUniqueId='" + dealUniqueId + '\'' +
                ", fromCurrency='" + fromCurrency + '\'' +
                ", toCurrency='" + toCurrency + '\'' +
                ", dealAmount=" + dealAmount +
                ", dealTimestamp=" + dealTimestamp +
                '}';
    }
}
