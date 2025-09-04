/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.v2.term;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author BRIN
 */
public class ValidCharacters {
    
    public static final List<String> VALID_ALPHABETS = Collections.unmodifiableList(Arrays.asList(
        "a",
        "b",
        "c",
        "d",
        "e",
        "f",
        "g",
        "h",
        "i",
        "j",
        "k",
        "l",
        "m",
        "n",
        "o",
        "p",
        "q",
        "r",
        "s",
        "t",
        "u",
        "v",
        "w",
        "x",
        "y",
        "z"
    ));
    
    public static final List<String> VALID_SPECIAL_CHARACTERS = Collections.unmodifiableList(Arrays.asList(
        "_",
        "-"
    ));
}
