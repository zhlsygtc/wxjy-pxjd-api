package com.insigma.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration      //��Spring�����ظ�������
@EnableWebMvc       //����Mvc����springboot�����Ҫ����ע��@EnableWebMvc
@EnableSwagger2     //����Swagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("appkey").description("����")
                .defaultValue("faaaac26-8f96-11e7-bb31-be2e44b06b34")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(true).build();
        pars.add(tokenPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()).select()
                //ɨ��ָ�����е�swaggerע��
                //ɨ��������ע���api�������ַ�ʽ�����
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("������ҵ����ƽ̨ RESTful APIs")
                .description("������ҵ����ƽ̨ RESTful ���Ľӿ��ĵ���������ϸ������ļ�����ǰ��˵Ĺ�ͨ�ɱ���ͬʱȷ���������ĵ����ָ߶�һ�£�����ļ���ά���ĵ���ʱ�䡣")
                .termsOfServiceUrl("http://xiachengwei5.coding.me")
                .version("1.0.0")
                .build();
    }
}
