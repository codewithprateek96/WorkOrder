package com.api.WorkOrder.service;

import com.api.WorkOrder.repo.MySQLConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

@Service
public class WorkOrderService {

    @Autowired
    MySQLConnector mySQLConnector;

    public String getFile() throws IOException, SQLException {

        int value = mySQLConnector.getStatus();
        if (value == 1) {
            String uri = "http://localhost:7070/FileController/getFile";
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<ByteArrayResource> result = restTemplate.getForEntity(uri, ByteArrayResource.class);
            ByteArrayResource result_str = result.getBody();
            //creating Folder
            File folder = new File("." + File.separator + "Downloads");
            if (!folder.exists())
                folder.mkdirs();
            File file = new File("." + File.separator + "Downloads" + File.separator + "data_new.txt");
            OutputStream os = new FileOutputStream(file);
            System.out.println("File name is"+file.getName());
            os.write(result_str.getByteArray());
//            Random random = new Random();
//            int randomNumber=0;
//            for (int iCount = 0; iCount < 1; iCount++) {
//                randomNumber = random.nextInt(1000000); // This determines digits in a number, 100 for 2 digit random number
//                System.out.println("Random No : " + randomNumber);
//            }
//            String fileName = file.getName();
//            fileName = fileName+randomNumber;
            os.close();
            return "File Downloaded";
        } else {
            System.out.println("Status is not true.");
        }
        return "File Downloaded";
    }

    public String updateDeptNO(int empID, String job) {
        try {
            Connection conn = mySQLConnector.getConnection();
            String query = "UPDATE EMP SET job=? WHERE empno=?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            preparedStmt.setString(1,job);
            preparedStmt.setInt(2, empID);
            preparedStmt.executeUpdate();
            conn.close();
            return "updated";
        }
        catch (Exception e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            return null;
        }

    }
}
