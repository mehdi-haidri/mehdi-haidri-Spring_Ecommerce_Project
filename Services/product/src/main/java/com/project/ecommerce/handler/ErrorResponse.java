package com.project.ecommerce.handler;

import java.util.HashMap;

public record ErrorResponse(
        HashMap<String,  String> error
){
}
