package org.kata.service;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class KataService {

    /**
     * premier code issu de TDD
     * Permettant de convertir to FooBarQix without Streams & Lambdas
     * @param numberToFooBar
     * @return
     */
    public static String translateToFooBarQixWithoutStream(int numberToFooBar) {
        StringBuilder result = new StringBuilder();
        if (numberToFooBar % 3 == 0)
            result.append("Foo");
        if (numberToFooBar % 5 == 0)
            result.append("Bar");

        String strFooBar = String.valueOf(numberToFooBar);
        for (int j = 0; j < strFooBar.length(); j++) {
            char element = strFooBar.charAt(j);
            if(element == '3')
                result.append("Foo");
            if(element == '5')
                result.append("Bar");
            if(element == '7')
                result.append("Qix");
        }
        if (result.length() == 0)
            result.append(strFooBar);
        return result.toString();
    }


    /**
     * Deuxième code après refacto
     * Permettant de convertir to FooBarQix with Streams & Lambdas
     * @param numberToFooBar
     * @return
     */
    public static String translateToFooBarQixWithStream(int numberToFooBar) {
        Map<Integer, String> fooBarQix = new HashMap<>();
        fooBarQix.put(3, "Foo");
        fooBarQix.put(5, "Bar");
        fooBarQix.put(7, "Qix");

        String result = fooBarQix.keySet().stream()
                .filter(key -> key != 7)
                .filter(key -> numberToFooBar % key == 0)
                .map(fooBarQix::get)
                .collect(Collectors.joining());

        String strFooBar = String.valueOf(numberToFooBar);
        result += strFooBar.chars()
                .mapToObj(integerAsChar -> fooBarQix.get(Character.getNumericValue(integerAsChar)) )
                .filter(Objects::nonNull)
                .collect(Collectors.joining());

        return result.isEmpty() ? strFooBar : result;
    }

}
