package technology.plum.api.beans;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class House {
	private List<String> rids;
	private List<Room> rooms;
	private String location;
	private String hid;
	private List<Scene> scenes;

	@SerializedName("house_name")
	private String name;

	@SerializedName("house_access_token")
	private String accessToken;

	@SerializedName("local_tz")
	private Number tz;

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getHid() {
		return hid;
	}

	public void setHid(String hid) {
		this.hid = hid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Number getTz() {
		return tz;
	}

	public void setTz(Number tz) {
		this.tz = tz;
	}

	public List<String> getRids() {
		return rids;
	}

	public void setRids(List<String> rids) {
		this.rids = rids;
	}

	public List<Scene> getScenes() {
		return scenes;
	}

	public void setScenes(List<Scene> scenes) {
		this.scenes = scenes;
	}
}
