package com.example.classactivity.ModelMapperConfig;

import com.example.classactivity.dto.UserDTO;
import com.example.classactivity.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfigure {
    @Bean
    public ModelMapper modelMapper () {
        return  new ModelMapper();
    }


}
