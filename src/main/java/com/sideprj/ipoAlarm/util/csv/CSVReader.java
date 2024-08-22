package com.sideprj.ipoAlarm.util.csv;


import com.sideprj.ipoAlarm.ipo.entity.Ipo;
import com.sideprj.ipoAlarm.ipodetail.entity.IpoDetail;
import com.sideprj.ipoAlarm.listingshares.entity.ListingShares;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CSVReader {

    @Transactional
    public List<Ipo> readCSVIpoData(String filePath) throws IOException {

        Map<String, Ipo> ipoMap = new HashMap<>();

        CSVFormat csvFormat = CSVFormat.EXCEL.builder()
                .setHeader("name", "confirmPrice", "IPOPrice", "competitionRate", "securities", "start_date", "end_date")
                .setSkipHeaderRecord(true)
                .build();

        try (Reader reader = new FileReader(filePath , StandardCharsets.UTF_8);
             CSVParser csvParser = new CSVParser(reader, csvFormat)) {

            for (CSVRecord record : csvParser) {
                String name = record.get("name");
                Ipo ipo = ipoMap.getOrDefault(name, new Ipo());
                SimpleDateFormat startDateFormatter = new SimpleDateFormat("yyyy.MM.dd HH:mm");
                SimpleDateFormat endDateFormatter = new SimpleDateFormat("yyyy.MM.dd HH:mm");

                ipo = ipo.builder()
                        .ipoName(name)
                        .confirmPrice(record.get("confirmPrice"))
                        .ipoPrice(record.get("IPOPrice"))
                        .competitionRate(record.get("competitionRate"))
                        .securities(record.get("securities"))
                        .startDate(startDateFormatter.parse(record.get("start_date")))
                        .endDate(endDateFormatter.parse(record.get("end_date")))
                        .build();

                ipoMap.put(name, ipo);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return new ArrayList<>(ipoMap.values());
    }
    @Transactional
    public List<IpoDetail> readCSVIpoDetail(String filePath) throws IOException {

        Map<String, IpoDetail> ipoDetailMap = new HashMap<>();

        CSVFormat csvFormat = CSVFormat.EXCEL.builder()
                .setHeader("name", "industry", "representative", "revenue", "netProfit", "totalOfferedShares")
                .setSkipHeaderRecord(true)
                .build();

        try (Reader reader = new FileReader(filePath , StandardCharsets.UTF_8);
             CSVParser csvParser = new CSVParser(reader, csvFormat)) {

            for (CSVRecord record : csvParser) {
                String name = record.get("name");
                IpoDetail ipoDetail = ipoDetailMap.getOrDefault(name, new IpoDetail());

                ipoDetail = ipoDetail.builder()
                        .ipoName(name)
                        .industry(record.get("industry"))
                        .representative(record.get("representative"))
                        .revenue(record.get("revenue"))
                        .netProfit(record.get("netProfit"))
                        .totalOfferedShares(record.get("totalOfferedShares"))
                        .build();

                ipoDetailMap.put(name, ipoDetail);
            }
        }
        return new ArrayList<>(ipoDetailMap.values());
    }

    @Transactional
    public List<ListingShares> readCSVListingShares(String filePath) throws IOException {

        Map<String, ListingShares> listingSharesMap = new HashMap<>();

        CSVFormat csvFormat = CSVFormat.EXCEL.builder()
                .setHeader("name", "listingDate", "currentPrice", "changeFromPreviousDay", "offeringPrice", "changeRateFromOfferingPrice", "openingPrice",
                        "openingPriceToOfferingPrice", "closingPriceOnFirstDay")
                .setSkipHeaderRecord(true)
                .build();

        try (Reader reader = new FileReader(filePath , StandardCharsets.UTF_8);
             CSVParser csvParser = new CSVParser(reader, csvFormat)) {

            for (CSVRecord record : csvParser) {
                String name = record.get("name");
                ListingShares listingShares = listingSharesMap.getOrDefault(name, new ListingShares());

                listingShares = listingShares.builder()
                        .ipoName(name)
                        .listingDate(record.get("listingDate"))
                        .currentPrice(record.get("currentPrice"))
                        .changeRatePrevious(record.get("changeFromPreviousDay"))
                        .offeringPrice(record.get("offeringPrice"))
                        .changeRateOfferingPrice(record.get("changeRateFromOfferingPrice"))
                        .openingPrice(record.get("openingPrice"))
                        .changeRateOpeningToOfferingPrice(record.get("openingPriceToOfferingPrice"))
                        .closingPriceFirstDay(record.get("closingPriceOnFirstDay"))
                        .build();

                listingSharesMap.put(name, listingShares);
            }
        }
        return new ArrayList<>(listingSharesMap.values());
    }

}

