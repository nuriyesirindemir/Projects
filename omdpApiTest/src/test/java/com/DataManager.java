package com;

public enum DataManager
{
    API_KEY("apikey"),
    FILM_NAME("s"),
    TITLE("t"),
    TYPE("type"),
    YEAR("y"),
    PLOT("plot"),
    DATA_TYPE("r"),
    CALLBACK("callback"),
    APIVERSION("v"),
    PAGE("page"),
    ID("i");

    private String data;

    DataManager(String data)
    {
        this.data = data;
    }

    public String getData()
    {
        return data;
    }
}
