package com.tps;

import org.springframework.web.multipart.MultipartFile;

public class TPSCosImpl implements TPS {

    @Override
    public String tps(MultipartFile file) throws Exception {
        return COS.tps(file);

    }
}

