package com.qaprosoft.carina.demo.api.ilsen.project;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.api.annotation.Endpoint;
import com.qaprosoft.carina.core.foundation.api.annotation.ResponseTemplatePath;
import com.qaprosoft.carina.core.foundation.api.http.HttpMethodType;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

@Endpoint(url = "${base_url}/api/projects/v1/projects/SDQ", methodType = HttpMethodType.DELETE)
@ResponseTemplatePath(path = "api/project/delete/rs.json")
public class DeleteNewProjectMethod extends AbstractApiMethodV2 {

    public DeleteNewProjectMethod(String token) {
        setHeaders("Authorization=Bearer " + token);
        replaceUrlPlaceholder("base_url" , Configuration.getEnvArg("api_url"));
    }

    public void expectResponseStatus(String s) {
    }
}
