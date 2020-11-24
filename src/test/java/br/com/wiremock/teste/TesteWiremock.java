package br.com.wiremock.teste;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static io.restassured.RestAssured.*;

public class TesteWiremock {

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(8090);

	@Before
	public void setupStub() {

		stubFor(get(urlEqualTo("/exemplo/wiremock")).willReturn(aResponse().withHeader("Content-Type", "text/plain")
				.withStatus(200).withBody("Você está testando um exemplo de Wiremock")));
	}

	@Test
	public void testeStatusCodePositive() {

		given().when().get("http://localhost:8090/exemplo/wiremock").then().assertThat().statusCode(200);
		verify(1, getRequestedFor(urlEqualTo("/exemplo/wiremock")));

	}

}
