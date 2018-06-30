# Example project using AOP annotations

- Example of alias for replacing multiple annotations, marking some required

- Overriding existing swagger annotations with custom ones

- Using AOP (aspect annotation) to run code before and after a controller method

---

## Usage
Launch the spring boot application and call <a>http://localhost:8080/employee/list?onlyOne=false&test=ok</a>

Or go to <a>http://localhost:8080/swagger-ui.html</a> for the swagger ui interface.

---

## Custom annotation
The **`@CustomController`** annotation is defined in package com.hqrd.springaop.annotation;

It uses the `@AliasFor` annotation to use multiple annotations at once and simplify the duplication of controller methods without forgetting an annotation.

A custom **SwaggerBuilder** class is used to override the swagger annotations `@ApiResponses` and `@ApiOperation` because they required some default values.
It uses the springfox pluggin to redefines the swagger variables after the default ones are defined.

---

## AOP code
The **CustomControllerAspect** class uses the `@Aspect` annotation to execute some custom code on certain methods.
Each method in this class uses an annotation to define on which method the code will be added (`@Around` is used to execute some code before and after the execution of the targeted method).

You then have access to multiple variables, like the class, arguments or the response returned.

---

> _Base project inspired from <a>https://howtodoinjava.com/spring/spring-mvc/spring-mvc-hello-world-example/</a>_