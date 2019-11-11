package com.dev.ext.asansor.RestApi;

public class BaseManager {

    protected RestApi getRestApi()
    {
        RestApiClient restApiClient= new RestApiClient(BaseUrl.url);
        return  restApiClient.getmRestApi();
    }

}
