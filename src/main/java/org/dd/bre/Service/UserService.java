package org.dd.bre.Service;

import lombok.RequiredArgsConstructor;
import org.dd.bre.Dto.UserProfileDto;
import org.dd.bre.Exception.UserNotFoundException;
import org.dd.bre.Repo.OrderRepo;
import org.dd.bre.Repo.UserRepo;
import org.dd.bre.model.User;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final OrderRepo orderRepo;

    public UserProfileDto getUserProfile(Long userId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new UserNotFoundException("User Not Found"));

        UserProfileDto userProfileDto = new UserProfileDto();
        userProfileDto.setFirstName(user.getFirstName());
        userProfileDto.setLastName(user.getLastName());
        userProfileDto.setEmail(user.getEmail());
        userProfileDto.setPhoneNumber(user.getPhone());

        return userProfileDto;
    }
    public UserProfileDto updateProfile(Long userId, UserProfileDto userProfileDto){
        User user = userRepo.findById(userId).orElseThrow(()-> new UserNotFoundException("User Not Found"));

        user.setFirstName(userProfileDto.getFirstName());
        user.setLastName(userProfileDto.getLastName());
        user.setEmail(userProfileDto.getEmail());
        user.setPhone(userProfileDto.getPhoneNumber());

        userRepo.save(user);
        return userProfileDto;
    }

}
