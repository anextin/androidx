package com.example.ext.asansor.RestApi;

public class BaseManager {

    protected RestApi getRestApi()
    {
        RestApiClient restApiClient= new RestApiClient(BaseUrl.url);
        return  restApiClient.getmRestApi();
    }

}
