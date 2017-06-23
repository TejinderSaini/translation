package com.alphaseq.translation.exception;

import com.alphaseq.translation.entity.Status;

public class AlphaseqIllegalArgException extends RuntimeException {
	 
    private static final long serialVersionUID = -2859292084648724403L;
    private final Status errorStatus;
     
    public AlphaseqIllegalArgException(Status errorStatus) {
    	this.errorStatus = errorStatus;
    }
     
    public Status getErrorStatus() {
        return errorStatus;
    }
 
}
