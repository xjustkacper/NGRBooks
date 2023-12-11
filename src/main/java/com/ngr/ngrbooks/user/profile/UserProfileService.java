package com.ngr.ngrbooks.user.profile;

import com.ngr.ngrbooks.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;


    public void createUserProfile(User user) {
        var userProfile = new UserProfile();
        userProfile.setUser(user);
        userProfile.setDescription("Opis użytkownika");
        userProfile.setJoinDate(user.getJoinDate());
        userProfile.setAvatar("https://www.creativeuncut.com/gallery-42/art/de-harry-portrait.jpg");
        userProfileRepository.save(userProfile);
    }

    public void editUserProfile(UserProfile userProfile) {
        userProfileRepository.save(userProfile);

    }
}
