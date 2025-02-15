package com.loserico.mail.vo;

import com.loserico.mail.Gender;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Data
public class UserVO {

    private String username;

    private String password;

    private Gender gender;

    private LocalDateTime birthday;

    private LocalTime workTime;

    private Date quitIime;
}
