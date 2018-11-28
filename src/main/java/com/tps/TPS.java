package com.tps;

import org.springframework.web.multipart.MultipartFile;

public interface TPS {

    String tps(MultipartFile file) throws Exception;
}
