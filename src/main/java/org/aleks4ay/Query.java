package org.aleks4ay;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import org.aleks4ay.domain.Product;
import org.aleks4ay.utils.Constants;
import org.aleks4ay.utils.S3Utils;

import java.util.List;

import static org.aleks4ay.utils.Constants.BUCKET_NAME;
import static org.aleks4ay.utils.Constants.S3_FILE_MAME;
import static org.aleks4ay.utils.Constants.TABLE_NAME;

public class Query {

    public static void main(String[] args) {
        new Query().handleRequest();
    }

    public String handleRequest() {
        List<Product> productsFromDynamoDB = new Query().getProductsFromDynamoDB();
        String htmlContent = Constants.HTML_CONTENT_START + getItems(productsFromDynamoDB) + Constants.HTML_CONTENT_END;
        S3Utils.putStringToS3(htmlContent, BUCKET_NAME, S3_FILE_MAME);
        return htmlContent;
    }

    private List<Product> getProductsFromDynamoDB() {
        DynamoDBMapperConfig mapperConfig = new DynamoDBMapperConfig
                .Builder()
                .withTableNameOverride(DynamoDBMapperConfig.TableNameOverride.withTableNameReplacement(TABLE_NAME))
                .build();

        final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
        DynamoDBMapper mapper = new DynamoDBMapper(client, mapperConfig);

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();

        List<Product> products = mapper.scan(Product.class, scanExpression);

        products.forEach(System.out::println);
        return products;
    }

    private String getItems(List<Product> products) {
        if (products.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder("<div class=\"container\">\n");// open 'container'

        for (int i = 0; i < products.size(); i++) {
            if (i % 3 == 0) {
                sb.append("<div class=\"row\">\n"); // open 'row'
            }
            Product product = products.get(i);

            sb.append(getCard(product));

            if ( (i + 1) % 3 == 0 || i + 1 == products.size()) {
                sb.append("</div>\n"); //close 'row'
            }
        }
        sb.append("</div>").append(System.lineSeparator()); //close 'container'
        return sb.toString();
    }

    private String getCard(Product product) {
        StringBuilder sb = new StringBuilder();
        sb.append("<div class=\"card col-sm-3\">\n");
        sb.append("<ul>\n");
        sb.append("<li>name: ").append(product.getName()).append("</li>\n");
        sb.append("<li>price: ").append(product.getPrice()).append("</li>\n");
        sb.append("<img src=\"").append(product.getPictureUrl()).append("\" alt=\"img\"/>\n");
        sb.append("</ul>\n");
        sb.append("</div>\n" );
        return sb.toString();
    }
}