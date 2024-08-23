package com.sideprj.ipoAlarm.ipodetail.service.impl;

import com.sideprj.ipoAlarm.ipodetail.entity.IpoDetail;
import com.sideprj.ipoAlarm.ipodetail.repository.IpoDetailRepository;
import com.sideprj.ipoAlarm.ipodetail.service.IpoDetailService;
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
public class IpoDetailServiceImpl implements IpoDetailService {

    private final IpoDetailRepository ipoDetailRepository;
    private final CSVReader csvReader;
    private final S3Service s3Service;
    private final RedisTemplate<String, String> redisTemplate;
    private int count = 0;

    @Override
    @Transactional
    @Scheduled(cron = "0 30 2 * * ?") // 매일 새벽 2시 30분 실행 but 2일에 한번 실행 되도록 아래
    public void saveIpoDetail() throws IOException {
        s3Service.downloadFile(SaveFileConstants.ipoDetail);
        String filePath = S3ServiceImpl.saveDir + File.separator + SaveFileConstants.ipoDetail;
        List<IpoDetail> ipoList = csvReader.readCSVIpoDetail(filePath);
        ipoDetailRepository.saveAll(ipoList);
        count++;
        log.info("ipo Detail 데이터 갱신{}차", count);
        redisTemplate.opsForList().rightPush("IpoDetailRefresh", String.valueOf(LocalDate.now()));
    }
}
