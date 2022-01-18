package com.qaprosoft.carina.demo.zebApi;

import java.io.File;

import io.restassured.path.json.JsonPath;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.demo.api.ilsen.dashboard.SendingEmailMethod;
import com.qaprosoft.carina.demo.api.ilsen.project.AddNewPictureToProjectMethod;
import com.qaprosoft.carina.demo.api.ilsen.project.CreateNewProjectMethod;
import com.qaprosoft.carina.demo.api.ilsen.project.DeleteNewProjectMethod;
import com.qaprosoft.carina.demo.api.ilsen.project.GetNewProjectMethod;
import com.qaprosoft.carina.demo.api.ilsen.project.GetPictureFromProject;
import com.qaprosoft.carina.demo.api.ilsen.user.LoginUserByCredentialsMethod;
import com.qaprosoft.carina.demo.constants.Constants;
import com.zebrunner.agent.core.annotation.Maintainer;

@Maintainer("ilsen")
public class ZebrunnerTestsAPI implements IAbstractTest {

    @Test()
    public void loginUser() {
        String username = R.TESTDATA.get("username");
        String password = R.TESTDATA.get("password");
        LoginUserByCredentialsMethod loginUser = new LoginUserByCredentialsMethod(username, password);
        loginUser.expectResponseStatus("HTTP/1.1 200");
        loginUser.callAPI();
        loginUser.validateResponse();
    }

    @Test()
    public void loginWithWrongUsername()  {

        String username = R.TESTDATA.get("wrongUser");
        String password = R.TESTDATA.get("password");
        LoginUserByCredentialsMethod wrongUserName = new LoginUserByCredentialsMethod(username, password);
        wrongUserName.expectResponseStatus("HTTP/1.1 401");
        wrongUserName.callAPI();
        wrongUserName.validateResponse();
    }


    @Test
    public void getProject()  {
        String username = R.TESTDATA.get("username");
        String password = R.TESTDATA.get("password");
        LoginUserByCredentialsMethod loginUser = new LoginUserByCredentialsMethod(username, password);
        String rsString = loginUser.callApiMethod(new LoginUserByCredentialsMethod(username, password));
        String token = JsonPath.from(rsString).getString(Constants.AUTH_TOKEN);
        GetNewProjectMethod apiProject = new GetNewProjectMethod(token);
        apiProject.expectResponseStatus("HTTP/1.1 200");
        apiProject.callAPI();
        apiProject.validateResponse();
    }

    @Test
    public void createProject()  {
        String username = R.TESTDATA.get("username");
        String password = R.TESTDATA.get("password");
        LoginUserByCredentialsMethod loginUser = new LoginUserByCredentialsMethod(username, password);
        String rsString = loginUser.callApiMethod(new LoginUserByCredentialsMethod(username, password));
        String token = JsonPath.from(rsString).getString(Constants.AUTH_TOKEN);
        CreateNewProjectMethod apiProject = new CreateNewProjectMethod(token);
        apiProject.expectResponseStatus("HTTP/1.1 200");
        apiProject.callAPI();
        apiProject.validateResponse();
    }

    @Test
    public void createProjectNegative()  {
        String username = R.TESTDATA.get("username");
        String password = R.TESTDATA.get("password");
        LoginUserByCredentialsMethod loginUser = new LoginUserByCredentialsMethod(username, password);
        String rsString = loginUser.callApiMethod(new LoginUserByCredentialsMethod(username, password));
        String token = JsonPath.from(rsString).getString(Constants.AUTH_TOKEN);
        CreateNewProjectMethod negativeProject = new CreateNewProjectMethod(token);
        negativeProject.removeProperty("key");
        negativeProject.expectResponseStatus("HTTP/1.1 401");
        negativeProject.callAPI();
    }

    @Test
    public void deleteProject()  {
        String username = R.TESTDATA.get("username");
        String password = R.TESTDATA.get("password");
        LoginUserByCredentialsMethod login = new LoginUserByCredentialsMethod(username, password);
        String rsString = login.callApiMethod(new LoginUserByCredentialsMethod(username, password));
        String token = JsonPath.from(rsString).getString(Constants.AUTH_TOKEN);
        DeleteNewProjectMethod deleteProject = new DeleteNewProjectMethod(token);
        deleteProject.expectResponseStatus("HTTP/1.1 200");
        deleteProject.callAPI();
    }

    @Test
    public void postPicture() {
        String username = R.TESTDATA.get("username");
        String password = R.TESTDATA.get("password");
        LoginUserByCredentialsMethod login = new LoginUserByCredentialsMethod(username, password);
        String rsString = login.callApiMethod(new LoginUserByCredentialsMethod(username, password));
        String token = JsonPath.from(rsString).getString(Constants.AUTH_TOKEN);
        AddNewPictureToProjectMethod addPicture = new AddNewPictureToProjectMethod(1, 1, "screenshot.png", token);
        addPicture.expectResponseStatus("HTTP/1.1 201");
        addPicture.callAPI();
        addPicture.validateResponse();
    }



    @Test
    public void getPicture()  {
        String username = R.TESTDATA.get("username");
        String password = R.TESTDATA.get("password");
        LoginUserByCredentialsMethod login = new LoginUserByCredentialsMethod(username, password);
        String rsString = login.callApiMethod(new LoginUserByCredentialsMethod(username, password));
        String token = JsonPath.from(rsString).getString(Constants.AUTH_TOKEN);
        GetPictureFromProject getPictureFromProject = new GetPictureFromProject(1, 1, token);
        getPictureFromProject.expectResponseStatus("HTTP/1.1 201");
        getPictureFromProject.callAPI();
        getPictureFromProject.validateResponse();
    }

    @Test
    public void sendDashBoardEmail()  {
        File file = FileUtils.getFile("screenshot.png");
        File email  = FileUtils.getFile("email.json");
        String username = R.TESTDATA.get("username");
        String password = R.TESTDATA.get("password");
        LoginUserByCredentialsMethod login = new LoginUserByCredentialsMethod(username, password);
        String rsString = login.callApiMethod(new LoginUserByCredentialsMethod(username, password));
        String token = JsonPath.from(rsString).getString(Constants.AUTH_TOKEN);
        SendingEmailMethod sendingEmailMethod = new SendingEmailMethod(file, email, 1, token);
        sendingEmailMethod.expectResponseStatus("HTTP/1.1 201");
        sendingEmailMethod.callAPI();
    }
}
