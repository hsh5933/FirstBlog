package com.cos.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class KakaoAccount {

//    public Boolean profile_nickname_needs_agreement;
//    public Boolean profile_image_needs_agreement;
    public Profile profile;
//    public Boolean has_email;
//    public Boolean email_needs_agreement;
//    public Boolean is_email_valid;
//    public Boolean is_email_verified;
    public String email;

    @JsonIgnoreProperties(ignoreUnknown=true)
    @Data
    public class Profile {
        public String nickname;
        public String thumbnail_image_url;
//        public String profile_image_url;
//        public Boolean is_default_image;

    }

}
