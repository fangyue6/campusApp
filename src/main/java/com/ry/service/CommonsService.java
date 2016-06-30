package com.ry.service;
/**
 * 
 * @author Runhang
 *
 */

import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ry.exception.ServiceException;

@Service
@Transactional
public class CommonsService {

	private Logger logger = LoggerFactory.getLogger(CommonsService.class);
	
	/**
	 * 
	 * @param phone
	 * @return code
	 */
	public String sendValidateCode(String phone) {
		String code = RandomStringUtils.randomNumeric(6);
		logger.debug("phone number :{}, code is {}",phone, code);
		///////////////////
		
		///////////////////////
		
		return code;
	}

	/**
     * 
     * @param phone
     * @param code
     * @param map
     */
    public void validateCode(String phone, String code, Map<String, Object> map) {

        
    	
        if (map != null){

            DateTime createTime = (DateTime) map.get("time");
            String oldPhone = (String) map.get("phone");
            String oldCode = (String) map.get("code");

            if (createTime.plusMinutes(5).isAfterNow()) {
                if (!(oldCode.equals(code) && oldPhone.equals(phone))) {
                    throw new ServiceException("code isn't equal phone");
                }
            } else {
                throw new ServiceException("ServiceException");
            }
        }else{
            throw new ServiceException("Map is null");
        }
    }

}
