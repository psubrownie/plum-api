package technology.plum.api;

import java.io.StringWriter;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import technology.plum.api.beans.House;
import technology.plum.api.beans.Lightpad;
import technology.plum.api.beans.LogicalLoad;
import technology.plum.api.beans.Room;

public class HouseService {

    public static List<House> getHouses(PlumContext ctx) {
	try {
	    CloseableHttpClient httpclient = HttpClients.createDefault();
	    HttpGet httpGet = new HttpGet(ctx.BASE_URL + "/v2/getHouses");
	    httpGet.addHeader("User-Agent", "Plum/2.3.0 (iPhone; iOS 9.2.1; Scale/2.00)");
	    httpGet.addHeader("Authorization", ctx.getAuthorizationString());
	    CloseableHttpResponse response1 = httpclient.execute(httpGet);
	    HttpEntity entity1 = response1.getEntity();
	    StringWriter writer = new StringWriter();
	    IOUtils.copy(entity1.getContent(), writer, "UTF-8");
	    String theString = writer.toString();
	    response1.close();

	    Type listType = new TypeToken<List<String>>() {
	    }.getType();
	    List<String> hids = new Gson().fromJson(theString, listType);
	    List<House> houses = new ArrayList<>();
	    for (String hid : hids) {
		houses.add(getHouse(ctx, hid));
	    }

	    return houses;

	} catch (Exception e) {

	}

	return null;
    }

    public static House getHouse(PlumContext ctx, String hid) throws Exception {
	String resp = plumPostProd(ctx, "/v2/getHouse", "{\"hid\":\"" + hid + "\"}");
	House house = new Gson().fromJson(resp, House.class);
	ctx.setHouseAccessToken(createAccessToken(house.getAccessToken()));

	List<Room> rooms = new ArrayList<>();
	for (String rid : house.getRids()) {
	    rooms.add(getRoom(ctx, rid));
	}
	house.setRooms(rooms);
	return house;
    }

    private static String createAccessToken(String houseToken) throws NoSuchAlgorithmException {
	MessageDigest md = MessageDigest.getInstance("SHA-256");
	md.update(houseToken.getBytes());
	byte[] mdbytes = md.digest();

	// convert the byte to hex format method 1
	StringBuilder sb = new StringBuilder();
	for (int i = 0; i < mdbytes.length; i++) {
	    sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
	}
	return sb.toString();
    }

    public static Room getRoom(PlumContext ctx, String rid) throws Exception {
	String resp = plumPostProd(ctx, "/v2/getRoom", "{\"rid\":\"" + rid + "\"}");
	Room room = new Gson().fromJson(resp, Room.class);
	List<LogicalLoad> loads = new ArrayList<>();
	for (String llid : room.getLlids()) {
	    loads.add(getLogicalLoad(ctx, llid));
	}
	room.setLloads(loads);
	return room;
    }

    public static LogicalLoad getLogicalLoad(PlumContext ctx, String llid) throws Exception {
	String resp = plumPostProd(ctx, "/v2/getLogicalLoad", "{\"llid\":\"" + llid + "\"}");
	LogicalLoad load = new Gson().fromJson(resp, LogicalLoad.class);
	List<Lightpad> lightPads = new ArrayList<>();
	for (String lpid : load.getLpids()) {
	    lightPads.add(getLightpad(ctx, lpid));
	}
	load.setLightPads(lightPads);
	return load;
    }

    public static Lightpad getLightpad(PlumContext ctx, String lpid) throws Exception {
	String resp = plumPostProd(ctx, "/v2/getLightpad", "{\"lpid\":\"" + lpid + "\"}");
	Lightpad load = new Gson().fromJson(resp, Lightpad.class);
	return load;
    }

    public static void setLogicalLoadGlow() {

    }

    public static void setLogicalLoadConfig() {

    }

    public static void triggerScene(PlumContext ctx, String sid) throws Exception {
	String resp = plumPostProd(ctx, "/v2/triggerScene", "{\"sid\":\"" + sid + "\"}");
    }

    public static void setLevel(PlumContext ctx, LogicalLoad logicalLoad, Integer level) throws Exception {
	setLevel(ctx, logicalLoad.getLlid(), level);
    }

    public static void setLevel(PlumContext ctx, String llid, Integer level) throws Exception {
	plumPostLocal(ctx, "192.168.1.201", "/v2/setLogicalLoadLevel",
		"{\"llid\":\"" + llid + "\",\"level\":" + level.toString() + "}");
    }

    private static String plumPostProd(PlumContext ctx, String uri, String body) throws Exception {

	CloseableHttpClient httpclient = HttpClients.createDefault();
	HttpPost httpPost = new HttpPost(ctx.BASE_URL + uri);
	httpPost.addHeader("User-Agent", "Plum/2.3.0 (iPhone; iOS 9.2.1; Scale/2.00)");
	httpPost.addHeader("Authorization", ctx.getAuthorizationString());
	if (body != null) {
	    StringEntity input = new StringEntity(body);
	    input.setContentType("application/json");
	    httpPost.setEntity(input);
	}

	CloseableHttpResponse response2 = httpclient.execute(httpPost);

	String resp = null;
	int returnCode = response2.getStatusLine().getStatusCode();
	if (200 == returnCode) {
	    try {
		System.out.println(response2.getStatusLine());
		HttpEntity entity2 = response2.getEntity();

		StringWriter writer = new StringWriter();
		IOUtils.copy(entity2.getContent(), writer, "UTF-8");
		resp = writer.toString();
		response2.close();

		EntityUtils.consume(entity2);

	    } finally {
		response2.close();
	    }

	    return resp;

	} else if (204 == returnCode) {
	    // no response
	    return "";
	} else {

	    throw new RuntimeException("Error");
	}

    }

    private static String plumPostLocal(PlumContext ctx, String ip, String uri, String body) throws Exception {

	HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;

	SSLContextBuilder builder = new SSLContextBuilder();
	builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());

	SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build(), hostnameVerifier);

	Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create()
		.register("http", new PlainConnectionSocketFactory()).register("https", sslsf).build();

	CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf)
		.setSSLHostnameVerifier(hostnameVerifier).build();

	// CloseableHttpClient httpclient = HttpClients.createDefault();
	HttpPost httpPost = new HttpPost("https://" + ip + ":8443" + uri);
	httpPost.addHeader("User-Agent", "Plum/2.3.0 (iPhone; iOS 9.2.1; Scale/2.00)");
	if (ctx.getHouseAccessToken() != null) {
	    httpPost.addHeader("X-Plum-House-Access-Token", ctx.getHouseAccessToken());
	} else
	    httpPost.addHeader("Authorization", ctx.getAuthorizationString());
	if (body != null) {
	    StringEntity input = new StringEntity(body);
	    input.setContentType("application/json");
	    httpPost.setEntity(input);
	}

	CloseableHttpResponse response2 = httpclient.execute(httpPost);

	String resp = null;

	try {
	    System.out.println(response2.getStatusLine());
	    HttpEntity entity2 = response2.getEntity();

	    StringWriter writer = new StringWriter();
	    IOUtils.copy(entity2.getContent(), writer, "UTF-8");
	    resp = writer.toString();
	    response2.close();

	    EntityUtils.consume(entity2);

	} finally {
	    response2.close();
	}
	if (200 == response2.getStatusLine().getStatusCode()) {
	    System.err.println("Error" + resp);
	}
	return resp;
    }

}
