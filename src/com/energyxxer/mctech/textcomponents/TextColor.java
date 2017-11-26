package com.energyxxer.mctech.textcomponents;

public enum TextColor {
    BLACK('0'),
    DARK_BLUE('1'),
    DARK_AQUA('2'),
    DARK_GREEN('3'),
    DARK_RED('4'),
    DARK_PURPLE('5'),
    GOLD('6'),
    LIGHT_GRAY('7'),
    DARK_GRAY('8'),
    BLUE('9'),
    GREEN('a'),
    AQUA('b'),
    RED('c'),
    LIGHT_PURPLE('d'),
    YELLOW('e'),
    WHITE('f');

    private char code;

    TextColor(char code) {
        this.code = code;
    }

    public char getCodeChar() {
        return code;
    }
}
