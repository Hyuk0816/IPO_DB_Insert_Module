package com.sideprj.ipoAlarm.listingshares.service.impl;

import com.sideprj.ipoAlarm.ipodetail.repository.IpoDetailRepository;
import com.sideprj.ipoAlarm.listingshares.entity.ListingShares;
import com.sideprj.ipoAlarm.listingshares.repository.ListingSharesRepository;
import com.sideprj.ipoAlarm.listingshares.service.ListingSharesService;
import com.sideprj.ipoAlarm.util.S3.constants.SaveFileConstants;
import com.sideprj.ipoAlarm.util.S3.service.S3Service;
import com.sideprj.ipoAlarm.util.S3.service.impl.S3ServiceImpl;
import com.sideprj.ipoAlarm.util.csv.CSVReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class ListingSharesServiceImpl implements ListingSharesService {

    private final ListingSharesRepository listingSharesRepository;
    private final CSVReader csvReader;
    private final S3Service s3Service;
    private final RedisTemplate<String, String> redisTemplate;
    private int count = 0;

    @Override
    @Transactional
    @Scheduled(cron = "0 30 9 * * ?") //매일 3시에 진행
    public void saveListingShares() throws IOException {
        s3Service.downloadFile(SaveFileConstants.listingShares);
        String filePath = S3ServiceImpl.saveDir + File.separator + SaveFileConstants.listingShares;
        List<ListingShares> listingShares = csvReader.readCSVListingShares(filePath);
        listingSharesRepository.saveAll(listingShares);
        count++;
        log.info("listing shares 데이터 갱신!{}차", count);
        redisTemplate.opsForList().rightPush("listingSharesRefresh", String.valueOf(LocalDate.now()));
    }
}
