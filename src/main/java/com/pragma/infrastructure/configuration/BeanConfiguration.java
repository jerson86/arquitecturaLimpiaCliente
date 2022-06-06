package com.pragma.infrastructure.configuration;

import com.pragma.application.mapper.ClienteMapper;
import com.pragma.application.mapper.ImagenMapper;
import com.pragma.domain.port.inbound.ClienteService;
import com.pragma.domain.port.inbound.ImagenService;
import com.pragma.domain.port.outbound.PersistCliente;
import com.pragma.domain.port.outbound.PersistImagen;
import com.pragma.domain.service.ClienteServiceImpl;
import com.pragma.domain.service.ImagenServiceImpl;
import com.pragma.infrastructure.PragmaApplication;
import com.pragma.infrastructure.mysql.mapper.ClienteMapperMysql;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = PragmaApplication.class)
public class BeanConfiguration {
    @Bean
    ClienteService clienteService(final PersistCliente repository) {
        return new ClienteServiceImpl(repository);
    }

    @Bean
    ImagenService imagenService(final PersistImagen repository) {
        return new ImagenServiceImpl(repository);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public ClienteMapper clienteMapper(){
        return new ClienteMapper();
    }

    @Bean
    public ClienteMapperMysql clienteMapperMysql(){
        return new ClienteMapperMysql();
    }

    @Bean
    public ImagenMapper imagenMapper(){
        return new ImagenMapper();
    }
}
