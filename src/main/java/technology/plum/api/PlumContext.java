package technology.plum.api;

import java.util.Base64;

public class PlumContext {
    private String username;
    private String password;
    public static final String BASE_URL = "https://production.plum.technology";
    private String houseAccessToken;

    public PlumContext(String username, String password) {
	super();
	this.username = username;
	this.password = password;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getAuthorizationString() {
	String s = username + ":" + password;
	return "Basic "+new String(Base64.getEncoder().encode(s.getBytes()));
    }

    public String getHouseAccessToken() {
        return houseAccessToken;
    }

    public void setHouseAccessToken(String houseAccessToken) {
        this.houseAccessToken = houseAccessToken;
    }
}
