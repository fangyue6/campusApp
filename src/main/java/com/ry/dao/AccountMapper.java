package com.ry.dao;

import org.apache.ibatis.annotations.Update;

import com.ry.entity.Account;


public interface AccountMapper {

	
	public Account isBusinessByPhone(String phone);

	public Account findAccountByPhone(String phone);
	
	public void save(Account account);

	@Update("update t_account set acpassword = #{acpassword} where id = #{id}")
	public int updatePassword(Account account);

	@Update("update t_account set acpassword = #{acpassword} where id= #{id}")
	public int resetPassword(Account account);

	
	
}
