package com.addressparser.address;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
public class AddressService {

    public String parseAddress(String address) {
        String json = "";

        HashMap<String,String> regexRuleMap = new HashMap<String, String>();
        regexRuleMap.put("1", "^[^0-9]+\\d+[^\\d]*$");
        regexRuleMap.put("2", "^[0-9]+[,]*+[^\\d]*$");
        regexRuleMap.put("3", "^[^0-9]+\\d+[^\\d]+\\d+[^\\d]*$");

        Address addressParsed = regexRuleMap
            .entrySet()
            .stream()
            .filter(key -> address.matches(key.getValue()))
            .map(key -> getAddress(key.getKey(), address.trim()))
            .findFirst().orElse(null);

        if(Objects.nonNull(addressParsed)) {
            Gson gson = new Gson();
             json = gson.toJson(addressParsed);
        }

    return json;

    }

    private Address getAddress(String rule, String addresstoParse) {
        Address  address = new Address();
        if("1".equalsIgnoreCase(rule)) {
            addresstoParse = addresstoParse.replaceAll(",", "");
            String[] addressArray = addresstoParse.split("(?=\\d)",2);

            address.setStreetName(addressArray[0].trim());
            address.setHousenumber(addressArray[1].trim());
        }
        if("2".equalsIgnoreCase(rule)) {
            String[] addressArray;
            if(addresstoParse.contains(",")){
                addressArray = addresstoParse.split(",");
            }else{
                addressArray =  addresstoParse.split("(?<=\\D)",2);
            }
            address.setStreetName(addressArray[1].trim());
            address.setHousenumber(addressArray[0].trim());
        }
        if("3".equalsIgnoreCase(rule)){
            String[] addressArray = addresstoParse.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
            StringBuilder houseNummer = new StringBuilder();
            for (int i = 0; i < addressArray.length; i++) {
                if (i>1){
                    houseNummer.append(addressArray[i]);
                }
            }

            address.setStreetName(String.format("%s%s", addressArray[0],addressArray[1]).trim());
            address.setHousenumber(houseNummer.toString().trim());
        }
        return address;
    }

}
