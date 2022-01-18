package com.qaprosoft.carina.demo.api.ilsen.project;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.api.annotation.Endpoint;
import com.qaprosoft.carina.core.foundation.api.annotation.RequestTemplatePath;
import com.qaprosoft.carina.core.foundation.api.annotation.ResponseTemplatePath;
import com.qaprosoft.carina.core.foundation.api.http.HttpMethodType;
import com.qaprosoft.carina.core.foundation.utils.Configuration;


@Endpoint(url = "https://ilsen.zebrunner.org/api/reporting/v1/test-runs/${testRunId}/tests/${testId}/screenshots/", methodType = HttpMethodType.DELETE)
@RequestTemplatePath(path = "api/screenshot/delete/rq.json")
@ResponseTemplatePath(path = "api/screenshot/delete/rs.json")

public class DeletePictureFromNewProject extends AbstractApiMethodV2 {


    public DeletePictureFromNewProject (int testRunId, int testId, String token){
        setHeaders("Authorization=Bearer " + token);
        replaceUrlPlaceholder("baseUrl", Configuration.getEnvArg("api_url"));
        replaceUrlPlaceholder("testRunId", String.valueOf(testRunId));
        replaceUrlPlaceholder("testId", String.valueOf(testId));
    }


    public void expectResponseStatus(String s) {
    }
}