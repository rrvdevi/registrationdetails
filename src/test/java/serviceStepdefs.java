 
ServiceStepdefs:


public int responsecode;

    public HttpURLConnection conn;

    @Before
    public void setUp()throws IOException {

      Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.1.4.44", 8080));
      URL url = new URL(
                "https://api.registrationdetails/SEZ123");
         conn = (HttpURLConnection) url.openConnection(proxy);

          }

    @After
    public void teardown() {

        conn.disconnect();
    }

    @Given("send a get request for a registrartiondetails")
    public void iMakeAGetRequest() throws IOException {
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Authorization", "Bearer 4c465132-7daf-48c1-8bf2-08d6a7bf39c2");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.connect();
            responsecode = conn.getResponseCode();
            assertEquals(200,responsecode);
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException(" HTTP error code : "
                + conn.getResponseCode());
            }
    }

    @Then("the client receives status code of {int}")
    public void theClientReceivesStatusCodeOf(int statusCode) throws IOException {
        assertEquals(statusCode,conn.getResponseCode());

    }


}




