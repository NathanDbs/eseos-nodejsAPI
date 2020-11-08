package com.eseos.tempoback.controller;

import com.eseos.tempoback.TempoBackApplication;
import com.eseos.tempoback.config.SpringBootProfiles;
import com.eseos.tempoback.exceptions.AppIsNotRunningException;
import com.eseos.tempoback.exceptions.ProfileRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestartProfileController {

    @Autowired
    Environment environment;

    @PostMapping("/system/changeprofile")
    public void changeProfileAndRestart(@RequestParam String profile) {
        if (!SpringBootProfiles.contains(profile)) {
            throw new ProfileRestartException("Le profil demandé (" + profile + ") n'existe pas");
        } else if (environment.getActiveProfiles()[0].equals(profile)) {
            throw new ProfileRestartException("Le profile " + profile + " est déjà celui actif");
        }
        TempoBackApplication.restart(profile);
    }

    @GetMapping("/system/profilechangeok")
    public void isAppRunning() {
        if (!TempoBackApplication.appIsRunning()) {
            throw new AppIsNotRunningException("TempoBackApplication ne s'est pas relancée correctement");
        }
    }
}
