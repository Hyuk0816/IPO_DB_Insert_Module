package com.sideprj.ipoAlarm.ipo.service.impl;

import com.sideprj.ipoAlarm.ipo.entity.Ipo;
import com.sideprj.ipoAlarm.ipo.repository.IpoRepository;
import com.sideprj.ipoAlarm.ipo.service.IpoService;

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
import java.time.temporal.ChronoUnit;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class IpoServiceImpl implements IpoService {

    private final IpoRepository ipoRepository;
    private final CSVReader csvReader;
    private final S3Service s3Service;
    private final RedisTemplate<String, String> redisTemplate;

    private int count = 0;



    @Override
    @Transactional
    @Scheduled(cron = "0 0 2 * * ?") // 매일 새벽 2시에 실행 but 2일에 한번 실행 되도록 아래
    public void saveIpoData() throws IOException {

        s3Service.downloadFile(SaveFileConstants.ipoData);
        String filePath = S3ServiceImpl.saveDir + File.separator + SaveFileConstants.ipoData;
        List<Ipo> ipoList = csvReader.readCSVIpoData(filePath);
        ipoRepository.saveAll(ipoList);
        count++;
        log.info("ipo 데이터 갱신!{}차", count);
        redisTemplate.opsForList().rightPush("IpoDataRefresh", String.valueOf(LocalDate.now()));

    }



}
