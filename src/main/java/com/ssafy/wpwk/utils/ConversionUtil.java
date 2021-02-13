package com.ssafy.wpwk.utils;

public class ConversionUtil {

    /**
     * 10진수 형태 -> 2진수 변환
     */
    public static String decimalToBinary(String data) {

        if(data == null) return null;
        int dataToInteger = Integer.parseInt(data);

        String binaryData = Integer.toBinaryString(dataToInteger);

        if(binaryData.length() == 8) { return binaryData; }

        StringBuffer sb = new StringBuffer();

        int len = binaryData.length();
        while(len++ != 8) {
            sb.append(0);
        }

        sb.append(binaryData);

        return sb.toString();
    }
}
