//my example get all text from <tag>asacas</tag>

//REGEX PATTERN <(.+)>([^<]+)</\\1>

String text = "<h1>Nayeem loves counseling</h1>" +
                "<h1><h1>Sanjay has no watch</h1></h1><par>So wait for a while</par>" +
                "<Amee>safat codes like a ninja</amee>" +
                "<SA premium>Imtiaz has a secret crush</SA premium>";

        Pattern pattern = Pattern.compile("<(.+)>([^<]+)</\\1>");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()){
            System.out.println(matcher.group(2));
        }
