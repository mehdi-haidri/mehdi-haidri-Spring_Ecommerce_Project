package com.project.ecommerce.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class PurchaseInformationError extends RuntimeException {
   final private String message;
}
