package com.example.app;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import net.arnx.jsonic.JSON;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
/**
 * �Ƒ��̖��O��JSON�`���ŕԋp����API�N���X�ł��B
 */
@Controller
public class FamilyController {
 
    private static final Logger logger =
            LoggerFactory.getLogger(FamilyController.class);
 
    @RequestMapping(value = "/family", method = RequestMethod.GET)
    public String home(HttpServletRequest request,
                       Locale locale,
                       Model model) {
        logger.debug("API start");
 
        model.addAttribute("callback", request.getParameter("callback"));
 
        // �t�@�~���[�N���X�ɖ��O���Z�b�g���܂��B
        Family family = new Family();
        family.setFirstName("�J�c�I");
        family.setLastName("���");
 
        // ���^�[���R�[�h�����b�Z�[�W���Z�b�g���܂��B
        Message message = new Message();
        message.setReturnCode(0);
        message.setMessage("success");
         
        // �t�@�~���[�N���X�A���b�Z�[�W�N���X��JSON�����܂��B
        String jsonFamily = JSON.encode(family);
        String jsonMessage = JSON.encode(message);
 
        // jsp��JSON��\��������悤�ݒ肵�܂��B
        model.addAttribute("responseData", jsonFamily);
        // jsp�Ƀ��b�Z�[�W��\��������悤�ݒ肵�܂��B
        model.addAttribute("responsMessage", jsonMessage);
 
        return "response";
    }
 
    /**
     * �Ƒ��̖��O������N���X�ł��B
     */
    private class Family {
        // ��
        private String firstName;
        // ��
        private String lastName;
        public String getFirstName() {
            return firstName;
        }
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }
        public String getLastName() {
            return lastName;
        }
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }
 
    /**
     * API�̃��^�[�����b�Z�[�W������N���X�ł��B
     */
    private class Message {
        // �߂�l
        private int returnCode;
        // ���b�Z�[�W
        private String message;
        public int getReturnCode() {
            return returnCode;
        }
        public void setReturnCode(int returnCode) {
            this.returnCode = returnCode;
        }
        public String getMessage() {
            return message;
        }
        public void setMessage(String message) {
            this.message = message;
        }
    }
}