package com.GoatRunner.exception;

public class GoatRunnerException extends Exception {
	 private final String reason;
	    /**
	     * Associates the exceptions thrown by other classes.
	     * @param reason	The exception message.
	     */
	    public GoatRunnerException(String reason){
	        this.reason = reason;
	    }

	    /** Returns the Exception Message. */
	    public String toString(){
	        return reason;
	    }

}
