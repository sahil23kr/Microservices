package EmployeeException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmpException extends Exception{
    private static final long serialVersionUID=1L;
    public EmpException(String msg)
    {
        super(msg);
    }
}

