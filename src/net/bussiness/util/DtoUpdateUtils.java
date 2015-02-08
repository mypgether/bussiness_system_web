package net.bussiness.util;

import net.bussiness.model.UserDto;

public class DtoUpdateUtils {
	public static void updateUserDto(UserDto temp, UserDto dto) {
		if (null != dto.getPosition()) {
			temp.setPosition(dto.getPosition());
		}
		if (null != dto.getDept()) {
			temp.setDept(dto.getDept());
		}
		if (null != dto.getPushUserId()) {
			temp.setPushUserId(dto.getPushUserId());
		}
		if (null != dto.getPushChannelId()) {
			temp.setPushChannelId(dto.getPushChannelId());
		}
		if (null != dto.getUserName()) {
			temp.setUserName(dto.getUserName());
		}
		if (null != dto.getPassword()) {
			temp.setPassword(dto.getPassword());
		}
		if (null != dto.getPhotoPath()) {
			temp.setPhotoPath(dto.getPhotoPath());
		}
		if (null != dto.getTel()) {
			temp.setTel(dto.getTel());
		}
		if (null != dto.getEmail()) {
			temp.setEmail(dto.getEmail());
		}
		if (null != dto.getJoinTime()) {
			temp.setJoinTime(dto.getJoinTime());
		}
		if (null != dto.getDescription()) {
			temp.setDescription(dto.getDescription());
		}
	}
}
