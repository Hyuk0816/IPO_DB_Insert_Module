package com.sideprj.ipoAlarm.ipo.service.impl;

import com.sideprj.ipoAlarm.ipo.dto.IpoGetAllDto;
import com.sideprj.ipoAlarm.ipo.entity.Ipo;
import com.sideprj.ipoAlarm.ipo.repository.IpoRepository;
import com.sideprj.ipoAlarm.ipo.service.IpoService;
import com.sideprj.ipoAlarm.util.S3.service.S3Service;
import com.sideprj.ipoAlarm.util.S3.service.impl.S3ServiceImpl;
import com.sideprj.ipoAlarm.util.converter.DateFormatter;
import com.sideprj.ipoAlarm.util.csv.CSVReader;
import com.sideprj.ipoAlarm.util.page.PageResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class IpoServiceImpl implements IpoService {

    private final IpoRepository ipoRepository;
    private final CSVReader csvReader;
    private final S3Service s3Service;
    private final RedisTemplate<String ,String> redisTemplate;

    private LocalDate lastRunDate = LocalDate.now().minusDays(3); // 초기값 설정

    private int count = 0;

    @Override
    @Transactional
    @Scheduled(cron = "0 0 2 * * ?") // 매일 새벽 3시에 실행 but 2일에 한번 실행 되도록 아래
    public void save() throws IOException {
        LocalDate today = LocalDate.now();
        long daysBetween = ChronoUnit.DAYS.between(lastRunDate, today);
        String formattedCreatedAt = DateFormatter.format(LocalDateTime.now());
        try{
            if (daysBetween >= 2) {
                // 3일이 지났다면 작업 수행
                //log DB를 하나 파자
                s3Service.downloadFile();
                String filePath = S3ServiceImpl.saveDir + File.separator + S3ServiceImpl.fileName;
                List<Ipo> ipoList = csvReader.readCSV(filePath);
                ipoRepository.saveAll(ipoList);
                count++;
                log.info("데이터 저장 경로 : " + filePath);
                log.info("데이터 갱신!" + count + "차");
                lastRunDate = today; // 마지막 실행일 갱신
                redisTemplate.opsForValue().set("refresh", formattedCreatedAt);
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }


    }

}
