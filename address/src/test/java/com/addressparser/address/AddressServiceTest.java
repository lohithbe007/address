package com.addressparser.address;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddressServiceTest {
    @Test
    void testWithStreetNameFirst() {
        AddressService  addressService = new AddressService();
        String address = "Winterallee 3";
        String parsedAddress = addressService.parseAddress(address);
        assertEquals("{\"streetName\":\"Winterallee\",\"housenumber\":\"3\"}",parsedAddress);
    }

    @Test
    void testWithStreetNameFirstCommaSeperated() {
        AddressService  addressService = new AddressService();
        String address = "Calle Aduana, 29";
        String parsedAddress = addressService.parseAddress(address);
        assertEquals("{\"streetName\":\"Calle Aduana\",\"housenumber\":\"29\"}",parsedAddress);
    }

    @Test
    void testHouseNumberWithName() {
        AddressService  addressService = new AddressService();
        String address = "laufeldweg 123B";
        String parsedAddress = addressService.parseAddress(address);
        assertEquals("{\"streetName\":\"laufeldweg\",\"housenumber\":\"123B\"}",parsedAddress);
    }

    @Test
    void testHouseNumberWithSpaceAndName() {
        AddressService  addressService = new AddressService();
        String address = "Musterstrasse 45 b";
        String parsedAddress = addressService.parseAddress(address);
        assertEquals("{\"streetName\":\"Musterstrasse\",\"housenumber\":\"45 b\"}",parsedAddress);
    }

    @Test
    void testWithHouseNumberFirst() {
        AddressService  addressService = new AddressService();
        String address = "200 Broadway Av";
        String parsedAddress = addressService.parseAddress(address);
        assertEquals("{\"streetName\":\"Broadway Av\",\"housenumber\":\"200\"}",parsedAddress);
    }

    @Test
    void testWithHouseNumberFirstCommaSeperated() {
        AddressService  addressService = new AddressService();
        String address = "4, rue de la revolutio";
        String parsedAddress = addressService.parseAddress(address);
        assertEquals("{\"streetName\":\"rue de la revolutio\",\"housenumber\":\"4\"}",parsedAddress);
    }

    @Test
    void testStreetWithSpecialCharacters() {
        AddressService  addressService = new AddressService();
        String address = "Am Bächle 23";
        String parsedAddress = addressService.parseAddress(address);
        assertEquals("{\"streetName\":\"Am Bächle\",\"housenumber\":\"23\"}",parsedAddress);
    }

    @Test
    void testStreetWithNumberAndHouseNumber() {
        AddressService  addressService = new AddressService();
        String address = "Calle 39 No 1540";
        String parsedAddress = addressService.parseAddress(address);
        assertEquals("{\"streetName\":\"Calle 39\",\"housenumber\":\"No 1540\"}",parsedAddress);
    }

}
