package net.apmoller.crb.azure.function.eventhub;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

import java.sql.*;
import java.util.*;

/**
 * Azure Functions with Event Hub trigger.
 */
public class EhProcessor {
    /**
     * This function will be invoked when an event is received from Event Hub.
     */
    @FunctionName("EhProcessor")
    public void run(
        @EventHubTrigger(name = "message", eventHubName = "xml-processor-eh",
                connection = "receiverConnectionString",
                consumerGroup = "$Default", cardinality = Cardinality.MANY, dataType = "string") List<String> message,
        @TableOutput(name="users", tableName="users", connection="AzureWebJobsStorage") OutputBinding<String> users,
        final ExecutionContext context
    ) {
        context.getLogger().info("Java Event Hub trigger function executed.");
        context.getLogger().info("Length:" + message.size());
        message.forEach(singleMessage -> {
            /*String connectionUrl =
                    "jdbc:sqlserver://servdemodb.database.windows.net:1433;database=DemoDB;user=Rimi@servdemodb;password={your_password_here};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

            ResultSet resultSet = null;

            try (Connection connection = DriverManager.getConnection(connectionUrl);
                 Statement statement = connection.createStatement();) {

                context.getLogger().info("Message Received :=> " +singleMessage); // This is printed out.

                // Create and execute a SELECT SQL statement.
                String selectSql = "INSERT USER([name]) values ("+singleMessage+")";
                context.getLogger().info("Executing SQL :=> " + selectSql);
                resultSet = statement.executeQuery(selectSql);

            }
            // Handle any errors that may have occurred.
            catch ( SQLException e) {
                e.printStackTrace();
            }*/
            context.getLogger().info(singleMessage);
        } );
    }
}
