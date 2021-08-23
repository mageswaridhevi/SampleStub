package TestRunner;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class WireMockRuleTest {
@Rule
public WireMockRule wm=new WireMockRule(wireMockConfig.port(8080));

@Test
public void wiremockTest() throws IOException
{
	configureStubs();
	OKHttpClient  client = new OKHttpClient(.newBuilder().build());
	Request request=new Request().builder().url("http://localhost/checkBattery").method(method:"GET",body:null);
	Response response = client.newCall(request).execute();
	
	assertEquals(expected:"Green",response.body().string());
	
	verify(getRequestedFor(urlEqualTo(testUrl:"checkBattery")));
}

private void configureStubs() {
	// TODO Auto-generated method stub
	configureFor(host:"localhost",port:8080);
	stubFor(get(urlEqualTo(testUrl:"checkBattery")).willReturn(aResponse().withBody("Green")));
	
}
}
