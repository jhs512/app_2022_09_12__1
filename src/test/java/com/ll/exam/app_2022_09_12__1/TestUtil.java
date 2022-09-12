package com.ll.exam.app_2022_09_12__1;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;

public class TestUtil {
    public static class mock {
        public static class file {
            public static MockMultipartFile from(String url, String filename) throws IOException {
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<Resource> response = restTemplate.getForEntity(url, Resource.class);
                InputStream inputStream = response.getBody().getInputStream();

                return new MockMultipartFile(
                        "profileImg",
                        filename,
                        "image/png",
                        inputStream
                );
            }
        }
    }
}
