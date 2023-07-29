package com.microservices.numerology.domain.exception;

import java.util.LinkedHashMap;
import java.util.Map;

public class DomainException extends RuntimeException {

    private final DomainCode domainCode;

    private final Map<String, Object> moreInfo;


    public DomainException(DomainCode domainCode, Map<String, Object> moreInfo) {
        super(String.format("%s - moreInfo: %s", domainCode, moreInfo));

        this.domainCode = domainCode;
        this.moreInfo = moreInfo;
    }

    public DomainException(DomainCode domainCode, Map<String, Object> moreInfo, Throwable cause) {
        super(String.format("%s - moreInfo: %s", domainCode, moreInfo), cause);

        this.domainCode = domainCode;
        this.moreInfo = moreInfo;
    }

    public DomainCode getDomainCode() {
        return domainCode;
    }

    public Object[] getArgs() {
        return moreInfo
                .values()
                .toArray();
    }

    public Map<String, Object> getMoreInfo() {
        return moreInfo;
    }

    public static LinkedHashMap<String, Object> buildMoreInfo(Object... arg) {
        if (arg.length % 2 != 0) {
            throw new IllegalArgumentException("Invalid key value pair");
        }
        var map = new LinkedHashMap<String, Object>();
        for (int i = 0; i < arg.length; i = i + 2) {
            map.put((String) arg[i], arg[i + 1]);
        }
        return map;
    }
}
