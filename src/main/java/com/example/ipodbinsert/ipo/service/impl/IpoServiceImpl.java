package com.example.ipodbinsert.ipo.service.impl;

import com.example.ipodbinsert.ipo.entity.Ipo;
import com.example.ipodbinsert.ipo.repository.IpoRepository;
import com.example.ipodbinsert.ipo.service.IpoService;
import com.example.ipodbinsert.util.S3.service.S3Service;
import com.example.ipodbinsert.util.S3.service.impl.S3ServiceImpl;
import com.example.ipodbinsert.util.csv.CSVReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    private LocalDate lastRunDate = LocalDate.now().minusDays(3); // 초기값 설정

    @Override
    @Transactional
    @Scheduled(cron = "0 0 3 * * ?") // 매일 새벽 3시에 실행 but 3일에 한번 실행 되도록 아래
    public void save() throws IOException {
        LocalDate today = LocalDate.now();
        long daysBetween = ChronoUnit.DAYS.between(lastRunDate, today);

        if (daysBetween >= 3) {
            // 3일이 지났다면 작업 수행
            //log DB를 하나 파자
            s3Service.downloadFile();
            String filePath = S3ServiceImpl.saveDir + File.separator + S3ServiceImpl.fileName;
            List<Ipo> ipoList = csvReader.readCSV(filePath);
            ipoRepository.saveAll(ipoList);
            lastRunDate = today; // 마지막 실행일 갱신
        }
    }



}
