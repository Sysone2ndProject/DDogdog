package com.sysone.ddogdog.customer.auth.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Getter;

@Getter
public class KakaoProfile {
    private Long id;
    private String email;
    private String nickname;
    private String ageRange;
    private String gender;

    public KakaoProfile(String jsonResponseBody){
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(jsonResponseBody);

        this.id = element.getAsJsonObject().get("id").getAsLong();

        JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
        this.nickname = properties.getAsJsonObject().get("nickname").getAsString();

        JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
        this.email = kakaoAccount.getAsJsonObject().get("email").getAsString();
        this.ageRange = kakaoAccount.getAsJsonObject().get("age_range").getAsString();
        this.gender = kakaoAccount.getAsJsonObject().get("gender").getAsString();
    }

}