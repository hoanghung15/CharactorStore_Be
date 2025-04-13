package com.example.enums;

public enum CountryEnum {
    VIETNAM("VIETNAM"),
    UNITED_STATES("UNITED_STATES"),
    CANADA("CANADA"),
    UNITED_KINGDOM("UNITED_KINGDOM"),
    FRANCE("FRANCE"),
    GERMANY("GERMANY"),
    JAPAN("JAPAN"),
    SOUTH_KOREA("SOUTH_KOREA"),
    CHINA("CHINA"),
    INDIA("INDIA"),
    AUSTRALIA("AUSTRALIA"),
    BRAZIL("BRAZIL"),
    RUSSIA("RUSSIA"),
    ITALY("ITALY"),
    SPAIN("SPAIN"),
    MEXICO("MEXICO"),
    INDONESIA("INDONESIA"),
    THAILAND("THAILAND"),
    MALAYSIA("MALAYSIA"),
    PHILIPPINES("PHILIPPINES"),
    SOUTH_AFRICA("SOUTH_AFRICA");

    public final String value;

    CountryEnum(String value) {
        this.value = value;
    }
}

