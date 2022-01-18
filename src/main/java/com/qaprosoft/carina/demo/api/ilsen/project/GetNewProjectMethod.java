package com.qaprosoft.carina.demo.api.ilsen.project;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.api.annotation.Endpoint;
import com.qaprosoft.carina.core.foundation.api.annotation.ResponseTemplatePath;
import com.qaprosoft.carina.core.foundation.api.http.HttpMethodType;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

@Endpoint(url = "${base_url}/api/projects/v1/projects/1", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/project/get/rs.json")
public class GetNewProjectMethod extends AbstractApiMethodV2 {

    public GetNewProjectMethod(String token) {
        setHeaders("Authorization=Bearer " + token);
        replaceUrlPlaceholder("base_url" , Configuration.getEnvArg("api_url"));
    }

    public void expectResponseStatus(String s) {
    }
}
