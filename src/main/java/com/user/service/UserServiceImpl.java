package com.user.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.user.entity.UserDataEntity;
@Service
public class UserServiceImpl implements UserService{

	List<UserDataEntity> userData= List.of(
			new UserDataEntity(1L,"Rana","Melaghar"),
			new UserDataEntity(2L,"Singha","Agartala"),
			new UserDataEntity(3L,"Bijay Dhar","Kamrangatali"),
			new UserDataEntity(4L,"Binay Dhar","Kamrangatali"),
			new UserDataEntity(5L,"Biplab Dhar","Bengalor")
			);
	@Override
	public UserDataEntity getUser(Long userId) {
		// TODO Auto-generated method stub
		return this.userData.stream().filter(user->user.getUserId().equals(userId)).findAny().orElse(null);
	}

}
