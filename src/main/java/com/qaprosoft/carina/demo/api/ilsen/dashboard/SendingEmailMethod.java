package com.qaprosoft.carina.demo.api.ilsen.dashboard;

import java.io.File;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.api.annotation.Endpoint;
import com.qaprosoft.carina.core.foundation.api.http.HttpMethodType;
import com.qaprosoft.carina.core.foundation.utils.Configuration;




@Endpoint(url = "https://ilsen.zebrunner.org/api/reporting/api/project-dashboards/${dashBoardId}/email?file=", methodType = HttpMethodType.POST)
public class SendingEmailMethod extends AbstractApiMethodV2 {



    public SendingEmailMethod(File file, File email, int dashBoardId, String token) {
        setHeaders("Content-Type=multipart/form-data");
        setHeaders("Authorization=Bearer " + token);
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
        replaceUrlPlaceholder("dashBoardId", String.valueOf(dashBoardId));
        request.multiPart("file", file, "image/png").multiPart("email", email, "application/json");
    }

    public void expectResponseStatus(String s) {
    }
}
