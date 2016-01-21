package cn.fh.ctr.exception;

/**
 * Created by whf on 1/21/16.
 */
public abstract class BaseCtrException extends Exception {
    protected BaseCtrException(String msg) {
        super(msg);
    }

    protected BaseCtrException() {}

    @Override
    public synchronized Throwable fillInStackTrace() {
        //return super.fillInStackTrace();
        return this;
    }
}
