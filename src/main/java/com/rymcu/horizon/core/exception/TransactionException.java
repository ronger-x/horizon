package com.rymcu.horizon.core.exception;

import com.rymcu.horizon.enumerate.TransactionCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ronger
 */
@Getter
@Setter
public class TransactionException extends BusinessException {

    private int code;

    public TransactionException(TransactionCode transactionCode) {
        super(transactionCode.getMessage());
        this.code = transactionCode.getCode();
    }

}
