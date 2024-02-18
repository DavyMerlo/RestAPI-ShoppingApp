package com.davy.restapi.customercard.mapper;

import com.davy.restapi.card.entity.CustomerCard;
import com.davy.restapi.card.mapper.CardMapper;
import com.davy.restapi.card.dto.CardRequest;
import com.davy.restapi.shared.mapper.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomerCardMapperTest {

    private ObjectMapper<CardRequest, CustomerCard> customerCardMapper;
    private CardRequest source;
    private CustomerCard destination;

    @BeforeEach
    public void setUp() {
        customerCardMapper = new CardMapper();
        source = mock(CardRequest.class);
        destination = new CustomerCard();
    }

    @Test
    public void shouldMapCustomerCardRequestToCustomerCard() {
        when(source.getPoints()).thenReturn((byte) 100);
        when(source.getNumber()).thenReturn("123456789");

        CustomerCard result = (CustomerCard) customerCardMapper.mapSourceToDestination(source, destination);

        assertEquals(100, result.getPoints());
        assertEquals("123456789", result.getNumber());
    }
}
