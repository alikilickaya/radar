package com.af.radar.utils;

import com.af.radar.constants.GeneralConstants;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by AKilickaya
 */
public class StringUtilsTest {

    @Test
    public void getStringWithLengthAndFilledWithCharacter() throws Exception {
        String filledString = StringUtils.getStringWithLengthAndFilledWithCharacter(5, GeneralConstants.SPACE_CHAR);

        assertNotNull(filledString);
        assertEquals("-----", filledString);
    }

}