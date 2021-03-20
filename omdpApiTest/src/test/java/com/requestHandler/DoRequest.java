package com.requestHandler;
 
import org.testng.annotations.Test;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import org.apache.http.HttpStatus;

import com.DataManager;
import com.sun.org.*;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DoRequest extends PrepareToRequest
{
    @Test
    //Search movie for taken imdb id
    public void testImdbID()
    {
        searchWithID(getSpesificFilmID(1));
    }

    //Get movie in chosen index
    public String getSpesificFilmID(int filmIndex)
    {
        Response response = requestDataSearch(searchData).when().get(baseURI).then().extract().response();
        String findFilm = new StringBuilder().append("Search[").append(filmIndex).append("].imdbID").toString();
        return response.jsonPath().getString(findFilm);
    }

    //Search With IMDB
    private void searchWithID(String imdbID)
    {
        requestDataID(imdbID).when().get(baseURI).then()
                .statusCode(HttpStatus.SC_OK).and()
                .body("Title", equalTo("Harry Potter and the Sorcerer's Stone")).and()
                .body("Year",equalTo("2001")).and()
                .body("Released",equalTo("16 Nov 2001"));
    }

    //initialize request body with search
    private RequestSpecification requestDataSearch(String searchData)
    {
        requestSpecification = given().
                param(DataManager.API_KEY.getData(), "6418d4d6").
                param(DataManager.FILM_NAME.getData(), searchData).
                param(DataManager.TYPE.getData(), "").
                param(DataManager.YEAR.getData(), "").
                param(DataManager.DATA_TYPE.getData(), "json").
                param(DataManager.PAGE.getData(), "1").
                param(DataManager.CALLBACK.getData(), "").
                param(DataManager.APIVERSION.getData(), "1");
        return requestSpecification;
    }

    //initialize request body with id
    public RequestSpecification requestDataID(String id)
    {
        requestSpecification = given().
                param(DataManager.API_KEY.getData(), "6418d4d6").
                param(DataManager.ID.getData(), id).
                param(DataManager.TITLE.getData(), "").
                param(DataManager.TYPE.getData(), "").
                param(DataManager.YEAR.getData(), "").
                param(DataManager.PLOT.getData(), "short").
                param(DataManager.DATA_TYPE.getData(), "json").
                param(DataManager.CALLBACK.getData(), "").
                param(DataManager.APIVERSION.getData(), "1");
        return requestSpecification;
    }
}