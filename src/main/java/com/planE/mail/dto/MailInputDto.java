package com.planE.mail.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailInputDto {
    private List<String> toUserList;
    private String emailTitle;
    private String emailText;
    private List<String> ccEmailAdr;
}
