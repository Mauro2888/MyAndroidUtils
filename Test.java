static void getRequestJava(String url) throws IOException{
        URL  baseUrl = new URL(url);
        OAuthConsumer consumer = new DefaultOAuthConsumer(oauth_consumer_key, ouath_consumer_secret);
        HttpURLConnection request = (HttpURLConnection)baseUrl.openConnection();
        try {
            consumer.sign(request);
        } catch (OAuthMessageSignerException | OAuthExpectationFailedException | OAuthCommunicationException e) {
            e.printStackTrace();
        }
        InputStream in = request.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder out = new StringBuilder();
        String line ;
        while ((line = reader.readLine()) != null) {
            out.append(line);
        }

        String serverResponse = out.toString();
        System.out.println(serverResponse);
        System.out.println(consumer.getRequestParameters().entrySet());
    }
