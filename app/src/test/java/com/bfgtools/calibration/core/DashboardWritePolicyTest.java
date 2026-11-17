package com.bfgtools.calibration.core;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DashboardWritePolicyTest {

    @Test
    public void fullyValidatedVersionsAreAllowed() {
        assertTrue(DashboardWritePolicy.allows(0x0259, 0x0155, 0x05CA, 0x0429));
        assertFalse(DashboardWritePolicy.needsExtraWarning(0x0259, 0x0155, 0x05CA, 0x0429));
    }

    @Test
    public void unknownVersionsAreBlocked() {
        assertFalse(DashboardWritePolicy.allows(-1, 0x0155, 0x05CA, 0x0429));
        assertFalse(DashboardWritePolicy.allows(0x0259, -1, 0x05CA, 0x0429));
        assertFalse(DashboardWritePolicy.allows(0x0259, 0x0155, -1, 0x0429));
        assertFalse(DashboardWritePolicy.allows(0x0259, 0x0155, 0x05CA, -1));
    }

    @Test
    public void otherVersionsAreAllowedButWarned() {
        assertTrue(DashboardWritePolicy.allows(0x0258, 0x0155, 0x05CA, 0x0429));
        assertTrue(DashboardWritePolicy.needsExtraWarning(0x0258, 0x0155, 0x05CA, 0x0429));
        assertTrue(DashboardWritePolicy.allows(0x0259, 0x0155, 0x05CB, 0x0429));
        assertTrue(DashboardWritePolicy.needsExtraWarning(0x0259, 0x0155, 0x05CB, 0x0429));
    }
}
