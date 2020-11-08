package com.eseos.tempoback.config;

import java.util.Arrays;

public enum SpringBootProfiles {
    dev,
    test,
    prod;

    public static boolean contains(String s) {
        return Arrays.stream(SpringBootProfiles.values()).anyMatch(profile -> profile.name().equals(s));
    }
}
