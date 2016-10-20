package com.ullink.gradle.symstore

import org.junit.Test

import static org.junit.Assert.assertFalse
import static org.junit.Assert.assertTrue

public class StoreLockerTest {
    @Test
    void 'Given a locker when locking a folder then the folder cant be deleted.'() {
        def dir = File.createTempDir()
        StoreLocker.lock(dir.path) {
            assertFalse dir.deleteDir()
        }
        assertTrue dir.deleteDir()
    }
}
