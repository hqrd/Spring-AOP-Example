package com.hqrd.springaop.annotation;

import com.google.common.base.Optional;
import io.swagger.annotations.ApiResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

import java.util.HashSet;
import java.util.Set;

/**
 * Overrides swagger annotations with our custom annotations
 *
 * @author Julien Pruvost
 */
@Component
@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER)
public class SwaggerBuilder implements OperationBuilderPlugin {

    @Override
    public void apply(OperationContext context) {
        Optional<CustomController> methodAnnotation = context.findAnnotation(CustomController.class);
        if (methodAnnotation.isPresent()) {
            CustomController customController = methodAnnotation.get();
            // do your processing here
            context.operationBuilder().notes(customController.apiDoc());
            context.operationBuilder().summary(customController.apiName());
            Set<ResponseMessage> mySet = new HashSet<>();
            for (ApiResponse apiResponse : customController.apiResponses()) {
                mySet.add(new ResponseMessage(apiResponse.code(), apiResponse.message(), null, null, null));
            }
            context.operationBuilder().responseMessages(mySet);
        }
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return SwaggerPluginSupport.pluginDoesApply(delimiter);
    }
}
