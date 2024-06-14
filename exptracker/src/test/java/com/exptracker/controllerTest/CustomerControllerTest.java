package com.exptracker.controllerTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.exptracker.serviceimpl.CustomerServiceImpl;

public class CustomerControllerTest {
	
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerServiceImpl customerServiceImpl;
    
    
    @DisplayName("Test post creation Customer")
    @Test
    public void createUserTest() throws Exception {
    	
    
    }

}
