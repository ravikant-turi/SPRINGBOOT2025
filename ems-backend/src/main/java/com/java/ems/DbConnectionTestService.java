package com.java.ems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DbConnectionTestService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean isDatabaseConnected() {
        try {
            jdbcTemplate.execute("SELECT 1");
            System.out.println("✅ Database connection successful!");
            return true;
        } catch (Exception e) {
            System.err.println("❌ Database connection failed: " + e.getMessage());
            return false;
        }
    }
}

