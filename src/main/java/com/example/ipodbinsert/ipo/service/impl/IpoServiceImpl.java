package com.example.ipodbinsert.ipo.service.impl;

import com.example.ipodbinsert.ipo.entity.Ipo;
import com.example.ipodbinsert.ipo.repository.IpoRepository;
import com.example.ipodbinsert.ipo.service.IpoService;
import com.example.ipodbinsert.util.S3.service.S3Service;
import com.example.ipodbinsert.util.S3.service.impl.S3ServiceImpl;
import com.example.ipodbinsert.util.csv.CSVReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class IpoServiceImpl implements IpoService {

    private final IpoRepository ipoRepository;
    private final CSVReader csvReader;
    private final S3Service s3Service;


    @Override
    @Transactional
    public void save() throws IOException {
        s3Service.downloadFile();
        String filePath =  S3ServiceImpl.saveDir + File.separator + S3ServiceImpl.fileName;
        List<Ipo> ipoList = csvReader.readCSV(filePath);
        ipoRepository.saveAll(ipoList);
    }

}
