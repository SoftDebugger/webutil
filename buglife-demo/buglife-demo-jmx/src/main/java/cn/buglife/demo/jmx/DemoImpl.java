package cn.buglife.demo.jmx;

import org.apache.log4j.Logger;

/**
 * Created by CrazyHarry on 2015/1/6.
 */
public class DemoImpl implements Demo {
    private static final Logger LOGGER = Logger.getLogger(DemoImpl.class);
    @Override
    public void log() {
        while (true) {
            try {
                Thread.currentThread().sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            LOGGER.trace("This is the TRACE logging");
            LOGGER.debug("This is the DEBUG logging");
            LOGGER.warn("This is the WARN logging");
            LOGGER.info("This is the INFO logging");
            LOGGER.error("This is the ERROR logging");
            LOGGER.fatal("This is the FATAL logging");
        }
    }
}
