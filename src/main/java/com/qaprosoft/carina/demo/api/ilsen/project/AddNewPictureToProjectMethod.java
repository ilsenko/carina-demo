package com.qaprosoft.carina.demo.api.ilsen.project;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.api.annotation.Endpoint;
import com.qaprosoft.carina.core.foundation.api.annotation.ResponseTemplatePath;
import com.qaprosoft.carina.core.foundation.api.http.HttpMethodType;
import com.qaprosoft.carina.core.foundation.utils.Configuration;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

@Endpoint(url = "https://ilsen.zebrunner.org/api/reporting/v1/test-runs/${testRunId}/tests/${testId}/screenshots/", methodType = HttpMethodType.POST)
@ResponseTemplatePath(path = "api/screenshot/post/rs.json")
public class AddNewPictureToProjectMethod extends AbstractApiMethodV2 {

    public AddNewPictureToProjectMethod(int testRunId, int testId, String filePath, String token) {
        setHeaders("Authorization=Bearer " + token);
        replaceUrlPlaceholder("base_url" , Configuration.getEnvArg("api_url"));
        replaceUrlPlaceholder("testRunId", String.valueOf(testRunId));
        replaceUrlPlaceholder("testId", String.valueOf(testId));
        setHeaders("Content-Type=image/png");

        try {
            getRequest().body(FileUtils.readFileToByteArray(new File(filePath).getAbsoluteFile()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Sms went wrong");
        }
    }

    public void expectResponseStatus(String s) {
    }
}
