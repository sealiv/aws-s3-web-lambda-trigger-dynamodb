package org.aleks4ay;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import org.aleks4ay.domain.Product;

import java.util.List;

public class Query {
    public static void main(String[] args) {
        new Query().init();
    }

    private void init() {
        DynamoDBMapperConfig mapperConfig = new DynamoDBMapperConfig
                .Builder()
                .withTableNameOverride(DynamoDBMapperConfig.TableNameOverride.withTableNameReplacement("products"))
                .build();

        final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
        DynamoDBMapper mapper = new DynamoDBMapper(client, mapperConfig);

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();

        List<Product> scanResult = mapper.scan(Product.class, scanExpression);
        System.out.println("scanResult.size() = " + scanResult.size());
        scanResult.forEach(System.out::println);
    }
}
