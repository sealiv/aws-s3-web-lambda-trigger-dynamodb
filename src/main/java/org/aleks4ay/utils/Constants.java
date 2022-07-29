package org.aleks4ay.utils;

public class Constants {
    public final static String BUCKET_NAME = "s3-epam-web";
    public final static String S3_FILE_MAME = "hello.html";
    public static final String TABLE_NAME = "products";

    public static final String HTML_CONTENT_START =
            "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "  <title>Static S3 site Example</title>\n" +
                    "  <meta charset=\"utf-8\">\n" +
                    "  <meta name=\"viewport\" cogntent=\"width=device-width, initial-scale=1\">\n" +
                    "  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">\n" +
                    "  <link rel=\"stylesheet\" href=\"style.css\">\n" +
                    "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\n" +
                    "  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js\"></script>\n" +
                    "  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js\"></script>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "  <h2 style=\"text-align: center;\">List of Products</h2>\n" +
                    "  <hr/>\n" ;

    public static final String HTML_CONTENT_END = "</body> </html>";
}
