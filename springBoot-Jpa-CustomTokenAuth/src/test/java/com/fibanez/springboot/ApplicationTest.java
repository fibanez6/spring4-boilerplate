package com.fibanez.springboot;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.actuate.health.ApplicationHealthIndicator;
import org.springframework.boot.actuate.health.Status;

public class ApplicationTest {

    @Test
    public void when_start_expext_up() throws Exception {
        ApplicationHealthIndicator healthIndicator = new ApplicationHealthIndicator();
        Assert.assertEquals(Status.UP, healthIndicator.health().getStatus());
    }

}
