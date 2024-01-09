package Service;

import DAO.CredentialDAO;
import RequestModel.Credential;
import RequestModel.RequestInputModel;
import ResponseModel.BaseResponse;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static Utilities.Utility.*;

public class ThirdPartyService {

    public static String getTokenAuth() throws SQLException {
        log.info("Enter getTokenAuth...");
        BaseResponse credentialsResponse = CredentialDAO.getCredentialsByName(new Credential("DOXTER"));
        Credential credentials = mapToCredentials(credentialsResponse);

        RequestInputModel<Credential> myRequest = new RequestInputModel<>();
        myRequest.setURL(credentials.getApiUrl() + "auth/tokenAuth/");

        List<String[]> headers = new ArrayList<>();
        headers.add(new String[]{"Content-Type", "application/json"});
        myRequest.setHeaders(headers);

        myRequest.setBody(credentials);

        String response = postRequest(myRequest);

        return response;
    }

    public static Credential mapToCredentials(BaseResponse response) {
        if (!response.getStatus().equals("Error")) {
            Credential credentials = new Credential();
            Map<String, Object> credsMap = convertJsonObjectToMap(response.getData());

            credentials.setId(Long.valueOf((Integer) credsMap.get("id")));
            credentials.setName((String) credsMap.get("name"));
            credentials.setApiUrl((String) credsMap.get("apiUrl"));
            credentials.setUsername((String) credsMap.get("username"));
            credentials.setPassword((String) credsMap.get("password"));

            return credentials;
        } else {
            return null;
        }
    }
}
