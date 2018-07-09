package com.example.kafein.otogalerim.RestApi;

/**
 * Created by Kafein on 5/10/2018.
 */

public class BaseManager {

    protected RestApi getRestApi()
    {
        RestApiClient restApiClient= new RestApiClient(BaseUrl.url);
        return  restApiClient.getmRestApi();
    }

}

