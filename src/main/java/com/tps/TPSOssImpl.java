package com.tps;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class TPSOssImpl implements TPS {

    @Override
    public String tps(MultipartFile file) throws IOException {
        return Oss.tps(file);

    }
}