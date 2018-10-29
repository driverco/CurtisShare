/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.driverco;

import com.driverco.security.model.AppUser;
import com.google.common.hash.Hashing;
import com.zaxxer.hikari.HikariConfig; 
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.sql.DataSource;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;


@Controller
@SpringBootApplication
public class Main {

  @Value("${spring.datasource.url}")
  private String dbUrl;

  @Autowired
  private DataSource dataSource;

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Main.class, args);
  }

  @RequestMapping("/")
  String index() {
    return "index";
  }
  @RequestMapping("/access-denied")
  public String accessDenied() {
      return "/error/access-denied";
  }
  
 
  @RequestMapping("/Login")
  String login(Model model, @RequestParam(value = "userName", required = false) String  userName, @RequestParam(value = "encryptedPassword", required = false) String  encryptedPassword) {
	  
	  try {
		  if (encryptedPassword.length() > 0 ) ;
		  String sha256hex = Hashing.sha256().hashString(encryptedPassword, StandardCharsets.UTF_8).toString().toUpperCase();
		  System.out.println("password:"+encryptedPassword+"::"+sha256hex+"::"+encryptedPassword.length() );
	          return "index";
	  }catch (NullPointerException npe) {
          model.addAttribute("AppUser", new AppUser());
          return "login";
      }
  }

  @RequestMapping("/db")
  String db(Map<String, Object> model) {
    try (Connection connection = dataSource.getConnection()) {
      Statement stmt = connection.createStatement();
      /*stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
      stmt.executeUpdate("INSERT INTO ticks VALUES (now())");*/
      ResultSet rs = stmt.executeQuery("SELECT USER_ID, USER_NAME, ENCRYTED_PASSWORD, ENABLED from dbo.App_User");

      ArrayList<String> output = new ArrayList<String>();
      while (rs.next()) {
          output.add("Read from DB: " + rs.getString("USER_ID") +":"+ rs.getString("USER_NAME"));
      }

      model.put("records", output);
      return "db";
    } catch (Exception e) {
      model.put("message", e.getMessage());
      return "error";
    }
  }

  @Bean
  public DataSource dataSource() throws SQLException {
    if (dbUrl == null || dbUrl.isEmpty()) {
      return new HikariDataSource();
    } else {
      HikariConfig config = new HikariConfig();
      config.setJdbcUrl(dbUrl);
      return new HikariDataSource(config);
    }
  }
  

}
