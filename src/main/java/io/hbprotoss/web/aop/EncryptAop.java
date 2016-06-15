package io.hbprotoss.web.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by hbprotoss on 12/14/15.
 */
public class EncryptAop {

    private static final Logger LOGGER = LoggerFactory.getLogger(EncryptAop.class);

    public void afterAdvice() {
        LOGGER.info("");
    }
}
