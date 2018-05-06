package com.econetwireless.epay.business.services.impl;
import com.econetwireless.epay.business.services.api.PartnerCodeValidator;
import com.econetwireless.epay.dao.requestpartner.api.RequestPartnerDao;
import com.econetwireless.epay.domain.RequestPartner;
import com.econetwireless.utils.execeptions.EpayException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PartnerCodeValidatorServiceImplTest {

    @Mock
    private RequestPartnerDao requestPartnerDao;

    private PartnerCodeValidator partnerCodeValidator;
    private String partnerCode;
    private RequestPartner requestPartner;


    @Before
    public void setUp() {
        partnerCode = "001";
        requestPartner = new RequestPartner();
        requestPartner.setId(1L);
        requestPartner.setCode(partnerCode);
        requestPartner.setDescription("PARTNER-DESCRIPTION");
        requestPartner.setName("VALID-PARTNER-NAME");
        partnerCodeValidator = new PartnerCodeValidatorImpl(requestPartnerDao);
    }
    @Test
    public void validatePartnerCodeShouldPassWhenThePartnerCodeIsValid() {
        when(requestPartnerDao.findByCode(partnerCode)).thenReturn(requestPartner);
        Boolean validationResponse = false;
        try {
            validationResponse =
                    partnerCodeValidator.validatePartnerCode(partnerCode);
            assertNotNull(validationResponse);
            assertTrue(validationResponse.booleanValue());
            verify(requestPartnerDao, times(1)).findByCode(partnerCode);
        } catch (EpayException e) {
            assertFalse(validationResponse.booleanValue());
            assertEquals("Invalid partner code supplied : null",
                    e.getMessage());
            verify(requestPartnerDao, times(1)).findByCode(partnerCode);
            throw e;
        }
    }

    @Test(expected = EpayException.class)
    public void validatePartnerCodeShouldFailIfPartnerCodeIsNull() {
        partnerCode = null;
        requestPartner = null;
        when(requestPartnerDao.findByCode(partnerCode)).thenReturn(requestPartner);
        Boolean validationResponse = false;
        try {
            validationResponse =
                    partnerCodeValidator.validatePartnerCode(partnerCode);
        } catch (EpayException e) {
            assertNotNull(e.getMessage());
            assertFalse(validationResponse.booleanValue());
            assertEquals("Invalid partner code supplied : null",
                    e.getMessage());
            verify(requestPartnerDao, times(1)).findByCode(partnerCode);
            throw e;
        }
    }



}

