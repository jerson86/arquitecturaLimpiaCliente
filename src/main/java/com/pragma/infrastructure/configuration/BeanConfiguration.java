package com.pragma.infrastructure.configuration;

import com.pragma.domain.port.outbound.PersistCliente;
import com.pragma.domain.service.ClienteServiceImpl;
import com.pragma.infrastructure.PragmaApplication;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = PragmaApplication.class)
public class BeanConfiguration {
    @Bean
    ClienteServiceImpl clienteService(PersistCliente repository) {
        return new ClienteServiceImpl(repository);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
