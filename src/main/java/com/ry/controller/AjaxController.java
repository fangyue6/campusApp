package com.ry.controller;

/**
 * 示例Controller
 */

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ry.dto.Message;
import com.ry.exception.ServiceException;
import com.ry.service.CommonsService;


@Controller
@RequestMapping("/api")
public class AjaxController {

	//private Logger Logger = LoggerFactory.getLogger(AjaxController.class);
	
	@Autowired
	private CommonsService commonsService;
	
	
     
	/**
    * 
    * @param phone
    * @param session
    * @return
    */
   @RequestMapping(value = "/sms/user/validatecode",method = RequestMethod.POST)
   @ResponseBody
   public String sendValidateCode2(String phone,HttpSession session) {
       String code = commonsService.sendValidateCode(phone);

       Map<String,Object> map = new HashMap<String, Object>();
       map.put("phone",phone);
       map.put("code",code);
       map.put("time", DateTime.now());

       session.setAttribute("validateCode",map);
       return "yes";
   }

    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/sms/validatecallback",method = RequestMethod.POST)
    @ResponseBody
    public Message validateSmsCode(String phone,String code,HttpSession session) {
        Map<String,Object> map = (Map<String, Object>) session.getAttribute("validateCode");
        Message message = new Message();
        try {
            commonsService.validateCode(phone, code, map);
            message.setState("success");
        } catch (ServiceException ex) {
            message.setState("error");
            message.setMessage(ex.getMessage());
        }
        return  message;
    }
    
    
	
}
