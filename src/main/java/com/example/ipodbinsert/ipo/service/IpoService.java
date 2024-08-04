package com.example.ipodbinsert.ipo.service;




import com.example.ipodbinsert.ipo.entity.Ipo;

import java.io.IOException;
import java.util.List;


public interface IpoService {

    void save() throws IOException;

    void cacheWarming();

}
