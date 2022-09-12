package com.ll.exam.app_2022_09_12__1;

import java.text.SimpleDateFormat;

public class Util {
    public static class date {
        public static String getNowYearMonth() {
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy_MM");

            return format1.format(System.currentTimeMillis());
        }

        public static String getNowYearMonthDay() {
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy_MM_dd");

            return format1.format(System.currentTimeMillis());
        }
    }

    public static class file {
        public static String getExtTypeCodeFromFileName(String fileName) {
            String ext = getExtFromFileName(fileName).toLowerCase();

            switch (ext) {
                case "jpeg":
                case "jpg":
                case "gif":
                case "png":
                    return "img";
                case "mp4":
                case "avi":
                case "mov":
                    return "video";
                case "mp3":
                    return "audio";
            }

            return "etc";
        }

        public static String getExtType2CodeFromFileName(String fileName) {
            String ext = getExtFromFileName(fileName).toLowerCase();

            switch (ext) {
                case "jpeg":
                case "jpg":
                    return "jpg";
                case "gif":
                    return ext;
                case "png":
                    return ext;
                case "mp4":
                    return ext;
                case "mov":
                    return ext;
                case "avi":
                    return ext;
                case "mp3":
                    return ext;
            }

            return "etc";
        }

        public static String getExtFromFileName(String fileName) {
            int pos = fileName.lastIndexOf(".");
            String ext = fileName.substring(pos + 1);

            return ext;
        }
    }
}
