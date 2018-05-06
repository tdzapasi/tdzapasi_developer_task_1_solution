package com.econetwireless.epay.business.services.impl;


import com.econetwireless.epay.business.integrations.api.ChargingPlatform;
import com.econetwireless.epay.business.services.api.CreditsService;
import com.econetwireless.epay.dao.subscriberrequest.api.SubscriberRequestDao;
import com.econetwireless.epay.domain.SubscriberRequest;
import com.econetwireless.utils.enums.ResponseCode;
import com.econetwireless.utils.messages.AirtimeTopupRequest;
import com.econetwireless.utils.messages.AirtimeTopupResponse;
import com.econetwireless.utils.pojo.INCreditRequest;
import com.econetwireless.utils.pojo.INCreditResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreditsServiceImplTest {

    @Mock
    private SubscriberRequestDao subscriberRequestDao;

    @Mock
    private ChargingPlatform chargingPlatform;

    private CreditsService creditsService;
    private AirtimeTopupRequest airtimeTopupRequest;
    private String msisdn;
    private double amount;
    private String partnerCode;
    private String referenceNumber;
    private SubscriberRequest subscriberRequest;
    private String requestType;
    private INCreditRequest inCreditRequest;
    private INCreditResponse inCreditResponse;

    @Before
    public void setUp(){
        creditsService = new CreditsServiceImpl(chargingPlatform,
                subscriberRequestDao);

        amount = 2.5;
        referenceNumber = "REF001";
        requestType = "Airtime Topup";
        msisdn = "0772997889";
        partnerCode = "001";
        subscriberRequest = new SubscriberRequest();
        subscriberRequest.setMsisdn(msisdn);
        subscriberRequest.setPartnerCode(partnerCode);
        subscriberRequest.setRequestType(requestType);
        subscriberRequest.setBalanceBefore(1);

        subscriberRequest.setBalanceAfter(3);
        subscriberRequest.setReference("REF001");
        subscriberRequest.setAmount(2);
        airtimeTopupRequest = new AirtimeTopupRequest();
        airtimeTopupRequest.setMsisdn(msisdn);
        airtimeTopupRequest.setAmount(amount);
        airtimeTopupRequest.setPartnerCode(partnerCode);
        airtimeTopupRequest.setReferenceNumber(referenceNumber);

        inCreditRequest = new INCreditRequest();
        inCreditRequest.setAmount(amount);
        inCreditRequest.setMsisdn(msisdn);
        inCreditRequest.setPartnerCode(partnerCode);
        inCreditRequest.setReferenceNumber(referenceNumber);

        inCreditResponse = new INCreditResponse();
        inCreditResponse.setBalance(3.0);
        inCreditResponse.setMsisdn(msisdn);
        inCreditResponse.setNarrative("Topup was successful");
        inCreditResponse.setResponseCode("200");
    }

    @Test
    public void creditShouldFailWhenTheServerIsNotReachable() {
        final String message = "Server not reachable";
        inCreditResponse.setResponseCode("500");
        inCreditResponse.setNarrative(message);
        when(subscriberRequestDao.save(any(SubscriberRequest.class))).thenReturn(subscriberRequest);
        when(chargingPlatform.creditSubscriberAccount(any(INCreditRequest.class))).thenReturn(inCreditResponse);
        final AirtimeTopupResponse airtimeTopupResponse =
                creditsService.credit(airtimeTopupRequest);
        assertNotNull(airtimeTopupResponse);
        assertEquals(message, airtimeTopupResponse.getNarrative());
        assertEquals(ResponseCode.FAILED.getCode(),
                airtimeTopupResponse.getResponseCode());
        verify(subscriberRequestDao,
                times(2)).save(any(SubscriberRequest.class));
        verify(chargingPlatform,
                times(1)).creditSubscriberAccount(any(INCreditRequest.class));
    }

    @Test
    public void
    enquireAirtimeBalanceShouldBeSuccessfulIfPartnerCodeAndMsisdnAreValid() {
        final String message = "Topup was successful";
        when(subscriberRequestDao.save(any(SubscriberRequest.class))).thenReturn(subscriberRequest);
        when(chargingPlatform.creditSubscriberAccount(any(INCreditRequest.class))).thenReturn(inCreditResponse);
        final AirtimeTopupResponse airtimeTopupResponse =
                creditsService.credit(airtimeTopupRequest);
        assertNotNull(airtimeTopupResponse);
        assertEquals(message, airtimeTopupResponse.getNarrative());
        assertEquals("200", airtimeTopupResponse.getResponseCode());
        verify(subscriberRequestDao,
                times(2)).save(any(SubscriberRequest.class));
        verify(chargingPlatform,
                times(1)).creditSubscriberAccount(any(INCreditRequest.class));
    }
}

