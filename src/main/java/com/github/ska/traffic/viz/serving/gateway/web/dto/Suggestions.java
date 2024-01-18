package com.github.ska.traffic.viz.serving.gateway.web.dto;

import java.util.Arrays;
import java.util.List;

public enum Suggestions {
    INSTANCE;

    /**
     * Replace with domains your enterprise has in its directory
     */
    public List<String> getAllDomains() {
        return Arrays.asList("domain-1",
                "domain-2");
    }
}
