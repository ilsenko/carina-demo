package com.qaprosoft.carina.demo.api.ilsen.user;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.api.annotation.Endpoint;
import com.qaprosoft.carina.core.foundation.api.annotation.HideRequestBodyPartsInLogs;
import com.qaprosoft.carina.core.foundation.api.annotation.RequestTemplatePath;
import com.qaprosoft.carina.core.foundation.api.annotation.ResponseTemplatePath;
import com.qaprosoft.carina.core.foundation.api.http.HttpMethodType;
import com.qaprosoft.carina.core.foundation.utils.Configuration;
import io.restassured.response.Response;

@Endpoint(url = "${base_url}/api/iam/v1/auth/login", methodType = HttpMethodType.POST)
@RequestTemplatePath(path = "/api/user/post/rq.json")
@ResponseTemplatePath(path = "/api/user/post/rs.json")
@HideRequestBodyPartsInLogs(paths = {"username" , "password"})
public class LoginUserByCredentialsMethod extends AbstractApiMethodV2 {

    public LoginUserByCredentialsMethod(String username, String password) {
        replaceUrlPlaceholder("base_url" , Configuration.getEnvArg("api_url"));
        addProperty("password", password);
        addProperty("username", username);
    }

    public void expectResponseStatus(String s) {
    }

    public String callApiMethod(AbstractApiMethodV2 method){
        Response response = method.callAPI();
        return response.asString().isEmpty() ? null : response.asString();
    }
}
