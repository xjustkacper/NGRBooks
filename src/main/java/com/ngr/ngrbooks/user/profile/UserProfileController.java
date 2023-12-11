package com.ngr.ngrbooks.user.profile;

import com.ngr.ngrbooks.user.User;
import com.ngr.ngrbooks.user.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileRepository userProfileRepository;
    private final UserService userService;


    @GetMapping("/profile")
    public String getUserProfile(Model model, Principal principal){
        addProfileToModel(model, principal);
        return "profile";
    }


    @GetMapping("/edit")
    public String getUserProfileEdit(Model model, Principal principal){
        addProfileToModel(model, principal);
        return "edit";
    }



    @PostMapping("/edit")
    public String editUserProfile( @ModelAttribute UserProfile formUserProfile, Model model, Principal principal){
        Optional<User> userOptional = userService.findByEmail(principal.getName());
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            UserProfile userProfile = userProfileRepository.findByUserId(user.getId());

            userProfile.setDescription(formUserProfile.getDescription());
            userProfile.setAvatar(formUserProfile.getAvatar());
            userProfileRepository.save(userProfile);

            model.addAttribute("nickname", user.getNickname());
            model.addAttribute("userProfile", userProfile);
        }
        return "redirect:/profile";
    }

    private void addProfileToModel(Model model, Principal principal) {
        Optional<User> userOptional = userService.findByEmail(principal.getName());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserProfile userProfile = userProfileRepository.findByUserId(user.getId());

            model.addAttribute("nickname", user.getNickname());
            model.addAttribute("userProfile", userProfile);
        }
    }

}
