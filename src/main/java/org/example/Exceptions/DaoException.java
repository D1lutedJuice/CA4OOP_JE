package org.example.Exceptions;

import java.sql.SQLException;

//custom exception to show errors
public class DaoException extends SQLException
{

    public DaoException(String aMessage)
    {
        super(aMessage);
    }
}
