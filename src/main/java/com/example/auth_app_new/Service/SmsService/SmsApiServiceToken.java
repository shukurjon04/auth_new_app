package com.example.auth_app_new.Service.SmsService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SmsApiServiceToken {
    private String Message;
    private SmsApiServiceTokenData data;
    private String token_type;
}
