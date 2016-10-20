package com.ullink.gradle.symstore

public class StoreLocker {
    public static lock(String dir, Closure closure) {
        def file = new File(dir, '.gradle-symstore-plugin.lck')
        def raf = new RandomAccessFile(file, 'rwd')
        def fileLock = raf.getChannel().lock();
        try {
            closure()
        } finally {
            fileLock.release()
            raf.close()
            file.delete()
        }
    }
}
