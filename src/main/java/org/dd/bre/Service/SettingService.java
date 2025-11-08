package org.dd.bre.Service;

import lombok.RequiredArgsConstructor;
import org.dd.bre.Dto.SettingResponse;
import org.dd.bre.Exception.UserNotFoundException;
import org.dd.bre.Repo.UserRepo;
import org.dd.bre.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SettingService {
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    public SettingResponse updateSetting(UserDetails userDetails, boolean email, boolean phone){
        User user = userRepo.findByUsername(userDetails.getUsername()).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        user.setIsEmailEnabled(email);
        user.setIsPhoneEnabled(phone);
        userRepo.save(user);
        return modelMapper.map(user,SettingResponse.class);
    }

    public SettingResponse getSetting(UserDetails userDetails){
        User user = userRepo.findByUsername(userDetails.getUsername()).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        return modelMapper.map(user,SettingResponse.class);
    }
}
