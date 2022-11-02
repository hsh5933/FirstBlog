package com.cos.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Generated;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
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






