package com.sysone.ddogdog.customer.common.util;

public class OAuthDataConverter {
    public static String convertAgeRange(String ageRange){
        switch (ageRange){
            case "0~9":
                return "꼬마";
            case "10~19":
                return "10대";
            case "20~29":
                return "20대";
            case "30~39":
                return "30대";
            case "40~49":
                return "40대";
            case "50~59":
                return "50대";
            case "60~69":
                return "60대";
            case "70~79":
                return "70대";
            case "80~89":
                return "80대";
            default:
                return "알수없음";
        }
    }

    public static String convertGender(String gender){
        switch (gender){
            case "male":
                return "남";
            case "female":
                return "여";
            default:
                return "알수없음";
        }
    }
}
