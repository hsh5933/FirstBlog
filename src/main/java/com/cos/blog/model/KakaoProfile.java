package com.cos.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.annotation.Generated;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class KakaoProfile {

    public Long id;
//    public String connected_at;
//    public Properties properties;
    public KakaoAccount kakao_account;

    @JsonIgnoreProperties(ignoreUnknown=true)
    @Data
    public class Properties {

        public String nickname;
//        public String profile_image;
//        public String thumbnail_image;

    }

}






