package com.wwdlb.hongruan.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 获取验证码服务实例
 */
@Service
public class GetCheckCodeServiceImpl {
    @Autowired
    private MailServiceImpl mailServiceImpl;

    static int[] codeChar = {'A', 'a', 'B', 'b', 'C', 'c', 'D', 'd', 'E', 'e', 'F', 'f', 'H', 'h', 'I', 'i', 'J', 'j', 'K', 'k',
                        'L', 'l', 'M', 'm', 'N', 'n', 'O', 'P', 'Q', 'o', 'p', 'q', 'R', 'r', 'S', 's', 'T', 't', 'U', 'u',
                        'V', 'v', 'W', 'w', 'X', 'x', 'Y', 'y', 'Z', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

    public String getCheckCode(String email) {
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < 6; i++) {
            System.out.println("stringBuilder:" + stringBuilder);
            stringBuilder.append(codeChar[(int) (Math.random() * codeChar.length)]);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("email:" + email + ",code:" + stringBuilder.toString());
                mailServiceImpl.sendSimpleMail(email, "虹软", "您的验证码为：" + stringBuilder.toString() + ",请在半小时内填写");
            }
        }).start();
        return stringBuilder.toString();
    }
}
